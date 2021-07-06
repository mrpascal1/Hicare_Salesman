package com.ab.hicaresalesman.activities;

import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.ab.hicaresalesman.BaseActivity;
import com.ab.hicaresalesman.R;
import com.ab.hicaresalesman.databinding.ActivityServiceCostBinding;
import com.ab.hicaresalesman.fragments.ServiceCostFragment;

import java.util.Objects;

public class ServiceCostActivity extends BaseActivity {
    ActivityServiceCostBinding mActivityServiceCostBinding;
    public static final String ARGS_ACTIVITY = "ARGS_ACTIVITY";
    private int activityId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityServiceCostBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_service_cost);
        activityId = getIntent().getIntExtra(ARGS_ACTIVITY, 0);
        addFragment(ServiceCostFragment.newInstance(activityId), "ServiceCostActivity - ServiceCostFragment");
        setSupportActionBar(mActivityServiceCostBinding.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public void onBackPressed() {
        try {
            getBack();
//            super.onBackPressed();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void getBack() {
        int fragment = getSupportFragmentManager().getBackStackEntryCount();
        Log.e("fragments", String.valueOf(fragment));
        if (fragment < 1) {
            finish();
        } else {
            getFragmentManager().popBackStack();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }
}