package com.ackincolor.cloudito.entities;

public class Shop {

    private String name;
    private Integer id;
    private String ouverture;

    public Shop(String name, Integer id,String ouverture) {
        this.name = name;
        this.id = id;
        this.ouverture = ouverture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOuverture() {
        return ouverture;
    }

    public void setOuverture(String ouverture) {
        this.ouverture = ouverture;
    }

    public String toString(){
        return this.name+ " : " +this.ouverture;
    }
}
