package com.eirikrg.contacts_and.data.repositories;

import com.eirikrg.domain.entities.user.User;
import com.eirikrg.domain.repositories.ContactRepository;
import com.eirikrg.contacts_and.data.source_local.ContactDataSource;

import java.util.concurrent.CompletableFuture;

public class ContactRepositoryImpl implements ContactRepository {
    private final ContactDataSource contactDataSource;

    public ContactRepositoryImpl(ContactDataSource contactDataSource) {
        this.contactDataSource = contactDataSource;
    }

    @Override
    public CompletableFuture<Boolean> addContact(User user) {
        CompletableFuture<Boolean> result = new CompletableFuture<>();

        CompletableFuture.runAsync(() -> {
            try {
                result.complete(this.contactDataSource.addContact(user));
            } catch (Exception e) {
                e.printStackTrace();
                result.completeExceptionally(e);
            }
        });

        return result;
    }

}
