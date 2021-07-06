package com.ab.hicaresalesman.fragments;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.ab.hicaresalesman.R;
import com.ab.hicaresalesman.adapters.RecyclerViewActivityParentAdapter;
import com.ab.hicaresalesman.databinding.FragmentViewActivityBinding;
import com.ab.hicaresalesman.handler.OnViewSaveClickHandler;
import com.ab.hicaresalesman.network.models.area.AddAreaRequest;
import com.ab.hicaresalesman.network.models.area.ServiceActivity;
import com.ab.hicaresalesman.network.models.area.ServiceActivityRequest;
import com.ab.hicaresalesman.network.models.area.ServiceActivityType;
import com.ab.hicaresalesman.network.models.pest_service.AddServiceRequest;
import com.ab.hicaresalesman.utils.AppUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ViewActivityFragment extends DialogFragment implements OnViewSaveClickHandler {
    FragmentViewActivityBinding mFragmentViewActivityBinding;
    private static final String ARGS_SERVICE = "ARGS_SERVICE";

    RecyclerViewActivityParentAdapter mAdapter;
    RecyclerView.LayoutManager layoutManager;
    private List<ServiceActivityType> mServiceList = new ArrayList<>();
    private List<ServiceActivityType> mlist = new ArrayList<>();
    private HashMap<Integer, Boolean> map = new HashMap<>();

    private HashMap<String, ServiceActivityType> mMap = new HashMap<>();


    public ViewActivityFragment() {
        // Required empty public constructor
    }

    public static ViewActivityFragment newInstance(List<ServiceActivityType> mServiceList) {
        ViewActivityFragment fragment = new ViewActivityFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        args.putParcelableArrayList(ARGS_SERVICE, (ArrayList<? extends Parcelable>) mServiceList);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mServiceList = getArguments().getParcelableArrayList(ARGS_SERVICE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mFragmentViewActivityBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_view_activity, container, false);
        mFragmentViewActivityBinding.setHandler(this);
        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        }
        setCancelable(false);
        return mFragmentViewActivityBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (map != null) {
            map.clear();
        }
        mlist = mServiceList;
        mFragmentViewActivityBinding.txtTitle.setTypeface(mFragmentViewActivityBinding.txtTitle.getTypeface(), Typeface.BOLD);
        mFragmentViewActivityBinding.recycleView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        mFragmentViewActivityBinding.recycleView.setLayoutManager(layoutManager);
        mAdapter = new RecyclerViewActivityParentAdapter(getActivity(), mServiceList, (posParent, posChild, isChecked) -> {
//            if (isChecked) {
//            mAdapter.getItem(posParent).getServiceList().get(posChild).setIsSelected(isChecked);
//            mAdapter.getItem(posParent).getServiceList().get(posChild).setDefaultSelected(isChecked);
//            mServiceList.get(posParent).setServiceList(mAdapter.getItem(posParent).getServiceList());
//            mServiceList.get(posParent).setServiceType(mAdapter.getItem(posParent).getServiceType());
//            mServiceList.get(posParent).getServiceList().get(posChild).setIsSelected(isChecked);
//            mServiceList.get(posParent).getServiceList().get(posChild).setDefaultSelected(isChecked);
//                ServiceActivityType request = new ServiceActivityType();
//                request.setServiceType(mAdapter.getItem(posParent).getServiceType());
//                request.setServiceList(mAdapter.getItem(posParent).getServiceList());
//                mMap.put(mAdapter.getItem(posParent).getServiceType(), request);
//            } else {
//                if (mMap.containsKey(mAdapter.getItem(posParent).getServiceType())) {
//                    mMap.remove(mAdapter.getItem(posParent).getServiceType());
//                }
//            }
//            if (isChecked) {
//                map.put(posParent, isChecked);
//                for (int i = 0; i < mServiceList.size(); i++) {
//                    ServiceActivity request = new ServiceActivity();
//                    request.setActivityId(mAdapter.getItem(i).getActivityId());
//                    request.setIsSelected(map.get(i));
//                    request.setActivityAreaMappingId(mAdapter.getItem(i).getActivityAreaMappingId());
//                    request.setAreaId(mAdapter.getItem(i).getAreaId());
//                    request.setCreatedBy(mAdapter.getItem(i).getCreatedBy());
//                    request.setServiceActivityId(mAdapter.getItem(i).getServiceActivityId());
//                    request.setServiceType(mAdapter.getItem(i).getServiceType());
//                    request.setServiceId(mAdapter.getItem(i).getServiceId());
//                    request.setDefaultSelected(map.get(i));
//                    request.setServiceActivityName(mAdapter.getItem(i).getServiceActivityName());
//                    AppUtils.mServiceList.add(request);
//                }
//
//            } else {
//                if (mServiceList != null && mServiceList.size() > 0) {
//                    for (int i = 0; i < mServiceList.size(); i++) {
//                        mServiceList.remove(i);
//                    }
//                }
//            }
            mServiceList.get(posParent).setServiceList(mAdapter.getItem(posParent).getServiceList());
            mServiceList.get(posParent).setServiceType(mAdapter.getItem(posParent).getServiceType());
            mServiceList.get(posParent).getServiceList().get(posChild).setIsSelected(isChecked);
            mServiceList.get(posParent).getServiceList().get(posChild).setDefaultSelected(isChecked);
        });
        mFragmentViewActivityBinding.recycleView.setAdapter(mAdapter);
    }

    @Override
    public void onSaveButtonClicked(View view) {
//        if (mMap != null && mMap.size() > 0) {
//            mServiceList = new ArrayList<>(mMap.values());
//        mServiceList.addAll(mlist);
        AppUtils.mServiceList = mServiceList;
//        }
        dismiss();
    }

    @Override
    public void onCancelButtonClicked(View view) {
        dismiss();
    }
}