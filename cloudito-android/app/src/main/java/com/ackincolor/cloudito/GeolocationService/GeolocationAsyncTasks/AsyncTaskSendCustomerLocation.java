package com.ackincolor.cloudito.GeolocationService.GeolocationAsyncTasks;

import android.content.Context;
import android.os.AsyncTask;

import com.ackincolor.cloudito.GeolocationService.GeolocationAndroidService;

public class AsyncTaskSendCustomerLocation extends AsyncTask<Context,Integer,Void> {
    @Override
    protected Void doInBackground(Context... contexts) {
        GeolocationAndroidService geolocationAndroidService = new GeolocationAndroidService(contexts[0]);
        try {
            geolocationAndroidService.startSendLocation();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
