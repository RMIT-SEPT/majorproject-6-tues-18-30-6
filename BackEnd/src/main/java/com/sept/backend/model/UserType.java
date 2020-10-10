package com.sept.backend.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public enum UserType {
    customer,
    admin,
    worker;

    private String id;

    public void setId(String id) {
        this.id = id;
    }

    @Id
    public String getId() {
        return id;
    }
}
