package com.ab.hicaresalesman.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.ab.hicaresalesman.BaseActivity;
import com.ab.hicaresalesman.R;
import com.ab.hicaresalesman.adapters.TaskViewPagerAdapter;
import com.ab.hicaresalesman.databinding.ActivityTaskDetailsBinding;
import com.ab.hicaresalesman.fragments.FrequencyFragment;
import com.ab.hicaresalesman.fragments.SelectServiceFragment;
import com.ab.hicaresalesman.fragments.ServiceAreaFragment;
import com.ab.hicaresalesman.fragments.ServiceQuestionFragment;
import com.ab.hicaresalesman.handler.AddServiceClickHandler;
import com.ab.hicaresalesman.handler.OnNextEventHandler;
import com.ab.hicaresalesman.handler.OnQuestionClickedHandler;
import com.ab.hicaresalesman.handler.UserTaskDetailClickHandler;
import com.ab.hicaresalesman.utils.AppUtils;

import java.util.Objects;

public class TaskDetailsActivity extends BaseActivity implements UserTaskDetailClickHandler, OnNextEventHandler {
    ActivityTaskDetailsBinding mActivityTaskDetailsBinding;
    public static final String ARGS_ACTIVITY = "ARGS_ACTIVITY";
    private TaskViewPagerAdapter mViewPager;
    private int page = 0;
    private boolean isServiceSelected = false;
    private int activityId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityTaskDetailsBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_task_details);
        mActivityTaskDetailsBinding.setHandler(this);
        setSupportActionBar(mActivityTaskDetailsBinding.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        activityId = getIntent().getIntExtra(ARGS_ACTIVITY, 0);
        mActivityTaskDetailsBinding.btnNext.setTypeface(mActivityTaskDetailsBinding.btnNext.getTypeface(), Typeface.BOLD);
        mActivityTaskDetailsBinding.btnPrev.setTypeface(mActivityTaskDetailsBinding.btnPrev.getTypeface(), Typeface.BOLD);
        mActivityTaskDetailsBinding.viewPager.setPagingEnabled(false);
        setViewPager();
        onPageChange();
    }


    private void onPageChange() {
        try {
            mActivityTaskDetailsBinding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onPageSelected(int position) {
                    page = position;
                    switch (position) {
                        case 0:
                            ((SelectServiceFragment) mViewPager.getItem(position)).refresh();
                            setTitle("Select Services");
                            mActivityTaskDetailsBinding.btnNext.setText("NEXT");
//                            mActivityTaskDetailsBinding.btnPrev.setTextColor(getResources().getColor(R.color.white));
//                            mActivityTaskDetailsBinding.btnPrev.getCompoundDrawables()[0].setTint(getResources().getColor(R.color.white));
//
//                            mActivityTaskDetailsBinding.btnNext.setTextColor(getResources().getColor(R.color.themeColor));
//                            mActivityTaskDetailsBinding.btnNext.getCompoundDrawables()[0].setTint(getResources().getColor(R.color.themeColor));
                            break;
                        case 1:
                            ((ServiceQuestionFragment) mViewPager.getItem(position)).refresh();

                            setTitle("Service Questions");
                            AppUtils.getAreaByActivity(activityId);
                            mViewPager.notifyDataSetChanged();
                            mActivityTaskDetailsBinding.btnNext.setText("NEXT");
//                            mActivityTaskDetailsBinding.btnPrev.setTextColor(getResources().getColor(R.color.themeColor));
//                            mActivityTaskDetailsBinding.btnPrev.getCompoundDrawables()[0].setTint(getResources().getColor(R.color.themeColor));
//
//                            mActivityTaskDetailsBinding.btnNext.setTextColor(getResources().getColor(R.color.themeColor));
//                            mActivityTaskDetailsBinding.btnNext.getCompoundDrawables()[0].setTint(getResources().getColor(R.color.themeColor));
                            break;
                        case 2:
                            setTitle("Service Area");
                            ((ServiceAreaFragment) mViewPager.getItem(position)).setViewPager();
                            ((ServiceAreaFragment) mViewPager.getItem(position)).refresh();
                            mActivityTaskDetailsBinding.btnNext.setText("NEXT");
//                            mActivityTaskDetailsBinding.btnPrev.setTextColor(getResources().getColor(R.color.themeColor));
//                            mActivityTaskDetailsBinding.btnPrev.getCompoundDrawables()[0].setTint(getResources().getColor(R.color.themeColor));
//
//                            mActivityTaskDetailsBinding.btnNext.setTextColor(getResources().getColor(R.color.themeColor));
//                            mActivityTaskDetailsBinding.btnNext.getCompoundDrawables()[0].setTint(getResources().getColor(R.color.themeColor));
                            break;
                        case 3:
                            setTitle("Recommendations");
                            AppUtils.getAreaByActivity(activityId);
//                            mActivityTaskDetailsBinding.btnPrev.setTextColor(getResources().getColor(R.color.themeColor));
//                            mActivityTaskDetailsBinding.btnPrev.getCompoundDrawables()[0].setTint(getResources().getColor(R.color.themeColor));
//
//                            mActivityTaskDetailsBinding.btnNext.setTextColor(getResources().getColor(R.color.themeColor));
//                            mActivityTaskDetailsBinding.btnNext.getCompoundDrawables()[0].setTint(getResources().getColor(R.color.themeColor));
                            mActivityTaskDetailsBinding.btnNext.setText("SAVE");
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
            setTitle("Services");
            mViewPager = new TaskViewPagerAdapter(getSupportFragmentManager(), this);
            mViewPager.addFragment(SelectServiceFragment.newInstance(activityId), "Select Service");
            mViewPager.addFragment(ServiceQuestionFragment.newInstance(activityId), "Service Questions");
            mViewPager.addFragment(ServiceAreaFragment.newInstance(activityId), "Service Area");
            mViewPager.addFragment(FrequencyFragment.newInstance(activityId), "Service Frequency");
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
            if (mActivityTaskDetailsBinding.viewPager.getCurrentItem() == 0) {
                SelectServiceFragment fragment = (SelectServiceFragment) mActivityTaskDetailsBinding.viewPager.getAdapter().instantiateItem(mActivityTaskDetailsBinding.viewPager, mActivityTaskDetailsBinding.viewPager.getCurrentItem());
                fragment.addServiceByActivity();
            } else if (mActivityTaskDetailsBinding.viewPager.getCurrentItem() == 1) {
                ServiceQuestionFragment fragment = (ServiceQuestionFragment) mActivityTaskDetailsBinding.viewPager.getAdapter().instantiateItem(mActivityTaskDetailsBinding.viewPager, mActivityTaskDetailsBinding.viewPager.getCurrentItem());
                fragment.saveAnswers();
            } else if (mActivityTaskDetailsBinding.viewPager.getCurrentItem() == 2) {
                ServiceAreaFragment fragment = (ServiceAreaFragment) mActivityTaskDetailsBinding.viewPager.getAdapter().instantiateItem(mActivityTaskDetailsBinding.viewPager, mActivityTaskDetailsBinding.viewPager.getCurrentItem());
                fragment.addSubArea();
            } else if (mActivityTaskDetailsBinding.viewPager.getCurrentItem() == 3) {
                FrequencyFragment fragment = (FrequencyFragment) mActivityTaskDetailsBinding.viewPager.getAdapter().instantiateItem(mActivityTaskDetailsBinding.viewPager, mActivityTaskDetailsBinding.viewPager.getCurrentItem());
                fragment.addFrequency();
            }
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

    @Override
    public void isServiceSelected(Boolean b) {
        isServiceSelected = b;
    }
}