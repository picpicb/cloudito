package esipe.fr.scope3.entities;

import java.util.UUID;

public class Noeuds {
    private UUID id;
    private Integer order;
    private String type;
    private Store store;
    private Borne borne;

    public Noeuds(UUID id,Integer order, String type, Store store, Borne borne) {
        this.id = id;
        this.order = order;
        this.type = type;
        this.store = store;
        this.borne = borne;
    }

    public UUID getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public Store getStore() {
        return store;
    }

    public Borne getBorne() {
        return borne;
    }

    public Integer getOrder() {
        return order;
    }
}
