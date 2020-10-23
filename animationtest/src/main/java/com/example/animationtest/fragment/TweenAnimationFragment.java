package com.example.animationtest.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.animationtest.R;

/**
 * @author by chenlp
 * @date 2020/10/20
 * @describe
 */
public class TweenAnimationFragment extends Fragment {
    private View mView;
    private ImageView mImageView;
    private Animation mAnimation;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("clp--", "onCreateView: ");
        mView= inflater.inflate(R.layout.tween_animation_fragment,container,false);
        initView();
        return mView ;
    }

    private void initView() {
        mAnimation = AnimationUtils.loadAnimation(getContext(),R.anim.alpha_anim);
        mImageView = mView.findViewById(R.id.img_tween_animation);
        mImageView.startAnimation(mAnimation);
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (mImageView!=null){
            if (isVisibleToUser){
                mImageView.startAnimation(mAnimation);
            }else{
                if (mImageView.getAnimation()!=null){
                    mImageView.clearAnimation();
                }
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
