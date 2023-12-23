package com.eirikrg.contacts_and.data.repositories;

import com.eirikrg.contacts_and.data.model.mappers.Mapper;
import com.eirikrg.domain.entities.api.ApiResponse;
import com.google.gson.Gson;

import java.util.concurrent.CompletableFuture;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Case base encargada de la ejecución asincrona de las peticiones REST
 *
 * @param <T> Modelo esperado de la llamada al api
 * @param <A> La entidad a la que hay que mapear
 * @param <M> Mapper de la entidad
 */
public abstract class BaseRepository<T, A, M extends Mapper<T, A>> {
    protected CompletableFuture<ApiResponse<A>> apiCall(Call<T> call, M mapper) {
        CompletableFuture<ApiResponse<A>> result = new CompletableFuture<>();

        CompletableFuture.runAsync(() -> {
            try {
                Response<T> response = call.execute();
                result.complete(mapResponse(response, mapper));
            } catch (Exception e) {
                e.printStackTrace();
                result.completeExceptionally(e);
            }
        });

        return result;
    }

    private ApiResponse<A> mapResponse(Response<T> retrofitResponse, M mapper) {
        if (retrofitResponse != null && retrofitResponse.isSuccessful()) {
            return handleSuccessResponse(retrofitResponse, mapper);
        } else {
            return handleErrorResponse(retrofitResponse);
        }
    }

    private ApiResponse<A> handleSuccessResponse(Response<T> retrofitResponse, M mapper) {
        A mappedEntity = null;
        if (retrofitResponse.body() != null) {
            mappedEntity = mapper.mapToDomain(retrofitResponse.body());
        }

        return new ApiResponse.Success(mappedEntity);
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
