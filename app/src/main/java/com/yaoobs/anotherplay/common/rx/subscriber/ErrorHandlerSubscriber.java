package com.yaoobs.anotherplay.common.rx.subscriber;

import android.content.Context;
import android.util.Log;

import com.yaoobs.anotherplay.common.exception.BaseException;
import com.yaoobs.anotherplay.common.rx.RxErrorHandler;

public abstract class ErrorHandlerSubscriber<T> extends DefualtSubscriber<T> {

    protected RxErrorHandler mRxErrorHandler;

    public ErrorHandlerSubscriber(RxErrorHandler errorHandler) {

        this.mRxErrorHandler = errorHandler;

    }


    @Override
    public void onError(Throwable e) {

        e.printStackTrace();

        BaseException baseException = mRxErrorHandler.handleError(e);
        mRxErrorHandler.showErrorMessage(baseException);

    }


}
