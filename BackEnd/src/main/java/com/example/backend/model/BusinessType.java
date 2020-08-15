package com.example.backend.model;

import com.sun.xml.internal.ws.api.model.wsdl.editable.EditableWSDLBoundFault;
import javax.persistence.*;
import com.example.backend.model.EBusinessType;

@Entity
public class BusinessType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private EBusinessType name;

    public BusinessType(){}

    public BusinessType(EBusinessType name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EBusinessType getName() {
        return name;
    }

    public void setName(EBusinessType name) {
        this.name = name;
    }
}
