package com.ab.hicaresalesman.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.CalendarView;
import android.widget.TextView;

import com.ab.hicaresalesman.BaseActivity;
import com.ab.hicaresalesman.R;
import com.ab.hicaresalesman.adapters.TaskViewPagerAdapter;
import com.ab.hicaresalesman.databinding.ActivityTaskDetailsBinding;
import com.ab.hicaresalesman.fragments.FrequencyFragment;
import com.ab.hicaresalesman.fragments.SelectServiceFragment;
import com.ab.hicaresalesman.fragments.ServiceAreaFragment;
import com.ab.hicaresalesman.fragments.ServiceCostFragment;
import com.ab.hicaresalesman.fragments.ServiceQuestionFragment;
import com.ab.hicaresalesman.handler.AddServiceClickHandler;
import com.ab.hicaresalesman.handler.OnNextEventHandler;
import com.ab.hicaresalesman.handler.OnQuestionClickedHandler;
import com.ab.hicaresalesman.handler.UserTaskDetailClickHandler;
import com.ab.hicaresalesman.utils.AppUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import es.dmoral.toasty.Toasty;

import static android.view.View.GONE;

public class TaskDetailsActivity extends BaseActivity implements UserTaskDetailClickHandler, OnNextEventHandler {
    ActivityTaskDetailsBinding mActivityTaskDetailsBinding;
    public static final String ARGS_ACTIVITY = "ARGS_ACTIVITY";
    public static final String ARGS_COST = "ARGS_COST";
    private TaskViewPagerAdapter mViewPager;
    private int page = 0;
    private boolean isServiceSelected = false;
    private int activityId = 0;
    private boolean isCostGenerated = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityTaskDetailsBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_task_details);
        mActivityTaskDetailsBinding.setHandler(this);
        setSupportActionBar(mActivityTaskDetailsBinding.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        activityId = getIntent().getIntExtra(ARGS_ACTIVITY, 0);
        isCostGenerated = getIntent().getBooleanExtra(ARGS_COST, false);
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

//                            mActivityTaskDetailsBinding.btnNext.setTextColor(getResources().getColor(R.color.themeColor));
//                            mActivityTaskDetailsBinding.btnNext.getCompoundDrawables()[0].setTint(getResources().getColor(R.color.themeColor));
                            break;
                        case 1:
                            ((ServiceQuestionFragment) mViewPager.getItem(position)).refresh();
                            setTitle("Service Questions");
                            if (AppUtils.mAreaData != null) {
                                AppUtils.mAreaData.clear();
                            }
                            if (AppUtils.mHashArea != null) {
                                AppUtils.mHashArea.clear();
                            }
                            AppUtils.getAreaByActivity(activityId);
//                            mViewPager.notifyDataSetChanged();
                            mActivityTaskDetailsBinding.btnNext.setText("NEXT");
//                            mActivityTaskDetailsBinding.btnPrev.setTextColor(getResources().getColor(R.color.themeColor));
//                            mActivityTaskDetailsBinding.btnPrev.getCompounfdDrawables()[0].setTint(getResources().getColor(R.color.themeColor));

//                            mActivityTaskDetailsBinding.btnNext.setTextColor(getResources().getColor(R.color.themeColor));
//                            mActivityTaskDetailsBinding.btnNext.getCompoundDrawables()[0].setTint(getResources().getColor(R.color.themeColor));
                            break;
                        case 2:
                            setTitle("Service Area");
//                            ((ServiceAreaFragment) mViewPager.getItem(position)).setViewPager();
                            ((ServiceAreaFragment) mViewPager.getItem(position)).refresh();
                            mActivityTaskDetailsBinding.btnNext.setText("NEXT");
//                            mActivityTaskDetailsBinding.btnPrev.setTextColor(getResources().getColor(R.color.themeColor));
//                            mActivityTaskDetailsBinding.btnPrev.getCompoundDrawables()[0].setTint(getResources().getColor(R.color.themeColor));

//                            mActivityTaskDetailsBinding.btnNext.setTextColor(getResources().getColor(R.color.themeColor));
//                            mActivityTaskDetailsBinding.btnNext.getCompoundDrawables()[0].setTint(getResources().getColor(R.color.themeColor));
                            break;
                        case 3:
                            setTitle("Recommendations");
                            if (AppUtils.mAreaData != null) {
                                AppUtils.mAreaData.clear();
                            }
                            if (AppUtils.mHashArea != null) {
                                AppUtils.mHashArea.clear();
                            }
                            AppUtils.getAreaByActivity(activityId);
//                            mActivityTaskDetailsBinding.btnPrev.setTextColor(getResources().getColor(R.color.themeColor));
//                            mActivityTaskDetailsBinding.btnPrev.getCompoundDrawables()[0].setTint(getResources().getColor(R.color.themeColor));

//                            mActivityTaskDetailsBinding.btnNext.setTextColor(getResources().getColor(R.color.themeColor));
//                            mActivityTaskDetailsBinding.btnNext.getCompoundDrawables()[0].setTint(getResources().getColor(R.color.themeColor));
                            if (isCostGenerated) {
                                mActivityTaskDetailsBinding.btnNext.setText("NEXT");
                            } else {
                                mActivityTaskDetailsBinding.btnNext.setText("FINISH");
                            }
                            break;
                        case 4:
                            setTitle("Service Cost");
                            mActivityTaskDetailsBinding.btnNext.setText("FINISH");
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

    private void showConfirmationDialog() {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Are you sure?");
            builder.setMessage("Once you've finished it, you won't be able to edit it again.")
                    .setNegativeButton(getResources().getString(R.string.no), (dialog, id) -> dialog.cancel())
                    .setPositiveButton(getResources().getString(R.string.yes), (dialog, id) -> {
                        startActivity(new Intent(this, ServiceCostActivity.class).putExtra(ServiceCostActivity.ARGS_ACTIVITY, activityId));
                        finish();
                    });
            AlertDialog alertdialog = builder.create();
            alertdialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setViewPager() {
        try {
            setTitle("Services");
            mViewPager = new TaskViewPagerAdapter(getSupportFragmentManager(), this);
            mViewPager.addFragment(SelectServiceFragment.newInstance(activityId, isCostGenerated), "Select Service");
            mViewPager.addFragment(ServiceQuestionFragment.newInstance(activityId, isCostGenerated), "Service Questions");
            mViewPager.addFragment(ServiceAreaFragment.newInstance(activityId, isCostGenerated), "Service Area");
            mViewPager.addFragment(FrequencyFragment.newInstance(activityId, isCostGenerated), "Service Frequency");
            if (isCostGenerated) {
                mViewPager.addFragment(ServiceCostFragment.newInstance(activityId), "Service Cost");
            }
            mActivityTaskDetailsBinding.viewPager.setAdapter(mViewPager);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPrevClicked(View view) {
        try {
            if (mActivityTaskDetailsBinding.viewPager.getCurrentItem() == 0) {
                finish();
            }
            mActivityTaskDetailsBinding.viewPager.setCurrentItem(mActivityTaskDetailsBinding.viewPager.getCurrentItem() - 1, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onNextClicked(View view) {
        try {
            if (mActivityTaskDetailsBinding.viewPager.getCurrentItem() == 0) {
                if (isCostGenerated) {
                    mActivityTaskDetailsBinding.viewPager.setCurrentItem(mActivityTaskDetailsBinding.viewPager.getCurrentItem() + 1, true);
                } else {
                    SelectServiceFragment fragment = (SelectServiceFragment) mActivityTaskDetailsBinding.viewPager.getAdapter().instantiateItem(mActivityTaskDetailsBinding.viewPager, mActivityTaskDetailsBinding.viewPager.getCurrentItem());
                    fragment.addServiceByActivity();
                }

            } else if (mActivityTaskDetailsBinding.viewPager.getCurrentItem() == 1) {
                if (isCostGenerated) {
                    mActivityTaskDetailsBinding.viewPager.setCurrentItem(mActivityTaskDetailsBinding.viewPager.getCurrentItem() + 1, true);
                } else {
                    ServiceQuestionFragment fragment = (ServiceQuestionFragment) mActivityTaskDetailsBinding.viewPager.getAdapter().instantiateItem(mActivityTaskDetailsBinding.viewPager, mActivityTaskDetailsBinding.viewPager.getCurrentItem());
                    fragment.saveAnswers();
                }

            } else if (mActivityTaskDetailsBinding.viewPager.getCurrentItem() == 2) {
                if (isCostGenerated) {
                    mActivityTaskDetailsBinding.viewPager.setCurrentItem(mActivityTaskDetailsBinding.viewPager.getCurrentItem() + 1, true);
                } else {
                    ServiceAreaFragment fragment = (ServiceAreaFragment) mActivityTaskDetailsBinding.viewPager.getAdapter().instantiateItem(mActivityTaskDetailsBinding.viewPager, mActivityTaskDetailsBinding.viewPager.getCurrentItem());
                    fragment.addSubArea();
                }

            } else if (mActivityTaskDetailsBinding.viewPager.getCurrentItem() == 3) {
                if (isCostGenerated) {
                    mActivityTaskDetailsBinding.viewPager.setCurrentItem(mActivityTaskDetailsBinding.viewPager.getCurrentItem() + 1, true);
                } else {
                    FrequencyFragment fragment = (FrequencyFragment) mActivityTaskDetailsBinding.viewPager.getAdapter().instantiateItem(mActivityTaskDetailsBinding.viewPager, mActivityTaskDetailsBinding.viewPager.getCurrentItem());
                    fragment.addFrequency();
                    showConfirmationDialog();
                }

            } else if (mActivityTaskDetailsBinding.viewPager.getCurrentItem() == 4) {
                finish();
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
        if (AppUtils.mAreaData != null) {
            AppUtils.mAreaData.clear();
        }
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