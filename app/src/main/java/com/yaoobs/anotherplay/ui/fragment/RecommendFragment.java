package com.yaoobs.anotherplay.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yaoobs.anotherplay.R;
import com.yaoobs.anotherplay.bean.AppInfo;
import com.yaoobs.anotherplay.bean.PageBean;
import com.yaoobs.anotherplay.http.ApiService;
import com.yaoobs.anotherplay.http.HttpManager;
import com.yaoobs.anotherplay.ui.adapter.RecomendAppAdatper;
import com.yaoobs.anotherplay.ui.decoration.DividerItemDecoration;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ivan on 16/9/26.
 */

public class RecommendFragment extends Fragment {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    private RecomendAppAdatper mAdatper;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_recomend, container, false);

        ButterKnife.bind(this, view);
        initData();
        return view;

    }

    private void initData(){

        HttpManager manager = new HttpManager();

        ApiService apiService = manager.getRetrofit(manager.getOkHttpClient()).create(ApiService.class);

        apiService.getApps("{'page':0}").enqueue(new Callback<PageBean<AppInfo>>() {
            @Override
            public void onResponse(Call<PageBean<AppInfo>> call, Response<PageBean<AppInfo>> response) {
                PageBean<AppInfo> pageBean = response.body();
                List<AppInfo> datas = pageBean.getDatas();
                initRecycleView(datas);
            }

            @Override
            public void onFailure(Call<PageBean<AppInfo>> call, Throwable t) {

            }
        });
    }

    private void initRecycleView(List<AppInfo> datas){

        //为RecyclerView设置布局管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //为RecyclerView设置分割线(这个可以对DividerItemDecoration进行修改，自定义)
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL_LIST));

        //动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdatper = new RecomendAppAdatper(getActivity(),datas);

        mRecyclerView.setAdapter(mAdatper);



    }
}
