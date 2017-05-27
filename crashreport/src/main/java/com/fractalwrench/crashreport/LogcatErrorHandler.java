package com.fractalwrench.crashreport;

import android.util.Log;

/**
 * Writes errors directly to Logcat.
 */
class LogcatErrorHandler implements ErrorHandler {

    @Override
    public void onError(Thread thread, Throwable error) {
        ErrorInfo errorInfo = new ErrorInfo(error);
        Log.e("CrashReporter", errorInfo.toString());
        System.exit(1);
    }

}
