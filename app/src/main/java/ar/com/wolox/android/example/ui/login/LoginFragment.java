package ar.com.wolox.android.example.ui.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import ar.com.wolox.android.R;
import ar.com.wolox.android.example.ui.viewpager.ViewPagerActivity;
import ar.com.wolox.wolmo.core.fragment.WolmoFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

import com.google.android.material.textfield.TextInputEditText;

import java.util.regex.Pattern;

import javax.inject.Inject;

/**
 * A simple {@link WolmoFragment} subclass.
 */
public class LoginFragment extends WolmoFragment<LoginPresenter> implements ILoginView {

    @BindView(R.id.vLoginButton)
    Button vLoginButton;
    @BindView(R.id.vSignUpButton)
    Button vSignUpButton;
    @BindView(R.id.vEmailInput)
    TextInputEditText vEmailInput;
    @BindView(R.id.vPasswordInput)
    TextInputEditText vPasswordInput;
    @BindView(R.id.vTermsConditionsText)
    TextView vTermsConditionsText;

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    @Inject
    public LoginFragment() { }

    public int layout() {
        return R.layout.fragment_login;
    } // layout()

    /**
     *
     */
    public void init() {
        ButterKnife.bind(this, getActivity());
        Context context = getActivity();

        sharedPref = context.getSharedPreferences(
                getString(R.string.preferences_name), Context.MODE_PRIVATE);

        editor = sharedPref.edit();

        vTermsConditionsText.setMovementMethod(LinkMovementMethod.getInstance());

        vEmailInput.setText(sharedPref.getString(getString(R.string.login_email), ""));
        vPasswordInput.setText(sharedPref.getString(getString(R.string.login_pass), ""));
    } // public void init()

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
                if (!hasFocus) {
                    if (!validateEmail(vEmailInput.getText().toString())) {
                        vEmailInput.setError(getText(R.string.login_email_invalid));
                    }
                }
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
                int ret;
                if (validateFields()) {
                    editor.putString(getString(R.string.login_email), vEmailInput.getText().toString());
                    editor.putString(getString(R.string.login_pass), vPasswordInput.getText().toString());
                    editor.commit();

                    getPresenter().onLoginButtonClicked(vEmailInput.getText().toString(),
                            vPasswordInput.getText().toString());

                }

            } //public void onClick(View v)
        }); //vLoginButton.setOnClickListener(new View.OnClickListener()

        vSignUpButton.setOnClickListener(new View.OnClickListener() {

            @OnClick
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), RegisterActivity.class);
                startActivity(intent);

            } //public void onClick(View v)
        }); //vSignUpButton.setOnClickListener(new View.OnClickListener()

    } // public void setListeners()

    @Override
    public void onUsernameSaved() {
        Intent intent = new Intent(getActivity(), ViewPagerActivity.class);
        startActivity(intent);
    }

    @Override
    public void showLoginSuccess() {
        Intent intent = new Intent(getActivity(), HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void showLoginFailure(int error) {
        Toast.makeText(getActivity(), getText(error), Toast.LENGTH_SHORT).show();

    }

    private Boolean validateFields() {
        Boolean ret = true;

        if (vEmailInput.getText().toString().equals("")) {
            vEmailInput.setError(getText(R.string.login_email_error));
            ret = false;
        } else {
            if (!validateEmail(vEmailInput.getText().toString())) {
                vEmailInput.setError(getText(R.string.login_email_invalid));
                ret = false;
            }
        }

        if (vPasswordInput.getText().toString().equals("")) {
            vPasswordInput.setError(getText(R.string.login_pass_error));
            ret = false;
        }

        return ret;
    } // validateFields()

    private Boolean validateEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    } // validateEmail(String email)

}
