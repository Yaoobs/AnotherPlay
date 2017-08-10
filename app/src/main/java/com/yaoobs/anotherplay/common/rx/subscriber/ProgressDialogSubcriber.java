package com.yaoobs.anotherplay.common.rx.subscriber;

import android.app.ProgressDialog;
import android.content.Context;

import com.yaoobs.anotherplay.bean.BaseBean;
import com.yaoobs.anotherplay.common.rx.RxErrorHandler;
import com.yaoobs.anotherplay.common.util.ProgressDialogHandler;
import com.yaoobs.anotherplay.ui.BaseView;

public abstract class ProgressDialogSubcriber<T> extends ErrorHandlerSubscriber<T> {

    private ProgressDialog mProgressDialog;
    private BaseView mBaseView;

    public ProgressDialogSubcriber(BaseView baseView, RxErrorHandler rxErrorHandler) {
        super(rxErrorHandler);
        this.mBaseView = baseView;
    }

    protected boolean isShowDialog() {
        return true;
    }

    @Override
    public void onStart() {
            showProgressDialog();

    }

    @Override
    public void onCompleted() {
            dismissProgressDialog();
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
        dismissProgressDialog();
    }

    private void initProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(mContext);
            mProgressDialog.setMessage("loading.....");
        }

    }

    private void showProgressDialog() {
//        initProgressDialog();
//        mProgressDialog.show();
        mBaseView.showLoading();
    }

    private void dismissProgressDialog() {
//        if (mProgressDialog != null && mProgressDialog.isShowing()) {
//            mProgressDialog.dismiss();
//        }
        mBaseView.dismissLoading();
    }

}
