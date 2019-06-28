package ar.com.wolox.android.example.ui.login;

/**
 * ILoginView
 */
public interface ILoginView {
    void onUsernameSaved();

    void setEmailError(int error);

    void setPasswordError(int error);

    void onLoginButtonPressed();

}
