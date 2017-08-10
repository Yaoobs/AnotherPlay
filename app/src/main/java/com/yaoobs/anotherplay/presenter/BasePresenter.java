package com.yaoobs.anotherplay.presenter;


import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.yaoobs.anotherplay.ui.BaseView;

public class BasePresenter <M,V extends BaseView>{

    protected M mModel;

    protected V mView;

    private Context mContext;

    public BasePresenter(M m,V v){

        this.mModel=m;
        this.mView = v;

        initContext();
    }

    private void initContext(){
        if (mView instanceof Fragment){
            mContext = ((Fragment) mView).getActivity();
        } else {
            mContext = (Activity) mView;
        }
    }

}
