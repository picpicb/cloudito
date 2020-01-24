package com.ackincolor.cloudito.ui.notifications;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

public class WebSocketEcho extends WebSocketListener {
    private void run() {
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(0, TimeUnit.MILLISECONDS)
                .build();

        Request request = new Request.Builder()
                .url("http://172.31.254.54:8082/cloudito/notification")
                .build();
        client.newWebSocket(request, this);

        //Trigger shutdown of the dispatcher's executor so this process can exit cleanly.

        client.dispatcher().executorService().shutdown();
    }

    @Override public void onOpen(WebSocket webSocket, Response response) {
        webSocket.send("hello");
        webSocket.send("world !");
        webSocket.send(ByteString.decodeHex("abconetwothree"));
        webSocket.close(1000, "Goodbye, World !");
    }

    @Override public void onMessage(WebSocket webSocket, String text) {
        System.out.println("Message: " + text);
    }

    @Override public void onMessage(WebSocket webSocket, ByteString bytes) {
        System.out.println("Message: " + bytes.hex());
    }

    @Override public void onClosing(WebSocket webSocket, int code, String reason) {
        webSocket.close(1000, null);
        System.out.println("CLOSE : " + code + "" + reason);
    }

    @Override public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        t.printStackTrace();
    }

    public static void main (String... args){
        new WebSocketEcho().run();
    }
}
