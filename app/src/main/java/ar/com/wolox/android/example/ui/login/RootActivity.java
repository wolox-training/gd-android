package ar.com.wolox.android.example.ui.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import androidx.annotation.Nullable;

import java.util.List;

import javax.inject.Inject;

import ar.com.wolox.android.R;
import ar.com.wolox.android.example.model.User;
import ar.com.wolox.android.example.network.UserService;
import ar.com.wolox.wolmo.core.activity.WolmoActivity;
import ar.com.wolox.wolmo.networking.retrofit.RetrofitServices;
import ar.com.wolox.wolmo.networking.retrofit.callback.NetworkCallback;
import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * RootActivity extends WolmoActivity
 */
public class RootActivity extends WolmoActivity {

    @Inject
    RetrofitServices mRetrofiServices;

    SharedPreferences sharedPref;
    String email, password, defaultValue;
    Intent intent;

    @Override
    protected int layout() {
        return R.layout.activity_root;
    }

    @Override
    protected void init() {
        sharedPref = this.getSharedPreferences(
                getString(R.string.preferences_name), Context.MODE_PRIVATE);

        intent = new Intent(this, HomeActivity.class);

        defaultValue = getString(R.string.prefs_default_value);
        email = sharedPref.getString(getString(R.string.login_email), defaultValue);
        password = sharedPref.getString(getString(R.string.login_pass), defaultValue);

        if (email.isEmpty() || password.isEmpty()) {
            replaceFragment(R.id.vActivityBaseContent, new LoginFragment());
        } else {
            connectUser();
        }

    }

    /**
     * connectUser()
     */
    public void connectUser() {
        Call<List<User>> call = mRetrofiServices.getService(UserService.class).getUserLogin(email);

        call.enqueue(new NetworkCallback<List<User>>() {

            @Override
            public void onResponseSuccessful(@Nullable List<User> response) {

                if ((response.isEmpty()) || (!response.get(0).getPassword().equals(password))) {
                    replaceFragment(R.id.vActivityBaseContent, new LoginFragment());
                } else {
                    startActivity(intent);
                }
            }

            @Override
            public void onResponseFailed(@Nullable ResponseBody responseBody, int code) {
                replaceFragment(R.id.vActivityBaseContent, new LoginFragment());
            }

            @Override
            public void onCallFailure(Throwable t) {
                replaceFragment(R.id.vActivityBaseContent, new LoginFragment());
            }
        });
    }

}
