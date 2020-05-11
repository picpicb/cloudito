package esipe.fr.cloudito_encryption.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Coordinate {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private long id;
    private int x;
    private int y;

    @Convert(converter = AttributeEncryptor.class)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinate(long id, int x, int y, String name) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.name = name;
    }

    public Coordinate() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "id=" + id +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}