package com.ab.hicaresalesman.activities;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.ab.hicaresalesman.BaseActivity;
import com.ab.hicaresalesman.R;
import com.ab.hicaresalesman.databinding.ActivityLoginBinding;
import com.ab.hicaresalesman.fragments.LoginFragment;

public class LoginActivity extends BaseActivity {
    ActivityLoginBinding mActivityLoginBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityLoginBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_login);
        addFragment(LoginFragment.newInstance(), "LoginActivity-LoginFragment");
        Window window = this.getWindow();

// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.status_bar));        }
    }
}