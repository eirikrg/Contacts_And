package com.eirikrg.contacts_and.di;

import android.content.Context;
import com.eirikrg.contacts_and.data.model.mappers.UsersMapper;
import com.eirikrg.contacts_and.data.repositories.ContactRepositoryImpl;
import com.eirikrg.contacts_and.data.repositories.UserRepositoryImpl;
import com.eirikrg.contacts_and.data.source_local.ContactDataSource;
import com.eirikrg.contacts_and.data.source_local.ContactDataSourceImpl;
import com.eirikrg.contacts_and.data.source_remote.UserDataSource;
import com.eirikrg.contacts_and.utils.Constants;
import com.eirikrg.domain.repositories.UserRepository;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@InstallIn(SingletonComponent.class)
@Module
public class AppModule {

  @Provides
  public static Retrofit provideRetrofit() {
    return new Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
  }

  // Data Source
  @Provides
  public static UserDataSource provideUserDataSource(Retrofit retrofit) {
    return retrofit.create(UserDataSource.class);
  }

  @Provides
  public static ContactDataSource provideContactDataSource(@ApplicationContext Context context) {
    return new ContactDataSourceImpl(context);
  }

  // Repositories
  @Provides
  public static UserRepository providesUserRepository(
      UserDataSource userDataSource, UsersMapper usersMapper) {
    return new UserRepositoryImpl(userDataSource, usersMapper);
  }

  @Provides
  public static ContactRepositoryImpl providesContactRepository(
      ContactDataSource contactDataSource) {
    return new ContactRepositoryImpl(contactDataSource);
  }

  // Mapper
  @Provides
  public static UsersMapper providesUsersMapper() {
    return new UsersMapper();
  }
}
