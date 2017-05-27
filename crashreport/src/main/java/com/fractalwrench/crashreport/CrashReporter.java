package com.fractalwrench.crashreport;

import android.content.Context;

public class CrashReporter {

    private static CrashReportClient instance;

    public static CrashReportClient initialise(Context context) {
        if (instance == null) {
            instance = new CrashReportClient.Builder().addErrorCallback(new UiDisplayErrorCallback(context))
                                                      .build();
        }
        return instance;
    }

    private CrashReporter() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

}
