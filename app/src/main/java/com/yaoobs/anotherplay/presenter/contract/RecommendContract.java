package com.yaoobs.anotherplay.presenter.contract;


import com.yaoobs.anotherplay.bean.AppInfo;
import com.yaoobs.anotherplay.bean.IndexBean;
import com.yaoobs.anotherplay.presenter.BasePresenter;
import com.yaoobs.anotherplay.ui.BaseView;

import java.util.List;

/**
 * Created by yaoobs on 2017/1/3.
 */

public interface RecommendContract {

    interface View extends BaseView {

        void showResult(IndexBean indexBean);

        void onRequestPermissonSuccess();
        void onRequestPermissonError();
    }


}
