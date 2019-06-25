package ar.com.wolox.android.example.ui.login;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;

import ar.com.wolox.android.R;
import ar.com.wolox.android.example.ui.viewpager.ViewPagerActivity;
import ar.com.wolox.wolmo.core.fragment.WolmoFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

import com.google.android.material.textfield.TextInputEditText;

/**
* A simple {@link WolmoFragment} subclass.
*/
public class LoginFragment extends WolmoFragment implements ILoginView {
    LoginPresenter presenter;

    @BindView(R.id.vLoginButton) Button vLoginButton;
    @BindView(R.id.vSignUpButton) Button vSignUpButton;
    @BindView(R.id.vFirstNameInput) TextInputEditText vFirstNameInput;
    @BindView(R.id.vLastNameInput) TextInputEditText vLastNameInput;

    public int layout() {
        return R.layout.fragment_login;
    }

    public void init() {
        ButterKnife.bind(this, getActivity());
        vLoginButton.setEnabled(false);
    }

    /**
     *
     */
    public void setListeners() {
        vFirstNameInput.addTextChangedListener(new TextWatcher() {
            @OnTextChanged
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @OnTextChanged
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!vFirstNameInput.getText().toString().equals("")) {
                    vLoginButton.setEnabled(true);
                } else {
                    vLoginButton.setEnabled(false);
                }
            }

            @OnTextChanged
            public void afterTextChanged(Editable s) {
            }
        });

        vLoginButton.setOnClickListener(new View.OnClickListener() {
            @OnClick
            public void onClick(View v) {
                presenter.storeUsername(vFirstNameInput.getText().toString());
            }
        });
    }

    @Override
    public void onUsernameSaved() {
        Intent intent = new Intent(getActivity(), ViewPagerActivity.class);
        startActivity(intent);
    }
}
