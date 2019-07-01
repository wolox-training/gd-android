package ar.com.wolox.android.example.ui.login;

import android.os.Bundle;

import ar.com.wolox.android.R;
import ar.com.wolox.wolmo.core.activity.WolmoActivity;

/**
 * A simple {@link WolmoActivity} subclass.
 */
public class HomeActivity extends WolmoActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    @Override
    protected int layout() {
        return R.layout.activity_home;
    }

    @Override
    protected void init() {

    }
}
