package ar.com.wolox.android.example.ui.example.login;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ar.com.wolox.android.example.network.APIAdapter;
import ar.com.wolox.android.example.network.OnLoginListener;
import ar.com.wolox.android.example.ui.login.ILoginView;
import ar.com.wolox.android.example.ui.login.LoginPresenter;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class LoginPresenterTest {

    private LoginPresenter loginPresenter;
    @Mock
    private ILoginView loginView;
    @Mock
    private APIAdapter apiAdapter;

    private final String INVALID_EMAIL = "invalidemail.com";
    private final String VALID_EMAIL = "susan.stevens38@example.com";
    private final String INVALID_PASSWORD = "asdasdasd";
    private final String VALID_PASSWORD = "12345678";

    @Before
    public void setupTestMailIsEmpty() {
        MockitoAnnotations.initMocks(this);
        loginPresenter = new LoginPresenter(apiAdapter);
        loginPresenter.attachView(loginView);
    }

    @Test
    public void testMailIsEmpty() {
        assertTrue(loginPresenter.mailIsEmpty(""));
    }

    @Test
    public void testMailIsInvalid() {
        assertFalse(loginPresenter.validateEmail(INVALID_EMAIL));
    }

    @Test
    public void testMailIsValid() {
        assertTrue(loginPresenter.validateEmail(VALID_EMAIL));
    }

    @Test
    public void testShowDialog() {
        loginPresenter.getUserByMail(VALID_EMAIL, INVALID_PASSWORD);
        verify(loginView, times(1)).showLoading();
    }

    @Test
    public void testAPIOnSuccess() {
        doAnswer(invocation -> {
            ((OnLoginListener) invocation.getArguments()[2]).onLoginSuccess();
            return invocation;
        }).when(apiAdapter).getUserById(anyString(), anyString(), any());
        loginPresenter.getUserByMail(VALID_EMAIL, VALID_PASSWORD);
        verify(loginView, times(1)).onGetUserByMailFinished(true);
    }

    @Test
    public void testAPIOnFailed() {
        doAnswer(invocation -> {
            ((OnLoginListener) invocation.getArguments()[2]).onLoginFail();
            return invocation;
        }).when(apiAdapter).getUserById(anyString(), anyString(), any());
        loginPresenter.getUserByMail(VALID_EMAIL, INVALID_PASSWORD);
        verify(loginView, times(1)).failedApiConnection();
    }

    @Test
    public void testAPIOnUserNotFound() {
        doAnswer(invocation -> {
            ((OnLoginListener) invocation.getArguments()[2]).onLoginUserNotFound();
            return invocation;
        }).when(apiAdapter).getUserById(anyString(), anyString(), any());
        loginPresenter.getUserByMail(VALID_EMAIL, INVALID_PASSWORD);
        verify(loginView, times(1)).onGetUserByMailFinished(false);
    }

}