package com.ab.hicaresalesman.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ab.hicaresalesman.BaseApplication;
import com.ab.hicaresalesman.BaseFragment;
import com.ab.hicaresalesman.R;
import com.ab.hicaresalesman.activities.HomeActivity;
import com.ab.hicaresalesman.adapters.RecyclerCommonAreaAdapter;
import com.ab.hicaresalesman.adapters.RecyclerRegularParentAdapter;
import com.ab.hicaresalesman.databinding.FragmentRegularAreaBinding;
import com.ab.hicaresalesman.handler.OnAreaClickedHandler;
import com.ab.hicaresalesman.handler.OnItemCloneClickHandler;
import com.ab.hicaresalesman.network.models.area.AddAreaRequest;
import com.ab.hicaresalesman.network.models.area.AreaData;
import com.ab.hicaresalesman.network.models.area.AreaType;
import com.ab.hicaresalesman.network.models.area.ServiceActivity;
import com.ab.hicaresalesman.network.models.area.ServiceActivityType;
import com.ab.hicaresalesman.network.models.area.TowerData;
import com.ab.hicaresalesman.network.models.login.LoginData;
import com.ab.hicaresalesman.utils.AppUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import es.dmoral.toasty.Toasty;
import io.realm.RealmResults;


public class RegularAreaFragment extends BaseFragment implements OnAreaClickedHandler, OnItemCloneClickHandler {
    FragmentRegularAreaBinding mFragmentRegularAreaBinding;
    RecyclerRegularParentAdapter mAdapter;
    RecyclerView.LayoutManager layoutManager;
    private List<TowerData> mTowerList;
    private HashMap<Integer, AddAreaRequest> map = new HashMap<>();
    private List<ServiceActivityType> mServiceList = new ArrayList<>();
    private List<AreaData> mAreaList = new ArrayList<>();
    private List<AddAreaRequest> areaList;
    private boolean isCostGenerated = false;
    public static final String ARGS_COST = "ARGS_COST";



    public RegularAreaFragment() {
        // Required empty public constructor
    }


    public static RegularAreaFragment newInstance(boolean isCostGenerated) {
        RegularAreaFragment fragment = new RegularAreaFragment();
        Bundle args = new Bundle();
        args.putBoolean(ARGS_COST, isCostGenerated);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            isCostGenerated = getArguments().getBoolean(ARGS_COST);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mFragmentRegularAreaBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_regular_area, container, false);
        return mFragmentRegularAreaBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (map != null) {
            map.clear();
        }
        if (AppUtils.isRegularAllowMultiple) {
            mTowerList = new ArrayList<>();
            mTowerList = AppUtils.mRegularTowerList;
            mFragmentRegularAreaBinding.recyclerView.setHasFixedSize(true);
            layoutManager = new LinearLayoutManager(getActivity());
            mFragmentRegularAreaBinding.recyclerView.setLayoutManager(layoutManager);
            mAdapter = new RecyclerRegularParentAdapter(getActivity(), mTowerList, isCostGenerated, (parentPos, childPos, subId, towerId, charSeq) -> {
                try {
                    if (getActivity() != null) {
                        RealmResults<LoginData> LoginRealmModels =
                                BaseApplication.getRealm().where(LoginData.class).findAll();
                        if (LoginRealmModels != null && LoginRealmModels.size() > 0) {
                            int id = LoginRealmModels.get(0).getUserId();

                            mServiceList = mAdapter.getItem(parentPos).getData().get(childPos).getServiceActivity();
                            if (mServiceList != null) {
                                AppUtils.mServiceList = mServiceList;
                            }
                            AddAreaRequest request = new AddAreaRequest();
                            request.setActivityId(mAdapter.getItem(parentPos).getData().get(childPos).getActivityId());
                            request.setAdditionalSqFt(mAdapter.getItem(parentPos).getData().get(childPos).getAdditionalSqFt());
                            request.setAreaSqFt(mAdapter.getItem(parentPos).getData().get(childPos).getAreaSqFt());
                            request.setCreatedBy(id);
                            request.setFloorNo(mAdapter.getItem(parentPos).getData().get(childPos).getFloorNo());
                            request.setServiceActivity(AppUtils.mServiceList);
                            request.setTotalArea(mAdapter.getItem(parentPos).getData().get(childPos).getTotalArea());
                            request.setSubareaId(mAdapter.getItem(parentPos).getData().get(childPos).getSubareaId());
                            request.setTemplateName(mAdapter.getItem(parentPos).getData().get(childPos).getTemplateName());
                            request.setSubareaName(mAdapter.getItem(parentPos).getData().get(childPos).getSubareaName());
                            if (mAdapter.getItem(parentPos).getTower() == 0) {
                                request.setTowerNo(mAdapter.getItem(parentPos).getTower() + 1);
                            } else {
                                request.setTowerNo(mAdapter.getItem(parentPos).getTower());
                            }

                            request.setUnit(mAdapter.getItem(parentPos).getData().get(childPos).getUnit());
                            request.setTemplateType(mAdapter.getItem(parentPos).getData().get(childPos).getTemplateType());

                            if (request.getUnit() != null && request.getAreaSqFt() != null) {
                                if (!request.getUnit().equals("") && !request.getAreaSqFt().equals("")) {
                                    if (mAdapter.getItem(parentPos).getTower() == 1) {
                                        AppUtils.mHashArea.put(mAdapter.getItem(parentPos).getData().get(childPos).getUniqueValue(), request);
                                    } else {
                                        AppUtils.mHashArea.put(mAdapter.getItem(parentPos).getData().get(childPos).getUniqueValue() + mAdapter.getItem(parentPos).getTower(), request);
                                    }
                                }
                            }

                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            mFragmentRegularAreaBinding.recyclerView.setAdapter(mAdapter);
            mAdapter.setOnAreaClickedHandler(this);
            mAdapter.setOnCloneClickHandler(this);
            mAdapter.notifyDataSetChanged();
        }
    }

    public void getSortArea() {
        Collections.sort(mAdapter.getItem(0).getData(), new Comparator<AreaData>() {
            @Override
            public int compare(AreaData areaData, AreaData t1) {
                return areaData.getSubareaName().compareTo(t1.getSubareaName());
            }
        });
    }

    @Override
    public void onCloneClicked(int parentPosition, int childPosition) {
        AreaData data = new AreaData();
        data.setCloned(true);
        data.setUnit(mAdapter.getItem(parentPosition).getData().get(childPosition).getUnit());
        data.setFloorNo(mAdapter.getItem(parentPosition).getData().get(childPosition).getFloorNo());
        data.setFloorList(mAdapter.getItem(parentPosition).getData().get(childPosition).getFloorList());
        data.setAreaSqFt(mAdapter.getItem(parentPosition).getData().get(childPosition).getAreaSqFt());
        data.setAdditionalSqFt(mAdapter.getItem(parentPosition).getData().get(childPosition).getAdditionalSqFt());
        data.setAreaMappingId(mAdapter.getItem(parentPosition).getData().get(childPosition).getAreaMappingId());
        data.setIndustryId(mAdapter.getItem(parentPosition).getData().get(childPosition).getIndustryId());
        data.setAllowMultiple(mAdapter.getItem(parentPosition).getData().get(childPosition).getAllowMultiple());
        data.setActivityId(mAdapter.getItem(parentPosition).getData().get(childPosition).getActivityId());
        data.setIndustryName(mAdapter.getItem(parentPosition).getData().get(childPosition).getIndustryName());
        data.setSubareaId(mAdapter.getItem(parentPosition).getData().get(childPosition).getSubareaId());
        data.setServiceActivity(mAdapter.getItem(parentPosition).getData().get(childPosition).getServiceActivity());
        data.setTowerNo(mAdapter.getItem(parentPosition).getData().get(childPosition).getTowerNo() + 1);
        data.setTotalArea(mAdapter.getItem(parentPosition).getData().get(childPosition).getTotalArea());
        data.setTemplateType(mAdapter.getItem(parentPosition).getData().get(childPosition).getTemplateType());
        data.setTemplateName(mAdapter.getItem(parentPosition).getData().get(childPosition).getTemplateName());
        data.setSubareaName(mAdapter.getItem(parentPosition).getData().get(childPosition).getSubareaName());
        mAdapter.getItem(parentPosition).getData().add(data);
        Toasty.success(getActivity(), mAdapter.getItem(parentPosition).getData().get(childPosition).getSubareaName() + " cloned successfully.", Toast.LENGTH_SHORT).show();
        getSortArea();
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDeleteClicked(int parentPosition, int childPosition) {
        mAdapter.getItem(parentPosition).getData().remove(childPosition);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onViewActivityClicked(int parentPosition, int childPosition) {
        try {
            mServiceList = mAdapter.getItem(parentPosition).getData().get(childPosition).getServiceActivity();
//            mServiceList = AppUtils.mServiceList;
            if (mServiceList.size() > 0) {
                ViewActivityFragment dialog = ViewActivityFragment.newInstance(mServiceList);
                dialog.show(getActivity().getSupportFragmentManager(), "check_up");
            } else {
                Toast.makeText(getActivity(), "Data Not Available!", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCloneAreaClicked(int position) {
        TowerData data = new TowerData();
        data.setTower(mAdapter.getItem(position).getTower() + 1);
        data.setData(mAdapter.getItem(position).getData());
        data.setCloned(true);
        mTowerList.add(data);
        mAdapter.notifyDataSetChanged();
        Toasty.success(getActivity(), "Tower " + String.valueOf(mAdapter.getItem(position).getTower()) + " cloned successfully.", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onTowerDeleteClicked(int position) {
        if (mTowerList.get(position).isCloned()) {
            mTowerList.remove(position);
            mAdapter.notifyDataSetChanged();
        }
    }
}