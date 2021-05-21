package com.ab.hicaresalesman.fragments;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ab.hicaresalesman.BaseFragment;
import com.ab.hicaresalesman.R;
import com.ab.hicaresalesman.adapters.RecycleSelectServiceAdapter;
import com.ab.hicaresalesman.databinding.FragmentSelectServiceBinding;
import com.ab.hicaresalesman.handler.OnListItemClickHandler;
import com.ab.hicaresalesman.handler.OnNextEventHandler;
import com.ab.hicaresalesman.network.NetworkCallController;
import com.ab.hicaresalesman.network.NetworkResponseListner;
import com.ab.hicaresalesman.network.models.BaseResponse;
import com.ab.hicaresalesman.network.models.pest_service.AddServiceRequest;
import com.ab.hicaresalesman.network.models.pest_service.ServiceData;
import com.ab.hicaresalesman.utils.AppUtils;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import es.dmoral.toasty.Toasty;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SelectServiceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SelectServiceFragment extends BaseFragment {
    FragmentSelectServiceBinding mFragmentSelectServiceBinding;
    public static final String ARGS_ACTIVITY = "ARGS_ACTIVITY";

    RecycleSelectServiceAdapter mAdapter;
    RecyclerView.LayoutManager layoutManager;
    private OnNextEventHandler mCallback;
    private ViewPager viewPager;
    private int activityId = 0;
    private HashMap<Integer, AddServiceRequest> mMap = new HashMap<>();

    private List<AddServiceRequest> mServiceList = new ArrayList<>();

    public SelectServiceFragment() {
        // Required empty public constructor
    }

    public static SelectServiceFragment newInstance(int activityId) {
        SelectServiceFragment fragment = new SelectServiceFragment();
        Bundle args = new Bundle();
        args.putInt(ARGS_ACTIVITY, activityId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onSaveInstanceState(Bundle oldInstanceState) {
        super.onSaveInstanceState(oldInstanceState);
    }

    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
        try {
            mCallback = (OnNextEventHandler) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement FragmentToActivity");
        }
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
        mFragmentSelectServiceBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_select_service, container, false);
        return mFragmentSelectServiceBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPager = getActivity().findViewById(R.id.viewPager);
        mFragmentSelectServiceBinding.recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        mFragmentSelectServiceBinding.recyclerView.setLayoutManager(layoutManager);
        mAdapter = new RecycleSelectServiceAdapter(getActivity(), mMap, (serviceId, position, isChecked) -> {
            if(isChecked){
                AddServiceRequest request = new AddServiceRequest();
                request.setActivityId(activityId);
                request.setServiceId(mAdapter.getItem(position).getServiceId());
                request.setServiceName(mAdapter.getItem(position).getServiceName());
                request.setServiceCode(mAdapter.getItem(position).getServiceCode());
                request.setCreatedBy(mAdapter.getItem(position).getCreatedBy());
                mMap.put(serviceId, request);
            }else {
                if (mMap.containsKey(serviceId)) {
                    mMap.remove(serviceId);
                }
            }
//            if (isChecked) {
//                AddServiceRequest request = new AddServiceRequest();
//                request.setActivityId(activityId);
//                request.setServiceId(mAdapter.getItem(position).getServiceId());
//                request.setServiceName(mAdapter.getItem(position).getServiceName());
//                request.setServiceCode(mAdapter.getItem(position).getServiceCode());
//                request.setCreatedBy(mAdapter.getItem(position).getCreatedBy());
//                mMap.put(serviceId, request);
//            } else {
//                if (mMap.containsKey(serviceId)) {
//                    mMap.remove(serviceId);
//                }
//            }
        });
        mFragmentSelectServiceBinding.recyclerView.setAdapter(mAdapter);
        mFragmentSelectServiceBinding.txtHeader.setTypeface(mFragmentSelectServiceBinding.txtHeader.getTypeface(), Typeface.BOLD);
        getServicesByActivity();
    }

    private void getServicesByActivity() {
        try {

            NetworkCallController controller = new NetworkCallController(this);
            controller.setListner(new NetworkResponseListner<List<ServiceData>>() {

                @Override
                public void onResponse(List<ServiceData> items) {
                    if (items != null && items.size() > 0) {
                        mAdapter.setData(items);
                        mAdapter.notifyDataSetChanged();
                        mAdapter.setOnItemClickHandler(new OnListItemClickHandler() {
                            @Override
                            public void onItemClick(int position) {

                            }
                        });
                    }
                }

                @Override
                public void onFailure() {

                }
            });
            controller.getServiceByActivity(activityId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addServiceByActivity() {
        try {
            if (mMap != null && mMap.size() > 0) {
                mServiceList = new ArrayList<>(mMap.values());
                NetworkCallController controller = new NetworkCallController(this);
                controller.setListner(new NetworkResponseListner<BaseResponse>() {
                    @Override
                    public void onResponse(BaseResponse response) {
                        if (response.getIsSuccess()) {
                            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
                        }
                    }

                    @Override
                    public void onFailure() {

                    }
                });
                controller.addServiceByActivity(mServiceList);
            } else {
                Toasty.error(getActivity(), "Please select service!",Toasty.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void refresh() {
        getServicesByActivity();
    }
}