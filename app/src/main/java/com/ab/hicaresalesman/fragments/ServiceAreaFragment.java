package com.ab.hicaresalesman.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ab.hicaresalesman.BaseFragment;
import com.ab.hicaresalesman.R;
import com.ab.hicaresalesman.adapters.AreaViewPagerAdapter;
import com.ab.hicaresalesman.databinding.FragmentServiceAreaBinding;
import com.ab.hicaresalesman.network.NetworkCallController;
import com.ab.hicaresalesman.network.NetworkResponseListner;
import com.ab.hicaresalesman.network.models.BaseResponse;
import com.ab.hicaresalesman.network.models.area.AreaType;
import com.ab.hicaresalesman.network.models.area.TowerData;
import com.ab.hicaresalesman.utils.AppUtils;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ServiceAreaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ServiceAreaFragment extends BaseFragment {
    FragmentServiceAreaBinding mFragmentServiceAreaBinding;
    private AreaViewPagerAdapter mAdapter;
    private ViewPager viewPager;
    private int activityId = 0;
    public static final String ARGS_ACTIVITY = "ARGS_ACTIVITY";

    public ServiceAreaFragment() {
        // Required empty public constructor
    }

    public static ServiceAreaFragment newInstance(int activityId) {
        ServiceAreaFragment fragment = new ServiceAreaFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            activityId = getArguments().getInt(ARGS_ACTIVITY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mFragmentServiceAreaBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_service_area, container, false);
        return mFragmentServiceAreaBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPager = getActivity().findViewById(R.id.viewPager);
        setViewPager();
    }

    private void setViewPager() {
        try {
            mFragmentServiceAreaBinding.viewPager.setOffscreenPageLimit(2);
            mAdapter = new AreaViewPagerAdapter(getChildFragmentManager(), getActivity());
            if (AppUtils.mCommonList != null && AppUtils.mCommonList.size() > 0) {
                mAdapter.addFragment(CommonAreaFragment.newInstance(), "COMMON AREA");
            }
            if (AppUtils.mRegularList != null && AppUtils.mRegularList.size() > 0) {
                mAdapter.addFragment(RegularAreaFragment.newInstance(), "REGULAR AREA");
            }
            mFragmentServiceAreaBinding.viewpagertab.setDistributeEvenly(true);
            mFragmentServiceAreaBinding.viewPager.setAdapter(mAdapter);
            mFragmentServiceAreaBinding.viewpagertab.setViewPager(mFragmentServiceAreaBinding.viewPager);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addSubArea() {
        try {
            NetworkCallController controller = new NetworkCallController(this);
            controller.setListner(new NetworkResponseListner<BaseResponse>() {
                @Override
                public void onResponse(BaseResponse response) {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
                    if (response.getIsSuccess()) {
                        AppUtils.mAreaData.clear();
                        AppUtils.mServiceList.clear();
                        Toast.makeText(getActivity(), "Saved Successfully", Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure() {

                }
            });
            controller.addSubArea(AppUtils.mAreaData);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}