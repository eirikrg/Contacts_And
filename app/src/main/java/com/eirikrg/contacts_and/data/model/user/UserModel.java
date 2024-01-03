package com.eirikrg.contacts_and.data.model.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class UserModel implements Serializable {

  private static final long serialVersionUID = -5644762896821011956L;

  @SerializedName("gender")
  @Expose
  private String gender;

  @SerializedName("name")
  @Expose
  private NameModel name;

  @SerializedName("location")
  @Expose
  private LocationModel location;

  @SerializedName("email")
  @Expose
  private String email;

  @SerializedName("login")
  @Expose
  private LoginModel login;

  @SerializedName("dob")
  @Expose
  private DobModel dob;

  @SerializedName("registered")
  @Expose
  private RegisteredModel registered;

  @SerializedName("phone")
  @Expose
  private String phone;

  @SerializedName("cell")
  @Expose
  private String cell;

  @SerializedName("id")
  @Expose
  private IdModel id;

  @SerializedName("picture")
  @Expose
  private PictureModel picture;

  @SerializedName("nat")
  @Expose
  private String nat;

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public NameModel getName() {
    return name;
  }

  public void setName(NameModel name) {
    this.name = name;
  }

  public LocationModel getLocation() {
    return location;
  }

  public void setLocation(LocationModel location) {
    this.location = location;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public LoginModel getLogin() {
    return login;
  }

  public void setLogin(LoginModel login) {
    this.login = login;
  }

  public DobModel getDob() {
    return dob;
  }

  public void setDob(DobModel dob) {
    this.dob = dob;
  }

  public RegisteredModel getRegistered() {
    return registered;
  }

  public void setRegistered(RegisteredModel registered) {
    this.registered = registered;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getCell() {
    return cell;
  }

  public void setCell(String cell) {
    this.cell = cell;
  }

  public IdModel getId() {
    return id;
  }

  public void setId(IdModel id) {
    this.id = id;
  }

  public PictureModel getPicture() {
    return picture;
  }

  public void setPicture(PictureModel picture) {
    this.picture = picture;
  }

  public String getNat() {
    return nat;
  }

  public void setNat(String nat) {
    this.nat = nat;
  }
}
