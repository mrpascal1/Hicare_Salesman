package com.ab.hicaresalesman.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.ab.hicaresalesman.R;
import com.ab.hicaresalesman.databinding.ItemCostServiceRecyclerAdapterBinding;
import com.ab.hicaresalesman.databinding.ItemRecycleFrequencyAdapterBinding;
import com.ab.hicaresalesman.handler.OnListItemClickHandler;
import com.ab.hicaresalesman.network.models.cost_service_list.CostData;
import com.ab.hicaresalesman.network.models.frequency.FrequencyData;
import com.ab.hicaresalesman.network.models.frequency.RecommendedFrequency;
import com.ab.hicaresalesman.viewmodel.CostViewModel;
import com.ab.hicaresalesman.viewmodel.FrequencyViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arjun Bhatt on 6/16/2021.
 */
public class RecyclerCostServiceAdapter extends RecyclerView.Adapter<RecyclerCostServiceAdapter.ViewHolder> {

    private OnListItemClickHandler onItemClickHandler;
    private final Context mContext;
    private static List<CostViewModel> items = null;

    public RecyclerCostServiceAdapter(Context context) {
        if (items == null) {
            items = new ArrayList<>();
        }
        this.mContext = context;
    }

    @NotNull
    @Override
    public RecyclerCostServiceAdapter.ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        ItemCostServiceRecyclerAdapterBinding mItemCostServiceRecyclerAdapterBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_cost_service_recycler_adapter, parent, false);
        return new RecyclerCostServiceAdapter.ViewHolder(mItemCostServiceRecyclerAdapterBinding);
    }

    @Override
    public void onBindViewHolder(@NotNull final RecyclerCostServiceAdapter.ViewHolder holder, final int position) {
        try {
            holder.mItemCostServiceRecyclerAdapterBinding.txtTitle.setText(items.get(position).getServiceName());
            holder.mItemCostServiceRecyclerAdapterBinding.txtTitle.setTypeface(holder.mItemCostServiceRecyclerAdapterBinding.txtTitle.getTypeface(), Typeface.BOLD);
            holder.mItemCostServiceRecyclerAdapterBinding.txtCost.setTypeface(holder.mItemCostServiceRecyclerAdapterBinding.txtCost.getTypeface(), Typeface.BOLD);
            holder.mItemCostServiceRecyclerAdapterBinding.txtTotalService.setText(String.valueOf(items.get(position).getTotalService()));
            holder.mItemCostServiceRecyclerAdapterBinding.txtCost.setText(String.valueOf("₹ " + (int) Math.round(items.get(position).getServiceCost())));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setOnItemClickHandler(OnListItemClickHandler onItemClickHandler) {
        this.onItemClickHandler = onItemClickHandler;
    }

    public void setData(List<CostData> data) {
        items.clear();
        for (int index = 0; index < data.size(); index++) {
            CostViewModel costViewModel = new CostViewModel();
            costViewModel.clone(data.get(index));
            items.add(costViewModel);
        }
    }

    public CostViewModel getItem(int position) {
        return items.get(position);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ItemCostServiceRecyclerAdapterBinding mItemCostServiceRecyclerAdapterBinding;

        public ViewHolder(ItemCostServiceRecyclerAdapterBinding mItemCostServiceRecyclerAdapterBinding) {
            super(mItemCostServiceRecyclerAdapterBinding.getRoot());
            this.mItemCostServiceRecyclerAdapterBinding = mItemCostServiceRecyclerAdapterBinding;
        }
    }
}

