package ar.com.wolox.android.example.ui.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import ar.com.wolox.android.R;
import ar.com.wolox.android.example.ui.home.HomeActivity;
import ar.com.wolox.android.example.ui.viewpager.ViewPagerActivity;
import ar.com.wolox.wolmo.core.fragment.WolmoFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

import com.google.android.material.textfield.TextInputEditText;

import javax.inject.Inject;

/**
 * A simple {@link WolmoFragment} subclass.
 */
public class LoginFragment extends WolmoFragment<LoginPresenter> implements ILoginView {

    @BindView(R.id.vLoginButton) Button vLoginButton;
    @BindView(R.id.vSignUpButton) Button vSignUpButton;
    @BindView(R.id.vEmailInput) TextInputEditText vEmailInput;
    @BindView(R.id.vPasswordInput) TextInputEditText vPasswordInput;
    @BindView(R.id.vTermsConditionsText) TextView vTermsConditionsText;

    SharedPreferences sharedPref;

    @Inject
    public LoginFragment() { }

    public int layout() {
        return R.layout.fragment_login;
    }

    /**
     *
     */
    public void init() {
        ButterKnife.bind(this, getActivity());

        sharedPref = getActivity().getSharedPreferences(
                getString(R.string.preferences_name), Context.MODE_PRIVATE);

        vTermsConditionsText.setMovementMethod(LinkMovementMethod.getInstance());

        getPresenter().setPreferencesConf(getContext(),
                getString(R.string.preferences_name),
                getString(R.string.login_email),
                getString(R.string.login_pass));

        getPresenter().getInitialCredentials(getString(R.string.login_email),
                getString(R.string.login_pass),
                getString(R.string.prefs_default_value));

    }

    /**
     *
     */
    public void setListeners() {
        vEmailInput.addTextChangedListener(new TextWatcher() {
            @OnTextChanged
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @OnTextChanged
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                vEmailInput.setError(null);
            }

            @OnTextChanged
            public void afterTextChanged(Editable s) {
            }
        });

        vEmailInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                getPresenter().onEmailLostFocus(hasFocus, vEmailInput.getText().toString());

            }
        });

        vPasswordInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                vEmailInput.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        vLoginButton.setOnClickListener(new View.OnClickListener() {

            @OnClick
            public void onClick(View v) {

                getPresenter().onLoginButtonClicked(vEmailInput.getText().toString(),
                        vPasswordInput.getText().toString());

            }
        });

        vSignUpButton.setOnClickListener(new View.OnClickListener() {

            @OnClick
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), RegisterActivity.class);
                startActivity(intent);

            }
        });

    }

    @Override
    public void onUsernameSaved() {
        Intent intent = new Intent(getActivity(), ViewPagerActivity.class);
        startActivity(intent);
    }

    @Override
    public void showLoginSuccess() {
    }

    @Override
    public void showLoginFailure(int error) {
        Toast.makeText(getActivity(), getText(error), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void setInitialCredentials(@Nullable String email, @Nullable String password) {
        vEmailInput.setText(email);
        vPasswordInput.setText(password);
    }

    public void setEmailError(int error) {
        vEmailInput.setError(getText(error));
    }

    @Override
    public void setPasswordError(int error) {
        vPasswordInput.setError(getText(error));
    }

    @Override
    public void onLoginSuccesfully() {
        Intent intent = new Intent(getActivity(), HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

}
