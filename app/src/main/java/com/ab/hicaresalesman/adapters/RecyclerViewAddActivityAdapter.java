package com.ab.hicaresalesman.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.ab.hicaresalesman.R;
import com.ab.hicaresalesman.databinding.ItemRecyclerActivityAdapterBinding;
import com.ab.hicaresalesman.databinding.ItemRecyclerOpportunityBinding;
import com.ab.hicaresalesman.handler.OnItemDeleteClickHandler;
import com.ab.hicaresalesman.handler.OnListItemClickHandler;
import com.ab.hicaresalesman.network.models.activity.ActivityData;
import com.ab.hicaresalesman.network.models.opportunity.OpportunityData;
import com.ab.hicaresalesman.utils.AppUtils;
import com.ab.hicaresalesman.viewmodel.ActivityViewModel;
import com.ab.hicaresalesman.viewmodel.OpportunityViewModel;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arjun Bhatt on 4/23/2021.
 */
public class RecyclerViewAddActivityAdapter extends RecyclerView.Adapter<RecyclerViewAddActivityAdapter.ViewHolder> {

    private OnListItemClickHandler onItemClickHandler;
    private OnItemDeleteClickHandler mItemDeleteClickHandler;
    private final Context mContext;
    private static List<ActivityViewModel> items = null;

    public RecyclerViewAddActivityAdapter(Context context) {
        if (items == null) {
            items = new ArrayList<>();
        }
        this.mContext = context;
    }

    @NotNull
    @Override
    public RecyclerViewAddActivityAdapter.ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        ItemRecyclerActivityAdapterBinding mRecyclerActivityAdapterBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_recycler_activity_adapter, parent, false);
        return new RecyclerViewAddActivityAdapter.ViewHolder(mRecyclerActivityAdapterBinding);
    }

    @Override
    public void onBindViewHolder(@NotNull final RecyclerViewAddActivityAdapter.ViewHolder holder, final int position) {
        try {
            holder.itemRecyclerActivityAdapterBinding.txtActivityName.setText(items.get(position).getActivityName());
            holder.itemRecyclerActivityAdapterBinding.txtActivityCode.setText(items.get(position).getActivityCode());
            holder.itemRecyclerActivityAdapterBinding.txtIndustryName.setText(items.get(position).getIndustryName());
            holder.itemRecyclerActivityAdapterBinding.txtActivityName.setTypeface(holder.itemRecyclerActivityAdapterBinding.txtActivityName.getTypeface(), Typeface.BOLD);
            String dt = items.get(position).getModifiedOn();
            String time = null;
            String date = null;
            try {
                time = AppUtils.reFormatDateTime(dt, "HH:mm");
                date = AppUtils.reFormatDateTime(dt, "MMM dd, yyyy");

            } catch (ParseException e) {
                e.printStackTrace();
            }
            holder.itemRecyclerActivityAdapterBinding.txtModifidOn.setText("At " + time + " on " + date);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickHandler.onItemClick(position);
                }
            });

            holder.itemRecyclerActivityAdapterBinding.imgDelete.setOnClickListener(view -> mItemDeleteClickHandler.onDeleteActivityClicked(position));

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

    public void setItemDeleteClickHandler(OnItemDeleteClickHandler mOnItemDeleteClickHandler) {
        this.mItemDeleteClickHandler = mOnItemDeleteClickHandler;
    }

    public void setData(List<ActivityData> data) {
        items.clear();
        for (int index = 0; index < data.size(); index++) {
            ActivityViewModel activityViewModel = new ActivityViewModel();
            activityViewModel.clone(data.get(index));
            items.add(activityViewModel);
        }
    }

    public ActivityViewModel getItem(int position) {
        return items.get(position);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ItemRecyclerActivityAdapterBinding itemRecyclerActivityAdapterBinding;

        public ViewHolder(ItemRecyclerActivityAdapterBinding itemRecyclerActivityAdapterBinding) {
            super(itemRecyclerActivityAdapterBinding.getRoot());
            this.itemRecyclerActivityAdapterBinding = itemRecyclerActivityAdapterBinding;
        }
    }

}


