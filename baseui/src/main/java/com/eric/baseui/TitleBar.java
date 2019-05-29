package com.eric.baseui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class TitleBar extends RelativeLayout {
    public TitleBar(Context context) {
        super(context);

    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(context,R.layout.view_title,this);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
//    private void init(Context context,AttributeSet attrs){
//        inflate(context,R.layout.,this);
//
//    }
}
