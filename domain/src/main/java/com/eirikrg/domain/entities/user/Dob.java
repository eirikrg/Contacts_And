package com.eirikrg.domain.entities.user;

import java.io.Serializable;

public class Dob implements Serializable {

    private static final long serialVersionUID = -4839428741741439736L;
    private String date;
    private Integer age;

    public Dob(String date, Integer age) {
        this.date = date;
        this.age = age;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

}
