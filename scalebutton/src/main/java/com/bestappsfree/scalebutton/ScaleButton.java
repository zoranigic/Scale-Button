package com.bestappsfree.scalebutton;

import android.content.Context;
import androidx.appcompat.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

import java.util.ArrayList;
import java.util.List;


public class ScaleButton extends AppCompatButton {
    private List<Animation> mAnimationBuffer = new ArrayList<>();
    private boolean mIsAnimating;

    public ScaleButton(Context context)
    {
        super(context);

    }

    public ScaleButton(Context context, AttributeSet attrs)
    {
        super(context, attrs);

    }

    public ScaleButton(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);

    }


//    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if(this.isClickable()) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                generateAnimation(1, 0.8f);
                triggerNextAnimation();
            } else if (event.getAction() == MotionEvent.ACTION_CANCEL || event.getAction() == MotionEvent.ACTION_UP) {
                generateAnimation(0.8f, 1);
                triggerNextAnimation();
            }
        }
        return super.onTouchEvent(event);
    }

    private void generateAnimation(float from, float to)
    {
        ScaleAnimation scaleAnimation = new ScaleAnimation(from, to, from, to, Animation.RELATIVE_TO_SELF, 0.5F, Animation.RELATIVE_TO_SELF, 0.5F);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setDuration(100);
        scaleAnimation.setAnimationListener(new ScaleAnimation.AnimationListener()
        {
            @Override
            public void onAnimationStart(Animation animation) { }

            @Override
            public void onAnimationRepeat(Animation animation) { }

            @Override
            public void onAnimationEnd(Animation animation)
            {
                mIsAnimating = false;
                triggerNextAnimation();
            }
        });

        mAnimationBuffer.add(scaleAnimation);
    }

    private void triggerNextAnimation()
    {
        if (mAnimationBuffer.size() > 0 && !mIsAnimating)
        {
            mIsAnimating = true;
            Animation currentAnimation = mAnimationBuffer.get(0);
            mAnimationBuffer.remove(0);

            startAnimation(currentAnimation);
        }
    }



}
