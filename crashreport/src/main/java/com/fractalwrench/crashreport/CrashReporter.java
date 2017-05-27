package com.fractalwrench.crashreport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class CrashReporter implements Thread.UncaughtExceptionHandler {

    private final List<ErrorInterceptor> interceptors= new ArrayList<>();

    private CrashReporter(List<ErrorInterceptor> interceptors) {
        Thread.setDefaultUncaughtExceptionHandler(this);
        this.interceptors.addAll(interceptors);
    }

    public List<ErrorInterceptor> getInterceptors() {
        return Collections.unmodifiableList(interceptors);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        for (ErrorInterceptor interceptor : interceptors) {
            interceptor.onError(t, e);
        }
    }


    public static class Builder { // TODO add

        private final List<ErrorInterceptor> interceptors;

        public Builder() {
            this.interceptors = new ArrayList<>();
        }

        public Builder addErrorInterceptor(ErrorInterceptor errorInterceptor) {
            if (errorInterceptor == null) {
                throw new IllegalArgumentException("Cannot be null");
            }
            interceptors.add(errorInterceptor);
            return this;
        }

        public CrashReporter build() {
            return new CrashReporter(interceptors);
        }
    }

}
