package com.yaoobs.anotherplay.di.module;

import android.app.ProgressDialog;

import com.yaoobs.anotherplay.data.RecommendModel;
import com.yaoobs.anotherplay.data.http.ApiService;
import com.yaoobs.anotherplay.presenter.RecommendPresenter;
import com.yaoobs.anotherplay.presenter.contract.RecommendContract;
import com.yaoobs.anotherplay.ui.fragment.RecommendFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class RecommendModule {

    private RecommendContract.View mView;

    public RecommendModule(RecommendContract.View view) {
        this.mView = view;
    }

    @Provides
    public RecommendContract.View provideView(){
        return mView;
    }

    @Provides
    public RecommendModel privodeModel(ApiService apiService){
        return new RecommendModel(apiService);
    }
    @Provides
    public ProgressDialog provideProgressDialog(RecommendContract.View view){

        return new ProgressDialog(((RecommendFragment)view).getActivity());
    }

}
