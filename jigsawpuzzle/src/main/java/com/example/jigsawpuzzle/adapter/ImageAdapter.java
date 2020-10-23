package com.example.jigsawpuzzle.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.jigsawpuzzle.utils.ScreenUtils;

import java.util.List;

/**
 * @author by chenlp
 * @date 2020/9/7
 * @describe
 */
public class ImageAdapter extends BaseAdapter {

    private Context mContext;
    private List<Bitmap> mJigsawImageList;
    private OnJumpListener mOnJumpListener;

    public ImageAdapter(Context context) {
        mContext = context;
    }

    public void addList(List<Bitmap> jigsawImageList) {
        mJigsawImageList = jigsawImageList;
    }

    @Override
    public int getCount() {
        return mJigsawImageList.size();
    }

    @Override
    public Object getItem(int i) {
        return mJigsawImageList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView img_jigsaw_item;
        int density = (int) ScreenUtils.getDeviceDensity(mContext);
        if (view == null) {
            img_jigsaw_item = new ImageView(mContext);
            img_jigsaw_item.setLayoutParams(new ViewGroup.LayoutParams(80 * density, 100 * density));
            img_jigsaw_item.setScaleType(ImageView.ScaleType.FIT_XY);
        } else {
            img_jigsaw_item = (ImageView) view;
        }

        img_jigsaw_item.setBackgroundColor(android.R.color.black);
        img_jigsaw_item.setImageBitmap(mJigsawImageList.get(i));
        initClick(img_jigsaw_item,i);
        return img_jigsaw_item;
    }

    private void initClick(ImageView img_jigsaw_item, final int i) {
        img_jigsaw_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnJumpListener!=null){
                    mOnJumpListener.jump(i);
                }
            }
        });
    }

    public interface OnJumpListener{
        void jump(int position);
    }

    public void setOnJumpListener(OnJumpListener onJumpListener){
        mOnJumpListener = onJumpListener;
    }
}
