package com.eirikrg.domain.repositories;

import com.eirikrg.domain.entities.api.ApiResponse;
import com.eirikrg.domain.entities.user.User;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface UserRepository {

    CompletableFuture<ApiResponse<List<User>>> getUsers(int results);

}
