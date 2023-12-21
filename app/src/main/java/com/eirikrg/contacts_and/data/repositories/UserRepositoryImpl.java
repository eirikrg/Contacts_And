package com.eirikrg.contacts_and.data.repositories;

import com.eirikrg.domain.entities.api.ApiResponse;
import com.eirikrg.domain.entities.user.User;
import com.eirikrg.contacts_and.data.model.UsersApiResponse;
import com.eirikrg.contacts_and.data.source_remote.UserDataSource;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import retrofit2.Call;

public class UserRepositoryImpl extends BaseRepository<UsersApiResponse, List<User>> implements com.eirikrg.domain.repositories.UserRepository {
    private final UserDataSource userDataSource;

    public UserRepositoryImpl(UserDataSource userDataSource) {
        this.userDataSource = userDataSource;
    }

    @Override
    public CompletableFuture<ApiResponse<List<User>>> getUsers(int results) {
        Call<UsersApiResponse> call = this.userDataSource.getUsers(results);
        return apiCall(call);
    }

}
