package com.yaoobs.anotherplay.ui.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.yaoobs.anotherplay.AppApplication;
import com.yaoobs.anotherplay.R;
import com.yaoobs.anotherplay.bean.AppInfo;

import com.yaoobs.anotherplay.di.component.DaggerRecommendComponent;
import com.yaoobs.anotherplay.di.module.RecommendModule;
import com.yaoobs.anotherplay.presenter.contract.RecommendContract;
import com.yaoobs.anotherplay.ui.adapter.RecommendAppAdatper;
import com.yaoobs.anotherplay.ui.decoration.DividerItemDecoration;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ivan on 16/9/26.
 */

public class RecommendFragment extends Fragment implements RecommendContract.View {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    private RecommendAppAdatper mAdatper;

    @Inject
    ProgressDialog mProgressDialog;
    @Inject
    RecommendContract.Presenter mPresenter;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_recomend, container, false);

        ButterKnife.bind(this, view);
//        mProgressDialog = new ProgressDialog(getActivity());
//        mPresenter = new RecommendPresenter(this);


        DaggerRecommendComponent.builder().appComponent(((AppApplication) getActivity().getApplication()).getAppComponent())
                .recommendModule(new RecommendModule(this)).build().inject(this);
        initData();
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private void initData() {

        mPresenter.requestDatas();
    }

    private void initRecyclerView(List<AppInfo> datas) {

        //为RecyclerView设置布局管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //为RecyclerView设置分割线(这个可以对DividerItemDecoration进行修改，自定义)
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL_LIST));

        //动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdatper = new RecommendAppAdatper(getActivity(), datas);

        mRecyclerView.setAdapter(mAdatper);


    }

    @Override
    public void shwLoading() {
        mProgressDialog.show();
    }

    @Override
    public void dismissLoading() {
        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void showResult(List<AppInfo> datas) {
        initRecyclerView(datas);
    }

    @Override
    public void showNodata() {
        Toast.makeText(getActivity(), "暂时无数据，请吃完饭再来", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(getActivity(), "服务器开小差了：" + msg, Toast.LENGTH_LONG).show();
    }
}
