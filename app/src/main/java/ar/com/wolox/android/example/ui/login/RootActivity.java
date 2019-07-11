package ar.com.wolox.android.example.ui.login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.List;

import javax.inject.Inject;

import ar.com.wolox.android.R;
import ar.com.wolox.android.example.model.User;
import ar.com.wolox.android.example.network.UserService;
import ar.com.wolox.android.example.ui.example.ExampleActivity;
import ar.com.wolox.android.example.ui.home.HomeActivity;
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
    Intent intentHome, intentBase;
    ProgressDialog dialog;

    @Override
    protected int layout() {
        return R.layout.activity_base;
    }

    @Override
    protected void init() {
        sharedPref = this.getSharedPreferences(
                getString(R.string.preferences_name), Context.MODE_PRIVATE);

        defaultValue = getString(R.string.prefs_default_value);
        email = sharedPref.getString(getString(R.string.login_email), defaultValue);
        password = sharedPref.getString(getString(R.string.login_pass), defaultValue);

        intentBase = new Intent(this, ExampleActivity.class);
        intentBase.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        intentHome = new Intent(this, HomeActivity.class);
        intentHome.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        if (email.isEmpty() || password.isEmpty()) {
            startActivity(intentBase);
        } else {
            if (isNetworkAvaliable(getApplicationContext())) {
                connectUser();
            } else {
                Toast.makeText(this, getText(R.string.error_internet_connection), Toast.LENGTH_SHORT).show();
            }

        }

    }

    /**
     * connectUser()
     */
    public void connectUser() {
        Call<List<User>> call = mRetrofiServices.getService(UserService.class).getUserLogin(email);
        dialog = ProgressDialog.show(this, getText(R.string.login_dialog_title), getText(R.string.login_dialog_message), true);

        call.enqueue(new NetworkCallback<List<User>>() {

            @Override
            public void onResponseSuccessful(@Nullable List<User> response) {

                if ((response.isEmpty()) || (!response.get(0).getPassword().equals(password))) {
                    dialog.dismiss();
                    startActivity(intentBase);
                } else {
                    dialog.dismiss();
                    startActivity(intentHome);
                }
            }

            @Override
            public void onResponseFailed(@Nullable ResponseBody responseBody, int code) {
                dialog.dismiss();
                startActivity(intentBase);
            }

            @Override
            public void onCallFailure(Throwable t) {
                dialog.dismiss();
                startActivity(intentBase);
            }
        });
    }

    /**
     *
     * @param ctx context
     * @return boolean
     */
    public boolean isNetworkAvaliable(Context ctx) {
        ConnectivityManager connectivityManager = (ConnectivityManager) ctx
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED;
    }

}