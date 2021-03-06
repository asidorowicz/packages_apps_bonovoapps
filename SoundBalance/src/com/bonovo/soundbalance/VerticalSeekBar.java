package com.bonovo.soundbalance;

import android.widget.SeekBar;
import android.content.Context;
import android.util.AttributeSet;
import android.graphics.Canvas;
import android.view.MotionEvent;

public class VerticalSeekBar extends SeekBar {
    
    public VerticalSeekBar(Context context) {
        super(context);
    }
    
    public VerticalSeekBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    
    public VerticalSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(h, w, oldh, oldw);
    }
    
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(heightMeasureSpec, widthMeasureSpec);
        setMeasuredDimension(getMeasuredHeight(), getMeasuredWidth());
    }
    
    protected void onDraw(Canvas c) {
        c.rotate(-90.0f);
        c.translate((float)-getHeight(), 0.0f);
        super.onDraw(c);
    }
    
    public synchronized void setProgress(int progress) {
        super.setProgress(progress);
        onSizeChanged(getWidth(), getHeight(), 0, 0);
    }
    
    public boolean onTouchEvent(MotionEvent event) {
        if(!isEnabled()) {
            return false;
        }
        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
            {
                setProgress((getMax() - (int)(((float)getMax() * event.getY()) / (float)getHeight())));
                onSizeChanged(getWidth(), getHeight(), 0, 0);
                break;
            }
        }
        return true;
    }
}
