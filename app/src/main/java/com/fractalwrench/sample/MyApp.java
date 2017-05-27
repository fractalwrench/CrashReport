package com.fractalwrench.sample;

import android.app.Application;

import com.fractalwrench.crashreport.CrashReporter;

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // initialises automatic reporting of uncaught exceptions.
        CrashReporter.initialise();
    }

}
