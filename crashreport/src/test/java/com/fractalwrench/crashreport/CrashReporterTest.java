package com.fractalwrench.crashreport;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class CrashReporterTest {

    private static final ErrorInterceptor DUMMY_INTERCEPTOR = new ErrorInterceptor() {
        @Override
        public void onError(Thread thread, Throwable error) {

        }
    };

    private CrashReporter.Builder builder;

    @Before
    public void setUp() throws Exception {
        builder = new CrashReporter.Builder();
    }

    @Test(expected = IllegalArgumentException.class)
    public void rejectNullInterceptor() throws Exception {
        builder.addErrorInterceptor(null);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void preventInterceptorMutation() throws Exception {
        CrashReporter reporter = builder.addErrorInterceptor(DUMMY_INTERCEPTOR)
                                     .build();
        assertEquals(1, reporter.getInterceptors().size()); // default + custom impl = 2
        reporter.getInterceptors().remove(0); // cannot remove from immutable obj
    }
}