package com.fractalwrench.crashreport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class Client implements Thread.UncaughtExceptionHandler {

    private final List<ErrorCallback> callbacks = new ArrayList<>();

    private Client(List<ErrorCallback> callbacks) {
        Thread.setDefaultUncaughtExceptionHandler(this);
        this.callbacks.addAll(callbacks);
    }

    List<ErrorCallback> getErrorCallbacks() {
        return Collections.unmodifiableList(callbacks);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        for (ErrorCallback callback : callbacks) {
            callback.onError(t, e);
        }
    }

    static class Builder {

        private final List<ErrorCallback> errorCallbacks;

        Builder() {
            this.errorCallbacks = new ArrayList<>();
        }

        Builder addErrorCallback(ErrorCallback errorCallback) {
            if (errorCallback == null) {
                throw new IllegalArgumentException("Cannot be null");
            }
            errorCallbacks.add(errorCallback);
            return this;
        }

        Client build() {
            return new Client(errorCallbacks);
        }
    }

}
