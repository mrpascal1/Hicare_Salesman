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
import com.ab.hicaresalesman.network.models.area.AddAreaRequest;
import com.ab.hicaresalesman.network.models.area.AreaType;
import com.ab.hicaresalesman.network.models.area.TowerData;
import com.ab.hicaresalesman.network.models.question.SaveAnswerRequest;
import com.ab.hicaresalesman.utils.AppUtils;

import java.util.List;

import es.dmoral.toasty.Toasty;

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
        args.putInt(ARGS_ACTIVITY, activityId);
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

    public void setViewPager() {
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

    private boolean isUnitChecked(List<AddAreaRequest> listData) {
        for (AddAreaRequest data : listData) {
            if ((data.getUnit() == null || data.getUnit().equals(""))) {
                return false;
            }
        }
        return true;
    }


    private boolean isAreaChecked(List<AddAreaRequest> listData) {
        for (AddAreaRequest data : listData) {
            if ((data.getAreaSqFt() == null) || data.getAreaSqFt().equals("")) {
                return false;
            }
        }
        return true;
    }

    public void refresh() {
//        if (AppUtils.mHashArea != null) {
//            AppUtils.mHashArea.clear();
//        }
//        if (AppUtils.mAreaData != null) {
//            AppUtils.mAreaData.clear();
//        }
    }


    private boolean isFloorChecked(List<AddAreaRequest> listData) {
        for (AddAreaRequest data : listData) {
            if ((data.getFloorNo() == null || data.getFloorNo().equals(""))) {
                return false;
            }
        }
        return true;
    }

    public void addSubArea() {
        try {
            if (AppUtils.mAreaData != null) {
                AppUtils.mAreaData.clear();
            }
            AppUtils.mAreaData.addAll(AppUtils.mHashArea.values());
            if (AppUtils.mAreaData != null && AppUtils.mAreaData.size() > 0) {
                if (isUnitChecked(AppUtils.mAreaData)) {
                    if (isAreaChecked(AppUtils.mAreaData)) {
                        if (isFloorChecked(AppUtils.mAreaData)) {
                            NetworkCallController controller = new NetworkCallController(this);
                            controller.setListner(new NetworkResponseListner<BaseResponse>() {
                                @Override
                                public void onResponse(BaseResponse response) {
                                    if (response.getIsSuccess()) {
                                        AppUtils.mAreaData.clear();
                                        AppUtils.mServiceList.clear();
                                        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
                                    }
                                }

                                @Override
                                public void onFailure() {

                                }
                            });
                            controller.addSubArea(AppUtils.mAreaData);
                        } else {
                            Toasty.error(getActivity(), "Floor No. is required!", Toasty.LENGTH_SHORT).show();
                        }
                    } else {
                        Toasty.error(getActivity(), "Area Sq.Ft. is required!", Toasty.LENGTH_SHORT).show();
                    }
                } else {
                    Toasty.error(getActivity(), "Unit is required!", Toasty.LENGTH_SHORT).show();
                }
            } else {
                Toasty.error(getActivity(), "Area cannot be blank!", Toasty.LENGTH_SHORT).show();

//                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}