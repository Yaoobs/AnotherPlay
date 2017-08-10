package com.yaoobs.anotherplay.presenter;


import android.app.Activity;
import android.support.v4.app.Fragment;

import com.yaoobs.anotherplay.bean.AppInfo;
import com.yaoobs.anotherplay.bean.BaseBean;
import com.yaoobs.anotherplay.bean.PageBean;
import com.yaoobs.anotherplay.common.rx.RxErrorHandler;
import com.yaoobs.anotherplay.common.rx.RxHttpReponseCompat;
import com.yaoobs.anotherplay.common.rx.subscriber.ErrorHandlerSubscriber;
import com.yaoobs.anotherplay.common.rx.subscriber.ProgressDialogSubcriber;
import com.yaoobs.anotherplay.data.RecommendModel;
import com.yaoobs.anotherplay.di.module.RecommendModule;
import com.yaoobs.anotherplay.presenter.contract.RecommendContract;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.observers.SafeSubscriber;
import rx.schedulers.Schedulers;


public class RecommendPresenter extends BasePresenter<RecommendModel, RecommendContract.View> {

    private RxErrorHandler mErrorHandler;

    @Inject
    public RecommendPresenter(RecommendModel recommendModel, RecommendContract.View view, RxErrorHandler errorHandler) {
        super(recommendModel, view);
        this.mErrorHandler = errorHandler;
    }

    public void requestDatas() {


        mModel.getApps()
                .compose(RxHttpReponseCompat.<PageBean<AppInfo>>compatResult())
                .subscribe(new ProgressDialogSubcriber<PageBean<AppInfo>>(mView,mErrorHandler) {
                    @Override
                    public void onNext(PageBean<AppInfo> appInfoPageBean) {
                        mView.showResult(appInfoPageBean.getDatas());
                    }
                });
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
