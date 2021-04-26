package com.ab.hicaresalesman.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.ab.hicaresalesman.R;
import com.ab.hicaresalesman.databinding.ItemRecyclerOpportunityBinding;
import com.ab.hicaresalesman.databinding.ItemRecyclerSelectServiceAdapterBinding;
import com.ab.hicaresalesman.handler.OnListItemClickHandler;
import com.ab.hicaresalesman.network.models.opportunity.OpportunityData;
import com.ab.hicaresalesman.network.models.pest_service.ServiceData;
import com.ab.hicaresalesman.viewmodel.OpportunityViewModel;
import com.ab.hicaresalesman.viewmodel.ServiceViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arjun Bhatt on 4/26/2021.
 */
public class RecyclerViewServiceAdapter extends RecyclerView.Adapter<RecyclerViewServiceAdapter.ViewHolder> {

    private OnListItemClickHandler onItemClickHandler;
    private final Context mContext;
    private static List<ServiceViewModel> items = null;

    public RecyclerViewServiceAdapter(Context context) {
        if (items == null) {
            items = new ArrayList<>();
        }
        this.mContext = context;
    }

    @NotNull
    @Override
    public RecyclerViewServiceAdapter.ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        ItemRecyclerSelectServiceAdapterBinding mItemRecyclerSelectServiceAdapterBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_recycler_select_service_adapter, parent, false);
        return new RecyclerViewServiceAdapter.ViewHolder(mItemRecyclerSelectServiceAdapterBinding);
    }

    @Override
    public void onBindViewHolder(@NotNull final RecyclerViewServiceAdapter.ViewHolder holder, final int position) {
        holder.mItemRecyclerSelectServiceAdapterBinding.txtServiceName.setText(items.get(position).getServiceName()+"("+items.get(position).getServiceCode()+")");
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setOnItemClickHandler(OnListItemClickHandler onItemClickHandler) {
        this.onItemClickHandler = onItemClickHandler;
    }


    public void setData(List<ServiceData> data) {
        items.clear();
        for (int index = 0; index < data.size(); index++) {
            ServiceViewModel serviceViewModel = new ServiceViewModel();
            serviceViewModel.clone(data.get(index));
            items.add(serviceViewModel);
        }
    }

    public ServiceViewModel getItem(int position) {
        return items.get(position);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ItemRecyclerSelectServiceAdapterBinding mItemRecyclerSelectServiceAdapterBinding;

        public ViewHolder(ItemRecyclerSelectServiceAdapterBinding mItemRecyclerSelectServiceAdapterBinding) {
            super(mItemRecyclerSelectServiceAdapterBinding.getRoot());
            this.mItemRecyclerSelectServiceAdapterBinding = mItemRecyclerSelectServiceAdapterBinding;
        }
    }

}



