package com.eric.base.base_view.mvp;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.eric.base.base_view.ac_manage.ActivityStack;

/**
 * Created by Eric on 2018/3/21.
 */

public abstract class BaseActivity extends FragmentActivity  {

    public Context mContext;
    public String TAG;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStack.getInstance().addActivity(this);
        mContext = this;
        TAG = this.getClass().getName();
        setContentView(getLayoutId());
        initView();
        setData();
        doLinstener();
    }

    /**
     * 初始化布局
     *
     * @author blue
     */
    protected abstract int getLayoutId();

    public void initView() {

    }

    public void setData() {

    }

    public void doLinstener() {
    }

    public <T extends View> T getViewById(int id) {
        return (T) super.findViewById(id);
    }

    public void startActivityForResult(Class<?> cls, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(mContext, cls);
        startActivityForResult(intent, requestCode);
    }
    /**
     * 得到Res下颜色
     *
     * @param color
     * @return
     */
    public int getResColor(int color) {
        return ContextCompat.getColor(getApplicationContext(), color);
    }
    /**
     * 得到Res下Drawable
     *
     * @param drawable
     * @return
     */
    public Drawable getResDrawable(int drawable) {
        return ContextCompat.getDrawable(getApplicationContext(), drawable);
    }
    /**
     * 得到Res下string
     *
     * @param string
     * @return
     */
    public String getResString(int string) {
        return getResources().getString(string);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        ActivityStack.getInstance().removeActivity(this);
        super.onDestroy();
    }
}