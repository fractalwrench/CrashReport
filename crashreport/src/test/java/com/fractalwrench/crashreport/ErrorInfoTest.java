package com.fractalwrench.crashreport;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class ErrorInfoTest {

    @Test
    public void getCanonicalName() throws Exception {
        String canonicalName = new ErrorInfo(new RuntimeException()).getCanonicalName();
        assertEquals("java.lang.RuntimeException", canonicalName);
    }

    @Test
    public void getMessage() throws Exception {
        String message = "Message";
        ErrorInfo errorInfo = new ErrorInfo(new IllegalStateException(message));
        assertEquals(message, errorInfo.getMessage());
    }

}