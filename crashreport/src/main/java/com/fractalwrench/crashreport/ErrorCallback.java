package com.fractalwrench.crashreport;

interface ErrorCallback {

    void onError(Thread thread, Throwable error);

}
