package com.eirikrg.contacts_and.ui.activities.users_activity;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.eirikrg.domain.entities.api.ApiResponse;
import com.eirikrg.domain.entities.user.User;
import com.eirikrg.domain.repositories.UserRepository;
import dagger.hilt.android.lifecycle.HiltViewModel;
import java.util.List;
import javax.inject.Inject;

@HiltViewModel
public class UsersActivityViewModel extends ViewModel {
  private final UserRepository userRepository;

  private final MutableLiveData<ApiResponse<List<User>>> users;

  @Inject
  public UsersActivityViewModel(UserRepository userRepository) {
    this.userRepository = userRepository;
    this.users = new MutableLiveData<>();
  }

  public void fetchUsers() {
    this.users.postValue(new ApiResponse.Loading());
    this.userRepository.getUsers(100).thenAccept(this.users::postValue);
  }

  public MutableLiveData<ApiResponse<List<User>>> getUsers() {
    return this.users;
  }
}
