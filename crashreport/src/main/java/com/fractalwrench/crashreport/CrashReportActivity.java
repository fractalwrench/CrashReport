package com.fractalwrench.crashreport;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.io.Serializable;

public class CrashReportActivity extends Activity {

    public static final String KEY_ERROR_INFO = "ERROR_INFO";

    public static Intent getIntent(Context context, ErrorInfo errorInfo) {
        Intent intent = new Intent(context, CrashReportActivity.class);
        intent.putExtra(KEY_ERROR_INFO, errorInfo);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crash_report);

        Bundle extras = getIntent().getExtras();
        ErrorInfo errorInfo = getErrorInfoFromExtras(extras);
        bindErrorInfo(errorInfo);
    }

    private void bindErrorInfo(ErrorInfo errorInfo) {
        // TODO
    }

    private ErrorInfo getErrorInfoFromExtras(Bundle extras) {
        ErrorInfo errorInfo = null;

        if (extras != null) {
            Serializable serializable = extras.getSerializable(KEY_ERROR_INFO);

            if (serializable instanceof ErrorInfo) {
                errorInfo = (ErrorInfo) serializable;
            }
        }
        if (errorInfo == null) {
            throw new IllegalStateException("No error info supplied to Activity Intent Extras");
        }
        return errorInfo;
    }
}
