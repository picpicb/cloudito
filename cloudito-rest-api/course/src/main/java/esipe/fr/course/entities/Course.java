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
        this.liste.add(new Node(UUID.randomUUID(),1,"Magasin",new PoI("GoSport","madgasin de sport",null)));
        this.liste.add(new Node(UUID.randomUUID(),2,"Magasin",new PoI("Fnac","madgasin de tech",null)));
        this.liste.add(new Node(UUID.randomUUID(),3,"Magasin",new PoI("pylone","madgasin de tou&R",null)));
        this.liste.add(new Node(UUID.randomUUID(),4,"Magasin",new PoI("GoSport","encore Gosport",null)));
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

