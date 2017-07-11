package com.yll.scrolltextview;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by yelelen on 7/11/2017.
 */

public class MyTextView extends TextView {
    private final int START_MOVE = 0x11;
    private String mText;
    private int mTextWidth;
    private int mSpeed = 20;    // 滚动速度
    private int mDuration = 100; // 滚动的间隔时间
    private int x = 0;

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what){
                case START_MOVE:
                    x += mSpeed;
                    if (x > mTextWidth) x = 0;
                    scrollTo(x, 0);
                    break;
                default:
                    break;
            }
            return true;
        }
    });

    public MyTextView(Context context) {
        super(context);
    }


    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        /**
         * 在构造器中获取自定义的属性
         */
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MyTextView);
        mSpeed = a.getInteger(R.styleable.MyTextView_speed,  20);
        mDuration = a.getInteger(R.styleable.MyTextView_duration, 100);

        a.recycle();
    }

    public void startMarquee(){
        mText = this.getText().toString();
        mTextWidth = (int) getPaint().measureText(mText);
        new Thread(){
            @Override
            public void run() {

                while (true) {
                    try {
                        Thread.sleep(mDuration);
                        mHandler.sendEmptyMessage(START_MOVE);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }.start();
    }
}
