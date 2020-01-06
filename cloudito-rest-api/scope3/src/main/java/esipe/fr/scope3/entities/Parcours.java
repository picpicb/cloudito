package esipe.fr.scope3.entities;


import java.util.ArrayList;
import java.util.UUID;

public class Parcours {
    private UUID id;
    private ArrayList<Noeuds> liste;
    public Parcours(){
        this.id = UUID.randomUUID();
        this.liste = new ArrayList<>();
        this.liste.add(new Noeuds(UUID.randomUUID(),1,"Magasin",new Store("GoSport","madgasin de sport"),null));
        this.liste.add(new Noeuds(UUID.randomUUID(),2,"Magasin",new Store("Fnac","madgasin de tech"),null));
        this.liste.add(new Noeuds(UUID.randomUUID(),3,"Magasin",new Store("pylone","madgasin de tou&R"),null));
        this.liste.add(new Noeuds(UUID.randomUUID(),4,"Magasin",new Store("GoSport","encore Gosport"),null));
    }

    public Parcours getParcours() {
        return new Parcours();
    }
}

