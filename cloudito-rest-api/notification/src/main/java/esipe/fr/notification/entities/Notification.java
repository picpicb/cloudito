package esipe.fr.notification.entities;

public class Notification {
    public Notif data;
    public String to;
    public Notification(String title, String content, String imageUrl, String gameUrl,String to){
        this.data = new Notif(title,content,imageUrl,gameUrl);
        this.to = to;
    }
    public Notification(){}
    public class Notif {
        public String title;
        public String content;
        public String imageUrl;
        public String gameUrl;

        public Notif(String title, String content, String imageUrl, String gameUrl) {
            this.title = title;
            this.content = content;
            this.imageUrl = imageUrl;
            this.gameUrl = gameUrl;
        }
        public Notif() {}
    }
}

