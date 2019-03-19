package com.eric.base.base_view.mvp.test;

import com.eric.base.base_view.mvp.BasePresenter;
import com.eric.base.base_view.mvp.BaseView;

public interface DemoPresenter {

    interface  View extends BaseView<Presenter> {
        void test();
    }
    interface  Presenter extends BasePresenter {
        void test();

    }
}
