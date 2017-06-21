package com.savitha.practise.model;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by savithakumarasamy on 6/18/17.
 */

public class Buyer {

    int ID;

    @NotEmpty
    String name;

    Address address;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Buyer withAddress(Address address) {
        this.address = address;
        return this;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Buyer withID(int ID) {
        this.ID = ID;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Buyer withName(String name) {
        this.name = name;
        return this;
    }


}
