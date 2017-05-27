package com.fractalwrench.crashreport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class CrashReporter implements Thread.UncaughtExceptionHandler {

    private final List<ErrorCallback> callbacks = new ArrayList<>();

    private CrashReporter(List<ErrorCallback> callbacks) {
        Thread.setDefaultUncaughtExceptionHandler(this);
        this.callbacks.addAll(callbacks);
    }

    public List<ErrorCallback> getErrorCallbacks() {
        return Collections.unmodifiableList(callbacks);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        for (ErrorCallback callback : callbacks) {
            callback.onError(t, e);
        }
    }


    public static class Builder { // TODO add

        private final List<ErrorCallback> errorCallbacks;

        public Builder() {
            this.errorCallbacks = new ArrayList<>();
        }

        public Builder addErrorCallback(ErrorCallback errorCallback) {
            if (errorCallback == null) {
                throw new IllegalArgumentException("Cannot be null");
            }
            errorCallbacks.add(errorCallback);
            return this;
        }

        public CrashReporter build() {
            return new CrashReporter(errorCallbacks);
        }
    }

}
