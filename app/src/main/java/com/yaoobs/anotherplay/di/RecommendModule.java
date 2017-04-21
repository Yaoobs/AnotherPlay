package com.yaoobs.anotherplay.di;

import android.app.ProgressDialog;

import com.yaoobs.anotherplay.data.RecommendModel;
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
    public RecommendContract.Presenter providePresenter(RecommendContract.View view,RecommendModel privodeModel) {
        return new RecommendPresenter(view,privodeModel);
    }

    @Provides
    public RecommendContract.View provideView(){
        return mView;
    }

    @Provides
    public RecommendModel privodeModel(){
        return new RecommendModel();
    }
    @Provides
    public ProgressDialog provideProgressDialog(RecommendContract.View view){

        return new ProgressDialog(((RecommendFragment)view).getActivity());
    }

}
