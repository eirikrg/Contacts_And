package com.eirikrg.domain.repositories;

import com.eirikrg.domain.entities.user.User;

import java.util.concurrent.CompletableFuture;

public interface ContactRepository {

    CompletableFuture<Boolean> addContact(User user);

}
