package com.eric.base.base_view.mvp.test;

import com.eric.base.base_view.mvp.BaseActivity;

public class TextActivity extends BaseActivity implements DemoPresenter.View {
    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    public void initView() {
        super.initView();

    }

    @Override
    public void test() {

    }

    @Override
    public void createPresenter(DemoPresenter.Presenter var1) {

    }
}
