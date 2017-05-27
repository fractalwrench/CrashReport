package com.fractalwrench.crashreport;

/**
 * Automatically handles uncaught exceptions by writing them to Logcat, before exiting the app.
 */
public class CrashReporter {

    private static CrashReportClient instance;

    /**
     * Begins automatic handling of any uncaught exceptions. This method should only be called once,
     * in the {@link android.app.Application} class.
     * <p>
     * All uncaught exceptions are logged to Logcat by default.
     *
     * @return a singleton instance of the crashreporter implementation.
     */
    public static CrashReportClient initialise() {
        return initialiseWithErrorHandler(new LogcatErrorHandler());
    }

    /**
     * Begins automatic handling of any uncaught exceptions. This method should only be called
     * once, in the {@link android.app.Application} class.
     * <p>
     * All uncaught exceptions are delegated to the ErrorHandler implementation supplied by the
     * client.
     *
     * @param errorHandler a custom implementation of {@link ErrorHandler}
     * @return a singleton instance of the crashreporter implementation.
     * @see CrashReporter#initialise()
     */
    public static CrashReportClient initialiseWithErrorHandler(ErrorHandler errorHandler) {
        if (instance == null) {
            instance = new CrashReportClient.Builder().addErrorCallback(errorHandler)
                                                      .build();
        }
        return instance;
    }

    private CrashReporter() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

}
