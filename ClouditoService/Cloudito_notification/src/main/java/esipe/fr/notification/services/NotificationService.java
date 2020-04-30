package esipe.fr.notification.services;

import esipe.fr.notification.entities.Notification;

public interface NotificationService {
    public String sendNotification(Notification notif);
}
