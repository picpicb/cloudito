package esipe.fr.course.entities;


import java.util.ArrayList;
import java.util.UUID;

public class Course {
    private UUID id;
    private ArrayList<Node> liste;
    private String status;
    public Course(){
        this.id = UUID.randomUUID();
        this.liste = new ArrayList<>();
        this.status = "running";
    }
    public static Course build(){
        Course c = new Course();
        c.liste.add(new Node(UUID.randomUUID(),1,"Magasin",new PoI("GoSport","madgasin de sport",null)));
        c.liste.add(new Node(UUID.randomUUID(),2,"Magasin",new PoI("Fnac","madgasin de tech",null)));
        c.liste.add(new Node(UUID.randomUUID(),3,"Magasin",new PoI("pylone","madgasin de tou&R",null)));
        c.liste.add(new Node(UUID.randomUUID(),4,"Magasin",new PoI("GoSport","encore Gosport",null)));
        return c;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public ArrayList<Node> getListe() {
        return liste;
    }

    public void setListe(ArrayList<Node> liste) {
        this.liste = liste;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

