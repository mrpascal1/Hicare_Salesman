package com.ab.hicaresalesman.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ab.hicaresalesman.BaseFragment;
import com.ab.hicaresalesman.R;
import com.ab.hicaresalesman.adapters.RecycleFrequencyAdapter;
import com.ab.hicaresalesman.databinding.FragmentFrequencyBinding;
import com.ab.hicaresalesman.network.NetworkCallController;
import com.ab.hicaresalesman.network.NetworkResponseListner;
import com.ab.hicaresalesman.network.models.BaseResponse;
import com.ab.hicaresalesman.network.models.frequency.FrequencyData;
import com.ab.hicaresalesman.network.models.frequency.FrequencyRequest;
import com.ab.hicaresalesman.utils.AppUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FrequencyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FrequencyFragment extends BaseFragment {
    FragmentFrequencyBinding mFragmentFrequencyBinding;
    public static final String ARGS_ACTIVITY = "ARGS_ACTIVITY";
    public static final String ARGS_COST = "ARGS_COST";
    RecycleFrequencyAdapter mAdapter;
    RecyclerView.LayoutManager layoutManager;
    private ViewPager viewPager;
    private List<FrequencyRequest> frequencyList = new ArrayList<>();
    private HashMap<Integer, FrequencyRequest> mMap = new HashMap<>();
    private int activityId = 0;
    private boolean isCostGenerated = false;

    public FrequencyFragment() {
        // Required empty public constructor
    }

    public static FrequencyFragment newInstance(int activityId, boolean isCostGenerated) {
        FrequencyFragment fragment = new FrequencyFragment();
        Bundle args = new Bundle();
        args.putInt(ARGS_ACTIVITY, activityId);
        args.putBoolean(ARGS_COST, isCostGenerated);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            activityId = getArguments().getInt(ARGS_ACTIVITY);
            isCostGenerated = getArguments().getBoolean(ARGS_COST);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mFragmentFrequencyBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_frequency, container, false);
        return mFragmentFrequencyBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPager = getActivity().findViewById(R.id.viewPager);
        mFragmentFrequencyBinding.recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        mFragmentFrequencyBinding.recyclerView.setLayoutManager(layoutManager);
        mAdapter = new RecycleFrequencyAdapter(getActivity(), isCostGenerated, (position, option, freqName, freqId) -> {
            FrequencyRequest request = new FrequencyRequest();
            request.setActivityId(activityId);
            request.setCreatedByIdUser(0);
            request.setCreatedOn(AppUtils.currentDateTime());
            request.setFrequencyId(option.getFrequencyId());
            if (option.getFrequencyName().equals("Select Option")) {
                request.setFrequencyName(freqName);
                request.setFrequencyId(freqId);
            } else {
                request.setFrequencyName(option.getFrequencyName());
                request.setFrequencyId(option.getFrequencyId());
            }
            request.setId(mAdapter.getItem(position).getCategoryId());
            request.setServiceId(mAdapter.getItem(position).getServiceId());
            request.setModifiedByIdUser(0);
            request.setCategoryId(mAdapter.getItem(position).getCategoryId());
            request.setCategoryName(mAdapter.getItem(position).getCategoryName());
            request.setModifiedOn(AppUtils.currentDateTime());
            frequencyList.add(request);
            mMap.put(mAdapter.getItem(position).getServiceId(), request);
        });
        mFragmentFrequencyBinding.recyclerView.setAdapter(mAdapter);
        getFrequencyList();
    }

    private void getFrequencyList() {
        try {
            NetworkCallController controller = new NetworkCallController();
            controller.setListner(new NetworkResponseListner<List<FrequencyData>>() {
                @Override
                public void onResponse(List<FrequencyData> items) {
                    if (items != null && items.size() > 0) {
                        mAdapter.setData(items);
                        mAdapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onFailure() {

                }
            });
            controller.getFrequencyList(activityId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addFrequency() {
        try {
            frequencyList = new ArrayList<>(mMap.values());
            if (frequencyList.size() > 0) {
                NetworkCallController controller = new NetworkCallController(this);
                controller.setListner(new NetworkResponseListner<BaseResponse>() {
                    @Override
                    public void onResponse(BaseResponse response) {
                        if (response.getIsSuccess()) {
                            if (frequencyList != null) {
                                frequencyList.clear();
                            }
                        }
                    }

                    @Override
                    public void onFailure() {

                    }
                });
                controller.addFrequency(frequencyList);
            }
            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}