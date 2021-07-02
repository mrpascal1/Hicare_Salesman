package com.ab.hicaresalesman.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.ab.hicaresalesman.BaseActivity;
import com.ab.hicaresalesman.R;
import com.ab.hicaresalesman.databinding.ActivityAddTaskBinding;
import com.ab.hicaresalesman.fragments.AddTaskFragment;
import com.ab.hicaresalesman.fragments.HomeFragment;

import java.util.Objects;

public class AddTaskActivity extends BaseActivity {
    ActivityAddTaskBinding mActivityAddTaskBinding;
    public static final String ARGS_OPP_NO = "ARGS_OPP_NO";
    public static final String ARGS_INDUSTRY = "ARGS_INDUSTRY";
    public static final String ARGS_INDUSTRY_ID = "ARGS_INDUSTRY_ID";
    private String opportunityId;
    private String industryName;
    private int industryId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityAddTaskBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_add_task);
        opportunityId = getIntent().getStringExtra(ARGS_OPP_NO);
        industryName = getIntent().getStringExtra(ARGS_INDUSTRY);
        industryId = getIntent().getIntExtra(ARGS_INDUSTRY_ID, 0);
        addFragment(AddTaskFragment.newInstance(opportunityId, industryName, industryId), "AddTaskActivity - AddTaskFragment");
        setSupportActionBar(mActivityAddTaskBinding.toolbar);
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