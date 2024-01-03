package com.eirikrg.contacts_and.data.model.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LocationModel {

  @SerializedName("street")
  @Expose
  private StreetModel street;

  @SerializedName("city")
  @Expose
  private String city;

  @SerializedName("state")
  @Expose
  private String state;

  @SerializedName("country")
  @Expose
  private String country;

  @SerializedName("postcode")
  @Expose
  private String postcode;

  @SerializedName("coordinates")
  @Expose
  private CoordinatesModel coordinates;

  @SerializedName("timezone")
  @Expose
  private TimezoneModel timezone;

  public StreetModel getStreet() {
    return street;
  }

  public void setStreet(StreetModel street) {
    this.street = street;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getPostcode() {
    return postcode;
  }

  public void setPostcode(String postcode) {
    this.postcode = postcode;
  }

  public CoordinatesModel getCoordinates() {
    return coordinates;
  }

  public void setCoordinates(CoordinatesModel coordinates) {
    this.coordinates = coordinates;
  }

  public TimezoneModel getTimezone() {
    return timezone;
  }

  public void setTimezone(TimezoneModel timezone) {
    this.timezone = timezone;
  }
}
