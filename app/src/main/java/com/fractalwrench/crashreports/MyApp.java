package com.fractalwrench.crashreports;

import android.app.Application;

import com.fractalwrench.crashreport.CrashReporter;

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CrashReporter.initialise(this);
    }

}
