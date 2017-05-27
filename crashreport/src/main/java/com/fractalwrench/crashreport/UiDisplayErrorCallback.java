package com.fractalwrench.crashreport;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

class UiDisplayErrorCallback implements ErrorCallback {

    private static final int REQUEST_CODE = 9001;
    private static final int DELAY_MS = 150;
    private final Context context;

    UiDisplayErrorCallback(Context context) {
        this.context = context;
    }

    @Override
    public void onError(Thread thread, Throwable error) {
        ErrorInfo errorInfo = new ErrorInfo(error); // TODO add to intent
        Intent intent = new Intent(context, CrashReportActivity.class);
        intent.putExtra("errorInfo", errorInfo);
        launchErrorPage(intent);
        System.exit(0);
    }

    private void launchErrorPage(Intent intent) {
        PendingIntent pendingIntent = PendingIntent.getActivity(context, REQUEST_CODE, intent,
                                                                PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC, System.currentTimeMillis() + DELAY_MS, pendingIntent);
    }

}
