package com.fractalwrench.crashreport;

import android.content.Context;

public class CrashReporter {

    private static Client instance;

    public static Client initialise(Context context) {
        if (instance == null) {
            instance = new Client.Builder().addErrorCallback(new UiDisplayErrorCallback(context))
                                           .build();
        }
        return instance;
    }

    private CrashReporter() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

}
