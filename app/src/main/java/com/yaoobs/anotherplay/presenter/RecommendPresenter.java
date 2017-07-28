package com.yaoobs.anotherplay.presenter;



import com.yaoobs.anotherplay.bean.AppInfo;
import com.yaoobs.anotherplay.bean.PageBean;
import com.yaoobs.anotherplay.data.RecommendModel;
import com.yaoobs.anotherplay.di.module.RecommendModule;
import com.yaoobs.anotherplay.presenter.contract.RecommendContract;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RecommendPresenter extends BasePresenter<RecommendModel,RecommendContract.View>{

    @Inject
    public RecommendPresenter(RecommendModel recommendModel, RecommendContract.View view) {
        super(recommendModel, view);
    }

    public void requestDatas() {
        mView.shwLoading();

        mModel.getApps(new Callback<PageBean<AppInfo>>() {
            @Override
            public void onResponse(Call<PageBean<AppInfo>> call, Response<PageBean<AppInfo>> response) {
                if(response !=null){

                    mView.showResult(response.body().getDatas());
                }
                else{
                    mView.showNodata();
                }

                mView.dismissLoading();
            }

            @Override
            public void onFailure(Call<PageBean<AppInfo>> call, Throwable t) {
                mView.dismissLoading();
                mView.showError(t.getMessage());
            }
        });
    }
}
