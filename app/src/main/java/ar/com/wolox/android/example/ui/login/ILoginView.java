package ar.com.wolox.android.example.ui.login;

import androidx.annotation.NonNull;

/**
 * ILoginView
 */
public interface ILoginView {
    void onUsernameSaved();

    void setEmailError(@NonNull int error);

    void setPasswordError(@NonNull int error);

    void onLoginButtonPressed();

}
