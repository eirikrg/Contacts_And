package com.eirikrg.contacts_and.data.source_local;

import com.eirikrg.domain.entities.user.User;

public interface ContactDataSource {

    boolean addContact(User user);

}
