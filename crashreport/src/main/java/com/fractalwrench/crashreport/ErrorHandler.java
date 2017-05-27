package com.fractalwrench.crashreport;

interface ErrorHandler {

    void onError(Thread thread, Throwable error);

}
