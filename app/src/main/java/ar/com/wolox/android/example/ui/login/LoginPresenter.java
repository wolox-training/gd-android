package ar.com.wolox.android.example.ui.login;

import ar.com.wolox.android.example.utils.UserSession;
import ar.com.wolox.wolmo.core.presenter.BasePresenter;
import javax.inject.Inject;
import org.jetbrains.annotations.NotNull;

/**
 * A simple {@link BasePresenter} subclass.
 */
public class LoginPresenter extends BasePresenter {
    private UserSession mUserSession;

    public void storeUsername(@NotNull String text) {
        this.mUserSession.setUsername(text);
        ((ILoginView) this.getView()).onUsernameSaved();
    }

    @Inject
    public LoginPresenter(@NotNull UserSession mUserSession) {
        this.mUserSession = mUserSession;
    }
}
