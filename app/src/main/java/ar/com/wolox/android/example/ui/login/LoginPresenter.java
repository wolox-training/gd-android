package ar.com.wolox.android.example.ui.login;

import androidx.annotation.Nullable;

import java.util.List;

import ar.com.wolox.android.R;
import ar.com.wolox.android.example.model.User;
import ar.com.wolox.android.example.network.RetrofitInstance;
import ar.com.wolox.android.example.network.UserService;
import ar.com.wolox.wolmo.core.presenter.BasePresenter;
import ar.com.wolox.wolmo.networking.retrofit.callback.NetworkCallback;
import okhttp3.ResponseBody;
import retrofit2.Call;

import javax.inject.Inject;

/**
 * A simple {@link BasePresenter} subclass.
 */
public class LoginPresenter extends BasePresenter<ILoginView> {

    @Inject
    public LoginPresenter() {

    }

    /**
     *
     * @param email EMail
     * @param password Pass
     */
    public void onLoginButtonClicked(String email, String password) {

        UserService usuarioService = RetrofitInstance.getRetrofitInstance().create(UserService.class);
        Call<List<User>> call = usuarioService.getUserLogin(email);

        call.enqueue(new NetworkCallback<List<User>>() {

            @Override
            public void onResponseSuccessful(@Nullable List<User> response) {

                if ((response.isEmpty()) || (!response.get(0).getPassword().equals(password))) {
                    getView().showLoginFailure(R.string.login_error_credentials);
                } else {
                    getView().showLoginSuccess();
                }
            }

            @Override
            public void onResponseFailed(@Nullable ResponseBody responseBody, int code) {
                getView().showLoginFailure(R.string.login_error_credentials);
            }

            @Override
            public void onCallFailure(Throwable t) {
                getView().showLoginFailure(R.string.login_error_service);
            }
        });

    } // public void onLoginButtonClicked(String email, String password)

}
