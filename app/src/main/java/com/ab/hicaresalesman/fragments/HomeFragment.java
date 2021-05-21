package com.ab.hicaresalesman.fragments;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ab.hicaresalesman.BaseApplication;
import com.ab.hicaresalesman.BaseFragment;
import com.ab.hicaresalesman.R;
import com.ab.hicaresalesman.activities.AddTaskActivity;
import com.ab.hicaresalesman.activities.HomeActivity;
import com.ab.hicaresalesman.activities.LoginActivity;
import com.ab.hicaresalesman.adapters.RecyclerOpportunityAdapter;
import com.ab.hicaresalesman.databinding.FragmentHomeBinding;
import com.ab.hicaresalesman.handler.OnListItemClickHandler;
import com.ab.hicaresalesman.network.NetworkCallController;
import com.ab.hicaresalesman.network.NetworkResponseListner;
import com.ab.hicaresalesman.network.models.login.LoginData;
import com.ab.hicaresalesman.network.models.login.LoginResponse;
import com.ab.hicaresalesman.network.models.opportunity.OpportunityData;
import com.ab.hicaresalesman.utils.SharedPreferencesUtility;
import com.ab.hicaresalesman.viewmodel.OpportunityViewModel;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends BaseFragment {
    FragmentHomeBinding mFragmentHomeBinding;
    RecyclerOpportunityAdapter mAdapter;
    RecyclerView.LayoutManager layoutManager;
    private List<OpportunityData> mOpportunityData;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
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
        mFragmentHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        return mFragmentHomeBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentHomeBinding.recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        mFragmentHomeBinding.recyclerView.setLayoutManager(layoutManager);
        mAdapter = new RecyclerOpportunityAdapter(getActivity());
        mFragmentHomeBinding.recyclerView.setAdapter(mAdapter);

        mFragmentHomeBinding.imgLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                dialog.setTitle(getString(R.string.logout_exit));
                dialog.setMessage(getString(R.string.logout_do_you_really_want_to_exit));
                dialog.setPositiveButton(getString(R.string.yes), (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                    SharedPreferencesUtility.savePrefBoolean(getActivity(), SharedPreferencesUtility.IS_USER_LOGIN,
                            false);
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                    getActivity().finish();
                });
                dialog.setNegativeButton(getString(R.string.no), (dialogInterface, i) -> dialogInterface.dismiss());
                dialog.show();
            }
        });

        getRecentOpportunity();
        getSearchOpportunity();
    }

    private void getRecentOpportunity() {
        try {
            if ((HomeActivity) getActivity() != null) {
                RealmResults<LoginData> LoginRealmModels =
                        BaseApplication.getRealm().where(LoginData.class).findAll();
                if (LoginRealmModels != null && LoginRealmModels.size() > 0) {
                    String id = LoginRealmModels.get(0).getId();
                    NetworkCallController controller = new NetworkCallController(this);
                    controller.setListner(new NetworkResponseListner<List<OpportunityData>>() {
                        @Override
                        public void onResponse(List<OpportunityData> items) {
                            if (items != null && items.size() > 0) {
                                mOpportunityData = new ArrayList<>();
                                mOpportunityData = items;
                                mAdapter.setData(items);
                                mAdapter.notifyDataSetChanged();
                                mAdapter.setOnItemClickHandler(new OnListItemClickHandler() {
                                    @Override
                                    public void onItemClick(int position) {
                                        startActivity(new Intent(getActivity(), AddTaskActivity.class)
                                                .putExtra(AddTaskActivity.ARGS_OPP_NO, items.get(position).getOpportunityNumberC())
                                                .putExtra(AddTaskActivity.ARGS_INDUSTRY, items.get(position).getSubTypeC())
                                        );
                                    }
                                });
                            }
                        }

                        @Override
                        public void onFailure() {

                        }
                    });
                    controller.getRecentOpportunity(id);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void filter(String text, List<OpportunityData> items) {
        // creating a new array list to filter our data.
        ArrayList<OpportunityViewModel> filteredlist = new ArrayList<>();

        // running a for loop to compare elements.
        for (OpportunityData item : mOpportunityData) {
            OpportunityViewModel opportunityViewModel = new OpportunityViewModel();
            opportunityViewModel.clone(item);
            filteredlist.add(opportunityViewModel);
        }
//        for (int index = 0; index < items.size(); index++) {
//            OpportunityViewModel opportunityViewModel = new OpportunityViewModel();
//            opportunityViewModel.clone(items.get(index));
//            filteredlist.add(opportunityViewModel);
//        }

        if (filteredlist.isEmpty()) {
            // if no item is added in filtered list we are
            // displaying a toast message as no data found.
            Toast.makeText(getActivity(), "No Data Found..", Toast.LENGTH_SHORT).show();
        } else {
            // at last we are passing that filtered
            // list to our adapter class.
            mAdapter.filterList(filteredlist);
        }
    }

    private void getSearchOpportunity() {
        try {

            mFragmentHomeBinding.search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    searchByOpportunity(query);
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {

                    return false;
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void searchByOpportunity(String newText) {
        try {
            NetworkCallController controller = new NetworkCallController(HomeFragment.this);
            controller.setListner(new NetworkResponseListner<List<OpportunityData>>() {
                @Override
                public void onResponse(List<OpportunityData> items) {
                    if (items != null) {
                        filter(newText, items);
                    }
                }

                @Override
                public void onFailure() {

                }
            });
            controller.getSearchOpportunity(newText, "00528000002IZ04AAG");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}