package esipe.fr.scope3.entities;


import java.util.ArrayList;
import java.util.UUID;

public class Parcours {
    private ArrayList<Noeuds> parcours;
    public Parcours(){
        this.parcours = new ArrayList<>();
        this.parcours.add(new Noeuds(UUID.randomUUID(),1,"Magasin",new Store("GoSport","madgasin de sport"),null));
        this.parcours.add(new Noeuds(UUID.randomUUID(),2,"Magasin",new Store("Fnac","madgasin de tech"),null));
        this.parcours.add(new Noeuds(UUID.randomUUID(),3,"Magasin",new Store("pylone","madgasin de tou&R"),null));
        this.parcours.add(new Noeuds(UUID.randomUUID(),4,"Magasin",new Store("GoSport","encore Gosport"),null));
    }

    public ArrayList<Noeuds> getParcours() {
        return parcours;
    }
}

