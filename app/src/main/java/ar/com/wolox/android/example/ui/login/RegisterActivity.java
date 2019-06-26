package ar.com.wolox.android.example.ui.login;

import android.os.Bundle;

import ar.com.wolox.android.R;
import ar.com.wolox.wolmo.core.activity.WolmoActivity;

/**
 * A simple {@link WolmoActivity} subclass.
 */
public class RegisterActivity extends WolmoActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    @Override
    protected int layout() {
        return R.layout.activity_register;
    }

    @Override
    protected void init() {

    }
}
