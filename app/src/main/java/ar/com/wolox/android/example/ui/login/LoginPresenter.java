package ar.com.wolox.android.example.ui.login;

import androidx.annotation.Nullable;

import java.util.List;

import ar.com.wolox.android.R;
import ar.com.wolox.android.example.model.User;
import ar.com.wolox.android.example.network.UserService;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Patterns;

import androidx.annotation.NonNull;
import java.util.regex.Pattern;

import ar.com.wolox.android.example.utils.UserSession;
import ar.com.wolox.wolmo.core.presenter.BasePresenter;
import ar.com.wolox.wolmo.networking.retrofit.RetrofitServices;
import ar.com.wolox.wolmo.networking.retrofit.callback.NetworkCallback;
import okhttp3.ResponseBody;
import retrofit2.Call;

import javax.inject.Inject;

/**
 * A simple {@link BasePresenter} subclass.
 */
public class LoginPresenter extends BasePresenter<ILoginView> {

    private UserSession mUserSession;
    private String mPrefName;
    private String mPrefEmail;
    private String mPrefPass;

    @Inject
    RetrofitServices mRetrofiServices;

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    @Inject
    public LoginPresenter() {

    }

    /**
     *
     * @param email EMail
     * @param password Pass
     */
    public void onLoginButtonClicked(@Nullable String email, @Nullable String password) {

        if (validateFields(email, password)) {
            Call<List<User>> call = mRetrofiServices.getService(UserService.class).getUserLogin(email);

            call.enqueue(new NetworkCallback<List<User>>() {

                @Override
                public void onResponseSuccessful(@Nullable List<User> response) {

                    if ((response.isEmpty()) || (!response.get(0).getPassword().equals(password))) {
                        getView().showLoginFailure(R.string.login_error_credentials);
                    } else {
                        editor.putString(mPrefEmail, email);
                        editor.putString(mPrefPass, password);
                        editor.commit();

                        getView().onLoginSuccesfully();
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

        }

    }

    public void onUsernameSaved() {

    }

    public void onEmailLostFocus(@NonNull Boolean hasFocus, @Nullable String email) {
        if (!hasFocus) {
            if (!validateEmail(email)) {
                getView().setEmailError(R.string.login_email_invalid);
            }
        }
    }

    private Boolean validateFields(@Nullable String email, @Nullable String pass) {
        Boolean fieldsValidate = true;

        if (email.isEmpty()) {
            getView().setEmailError(R.string.login_email_error);
            fieldsValidate = false;
        } else {
            if (!validateEmail(email)) {
                getView().setEmailError(R.string.login_email_invalid);
                fieldsValidate = false;
            }
        }

        if (pass.isEmpty()) {
            getView().setPasswordError(R.string.login_pass_error);
            fieldsValidate = false;
        }

        return fieldsValidate;
    }

    private Boolean validateEmail(@NonNull String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    public void setPreferencesConf(@NonNull Context context, @NonNull String prefName,
                                   @NonNull String prefEmail, @NonNull String prefPass) {

        mPrefEmail = prefEmail;
        mPrefPass = prefPass;
        mPrefName = prefName;

        sharedPref = context.getSharedPreferences(mPrefName, Context.MODE_PRIVATE);

        editor = sharedPref.edit();

    }

    public void getInitialCredentials(@Nullable String prefEmail,
                                      @Nullable String prefPass,
                                      @Nullable String defaultValue) {

        getView().setInitialCredentials(sharedPref.getString(prefEmail, defaultValue),
                sharedPref.getString(prefPass, defaultValue));
    }

    public void isNetworkAvaliable(Context ctx) {
        ConnectivityManager connectivityManager = (ConnectivityManager) ctx
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            getView().showLoginSuccess();
        } else {
            getView().showLoginFailure(R.string.error_internet_connection);
        }
    }

}