package com.sept.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


public class BusinessRegisterRequest {

    @NotBlank
    @Size(min = 3, max = 20)
    private String Name;

    @NotBlank
    @Size(max = 11)
    private int abn;

    String type;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public int getAbn() {
        return abn;
    }

    public void setAbn(int abn) {
        this.abn = abn;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
