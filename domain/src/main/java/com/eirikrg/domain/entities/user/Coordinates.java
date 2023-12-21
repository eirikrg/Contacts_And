package com.eirikrg.domain.entities.user;

import java.io.Serializable;

public class Coordinates implements Serializable {

    private static final long serialVersionUID = 3076124741241539644L;
    private String latitude;
    private String longitude;

    public Coordinates(String latitude, String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

}
