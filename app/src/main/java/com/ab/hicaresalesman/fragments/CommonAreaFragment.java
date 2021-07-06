package com.ab.hicaresalesman.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
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
import com.ab.hicaresalesman.adapters.RecyclerCommonAreaAdapter;
import com.ab.hicaresalesman.databinding.FragmentCommonAreaBinding;
import com.ab.hicaresalesman.handler.OnAreaClickedHandler;
import com.ab.hicaresalesman.network.models.area.AddAreaRequest;
import com.ab.hicaresalesman.network.models.area.AreaData;
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


public class CommonAreaFragment extends BaseFragment implements OnAreaClickedHandler {
    FragmentCommonAreaBinding mFragmentCommonAreaBinding;
    RecyclerCommonAreaAdapter mAdapter;
    RecyclerView.LayoutManager layoutManager;
    private List<AreaData> mDataList;
    private List<AddAreaRequest> areaList;
    private List<ServiceActivityType> mServiceList = new ArrayList<>();
    private HashMap<Integer, AddAreaRequest> map = new HashMap<>();
    private boolean isCostGenerated = false;
    public static final String ARGS_COST = "ARGS_COST";


    public CommonAreaFragment() {
        // Required empty public constructor
    }

    public static CommonAreaFragment newInstance(boolean isCostGenerated) {
        CommonAreaFragment fragment = new CommonAreaFragment();
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
        mFragmentCommonAreaBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_common_area, container, false);
        return mFragmentCommonAreaBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (map != null) {
            map.clear();
        }
        if (AppUtils.isCommonAllowMultiple) {
        } else {
            mDataList = new ArrayList<>();
            if (AppUtils.mCommonTowerList != null) {
                for (TowerData data : AppUtils.mCommonTowerList) {
                    mDataList.addAll(data.getData());
                }
            }
            mFragmentCommonAreaBinding.recyclerView.setHasFixedSize(true);
            mFragmentCommonAreaBinding.recyclerView.hasFixedSize();
            layoutManager = new LinearLayoutManager(getActivity());
            mFragmentCommonAreaBinding.recyclerView.setLayoutManager(layoutManager);
            getSort();

            mAdapter = new RecyclerCommonAreaAdapter(getActivity(), mDataList, isCostGenerated, (position, areaId, towerId, charSeq) -> {

                try {
                    if (getActivity() != null) {
                        RealmResults<LoginData> LoginRealmModels =
                                BaseApplication.getRealm().where(LoginData.class).findAll();
                        if (LoginRealmModels != null && LoginRealmModels.size() > 0) {
                            int id = LoginRealmModels.get(0).getUserId();
                            mServiceList = mAdapter.getItem(position).getServiceActivity();
                            if (mServiceList != null) {
                                AppUtils.mServiceList = mServiceList;
                            }
                            AddAreaRequest request = new AddAreaRequest();
                            request.setActivityId(mAdapter.getItem(position).getActivityId());
                            request.setAdditionalSqFt(mAdapter.getItem(position).getAdditionalSqFt());
                            request.setAreaSqFt(mAdapter.getItem(position).getAreaSqFt());
                            request.setCreatedBy(id);
                            request.setFloorNo(mAdapter.getItem(position).getFloorNo());
                            request.setServiceActivity(AppUtils.mServiceList);
                            request.setTotalArea(mAdapter.getItem(position).getTotalArea());
                            request.setSubareaId(mAdapter.getItem(position).getSubareaId());
                            request.setTemplateName(mAdapter.getItem(position).getTemplateName());
                            request.setSubareaName(mAdapter.getItem(position).getSubareaName());
                            request.setTowerNo(mAdapter.getItem(position).getTowerNo());
                            request.setUnit(mAdapter.getItem(position).getUnit());
                            request.setTemplateType(mAdapter.getItem(position).getTemplateType());

                            if(request.getAreaSqFt()!=null && request.getUnit()!=null){
                                if (!request.getUnit().equals("") && !request.getAreaSqFt().equals("")) {
                                    AppUtils.mHashArea.put(mAdapter.getItem(position).getUniqueValue(), request);
                                }
                            }
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            });
            mFragmentCommonAreaBinding.recyclerView.setAdapter(mAdapter);
            mAdapter.setmOnAreaClickedHandler(this);
            mAdapter.notifyDataSetChanged();
        }
    }

    public void getSort() {
        Collections.sort(mDataList, new Comparator<AreaData>() {
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
        data.setUnit(mAdapter.getItem(parentPosition).getUnit());
        data.setFloorNo(mAdapter.getItem(parentPosition).getFloorNo());
        data.setFloorList(mAdapter.getItem(parentPosition).getFloorList());
        data.setAreaSqFt(mAdapter.getItem(parentPosition).getAreaSqFt());
        data.setAdditionalSqFt(mAdapter.getItem(parentPosition).getAdditionalSqFt());
        data.setAreaMappingId(mAdapter.getItem(parentPosition).getAreaMappingId());
        data.setIndustryId(mAdapter.getItem(parentPosition).getIndustryId());
        data.setAllowMultiple(mAdapter.getItem(parentPosition).getAllowMultiple());
        data.setActivityId(mAdapter.getItem(parentPosition).getActivityId());
        data.setIndustryName(mAdapter.getItem(parentPosition).getIndustryName());
        data.setSubareaId(mAdapter.getItem(parentPosition).getSubareaId());
        data.setServiceActivity(mAdapter.getItem(parentPosition).getServiceActivity());
        data.setTowerNo(mAdapter.getItem(parentPosition).getTowerNo());
        data.setTotalArea(mAdapter.getItem(parentPosition).getTotalArea());
        data.setTemplateType(mAdapter.getItem(parentPosition).getTemplateType());
        data.setTemplateName(mAdapter.getItem(parentPosition).getTemplateName());
        data.setSubareaName(mAdapter.getItem(parentPosition).getSubareaName());
        mDataList.add(data);
        Toasty.success(getActivity(), mDataList.get(parentPosition).getSubareaName() + " cloned successfully.", Toast.LENGTH_SHORT).show();
        getSort();
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDeleteClicked(int parentPosition, int childPosition) {
        if (mDataList.get(parentPosition).isCloned()) {
            mDataList.remove(parentPosition);
            mAdapter.notifyDataSetChanged();
            getSort();
        }
    }

    @Override
    public void onViewActivityClicked(int parentPosition, int childPosition) {
        try {
            mServiceList = mAdapter.getItem(parentPosition).getServiceActivity();
            if (mServiceList.size() > 0) {
                ViewActivityFragment dialog = ViewActivityFragment.newInstance(mServiceList);
                dialog.show(getActivity().getSupportFragmentManager(), "check_up");
            } else {
                Toast.makeText(getActivity(), "No Data Available!", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}