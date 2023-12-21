package com.eirikrg.domain.entities.user;

import java.io.Serializable;

public class Street implements Serializable {

    private static final long serialVersionUID = 3035492341504221789L;
    private Integer number;
    private String name;

    public Street(Integer number, String name) {
        this.number = number;
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
