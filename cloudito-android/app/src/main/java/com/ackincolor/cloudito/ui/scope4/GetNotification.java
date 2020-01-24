/*
package com.ackincolor.cloudito.ui.notifications;

import java.io.IOException;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GetNotification {
    OkHttpClient okHttpClient = new OkHttpClient();

    Request myGetRequest = new Request.Builder()
            .url("172.31 .254 .54:8082/cloudito/notification")
            .build();

    okHttpClient.newCall(myGetRequest).enqueue(new Callback() {
        @Override
                public void onFailure(Request request, IOexception e) {

        }
        @Override
                public void onResponse(Response response) throws IOException {
            // le retour est effectué dans un thread différent
        final String text = response.body().string();
        final int statusCode = response.code();

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.setText(text);
            }
        });
        }
    })

    public static void main(String[] args) throws IOException {
        GetNotification notification = new GetNotification();
        String response = notification.run("https://raw.github.com/square/okhttp/master/README.md");
        System.out.println(response);
    }
    /*
    String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(172.31 .254 .54:8082 / cloudito / notification)
                .build();
    }
    try (Response response = client.newCall(request).execute()) {
        return response.body().string();
    }
}
    public static void main(String[] args) throws IOException {
        GetNotification notification = new GetNotification();
        String response = notification.run("https://raw.github.com/square/okhttp/master/README.md");
        System.out.println(response);
    }

}

*/