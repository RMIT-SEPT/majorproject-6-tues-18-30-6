package com.sept.backend.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public enum Role {
    CUSTOMER,
    ADMIN,
    WORKER;

    private String id;

    public void setId(String id) {
        this.id = id;
    }

    @Id
    public String getId() {
        return id;
    }
}
