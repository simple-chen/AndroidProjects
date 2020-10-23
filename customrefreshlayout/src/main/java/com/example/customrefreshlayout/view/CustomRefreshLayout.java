package com.example.customrefreshlayout.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Scroller;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author by chenlp
 * @date 2020/9/21
 * @describe
 */
public abstract class CustomRefreshLayout extends ViewGroup implements AbsListView.OnScrollListener {


    //下拉刷新的四个状态
    private static final int STATUS_HIDE = 0;//隐藏
    private static final int STATUS_DOWN_PULL_DOWN = 1;//下拉状态
    private static final int STATUS_DOWN_SLOW_REFRESH = 2;//下拉到一半，松开后进行刷新状态
    private static final int STATUS_DOWN_REFRESH = 3;//刷新状态
    //上拉加载的四个状态
    private static final int STATUS_UP_PULL_DOWN = 4;//下拉状态
    private static final int STATUS_UP_SLOW_REFRESH = 5;//下拉到一半，松开后进行刷新状态
    private static final int STATUS_UP_REFRESH = 6;//刷新状态

    private static final int STATUS_UP = 7;//上拉状态
    private static final int STATUS_DOWN = 8;//下拉状态
    private static final int STATUS_START = -1;//初始状态


    private int CURRENT_STATUS = STATUS_START;//加载状态,上拉 下拉 初始

    private int mDelayTime = 100;

    private int TouchSlop;//最小滑动距离

    private int mStatus = STATUS_HIDE;//当前刷新状态
    private int mTotalItemCount;//ListView的总数量
    private int mFirstListItem;//第一个可见item下标
    private int mVisibleItemCount;
    private int mFirst;//第一个可见item下标

    private int VIEW_HEAD_HEIGHT;//head布局高度
    private int VIEW_FOOT_HEIGHT;//foot布局高度

    private float mLastRawY;
    private float mLastRawX;

    private boolean isUpRefresh;//true:上拉加载MOVE_UP的时候刷新数据，false :move到底的时候刷新数据

    private Scroller mScroller;

    private View mHeadView;
    private View mFootView;


    public CustomRefreshLayout(Context context) {
        this(context, null);
    }

    public CustomRefreshLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomRefreshLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScroller = new Scroller(context);
        TouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        initView();
    }

    protected void initView() {
        addHeadView();
        addFootView();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.e("clp---------", "onLayout: " + "l:" + l + "   " + "t:" + t + "   " + "r" + r + "   " + "b" + b);
        mHeadView = getHeadView();
        mFootView = getFootView();
        VIEW_HEAD_HEIGHT = mHeadView.getHeight();
        VIEW_FOOT_HEIGHT = mFootView.getHeight();
        int contentHeight = 0;
        int footId = 0;
        int headId = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            if (childView.getVisibility() == View.GONE) {
                continue;
            }
            if (childView == mHeadView) {
                headId = i;
            } else if (childView == mFootView) {
                footId = i;
            } else {
                childView.layout(0, contentHeight, childView.getMeasuredWidth(), childView.getMeasuredHeight());
                contentHeight += childView.getMeasuredHeight();
            }
        }
        getChildAt(headId).layout(0, -getChildAt(headId).getMeasuredHeight(), getChildAt(headId).getMeasuredWidth(), 0);
        getChildAt(footId).layout(0, contentHeight, getChildAt(footId).getMeasuredWidth(), contentHeight + getChildAt(footId).getMeasuredHeight());
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        Log.e("clp", "onFinishInflate: ");
    }

    private void setStatus(int status) {
        if (mStatus != status) {
            mStatus = status;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.e("clp", "onMeasure: ");
        int totalLenght = 0;
        int maxWidth = Integer.MIN_VALUE;
        int childMeasureState = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            if (childView.getVisibility() == GONE) {
                continue;
            }
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
            childMeasureState = combineMeasuredStates(childMeasureState, childView.getMeasuredState());
            totalLenght += childView.getMeasuredHeight();
            maxWidth = Math.max(maxWidth, childView.getMeasuredWidth());
        }
        totalLenght = Math.max(totalLenght, getSuggestedMinimumHeight());
        maxWidth = Math.max(maxWidth, getSuggestedMinimumWidth());
        setMeasuredDimension(resolveSizeAndState(maxWidth, widthMeasureSpec, childMeasureState), resolveSizeAndState(totalLenght, heightMeasureSpec, childMeasureState));

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        float rawY = ev.getRawY();
        float rawX = ev.getRawX();


        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastRawY = rawY;
                mLastRawX = rawX;
                break;
            case MotionEvent.ACTION_MOVE:
                float offsetY = rawY - mLastRawY;
                float offsetX = rawX - mLastRawX;
                if (CURRENT_STATUS != STATUS_START) {//如果正在刷新状态直接拦截
                    return true;
                }
                //下拉并且没有拉到底部
                if (Math.abs(offsetX) > Math.abs(offsetY)) {
                    //水平滑动距离>垂直滑动距离就不处理下拉和上拉事件(ViewPager和下拉刷新的滑动冲突)
                    return false;
                } else {
                    //处理recyclerView和上拉事件下拉事件的冲突
                    if (offsetY > 0 && !canScrollDown()) {
                        return false;
                    }
                    if (offsetY < 0 && !canScrollUp()) {
                        return false;
                    }
                    return true;
                }

        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int rawY = (int) event.getRawY();
        Log.e("clp", "始onTouchEvent: CURRENT_STATUS" + CURRENT_STATUS);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastRawY = rawY;
                break;

            case MotionEvent.ACTION_MOVE:
                if (mStatus == STATUS_DOWN_REFRESH || mStatus == STATUS_UP_REFRESH) {
                    return false;
                }
                int offsetY = (int) (rawY - mLastRawY);

                if (Math.abs(offsetY) > 2) {//如果滑动距离大于最小滑动距离，处理滑动
                    Log.e("clp", "状态----CURRENT_STATUS " + CURRENT_STATUS + "offsetY " + offsetY + "getScrollY()" + getScrollY());
                    if (CURRENT_STATUS == STATUS_START) {//如果是初始状态
                        if (offsetY > 0) {
                            downRefresh(offsetY);
                        } else {
                            upRefresh(offsetY);
                        }
                    } else if (CURRENT_STATUS == STATUS_DOWN) {//如果是下拉刷新状态
                        downRefresh(offsetY);
                    } else {//如果是上拉加载状态
                        upRefresh(offsetY);
                    }
                }
                mLastRawY = rawY;
                break;
            case MotionEvent.ACTION_UP:
                if (getScrollY() == 0) {
                    CURRENT_STATUS = STATUS_START;
                    Log.e("clp", "ACTION_UP CURRENT_STATUS: " + CURRENT_STATUS);
                }
                mLastRawY = rawY;
                if (mStatus == STATUS_DOWN_SLOW_REFRESH) {//如果处于下拉松开刷新状态
                    setStatus(STATUS_DOWN_REFRESH);
                    //正在刷新
                    postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //隐藏
                            downDealWithRefreshing();
                            setStatus(STATUS_HIDE);
                            hide();
                        }
                    }, mDelayTime);
                } else if (mStatus == STATUS_UP_SLOW_REFRESH) {//如果处于上拉松开刷新状态
                    setStatus(STATUS_UP_REFRESH);
                    //正在刷新
                    if (isUpRefresh) {
                        upDealWithRefreshing();
                    }

                    postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //隐藏
                            setStatus(STATUS_HIDE);
                            hide();
                        }
                    }, mDelayTime);

                } else {//还未拉到底部隐藏...
                    setStatus(STATUS_HIDE);
                    hide();
                }

                break;
        }
        return true;
    }

    /**
     * 下拉刷新
     *
     * @param offsetY
     */
    private void downRefresh(int offsetY) {
        if (canScrollDown()) {
            CURRENT_STATUS = STATUS_DOWN;
            if (getScrollY() == -VIEW_HEAD_HEIGHT) {//拉到底部的处理
                setStatus(STATUS_DOWN_SLOW_REFRESH);
                downReachBottom();
            } else {//没有拉到底部的处理
                setStatus(STATUS_DOWN_PULL_DOWN);
                downNotReachBottom();
            }

            if (getScrollY() >= -VIEW_HEAD_HEIGHT) {
                if (offsetY < 0) {//上滑...
                    Log.e("clp", "onTouchEvent: getScrollY() " + getScrollY() + "offsetY " + offsetY + "VIEW_HEIGHT " + VIEW_HEAD_HEIGHT);
                    if (getScrollY() < 0) {
                        if (Math.abs(offsetY) <= Math.abs(getScrollY())) {//上滑offset距离后未超过初始位置，滑动offset
                            scrollBy(0, -offsetY);
                        } else {//上滑offset距离后超过初始位置，滑动偏移初始位置的距离
                            scrollBy(0, -getScrollY());

                        }
                    }
                    //上滑处理
                    downDealWithUp();
                } else {//下滑...
                    if (offsetY - getScrollY() < VIEW_HEAD_HEIGHT) {//如果下滑offset后未超过headView的高度就让下滑offset
                        scrollBy(0, -offsetY);
                    } else {//否则只允许下拉到headView的高度
                        scrollBy(0, -VIEW_HEAD_HEIGHT - getScrollY());
                    }
                    //下拉处理
                    downDealWithDown();
                }
            }

        }
    }


    /**
     * 上拉加载
     *
     * @param offsetY
     */
    private void upRefresh(int offsetY) {
        if (canScrollUp()) {
            CURRENT_STATUS = STATUS_UP;
            if (getScrollY() == VIEW_FOOT_HEIGHT) {//上拉到底部的处理
                setStatus(STATUS_UP_SLOW_REFRESH);
                upReachTop();
            } else {//没有拉到底部的处理
                setStatus(STATUS_UP_PULL_DOWN);
                upNotReachTop();
            }

            if (getScrollY() <= VIEW_FOOT_HEIGHT) {
                if (offsetY < 0) {//上滑...
                    Log.e("clp", "onTouchEvent: getScrollY() " + getScrollY() + "offsetY " + offsetY + "VIEW_HEIGHT " + VIEW_HEAD_HEIGHT);
                    if (getScrollY() < VIEW_FOOT_HEIGHT) {
                        if (Math.abs(offsetY) < VIEW_FOOT_HEIGHT - getScrollY()) {//上滑offset距离后未超过初始位置，滑动offset
                            scrollBy(0, -offsetY);
                        } else {
                            scrollBy(0, VIEW_FOOT_HEIGHT - getScrollY());

                            if (!isUpRefresh) {
                                upDealWithRefreshing();
                            }
                        }

                    }
                    //上滑处理
                    upDealWithUp();

                } else {
                    if (getScrollY() >= 0) {
                        if (offsetY < getScrollY()) {//如果下滑offset不大于偏移量
                            scrollBy(0, -offsetY);
                        } else {//下滑offset距离后超过初始位置，滑动偏移初始位置的距离
                            scrollBy(0, -getScrollY());
                        }
                    }
                    //下滑处理
                    upDealWithDown();
                }
            }
        }
    }


    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }

    /**
     * recyclerView和ListView处于第一个item的时候可以进行下拉刷新
     *
     * @return
     */
    public boolean canScrollDown() {
        View targetView = getChildAt(2);
        if (targetView instanceof RecyclerView) {
            final RecyclerView recyclerView = (RecyclerView) targetView;
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            if (linearLayoutManager != null) {
                return linearLayoutManager.findFirstVisibleItemPosition() == 0 && recyclerView.getChildCount() != 0;
            }
        } else if (targetView instanceof AbsListView) {
            return mFirstListItem + mVisibleItemCount== mTotalItemCount-1 && mTotalItemCount != 0;
        }
        return true;
    }


    /**
     * recyclerView和ListView处于最后一个item的时候可以进行上拉加载
     *
     * @return
     */
    public boolean canScrollUp() {
        View targetView = getChildAt(2);
        if (targetView instanceof RecyclerView) {
            final RecyclerView recyclerView = (RecyclerView) targetView;
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            RecyclerView.Adapter adapter = (RecyclerView.Adapter) recyclerView.getAdapter();
            if (linearLayoutManager != null && adapter != null) {
                return linearLayoutManager.findLastVisibleItemPosition() == recyclerView.getAdapter().getItemCount() - 1 && linearLayoutManager.getChildCount() != 0;
            }
        } else if (targetView instanceof AbsListView) {
            return mFirstListItem == mTotalItemCount - 1 && mTotalItemCount != 0;
        }
        return true;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int AbsListView, int visibleItemCount, int totalItemCount) {
        mFirstListItem = AbsListView;//第一个可见item
        mTotalItemCount = totalItemCount;
        mVisibleItemCount = visibleItemCount;
    }

    private void hide() {
        mScroller.startScroll(0, getScrollY(), 0, (-getScrollY()), 400);
        invalidate();
        CURRENT_STATUS = STATUS_START;
    }

    /**
     * @param isUpRefresh true:上拉加载MOVE_UP的时候刷新数据，false :move到底的时候刷新数据
     */
    public void setUpRefresh(boolean isUpRefresh) {
        this.isUpRefresh = isUpRefresh;
    }

    protected abstract void downDealWithRefreshing();//下拉处理正在刷新

    protected abstract void downDealWithDown();//下拉处理下拉

    protected abstract void downDealWithUp();//下拉处理上滑

    protected abstract void downNotReachBottom();//下拉未到达底部

    protected abstract void downReachBottom();//下拉到达底部

    protected abstract void upDealWithUp();

    protected abstract void upDealWithDown();

    protected abstract void upNotReachTop();

    protected abstract void upReachTop();

    protected abstract void upDealWithRefreshing();//上拉处理正在刷新

    protected abstract void addHeadView();//添加头部布局

    protected abstract void addFootView();//添加尾部布局

    protected abstract View getHeadView();//获得头部布局

    protected abstract View getFootView();//获得尾部布局

}
