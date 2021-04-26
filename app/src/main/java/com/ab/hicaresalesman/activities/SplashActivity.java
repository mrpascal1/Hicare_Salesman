package com.ab.hicaresalesman.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.ab.hicaresalesman.BaseActivity;
import com.ab.hicaresalesman.R;
import com.ab.hicaresalesman.databinding.ActivitySplashBinding;
import com.ab.hicaresalesman.utils.SharedPreferencesUtility;

public class SplashActivity extends BaseActivity {
ActivitySplashBinding mActivitySplashBinding;
    private static int SPLASH_TIME_OUT = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivitySplashBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_splash);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.splash);
        mActivitySplashBinding.imgSplash.startAnimation(animation);
        SharedPreferencesUtility.savePrefBoolean(SplashActivity.this, SharedPreferencesUtility.PREF_REFRESH, false);
   splashScreen();
    }

    private void splashScreen() {
        try {
            new Handler().postDelayed(() -> {

                if (SharedPreferencesUtility.getPrefBoolean(SplashActivity.this, SharedPreferencesUtility.IS_USER_LOGIN)) {
                    startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                    finish();
                } else {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    finish();
                }

            }, SPLASH_TIME_OUT);
        } catch (Exception e) {
          e.printStackTrace();
        }
    }
}