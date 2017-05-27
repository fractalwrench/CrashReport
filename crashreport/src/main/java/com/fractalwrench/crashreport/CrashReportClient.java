package com.fractalwrench.crashreport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class CrashReportClient implements Thread.UncaughtExceptionHandler {

    private final List<ErrorHandler> callbacks = new ArrayList<>();

    private CrashReportClient(List<ErrorHandler> callbacks) {
        Thread.setDefaultUncaughtExceptionHandler(this);
        this.callbacks.addAll(callbacks);
    }

    List<ErrorHandler> getErrorCallbacks() {
        return Collections.unmodifiableList(callbacks);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        for (ErrorHandler callback : callbacks) {
            callback.onError(t, e);
        }
    }

    static class Builder {

        private final List<ErrorHandler> errorHandlers;

        Builder() {
            this.errorHandlers = new ArrayList<>();
        }

        Builder addErrorCallback(ErrorHandler errorHandler) {
            if (errorHandler == null) {
                throw new IllegalArgumentException("Cannot be null");
            }
            errorHandlers.add(errorHandler);
            return this;
        }

        CrashReportClient build() {
            return new CrashReportClient(errorHandlers);
        }
    }

}
