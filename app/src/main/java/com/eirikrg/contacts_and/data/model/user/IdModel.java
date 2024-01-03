package com.eirikrg.contacts_and.data.model.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IdModel {

  @SerializedName("name")
  @Expose
  private String name;

  @SerializedName("value")
  @Expose
  private String value;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }
}
