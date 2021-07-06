package com.ab.hicaresalesman.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ab.hicaresalesman.R;
import com.ab.hicaresalesman.databinding.ItemViewActivityChildAdapterBinding;
import com.ab.hicaresalesman.databinding.ItemViewActivityParentAdapterBinding;
import com.ab.hicaresalesman.handler.OnListItemClickHandler;
import com.ab.hicaresalesman.network.models.area.ServiceActivity;
import com.ab.hicaresalesman.network.models.area.ServiceActivityType;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Created by Arjun Bhatt on 5/17/2021.
 */
public class RecyclerViewActivityParentAdapter extends RecyclerView.Adapter<RecyclerViewActivityParentAdapter.ViewHolder> {

    private OnListItemClickHandler onItemClickHandler;
    private final Context mContext;
    private static List<ServiceActivityType> items = null;
    private OnOptionClicked onOptionClicked;

    public RecyclerViewActivityParentAdapter(Context context, List<ServiceActivityType> mServiceList, OnOptionClicked onOptionClicked) {
        items = mServiceList;
        this.onOptionClicked = onOptionClicked;
        this.mContext = context;
    }

    @NotNull
    @Override
    public RecyclerViewActivityParentAdapter.ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        ItemViewActivityParentAdapterBinding mItemViewActivitiesAdapterBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_view_activity_parent_adapter, parent, false);
        return new RecyclerViewActivityParentAdapter.ViewHolder(mItemViewActivitiesAdapterBinding);
    }

    @Override
    public void onBindViewHolder(@NotNull final RecyclerViewActivityParentAdapter.ViewHolder holder, final int position) {
        holder.mItemViewActivitiesAdapterBinding.txtHeader.setText(items.get(position).getServiceType());
        holder.mItemViewActivitiesAdapterBinding.txtHeader.setTypeface(holder.mItemViewActivitiesAdapterBinding.txtHeader.getTypeface(), Typeface.BOLD);
        RecycleViewActivityChildAdapter childAdapter = new RecycleViewActivityChildAdapter(mContext, items.get(position).getServiceList(), (posChild, isChecked) -> {
            onOptionClicked.onClicked(position, posChild, isChecked);
        });
        holder.mItemViewActivitiesAdapterBinding.recycleChild.setLayoutManager(new LinearLayoutManager(mContext));
        holder.mItemViewActivitiesAdapterBinding.recycleChild.setHasFixedSize(true);
        holder.mItemViewActivitiesAdapterBinding.recycleChild.setClipToPadding(false);
        holder.mItemViewActivitiesAdapterBinding.recycleChild.setAdapter(childAdapter);
        childAdapter.notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setOnItemClickHandler(OnListItemClickHandler onItemClickHandler) {
        this.onItemClickHandler = onItemClickHandler;
    }


    public void setData(List<ServiceActivityType> data) {
        items.clear();
    }

    public ServiceActivityType getItem(int position) {
        return items.get(position);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ItemViewActivityParentAdapterBinding mItemViewActivitiesAdapterBinding;

        public ViewHolder(ItemViewActivityParentAdapterBinding mItemViewActivitiesAdapterBinding) {
            super(mItemViewActivitiesAdapterBinding.getRoot());
            this.mItemViewActivitiesAdapterBinding = mItemViewActivitiesAdapterBinding;
        }
    }

    public interface OnOptionClicked {
        void onClicked(int parentPos, int childPosition, boolean isChecked);
    }

}



