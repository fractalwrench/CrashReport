package com.fractalwrench.crashreport;

public class CrashReporter {

    private static CrashReportClient instance;

    public static CrashReportClient initialise() {
        if (instance == null) {
            instance = new CrashReportClient.Builder().addErrorCallback(new LogcatErrorHandler())
                                                      .build();
        }
        return instance;
    }

    private CrashReporter() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

}
