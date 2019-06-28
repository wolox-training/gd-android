package ar.com.wolox.android.example.ui.login;

/**
 * ILoginView
 */
public interface ILoginView {

    void onUsernameSaved();

    void showLoginSuccess();

    void showLoginFailure(int error);

}
