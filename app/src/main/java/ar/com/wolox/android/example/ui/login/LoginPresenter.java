package ar.com.wolox.android.example.ui.login;

import ar.com.wolox.android.example.utils.UserSession;
import ar.com.wolox.wolmo.core.presenter.BasePresenter;
import javax.inject.Inject;

/**
 * A simple {@link BasePresenter} subclass.
 */
public class LoginPresenter extends BasePresenter<ILoginView> {
    private UserSession mUserSession;

    @Inject
    public LoginPresenter() {}

    public void onUsernameSaved() {}

}
