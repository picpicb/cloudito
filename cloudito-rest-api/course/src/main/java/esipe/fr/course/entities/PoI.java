package esipe.fr.course.entities;

public class PoI {
    private String name;
    private String description;
    private Coordonee emplacement;

    public PoI(String name, String description, Coordonee emplacement) {
        this.name = name;
        this.description = description;
        this.emplacement = emplacement;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Coordonee getEmplacement() {
        return emplacement;
    }

    public void setEmplacement(Coordonee emplacement) {
        this.emplacement = emplacement;
    }
}
