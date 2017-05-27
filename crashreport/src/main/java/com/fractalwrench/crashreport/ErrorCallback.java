package com.fractalwrench.crashreport;

public interface ErrorCallback {

    void onError(Thread thread, Throwable error);

}
