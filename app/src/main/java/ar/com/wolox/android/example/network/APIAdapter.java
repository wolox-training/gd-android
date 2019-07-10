package ar.com.wolox.android.example.network;

import java.util.List;

import javax.inject.Inject;

import ar.com.wolox.android.example.model.APIClient;
import ar.com.wolox.android.example.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/**
 * Adapter class for API
 */
public class APIAdapter {
    private UserService userService2;

    @Inject
    public APIAdapter() {
        userService2 = APIClient.getRetrofitClient().create(UserService.class);
    }

    /**
     * @param mail - Mail entered by user
     * @param password - Password entered by user
     * @param listener - Invoked on login complete
     */
    public void getUserById(String mail, String password, OnLoginListener listener) {
        UserService userService = APIClient.getRetrofitClient().create(UserService.class);
        Call<List<User>> call = userService.getUserByMail(mail, password);
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.body() != null) {
                    if (response.body().size() != 0) {
                        listener.onLoginSuccess();
                    } else {
                        listener.onLoginUserNotFound();
                    }
                }

            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                listener.onLoginFail();
            }
        });
    }
}