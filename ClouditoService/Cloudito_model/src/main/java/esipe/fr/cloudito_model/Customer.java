package esipe.fr.cloudito_model;

import io.swagger.annotations.ApiModel;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@ApiModel(description = "esipe.fr.model.Customer")
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    //@Convert(converter = AttributeConverter.class)
    private String name;
    //@Convert(converter = AttributeConverter.class)
    private String pwd;
    //@Convert(converter = AttributeConverter.class)
    private String login;
    //@Convert(converter = AttributeConverter.class)
    private String sKey;
    //@Convert(converter = AttributeConverter.class)
    private UUID uuid;
    //@Convert(converter = AttributeConverter.class)
    private Date time;

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

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getsKey() {
        return sKey;
    }

    public void setsKey(String sKey) {
        this.sKey = sKey;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
