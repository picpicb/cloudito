package esipe.fr.cloudito_notification.services;

import esipe.fr.cloudito_notification.entities.Notification;

public interface NotificationService {
    public String sendNotification(Notification notif);
}
