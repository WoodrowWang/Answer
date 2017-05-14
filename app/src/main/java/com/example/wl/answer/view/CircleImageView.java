package com.example.wl.answer.view;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

/**
 * Created by Administrator on 2017/5/9 0009.
 */

public class CircleImageView extends AppCompatImageView {
    private Context mContext;
    private int mWidth;
    private int mHeight;

    public CircleImageView(Context context) {
        super(context);
        mContext = context;
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public CircleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }

    //
//    private void setCustomAttributes(AttributeSet attrs) {
//        TypedArray a = mContext.obtainStyledAttributes(attrs,
//                R.styleable.roundedimageview);
//        mBorderThickness = a.getDimensionPixelSize(
//                R.styleable.roundedimageview_border_thickness, 0);
//        mBorderOutsideColor = a
//                .getColor(R.styleable.roundedimageview_border_outside_color,
//                        defaultColor);
//        mBorderInsideColor = a.getColor(
//                R.styleable.roundedimageview_border_inside_color, defaultColor);
//    }


}
