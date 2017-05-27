package com.fractalwrench.crashreport;

/**
 * Handles an uncaught exception thrown by an Android application.
 */
public interface ErrorHandler {

    /**
     * Called whenever an uncaught exception is thrown
     *
     * @param thread the thread on which the exception was thrown
     * @param error  the error
     */
    void onError(Thread thread, Throwable error);

}
