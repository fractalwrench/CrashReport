package com.fractalwrench.crashreport;

public interface ErrorInterceptor {

    void onError(Thread thread, Throwable error);

}
