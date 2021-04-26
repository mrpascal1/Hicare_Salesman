package com.ab.hicaresalesman.fragments;

import android.graphics.Typeface;
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

import com.ab.hicaresalesman.BaseFragment;
import com.ab.hicaresalesman.R;
import com.ab.hicaresalesman.adapters.RecyclerViewOpportunityAdapter;
import com.ab.hicaresalesman.adapters.RecyclerViewServiceAdapter;
import com.ab.hicaresalesman.databinding.FragmentSelectServiceBinding;
import com.ab.hicaresalesman.handler.OnListItemClickHandler;
import com.ab.hicaresalesman.network.NetworkCallController;
import com.ab.hicaresalesman.network.NetworkResponseListner;
import com.ab.hicaresalesman.network.models.pest_service.ServiceData;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SelectServiceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SelectServiceFragment extends BaseFragment {
    private static final int REQ_SERVICE = 1000;
    FragmentSelectServiceBinding mFragmentSelectServiceBinding;
    RecyclerViewServiceAdapter mAdapter;
    RecyclerView.LayoutManager layoutManager;

    public SelectServiceFragment() {
        // Required empty public constructor
    }

    public static SelectServiceFragment newInstance() {
        SelectServiceFragment fragment = new SelectServiceFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

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
        mFragmentSelectServiceBinding.recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        mFragmentSelectServiceBinding.recyclerView.setLayoutManager(layoutManager);
        mAdapter = new RecyclerViewServiceAdapter(getActivity());
        mFragmentSelectServiceBinding.recyclerView.setAdapter(mAdapter);
        mFragmentSelectServiceBinding.txtHeader.setTypeface(mFragmentSelectServiceBinding.txtHeader.getTypeface(), Typeface.BOLD);
        getServicesByActivity();
    }

    private void getServicesByActivity() {
        try {
            NetworkCallController controller = new NetworkCallController(this);
            controller.setListner(new NetworkResponseListner<List<ServiceData>>() {

                @Override
                public void onResponse(int requestCode, List<ServiceData> items) {
                    if(items!=null && items.size() > 0){
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
                public void onFailure(int requestCode) {

                }
            });
            controller.getServiceByActivity(REQ_SERVICE, 1);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}