package com.eirikrg.contacts_and.data.repositories;

import com.eirikrg.domain.entities.api.ApiResponse;
import com.eirikrg.domain.entities.user.User;
import com.eirikrg.contacts_and.data.model.UsersApiResponse;
import com.eirikrg.contacts_and.data.model.mappers.UsersMapper;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Case base encargada de la ejecución asincrona de las peticiones REST
 *
 * @param <T> Modelo que se espera para hacer la llamada
 * @param <A> Entidad mapeada esperada de respuesta
 */
public abstract class BaseRepository<T, A> {
    protected CompletableFuture<ApiResponse<A>> apiCall(Call<T> call) {
        CompletableFuture<ApiResponse<A>> result = new CompletableFuture<>();

        CompletableFuture.runAsync(() -> {
            try {
                Response<T> response = call.execute();
                result.complete(mapResponse(response));
            } catch (Exception e) {
                e.printStackTrace();
                result.completeExceptionally(e);
            }
        });

        return result;
    }

    private ApiResponse<A> mapResponse(Response<T> retrofitResponse) {
        if (retrofitResponse != null && retrofitResponse.isSuccessful()) {
            return handleSuccessResponse(retrofitResponse);
        } else {
            return handleErrorResponse(retrofitResponse);
        }
    }

    private ApiResponse<A> handleSuccessResponse(Response<T> retrofitResponse) {
        UsersMapper usersMapper = new UsersMapper();

        UsersApiResponse userModelList = (UsersApiResponse) retrofitResponse.body();

        List<User> userList = new ArrayList<>();
        if (userModelList != null) {
            userList = usersMapper.mapToDomain(userModelList);
        }

        return new ApiResponse.Success(userList);
    }

    private ApiResponse<A> handleErrorResponse(Response<T> retrofitResponse) {
        if (retrofitResponse != null) {
            Error error = extractErrorFromResponse(retrofitResponse);
            return new ApiResponse.Error(retrofitResponse.code(), error != null ? error.getMessage() : "");
        } else {
            return new ApiResponse.Error(0, "");
        }
    }

    private Error extractErrorFromResponse(Response<T> retrofitResponse) {
        if (retrofitResponse.errorBody() != null) {
            return new Gson().fromJson(retrofitResponse.errorBody().toString(), Error.class);
        }
        return null;
    }

}
