package esipe.fr.customer.entities;



import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;

@Entity
@Table(name = "workflow")
public class Workflow {
    @Id
    private long id;
    private String name;
    private int customerId;
    public Workflow(){

    }

    public Workflow(long id, String name, int customerId) {
        this.id = id;
        this.name = name;
        this.customerId = customerId;
    }
}
