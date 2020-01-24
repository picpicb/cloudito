package esipe.fr.notification.controllers;

import esipe.fr.notification.entities.Notification;
import esipe.fr.notification.services.NotificationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.web.bind.annotation.*;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import java.io.IOException;
import java.util.*;

@RestController
@Api(tags = "Notification")
public class NotificationController {

    private NotificationService notificationService;
    private static List<Notification> notificationList = new ArrayList<>();
    public List<Session> clients = Collections.synchronizedList(new ArrayList<Session>());

    @Autowired
    public NotificationController(final NotificationService notificationService){
        this.notificationService = notificationService;
    }

    public NotificationController(){}

    @RequestMapping(value = "/notification", method = RequestMethod.GET)
    @ApiOperation(value = "Register user")
    @ApiResponse(code = 200, message="Request received")
    @ResponseBody
    public void onOpen(Session session) throws IOException {
        System.out.println("Client cookie : " + session.getCookie());
        if (clients.contains(session)) {
            System.out.println("Already connected");
        } else {
            System.out.println("New user detected");
            clients.add(session);
        }
        onCreate();
    }

    @OnClose
    @RequestMapping(value = "/notification/delete", method = RequestMethod.DELETE)
    @ApiOperation(value = "Unregister user")
    @ApiResponse(code = 200, message="User disconnected")
    @ResponseBody
    public void onClose(Session session) {
        clients.remove(session);
        //System.out.println("User : " + session.getId() + " disconnected");
    }

    /*
    @RequestMapping(value = "/notification", method = RequestMethod.GET)
    @ApiOperation(value="Check Notification Service")
    @ResponseBody
    public String chkNotifService() { return "Bonjour Notification Service is ON : "+ InetAddress.getLoopbackAddress().getAddress()[0];}
*/

    @RequestMapping(value = "/notification", method = RequestMethod.PUT)
    @ApiOperation(value="Create single notification")
    @ResponseBody
    public void onCreate() {
        Notification notif = new Notification("toto","un titre","un contenu");
        notificationList.add(notif);
        System.out.println("Notif created : " + notif.getTitle());
    }

    @RequestMapping(value = "/notification/single", method = RequestMethod.POST)
    @ApiOperation(value = "Send message to specific client")
    @ResponseBody
    public void onSendSpecificTarget(Notification singleNotification, Session session) throws IOException {
        synchronized(clients){
            for(Session client : clients){
                if (client.equals(session)){
                    this.notificationService.sendNotification(singleNotification);
                    break;
                }
            }
        }
    }

    @OnMessage
    @RequestMapping(value = "/notification", method = RequestMethod.POST)
    @ApiOperation(value = "Send notifications collection")
    @ResponseBody
    public void onSendAll(){
        //public void send(@RequestBody Notification notif){
        for(Notification singleNotification : notificationList) {
            this.notificationService.sendNotification(singleNotification);
        }
    }

    @OnError
    public void onError(Throwable t) {
        System.out.println("Error::" + t.getMessage());
    }
}
