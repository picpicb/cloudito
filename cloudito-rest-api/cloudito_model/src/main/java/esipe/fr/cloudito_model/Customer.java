package esipe.fr.cloudito_model;

import io.swagger.annotations.ApiModel;

import javax.persistence.*;
import java.util.List;

@ApiModel(description = "esipe.fr.cloudito_model.Customer")
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(
            mappedBy = "customer",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<CustomerLocation> locations;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CustomerLocation> getLocations() {
        return locations;
    }

    public void setLocations(List<CustomerLocation> locations) {
        this.locations = locations;
    }
}
