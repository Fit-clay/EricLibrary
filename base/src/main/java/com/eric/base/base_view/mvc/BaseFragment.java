package com.eric.base.base_view.mvc;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.logging.Logger;

/**
 * lazy加载fragment
 * Created by ERic on 2016/11/25.
 */
public abstract class BaseFragment extends Fragment implements BFragmentLifeCycleListener {

    Logger $logger = Logger.getLogger(this.getClass().getName());

    private LinearLayout rootView;
    public Context mContext;

    private boolean rootViewCreated;//根视图是否创建
    private boolean inited;          //是否完成初始化动作

    private void init(){

        synchronized(BaseFragment.class){
            if(inited){ return; }
            inited = true;
        }
        mContext=getContext();
        if(rootView != null){ rootView.addView(onCreateView());  }

        //初始化真正的视图、初始化数据等
        doView();
        doListeners();
        doHandler(getBundle());
    }
    @Override
    public void onAttach(Activity activity) {
        // TODO Auto-generated method stub
        super.onAttach(activity);
        mContext = activity;
    }
    /**
     * 处理视图创建,作用与onCreateView类似，如果重写onCreateRootView则请无视该方法
     * @return
     */
    public abstract View onCreateView();

    protected void doView(){}
    protected void doListeners(){}
    protected void doHandler(Bundle bundle){}

    /**
     * 刷新数据
     */
    public void refresh() {}

    @Override
    public void onFragmentShow(){}
    @Override
    public void onFragmentHide(){}

    /**
     * 用于获取bundle,注意:如果fragment不独立传参,则默认使用activity的参数
     * @return
     */
    protected Bundle getBundle(){
        Bundle b = getArguments();
        if(null == b){ b = getActivity().getIntent().getExtras();}
        if(null == b){ b = new Bundle(); }
        //TODO sasavedInstanceState
        return b;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (isVisibleToUser && rootViewCreated) {  init(); }

        if(isVisibleToUser){
           onFragmentShow();
        }else{
           if(inited) onFragmentHide();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getUserVisibleHint()) { init();   }
    }

    /**
     * 用于组件查找
     * @param id
     * @param <T>
     * @return
     */
    protected <T> T findView(int id){
        View view = getView().findViewById(id);
        if(view == null){ return null;}
        return (T)view;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootViewCreated = true;

        //如果重写onCreateRootView 则使用用户自定的View
        View root = onCreateRootView(inflater,container,savedInstanceState);
        if(root != null){ return root; }
        //默认情况下,创建一个空白的View布局
        if(rootView == null){
            rootView = new LinearLayout(getContext());
            rootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if(null != parent){ parent.removeView(rootView); }

        return rootView;
    }


    public View onCreateRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return null;
    }

    /**
     * 获取根节点下子元素,建议使用getView 而非 getRootView
     * @return
     */
    public View getView() {
        if(rootView != null){ return rootView; }
        return super.getView();
    }
    /**
     * 得到Res下颜色
     *
     * @param color
     * @return
     */
    public int getResColor(int color) {
        return ContextCompat.getColor(getActivity(), color);
    }

    /**
     * 得到Res下Drawable
     *
     * @param drawable
     * @return
     */
    public Drawable getResDrawable(int drawable) {
        return ContextCompat.getDrawable(getActivity(), drawable);
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
    public void onPause() {
        // TODO Auto-generated method stub
        super.onPause();

    }

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
