package com.fractalwrench.crashreport;

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;

public class ErrorInfo implements Serializable {

    private final String canonicalName;
    private final String message;
    private final String stackTrace;

    public ErrorInfo(Throwable t) {
        canonicalName = t.getClass()
                         .getCanonicalName();
        message = t.getMessage();
        stackTrace = getStackTrace(t);
    }

    private String getStackTrace(Throwable t) {
        StringWriter writer = new StringWriter();
        t.printStackTrace(new PrintWriter(writer));
        return writer.toString();
    }

    public String getCanonicalName() {
        return canonicalName;
    }

    public String getMessage() {
        return message;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    @Override
    public String toString() {
        return String.format("Crash Report:\nException Class = %s\nMessage = %s\n\nStackTrace = %s",
                             canonicalName, message, stackTrace);
    }
}
