package esipe.fr.Cloudito_notification.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import esipe.fr.Cloudito_notification.entities.Notification;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Override
    public String sendNotification(Notification notif) {
        try {
            URL url = new URL("https://fcm.googleapis.com/fcm/send");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Authorization" , "key=AAAAf2yowz4:APA91bHBQ-i-_cEeP4y-GD1nn0mHYsHfEOgBoN8HvQhejO6asJornJ1RwzKYQhYreX1wSG4W4vky-ThZZePA4H7IKrzkVpNG0574ir4vAqr19Sj5sDe-eZgUGZ2uSxEWU85C6bDV9pWf");
            con.setDoOutput(true);
            con.setDoInput(true);

            final GsonBuilder builder = new GsonBuilder();
            final Gson gson = builder.create();
            try(OutputStream os = con.getOutputStream()) {
                byte[] input = gson.toJson(notif).getBytes("utf-8");
                os.write(input, 0, input.length);
            }
            System.out.println(gson.toJson(notif));
            con.connect();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            return response.toString();

        }catch(Exception e) {
            e.printStackTrace();
        }
        return "error";
    }
}
