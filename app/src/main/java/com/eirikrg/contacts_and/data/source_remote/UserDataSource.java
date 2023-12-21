package com.eirikrg.contacts_and.data.source_remote;

import com.eirikrg.contacts_and.data.model.UsersApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserDataSource {

    @GET("/api/")
    Call<UsersApiResponse> getUsers(@Query("results") int results);

}
