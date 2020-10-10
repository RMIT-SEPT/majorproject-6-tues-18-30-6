package com.sept.backend.model;

import javax.persistence.*;

@Entity
public class Business {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String name;
    private String Abn;
    @Enumerated(EnumType.STRING)
    private Category category;

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbn() {
        return Abn;
    }

    public void setAbn(String Abn) {
        this.Abn = Abn;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getId() {
        return id;
    }
}
