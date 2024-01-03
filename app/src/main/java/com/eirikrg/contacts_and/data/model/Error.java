package com.eirikrg.contacts_and.data.model;

import com.google.gson.annotations.SerializedName;

class Error {
  @SerializedName("error")
  private String error;

  public String getError() {
    return error;
  }
}
