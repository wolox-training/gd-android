package ar.com.wolox.android.example.ui.login;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * ILoginView
 */
public interface ILoginView {

    void onUsernameSaved();

    void setEmailError(@NonNull int error);

    void setPasswordError(@NonNull int error);

    void onLoginButtonPressed();

    void showLoginSuccess();

    void showLoginFailure(@NonNull int error);

    void setInitialCredentials(@Nullable String email, @Nullable String password);

}