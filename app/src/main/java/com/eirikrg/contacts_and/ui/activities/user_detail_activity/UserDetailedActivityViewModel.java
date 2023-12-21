package com.eirikrg.contacts_and.ui.activities.user_detail_activity;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.eirikrg.domain.entities.user.User;
import com.eirikrg.contacts_and.data.repositories.ContactRepositoryImpl;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class UserDetailedActivityViewModel extends ViewModel {
    private final ContactRepositoryImpl contactRepository;

    private final MutableLiveData<Boolean> contactSaved;

    @Inject
    public UserDetailedActivityViewModel(ContactRepositoryImpl contactRepository) {
        this.contactRepository = contactRepository;
        this.contactSaved = new MutableLiveData<>();
    }

    public void addContact(User user) {
        contactRepository.addContact(user).thenAccept(contactSaved::postValue);
    }

    public MutableLiveData<Boolean> getContactSaved() {
        return this.contactSaved;
    }
}
