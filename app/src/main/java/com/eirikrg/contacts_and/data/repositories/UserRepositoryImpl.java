package com.eirikrg.contacts_and.data.repositories;

import com.eirikrg.contacts_and.data.model.UsersApiResponse;
import com.eirikrg.contacts_and.data.model.mappers.UsersMapper;
import com.eirikrg.contacts_and.data.source_remote.UserDataSource;
import com.eirikrg.domain.entities.api.ApiResponse;
import com.eirikrg.domain.entities.user.User;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.inject.Inject;

import retrofit2.Call;

public class UserRepositoryImpl extends BaseRepository<UsersApiResponse, List<User>, UsersMapper> implements com.eirikrg.domain.repositories.UserRepository {
    private final UserDataSource userDataSource;
    private final UsersMapper usersMapper;

    @Inject
    public UserRepositoryImpl(UserDataSource userDataSource, UsersMapper usersMapper) {
        this.userDataSource = userDataSource;
        this.usersMapper = usersMapper;
    }

    @Override
    public CompletableFuture<ApiResponse<List<User>>> getUsers(int results) {
        Call<UsersApiResponse> call = this.userDataSource.getUsers(results);
        return apiCall(call, this.usersMapper);
    }

}
