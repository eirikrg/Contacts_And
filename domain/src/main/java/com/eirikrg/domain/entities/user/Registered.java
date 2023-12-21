package com.eirikrg.domain.entities.user;

import java.io.Serializable;

public class Registered implements Serializable {

    private static final long serialVersionUID = -6178077767286128465L;
    private String date;
    private Integer age;

    public Registered(String date, Integer age) {
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
