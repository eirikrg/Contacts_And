package com.eirikrg.contacts_and.data.model.mappers;


import com.eirikrg.domain.entities.user.Coordinates;
import com.eirikrg.domain.entities.user.Dob;
import com.eirikrg.domain.entities.user.Id;
import com.eirikrg.domain.entities.user.Location;
import com.eirikrg.domain.entities.user.Login;
import com.eirikrg.domain.entities.user.Name;
import com.eirikrg.domain.entities.user.Picture;
import com.eirikrg.domain.entities.user.Registered;
import com.eirikrg.domain.entities.user.Street;
import com.eirikrg.domain.entities.user.Timezone;
import com.eirikrg.domain.entities.user.User;
import com.eirikrg.contacts_and.data.model.UsersApiResponse;
import com.eirikrg.contacts_and.data.model.user.CoordinatesModel;
import com.eirikrg.contacts_and.data.model.user.DobModel;
import com.eirikrg.contacts_and.data.model.user.IdModel;
import com.eirikrg.contacts_and.data.model.user.LocationModel;
import com.eirikrg.contacts_and.data.model.user.LoginModel;
import com.eirikrg.contacts_and.data.model.user.NameModel;
import com.eirikrg.contacts_and.data.model.user.PictureModel;
import com.eirikrg.contacts_and.data.model.user.RegisteredModel;
import com.eirikrg.contacts_and.data.model.user.StreetModel;
import com.eirikrg.contacts_and.data.model.user.TimezoneModel;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper de UserModel a User de la capa de dominio
 */
public class UsersMapper {

    public List<User> mapToDomain(UsersApiResponse usersApiResponse) {
        return usersApiResponse.getResults().stream().map(apiUserModel -> new User(
                apiUserModel.getGender(),
                mapApiName(apiUserModel.getName()),
                mapApiLocation(apiUserModel.getLocation()),
                apiUserModel.getEmail(),
                mapApiLogin(apiUserModel.getLogin()),
                mapApiDob(apiUserModel.getDob()),
                mapApiRegistered(apiUserModel.getRegistered()),
                apiUserModel.getPhone(),
                apiUserModel.getCell(),
                mapApiId(apiUserModel.getId()),
                mapApiPicture(apiUserModel.getPicture()),
                apiUserModel.getNat()
        )).collect(Collectors.toList());
    }

    private Name mapApiName(NameModel apiName) {
        return new Name(
                apiName.getTitle(),
                apiName.getFirst(),
                apiName.getLast()
        );
    }

    private Location mapApiLocation(LocationModel apiLocation) {
        return new Location(
                mapApiStreet(apiLocation.getStreet()),
                apiLocation.getCity(),
                apiLocation.getState(),
                apiLocation.getCountry(),
                apiLocation.getPostcode(),
                mapApiCoordinates(apiLocation.getCoordinates()),
                mapApiTimezone(apiLocation.getTimezone())
        );
    }

    private Street mapApiStreet(StreetModel apiStreet) {
        return new Street(
                apiStreet.getNumber(),
                apiStreet.getName()
        );
    }

    private Coordinates mapApiCoordinates(CoordinatesModel apiCoordinates) {
        return new Coordinates(
                apiCoordinates.getLatitude(),
                apiCoordinates.getLongitude()
        );
    }

    private Timezone mapApiTimezone(TimezoneModel apiTimezone) {
        return new Timezone(
                apiTimezone.getDescription(),
                apiTimezone.getDescription()
        );
    }

    private Login mapApiLogin(LoginModel apiLogin) {
        return new Login(
                apiLogin.getUuid(),
                apiLogin.getUsername(),
                apiLogin.getPassword(),
                apiLogin.getSalt(),
                apiLogin.getMd5(),
                apiLogin.getSha1(),
                apiLogin.getSha256()
        );
    }

    private Dob mapApiDob(DobModel apiDob) {
        return new Dob(
                apiDob.getDate(),
                apiDob.getAge()
        );
    }

    private Registered mapApiRegistered(RegisteredModel apiRegistered) {
        return new Registered(
                apiRegistered.getDate(),
                apiRegistered.getAge()
        );
    }

    private Id mapApiId(IdModel apiId) {
        return new Id(
                apiId.getName(),
                apiId.getValue()
        );
    }

    private Picture mapApiPicture(PictureModel apiPicture) {
        return new Picture(
                apiPicture.getLarge(),
                apiPicture.getMedium(),
                apiPicture.getThumbnail()
        );
    }

}
