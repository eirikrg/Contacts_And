package com.eirikrg.contacts_and.data.model;

import com.eirikrg.contacts_and.data.model.user.UserModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UsersApiResponse {

  @SerializedName("results")
  @Expose
  private List<UserModel> results;

  @SerializedName("info")
  @Expose
  private Info info;

  public List<UserModel> getResults() {
    return results;
  }

  public void setResults(List<UserModel> results) {
    this.results = results;
  }

  public Info getInfo() {
    return info;
  }

  public void setInfo(Info info) {
    this.info = info;
  }
}
