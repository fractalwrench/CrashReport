package com.fractalwrench.crashreport;

import android.content.Context;

public class UiDisplayErrorCallback implements ErrorCallback {

    private final Context context;

    public UiDisplayErrorCallback(Context context) {
        this.context = context;
    }

    @Override
    public void onError(Thread thread, Throwable error) {
        ErrorInfo errorInfo = new ErrorInfo(error);
        StackTraceElement[] stackTrace = error.getStackTrace();


        // TODO display!


    }

}
