package com.fractalwrench.crashreports;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity implements View.OnClickListener {

    private View bgThreadBtn;
    private View uiThreadBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bgThreadBtn = findViewById(R.id.bg_thread_btn);
        uiThreadBtn = findViewById(R.id.ui_thread_btn);

        bgThreadBtn.setOnClickListener(this);
        uiThreadBtn.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bgThreadBtn.setOnClickListener(null);
        uiThreadBtn.setOnClickListener(null);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bg_thread_btn:
                handleBgThreadClick();
                break;
            case R.id.ui_thread_btn:
                handleUiThreadClick();
                break;
            default:
                throw new UnsupportedOperationException();
        }
    }

    private void handleUiThreadClick() {
        throw new RuntimeException("Threw an exception from the UI Thread!");
    }

    private void handleBgThreadClick() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                throw new IllegalStateException("Threw an exception from a Background Thread!");
            }
        }.execute();
    }

}
