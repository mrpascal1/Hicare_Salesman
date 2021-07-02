package com.ab.hicaresalesman.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ab.hicaresalesman.BaseFragment;
import com.ab.hicaresalesman.R;
import com.ab.hicaresalesman.activities.TaskDetailsActivity;
import com.ab.hicaresalesman.adapters.RecyclerAddActivityAdapter;
import com.ab.hicaresalesman.databinding.FragmentAddTaskBinding;
import com.ab.hicaresalesman.handler.OnItemDeleteClickHandler;
import com.ab.hicaresalesman.handler.OnListItemClickHandler;
import com.ab.hicaresalesman.handler.UserActivityAddClickHandler;
import com.ab.hicaresalesman.network.NetworkCallController;
import com.ab.hicaresalesman.network.NetworkResponseListner;
import com.ab.hicaresalesman.network.models.activity.ActivityData;
import com.ab.hicaresalesman.network.models.activity.AddActivityRequest;
import com.ab.hicaresalesman.network.models.activity.AddActivityResponse;
import com.ab.hicaresalesman.utils.AppUtils;

import java.util.List;
import java.util.Objects;

import es.dmoral.toasty.Toasty;

public class AddTaskFragment extends BaseFragment implements UserActivityAddClickHandler, OnItemDeleteClickHandler, AddActivityBottomSheet.IdialogDismissFragmentReload {
    public static final String ARGS_OPP_NO = "ARGS_OPP_NO";
    public static final String ARGS_INDUSTRY = "ARGS_INDUSTRY";
    public static final String ARGS_INDUSTRY_ID = "ARGS_INDUSTRY_ID";
    FragmentAddTaskBinding mFragmentAddTaskBinding;
    private String opportunityId;
    private String industryName;
    private int industryId;
    RecyclerAddActivityAdapter mAdapter;
    RecyclerView.LayoutManager layoutManager;


    public AddTaskFragment() {
        // Required empty public constructor
    }

    public static AddTaskFragment newInstance(String opportunityId, String industryName, int industryId) {
        AddTaskFragment fragment = new AddTaskFragment();
        Bundle args = new Bundle();
        args.putString(ARGS_OPP_NO, opportunityId);
        args.putString(ARGS_INDUSTRY, industryName);
        args.putInt(ARGS_INDUSTRY_ID, industryId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            opportunityId = getArguments().getString(ARGS_OPP_NO);
            industryName = getArguments().getString(ARGS_INDUSTRY);
            industryId = getArguments().getInt(ARGS_INDUSTRY_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mFragmentAddTaskBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_task, container, false);
        getActivity().setTitle("Site Inspection Sheet");
        mFragmentAddTaskBinding.setHandler(this);
        return mFragmentAddTaskBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentAddTaskBinding.recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        mFragmentAddTaskBinding.recyclerView.setLayoutManager(layoutManager);
        mAdapter = new RecyclerAddActivityAdapter(getActivity());
        mAdapter.setItemDeleteClickHandler(this);
        mFragmentAddTaskBinding.recyclerView.setAdapter(mAdapter);


    }

    @Override
    public void onResume() {
        super.onResume();
        getActivityList();
    }

    private void getActivityList() {
        try {
            NetworkCallController controller = new NetworkCallController(this);

            controller.setListner(new NetworkResponseListner<List<ActivityData>>() {
                @Override
                public void onResponse(List<ActivityData> items) {
                    if (items != null && items.size() > 0) {
                        mAdapter.setData(items);
                        mAdapter.notifyDataSetChanged();
                        mAdapter.setOnItemClickHandler(new OnListItemClickHandler() {
                            @Override
                            public void onItemClick(int position) {
                                startActivity(new Intent(getActivity(), TaskDetailsActivity.class)
                                        .putExtra(TaskDetailsActivity.ARGS_ACTIVITY, mAdapter.getItem(position).getActivityId())
                                        .putExtra(TaskDetailsActivity.ARGS_COST, mAdapter.getItem(position).getCostGenerated())
                                );
                            }
                        });
                    }
                }

                @Override
                public void onFailure() {

                }
            });
            controller.getActivityList(opportunityId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onActivityAddClicked(View view) {
        try {
            AddActivityBottomSheet bottomSheet = new AddActivityBottomSheet(opportunityId, industryName, industryId);
            bottomSheet.setListener(this);
            bottomSheet.show(Objects.requireNonNull(getActivity()).getSupportFragmentManager(), bottomSheet.getTag());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDeleteActivityClicked(int position) {
        try {
            NetworkCallController controller = new NetworkCallController(this);
            AddActivityRequest request = new AddActivityRequest();
            request.setActivityCode(mAdapter.getItem(position).getActivityCode());
            request.setActivityId(mAdapter.getItem(position).getActivityId());
            request.setActivityName(mAdapter.getItem(position).getActivityName());
            request.setIndustryId(mAdapter.getItem(position).getIndistryId());
            request.setIndustryName(mAdapter.getItem(position).getIndustryName());
            request.setOpportunityId(mAdapter.getItem(position).getOpportunityId());
            request.setIsDeleted(true);
            request.setModifiedByIdUser(0);
            request.setCreatedOn(AppUtils.currentDateTime());
            request.setModifiedOn(AppUtils.currentDateTime());
            controller.setListner(new NetworkResponseListner<AddActivityResponse>() {
                @Override
                public void onResponse(AddActivityResponse response) {
                    if (response.getIsSuccess()) {
                        Toasty.success(getActivity(), "Deleted Successfully", Toast.LENGTH_SHORT).show();
                        getActivityList();
                    }
                }

                @Override
                public void onFailure() {

                }
            });
            controller.addActivity(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDismisClick() {
        getActivityList();
    }
}