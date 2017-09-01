package com.yaoobs.anotherplay.common.rx.subscriber;

import android.content.Context;
import android.util.Log;

import com.yaoobs.anotherplay.common.exception.BaseException;
import com.yaoobs.anotherplay.common.rx.RxErrorHandler;

public abstract class ErrorHandlerSubscriber<T> extends DefualtSubscriber<T> {

    protected RxErrorHandler mErrorHandler = null;

    protected Context mContext;

    public ErrorHandlerSubscriber(Context context) {

        this.mContext = context;

        this.mErrorHandler = new RxErrorHandler(mContext);

    }


    @Override
    public void onError(Throwable e) {

        BaseException baseException =  mErrorHandler.handleError(e);

        if(baseException==null){
            e.printStackTrace();
            Log.d("ErrorHandlerSubscriber",e.getMessage());
        }
        else {
            mErrorHandler.showErrorMessage(baseException);
        }

    }


}
