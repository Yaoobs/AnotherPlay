package com.yaoobs.anotherplay.common.rx.subscriber;

import android.content.Context;
import com.yaoobs.anotherplay.common.util.ProgressDialogHandler;

public abstract class ProgressDialogSubcriber<T> extends ErrorHandlerSubscriber<T> implements ProgressDialogHandler.OnProgressCancelListener{
    private ProgressDialogHandler mProgressDialogHandler;

    public ProgressDialogSubcriber(Context context) {
        super(context);
        mProgressDialogHandler = new ProgressDialogHandler(mContext,true,this);
    }

    protected boolean isShowProgressDialog() {
        return true;
    }

    @Override
    public void onCancelProgress() {
        unsubscribe();
    }

    @Override
    public void onStart() {
        if (isShowProgressDialog())
            this.mProgressDialogHandler.showProgressDialog();
    }

    @Override
    public void onCompleted() {
        if(isShowProgressDialog()){
            this.mProgressDialogHandler.dismissProgressDialog();
        }
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
        if(isShowProgressDialog()){
            this.mProgressDialogHandler.dismissProgressDialog();
        }
    }

}
