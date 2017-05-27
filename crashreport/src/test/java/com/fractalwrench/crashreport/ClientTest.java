package com.fractalwrench.crashreport;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class ClientTest {

    private static final ErrorCallback DUMMY_INTERCEPTOR = new ErrorCallback() {
        @Override
        public void onError(Thread thread, Throwable error) {

        }
    };

    private Client.Builder builder;

    @Before
    public void setUp() throws Exception {
        builder = new Client.Builder();
    }

    @Test(expected = IllegalArgumentException.class)
    public void rejectNullInterceptor() throws Exception {
        builder.addErrorCallback(null);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void preventCallbackMutation() throws Exception {
        Client reporter = builder.addErrorCallback(DUMMY_INTERCEPTOR).build();
        assertEquals(1, reporter.getErrorCallbacks().size()); // default + custom impl = 2
        reporter.getErrorCallbacks().remove(0); // cannot remove from immutable obj
    }
}