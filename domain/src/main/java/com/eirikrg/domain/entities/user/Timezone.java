package com.eirikrg.domain.entities.user;

import java.io.Serializable;

public class Timezone implements Serializable {

    private static final long serialVersionUID = -5045964629889980590L;
    private String offset;
    private String description;

    public Timezone(String offset, String description) {
        this.offset = offset;
        this.description = description;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
