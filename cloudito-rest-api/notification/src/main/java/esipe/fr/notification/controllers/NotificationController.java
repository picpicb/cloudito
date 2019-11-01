package esipe.fr.notification.controllers;


import esipe.fr.notification.entities.Notification;
import esipe.fr.notification.services.NotificationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.InetAddress;
import java.util.List;

@RestController
@Api(tags = "Notification")
public class NotificationController {

    private NotificationService notificationService;

    @Autowired
    public NotificationController(final NotificationService notificationService){
        this.notificationService = notificationService;
    }

    @RequestMapping(value = "/notification", method = RequestMethod.GET)
    @ApiOperation(value="Check Notification Service")
    @ResponseBody
    public String chkNotifService() { return "Bonjour Notification Service is ON : "+ InetAddress.getLoopbackAddress().getAddress()[0];}

    @RequestMapping(value = "/notification", method = RequestMethod.POST)
    @ApiOperation(value = "Send Notification")
    @ResponseBody
    public String addCity(@RequestBody Notification notif){
        return this.notificationService.sendNotification(notif);
    }

}
