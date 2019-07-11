package ar.com.wolox.android.example.network;

/**
 * OnLoginListener implements when need a response
 */
public interface OnLoginListener {
    void onLoginSuccess();
    void onLoginUserNotFound();
    void onLoginFail();
}