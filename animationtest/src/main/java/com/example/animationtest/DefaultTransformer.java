package com.example.animationtest;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

/**
 * @author by chenlp
 * @date 2020/10/21
 * @describe
 */
public class DefaultTransformer implements ViewPager.PageTransformer {
    @Override
    public void transformPage(@NonNull View page, float position) {
        float alpha = 0;
        if (0 <= position && position <= 1) {
            alpha = 1 - position;
        } else if (-1 < position && position < 0) {
            alpha = position + 1;
        }
        page.setAlpha(alpha);
        float transX = page.getWidth() * -position;
        page.setTranslationX(transX);
        float transY = position * page.getHeight();
        page.setTranslationY(transY);
    }
}
