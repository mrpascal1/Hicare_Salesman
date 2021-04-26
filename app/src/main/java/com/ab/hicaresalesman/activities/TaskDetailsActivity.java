package com.ab.hicaresalesman.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.ab.hicaresalesman.BaseActivity;
import com.ab.hicaresalesman.R;
import com.ab.hicaresalesman.adapters.TaskViewPagerAdapter;
import com.ab.hicaresalesman.databinding.ActivityTaskDetailsBinding;
import com.ab.hicaresalesman.fragments.SelectServiceFragment;
import com.ab.hicaresalesman.fragments.ServiceAreaFragment;
import com.ab.hicaresalesman.fragments.ServiceQuestionFragment;
import com.ab.hicaresalesman.handler.UserTaskDetailClickHandler;

import java.util.Objects;

public class TaskDetailsActivity extends BaseActivity implements UserTaskDetailClickHandler {
    ActivityTaskDetailsBinding mActivityTaskDetailsBinding;
    private TaskViewPagerAdapter mViewPager;
    private int page = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityTaskDetailsBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_task_details);
        mActivityTaskDetailsBinding.setHandler(this);
        setSupportActionBar(mActivityTaskDetailsBinding.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        setTitle("Service Details");
        setViewPager();
        onPageChange();
    }

    private void onPageChange() {
        try {
            mActivityTaskDetailsBinding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    page = position;
                    switch (position) {
                        case 0:

                            break;
                        case 1:
                            mActivityTaskDetailsBinding.btnPrev.setVisibility(View.VISIBLE);
                            mActivityTaskDetailsBinding.btnNext.setVisibility(View.VISIBLE);
                            break;
                        case 2:
                            mActivityTaskDetailsBinding.btnPrev.setVisibility(View.VISIBLE);
                            mActivityTaskDetailsBinding.btnNext.setVisibility(View.GONE);
                            break;
                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setViewPager() {
        try {
            mViewPager = new TaskViewPagerAdapter(getSupportFragmentManager(), this);
            mViewPager.addFragment(SelectServiceFragment.newInstance(), "ServiceSelectActivity-ServiceSelectFragment");
            mViewPager.addFragment(ServiceQuestionFragment.newInstance(), "ServiceSelectActivity-ServiceSelectFragment");
            mViewPager.addFragment(ServiceAreaFragment.newInstance(), "ServiceSelectActivity-ServiceSelectFragment");
            mActivityTaskDetailsBinding.viewPager.setAdapter(mViewPager);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPrevClicked(View view) {
        try {
            mActivityTaskDetailsBinding.viewPager.setCurrentItem(mActivityTaskDetailsBinding.viewPager.getCurrentItem() - 1, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onNextClicked(View view) {
        try {
            mActivityTaskDetailsBinding.viewPager.setCurrentItem(mActivityTaskDetailsBinding.viewPager.getCurrentItem() + 1, true);

        } catch (Exception e) {
            e.printStackTrace();
        }
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