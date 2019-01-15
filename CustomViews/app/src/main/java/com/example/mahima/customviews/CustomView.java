package com.example.mahima.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

public class CustomView extends View {


    private Rect rect;
    private Paint paint;
    private int squareColor;
    private int padding;

    public CustomView(Context context) {
        super(context);
        init(null);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(@Nullable AttributeSet attrs) {
        rect = new Rect();
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        if (attrs == null) return;

        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.CustomView);
        squareColor = typedArray.getColor(R.styleable.CustomView_square_color, Color.LTGRAY);
        paint.setColor(squareColor);
        typedArray.recycle();
    }

    public void swapColor() {
        paint.setColor(paint.getColor() == squareColor ? Color.GREEN : squareColor);
        postInvalidate();
    }

    public void paddingIncrease(int padding) {
        this.padding += padding;
        postInvalidate();
    }

    public void paddingDecrease(int padding) {
        this.padding -= padding;
        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        rect.left = padding;
        rect.right = getWidth() - padding;
        rect.top = padding;
        rect.bottom = getHeight() - padding;

        canvas.drawRect(rect, paint);
    }
}
