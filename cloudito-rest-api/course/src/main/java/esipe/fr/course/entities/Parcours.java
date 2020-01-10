package esipe.fr.course.entities;


import java.util.ArrayList;
import java.util.UUID;

public class Parcours {
    private UUID id;
    private ArrayList<Noeuds> liste;
    public Parcours(){
        this.id = UUID.randomUUID();
        this.liste = new ArrayList<>();
        this.liste.add(new Noeuds(UUID.randomUUID(),1,"Magasin",new PoI("GoSport","madgasin de sport",null)));
        this.liste.add(new Noeuds(UUID.randomUUID(),2,"Magasin",new PoI("Fnac","madgasin de tech",null)));
        this.liste.add(new Noeuds(UUID.randomUUID(),3,"Magasin",new PoI("pylone","madgasin de tou&R",null)));
        this.liste.add(new Noeuds(UUID.randomUUID(),4,"Magasin",new PoI("GoSport","encore Gosport",null)));
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public ArrayList<Noeuds> getListe() {
        return liste;
    }

    public void setListe(ArrayList<Noeuds> liste) {
        this.liste = liste;
    }
}

