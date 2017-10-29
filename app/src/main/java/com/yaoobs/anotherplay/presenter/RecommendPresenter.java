package com.yaoobs.anotherplay.presenter;


import android.Manifest;
import android.app.Activity;
import android.support.v4.app.Fragment;

import com.tbruyelle.rxpermissions.RxPermissions;
import com.yaoobs.anotherplay.bean.AppInfo;
import com.yaoobs.anotherplay.bean.IndexBean;
import com.yaoobs.anotherplay.bean.PageBean;
import com.yaoobs.anotherplay.common.rx.RxErrorHandler;
import com.yaoobs.anotherplay.common.rx.RxHttpReponseCompat;
import com.yaoobs.anotherplay.common.rx.subscriber.ProgressDialogSubcriber;
import com.yaoobs.anotherplay.common.rx.subscriber.ProgressSubcriber;
import com.yaoobs.anotherplay.data.RecommendModel;
import com.yaoobs.anotherplay.presenter.contract.RecommendContract;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

public class RecommendPresenter extends BasePresenter<RecommendModel, RecommendContract.View> {

    @Inject
    public RecommendPresenter(RecommendModel recommendModel, RecommendContract.View view) {
        super(recommendModel, view);
    }

//    public void requestPermission(){
//        RxPermissions rxPermissions = new RxPermissions((Activity) mContext);
//
//        rxPermissions.request(Manifest.permission.READ_PHONE_STATE).subscribe(new Action1<Boolean>() {
//            @Override
//            public void call(Boolean aBoolean) {
//                if(aBoolean){
//
//                    mView.onRequestPermissonSuccess();
//                }
//                else{
//
//                    mView.onRequestPermissonError();
//                }
//            }
//        });
//
//    }

    public void requestDatas() {
        mModel.index().compose(RxHttpReponseCompat.<IndexBean>compatResult())
                .subscribe(new ProgressSubcriber<IndexBean>(mContext,mView) {
                    @Override
                    public void onNext(IndexBean indexBean) {
                        mView.showResult(indexBean);
                    }
                });
//        RxPermissions rxPermissions = new RxPermissions((Activity) mContext);
//        rxPermissions.request(Manifest.permission.READ_PHONE_STATE)
//                .flatMap(new Func1<Boolean, Observable<PageBean<AppInfo>>>() {
//                    @Override
//                    public Observable<PageBean<AppInfo>>call(Boolean aBoolean) {
//
//                        if(aBoolean){
//
//                            return  mModel.getApps().compose(RxHttpReponseCompat.<PageBean<AppInfo>>compatResult());
//                        }
//                        else{
//
//                            return Observable.empty();
//                        }
//
//
//                    }
//                })
//                .subscribe(new ProgressSubcriber<PageBean<AppInfo>>(mContext,mView) {
//                    @Override
//                    public void onNext(PageBean<AppInfo> appInfoPageBean) {
//                        mView.showResult(appInfoPageBean.getDatas());
//                    }
//                });
//        mModel.getApps()
//                .compose(RxHttpReponseCompat.<PageBean<AppInfo>>compatResult())
//                .subscribe(new ProgressSubcriber<PageBean<AppInfo>>(mContext,mView) {
//                    @Override
//                    public void onNext(PageBean<AppInfo> appInfoPageBean) {
//                        mView.showResult(appInfoPageBean.getDatas());
//                    }
//                });
//        mView.shwLoading();

//        mModel.getApps(new Callback<PageBean<AppInfo>>() {
//            @Override
//            public void onResponse(Call<PageBean<AppInfo>> call, Response<PageBean<AppInfo>> response) {
//                if(response !=null){
//
//                    mView.showResult(response.body().getDatas());
//                }
//                else{
//                    mView.showNodata();
//                }
//
//                mView.dismissLoading();
//            }
//
//            @Override
//            public void onFailure(Call<PageBean<AppInfo>> call, Throwable t) {
//                mView.dismissLoading();
//                mView.showError(t.getMessage());
//            }
//        });
    }
}
