package com.wix.gil.loadingicons;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;


public class LoadingWheelView extends View
{
    private long mDuration = 10000;
    private int mNumberOfSegments = 15;
    private int mSegmentPadding = 5;

    private Paint mPaint;
    private RectF mBox;

    private float mAnimationRatio;

    public LoadingWheelView(Context context)
    {
        super(context);
        init();
    }

    public LoadingWheelView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    public LoadingWheelView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init()
    {
        mPaint = new Paint();
        mPaint.setARGB(255, 0, 0, 0);
        mPaint.setStyle(Paint.Style.STROKE);

        mBox = new RectF();
    }

    public void setNumberOfSegments(int numberOfSegments)
    {
        mNumberOfSegments = numberOfSegments;
    }

    public void start()
    {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);
        valueAnimator.setDuration(mDuration);
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        valueAnimator.setRepeatMode(ValueAnimator.INFINITE);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {
            @Override
            public void onAnimationUpdate(ValueAnimator animation)
            {
                mAnimationRatio = (float)animation.getAnimatedValue();
                postInvalidate();
            }
        });

        valueAnimator.start();
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        float startAngle;
        float maxDimension;

        mBox.set(0, 0, getMeasuredWidth(), getMeasuredHeight());
        maxDimension = Math.max(mBox.width(), mBox.height());
        float strokeWidth = 0.5f * (maxDimension - mSegmentPadding * mNumberOfSegments) / mNumberOfSegments;
        mPaint.setStrokeWidth(strokeWidth);

        float expandSize;

        for (int i = 0; i < mNumberOfSegments; i++)
        {
            expandSize = ((float)(i + 1) / (float)mNumberOfSegments) * (getMeasuredWidth() / 2f) - strokeWidth / 2;
            mBox.set(getMeasuredWidth() / 2 - expandSize, getMeasuredHeight() / 2 - expandSize, getMeasuredWidth() / 2 + expandSize, getMeasuredHeight() / 2 + expandSize);
            startAngle = 360 * (i + 1) * mAnimationRatio;

            canvas.drawArc(mBox, startAngle, 180, false, mPaint);
        }
    }
}
