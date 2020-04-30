package esipe.fr.cloudito_notification.entities;

public class Notification {
    public String title, content, target;

    public Notification(String target, String title, String content){
       this.target = target;
       this.title = title;
       this.content = content;
    }

    public String getTitle(){ return this.title;}

    public String getContent(){ return this.content;}

    public String getTarget(){ return this.target;}

}

