package com.ab.hicaresalesman.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.ab.hicaresalesman.R;

import com.ab.hicaresalesman.databinding.ItemViewActivityChildAdapterBinding;
import com.ab.hicaresalesman.handler.OnListItemClickHandler;
import com.ab.hicaresalesman.network.models.area.ServiceActivity;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Created by Arjun Bhatt on 5/5/2021.
 */
public class RecycleViewActivityChildAdapter extends RecyclerView.Adapter<RecycleViewActivityChildAdapter.ViewHolder> {

    private OnListItemClickHandler onItemClickHandler;
    private final Context mContext;
    private static List<ServiceActivity> items = null;
    private OnOptionClicked onOptionClicked;

    public RecycleViewActivityChildAdapter(Context context, List<ServiceActivity> mServiceList, OnOptionClicked onOptionClicked) {
        items = mServiceList;
        this.onOptionClicked = onOptionClicked;
        this.mContext = context;
    }

    @NotNull
    @Override
    public RecycleViewActivityChildAdapter.ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        ItemViewActivityChildAdapterBinding mItemViewActivitiesAdapterBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_view_activity_child_adapter, parent, false);
        return new RecycleViewActivityChildAdapter.ViewHolder(mItemViewActivitiesAdapterBinding);
    }

    @Override
    public void onBindViewHolder(@NotNull final RecycleViewActivityChildAdapter.ViewHolder holder, final int position) {
        try {
            holder.mItemViewActivitiesAdapterBinding.txtName.setText(items.get(position).getServiceActivityName());
            holder.mItemViewActivitiesAdapterBinding.chkSelected.setOnCheckedChangeListener(null);
            holder.mItemViewActivitiesAdapterBinding.chkSelected.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    if (isChecked) {
                        onOptionClicked.onClicked(position, true);
                    } else {
                        onOptionClicked.onClicked(position, false);
                    }
                }
            });
            holder.mItemViewActivitiesAdapterBinding.chkSelected.setChecked(items.get(position).getIsSelected());
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


    public void setData(List<ServiceActivity> data) {
        items.clear();

    }

    public ServiceActivity getItem(int position) {
        return items.get(position);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ItemViewActivityChildAdapterBinding mItemViewActivitiesAdapterBinding;

        public ViewHolder(ItemViewActivityChildAdapterBinding mItemViewActivitiesAdapterBinding) {
            super(mItemViewActivitiesAdapterBinding.getRoot());
            this.mItemViewActivitiesAdapterBinding = mItemViewActivitiesAdapterBinding;
        }
    }

    public interface OnOptionClicked {
        void onClicked(int position, boolean isChecked);
    }

}



