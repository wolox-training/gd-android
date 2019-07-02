package ar.com.wolox.android.example.network;

import java.util.List;

import ar.com.wolox.android.example.model.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Interface UserService
 */
public interface UserService {

    @GET("users/")
    Call<List<User>> getUserLogin(
            @Query("email") String email
    );

}
