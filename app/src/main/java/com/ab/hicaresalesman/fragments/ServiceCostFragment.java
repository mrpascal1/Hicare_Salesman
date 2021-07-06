package com.ab.hicaresalesman.fragments;

import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ab.hicaresalesman.BaseFragment;
import com.ab.hicaresalesman.R;
import com.ab.hicaresalesman.adapters.RecyclerCostServiceAdapter;
import com.ab.hicaresalesman.databinding.FragmentCostServiceBinding;
import com.ab.hicaresalesman.network.NetworkCallController;
import com.ab.hicaresalesman.network.NetworkResponseListner;
import com.ab.hicaresalesman.network.models.cost_service_list.CostData;

import java.util.List;


public class ServiceCostFragment extends BaseFragment {
    FragmentCostServiceBinding mFragmentCostServiceBinding;
    RecyclerCostServiceAdapter mAdapter;
    RecyclerView.LayoutManager layoutManager;
    public static final String ARGS_ACTIVITY = "ARGS_ACTIVITY";
    public static final String ARGS_COST = "ARGS_COST";
    private int activityId = 0;
    private boolean isCostGenerated = false;
    private int totalPrice = 0;

    public ServiceCostFragment() {
        // Required empty public constructor
    }

    public static ServiceCostFragment newInstance(int activityId) {
        ServiceCostFragment fragment = new ServiceCostFragment();
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
        mFragmentCostServiceBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_cost_service, container, false);
        getActivity().setTitle("Service Cost Sheet");
        return mFragmentCostServiceBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try {
            mFragmentCostServiceBinding.txtTotalCost.setTypeface(mFragmentCostServiceBinding.txtTotalCost.getTypeface(), Typeface.BOLD);
            mFragmentCostServiceBinding.txtCostLabel.setTypeface(mFragmentCostServiceBinding.txtCostLabel.getTypeface(), Typeface.BOLD);
            mFragmentCostServiceBinding.recyclerView.setHasFixedSize(true);
            layoutManager = new LinearLayoutManager(getActivity());
            mFragmentCostServiceBinding.recyclerView.setLayoutManager(layoutManager);
            mAdapter = new RecyclerCostServiceAdapter(getActivity());
            mFragmentCostServiceBinding.recyclerView.setAdapter(mAdapter);
            getServiceCostList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getServiceCostList() {
        try {
            NetworkCallController controller = new NetworkCallController(this);
            controller.setListner(new NetworkResponseListner<List<CostData>>() {
                @Override
                public void onResponse(List<CostData> items) {
                    if (items != null && items.size() > 0) {
                        mAdapter.setData(items);
                        mAdapter.notifyDataSetChanged();

                        for (int i = 0; i < items.size(); i++) {
                            totalPrice += (int) Math.round(items.get(i).getCost());
                        }
                        mFragmentCostServiceBinding.txtTotalCost.setText("₹ " + String.valueOf(totalPrice));
                    }
                }

                @Override
                public void onFailure() {

                }
            });
            controller.getServiceCostList(activityId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}