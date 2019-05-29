package com.eric.baseui;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TitleBar extends RelativeLayout {
    private TextView title, leftText, rightTest;
    private ImageView ivLeft, ivRightt;
    private Context mContext;

    public TitleBar(Context context) {
        super(context);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView(context);
        doLinstener();
    }


    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initView(Context context) {
        inflate(context, R.layout.view_title, this);
        title = findViewById(R.id.tv_title);
        leftText = findViewById(R.id.tv_left);
        rightTest = findViewById(R.id.tv_right);
        ivLeft = findViewById(R.id.iv_back);
        ivRightt = findViewById(R.id.iv_right);
    }

    private void doLinstener() {
        ivLeft.setOnClickListener(v -> {
            ((Activity) mContext).onBackPressed();
        });
    }

    public void setivLeftClick(OnClickListener onClick) {
        if (onClick != null) {
            ivLeft.setOnClickListener(onClick);
        }
    }

    public void setivRightClick(OnClickListener onClick) {
        if (onClick != null) {
            ivRightt.setOnClickListener(onClick);
        }
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }

    public void setLeftText(String leftText) {
        this.leftText.setText(leftText);
    }

    public void setRightTest(String rightTest) {
        this.rightTest.setText(rightTest);
    }

    public void setIvLeft(Bitmap ivLeft) {
        this.ivLeft.setImageBitmap(ivLeft);
    }

    public void setIvRightt(Bitmap ivRightt) {
        this.ivRightt.setImageBitmap(ivRightt);
    }


}
