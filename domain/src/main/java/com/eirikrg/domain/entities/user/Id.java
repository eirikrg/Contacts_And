package com.eirikrg.domain.entities.user;

import java.io.Serializable;

public class Id implements Serializable {

    private static final long serialVersionUID = -2920887763345844598L;
    private String name;
    private Object value;

    public Id(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

}
