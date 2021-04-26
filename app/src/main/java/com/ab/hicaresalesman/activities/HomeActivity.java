package com.ab.hicaresalesman.activities;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import com.ab.hicaresalesman.BaseActivity;
import com.ab.hicaresalesman.R;
import com.ab.hicaresalesman.databinding.ActivityHomeBinding;
import com.ab.hicaresalesman.fragments.HomeFragment;
import com.ab.hicaresalesman.fragments.LoginFragment;
import com.ab.hicaresalesman.utils.SharedPreferencesUtility;

public class HomeActivity extends BaseActivity {
    ActivityHomeBinding mActivityHomeBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityHomeBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_home);
        addFragment(HomeFragment.newInstance(), "HomeActivity - HomeFragment");
        SharedPreferencesUtility.savePrefBoolean(this, SharedPreferencesUtility.IS_USER_LOGIN, true);
    }

}