package ar.com.wolox.android.example.ui.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Patterns;

import java.util.regex.Pattern;

import ar.com.wolox.android.R;
import ar.com.wolox.android.example.utils.UserSession;
import ar.com.wolox.wolmo.core.presenter.BasePresenter;
import javax.inject.Inject;

/**
 * A simple {@link BasePresenter} subclass.
 */
public class LoginPresenter extends BasePresenter<ILoginView> {
    private UserSession mUserSession;

    private String mPrefName;
    private String mPrefEmail;
    private String mPrefPass;

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    @Inject
    public LoginPresenter() {

    }

    public void onUsernameSaved() {

    }

    public void onEmailLostFocus(Boolean hasFocus, String email) {
        if (!hasFocus) {
            if (!validateEmail(email)) {
                getView().setEmailError(R.string.login_email_invalid);
            }
        }
    }

    public void onLoginButtonClicked(String email, String pass) {
        if (validateFields(email, pass)) {
            editor.putString(mPrefEmail, email);
            editor.putString(mPrefPass, pass);
            editor.commit();

            onUsernameSaved();

            getView().onLoginButtonPressed();
        }

    }

    private Boolean validateFields(String email, String pass) {
        Boolean fieldsValidate = true;

        if ("".equals(email)) {
            getView().setEmailError(R.string.login_email_error);
            fieldsValidate = false;
        } else {
            if (!validateEmail(email)) {
                getView().setEmailError(R.string.login_email_invalid);
                fieldsValidate = false;
            }
        }

        if ("".equals(pass)) {
            getView().setPasswordError(R.string.login_pass_error);
            fieldsValidate = false;
        }

        return fieldsValidate;
    }

    private Boolean validateEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    public void setPreferencesConf(Context context, String prefName,
                                   String prefEmail, String prefPass) {

        mPrefEmail = prefEmail;
        mPrefPass = prefPass;
        mPrefName = prefName;

        sharedPref = context.getSharedPreferences(mPrefName, Context.MODE_PRIVATE);

        editor = sharedPref.edit();

    }

}
