package esipe.fr.course.entities;

public class Store {
    private String name;
    private String Desc;

    public Store(String name, String desc) {
        this.name = name;
        Desc = desc;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return Desc;
    }
}
