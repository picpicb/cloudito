package esipe.fr.cloudito_model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@ApiModel(description = "esipe.fr.cloudito_model.Location")
@Entity
public class Location {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    private double x;
    private double y;
    private int floor;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }
}
