package com.eirikrg.domain.entities.user;

import java.io.Serializable;

public class Name implements Serializable {

    private static final long serialVersionUID = -4701606719403673362L;
    private String title;
    private String first;
    private String last;

    public Name(String title, String first, String last) {
        this.title = title;
        this.first = first;
        this.last = last;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getFullName() {
        return String.format("%s %s", first, last);
    }

    public String getTitledName() {
        return String.format("%s %s", title != null ? title : "", getFullName());
    }
}
