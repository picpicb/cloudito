package com.ackincolor.cloudito.entities;

import java.util.ArrayList;
import java.util.UUID;

public class Node {
    private Long id;
    private Location location;

    public Node(Long id, Location location) {
        this.id = id;
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Location getLocation() {
        return this.location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
