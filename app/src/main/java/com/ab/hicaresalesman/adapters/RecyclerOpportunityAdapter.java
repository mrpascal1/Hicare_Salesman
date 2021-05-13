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
import com.ab.hicaresalesman.handler.OnListItemClickHandler;
import com.ab.hicaresalesman.network.models.opportunity.OpportunityData;
import com.ab.hicaresalesman.viewmodel.OpportunityViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arjun Bhatt on 4/22/2021.
 */
public class RecyclerOpportunityAdapter extends RecyclerView.Adapter<RecyclerOpportunityAdapter.ViewHolder> {

    private OnListItemClickHandler onItemClickHandler;
    private final Context mContext;
    private static List<OpportunityViewModel> items = null;

    public RecyclerOpportunityAdapter(Context context) {
        if (items == null) {
            items = new ArrayList<>();
        }
        this.mContext = context;
    }

    @NotNull
    @Override
    public RecyclerOpportunityAdapter.ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        ItemRecyclerOpportunityBinding mItemRecyclerOpportunityBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_recycler_opportunity, parent, false);
        return new RecyclerOpportunityAdapter.ViewHolder(mItemRecyclerOpportunityBinding);
    }

    @Override
    public void onBindViewHolder(@NotNull final RecyclerOpportunityAdapter.ViewHolder holder, final int position) {
        try {
            holder.mItemRecyclerOpportunityBinding.txtName.setText(items.get(position).getName());
            holder.mItemRecyclerOpportunityBinding.txtName.setTypeface(holder.mItemRecyclerOpportunityBinding.txtName.getTypeface(),Typeface.BOLD);
            holder.mItemRecyclerOpportunityBinding.txtCustType.setTypeface(holder.mItemRecyclerOpportunityBinding.txtCustType.getTypeface(),Typeface.BOLD);
            holder.mItemRecyclerOpportunityBinding.txtCustType.setText(items.get(position).getCustomerType().toUpperCase());
            holder.mItemRecyclerOpportunityBinding.txtLead.setText(items.get(position).getLeadSource());
            holder.mItemRecyclerOpportunityBinding.txtStage.setText(items.get(position).getStageName());
            holder.mItemRecyclerOpportunityBinding.txtOppNo.setText(items.get(position).getOpportunityNo());
            holder.mItemRecyclerOpportunityBinding.txtOppType.setText(items.get(position).getOpportunityType());
            if(items.get(position).getProbablity()!=null){
                holder.mItemRecyclerOpportunityBinding.txtProbablity.setText(items.get(position).getProbablity()+"%");
            }else {
                holder.mItemRecyclerOpportunityBinding.txtProbablity.setText("N/A");
            }
            holder.mItemRecyclerOpportunityBinding.txtSubType.setText(items.get(position).getCustomerSubType());
            if(items.get(position).getOpportunityAmount()!=null){
                holder.mItemRecyclerOpportunityBinding.lnrAmount.setVisibility(View.VISIBLE);
                holder.mItemRecyclerOpportunityBinding.txtAmount.setText("₹ "+String.valueOf(items.get(position).getOpportunityAmount()));
            }else {
                holder.mItemRecyclerOpportunityBinding.lnrAmount.setVisibility(View.GONE);
            }

            if(items.get(position).getSubSource()!=null){
                holder.mItemRecyclerOpportunityBinding.lnrSubSource.setVisibility(View.VISIBLE);
                holder.mItemRecyclerOpportunityBinding.txtSubSource.setText(items.get(position).getSubSource());
            }else {
                holder.mItemRecyclerOpportunityBinding.lnrSubSource.setVisibility(View.GONE);
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickHandler.onItemClick(position);
                }
            });

        }catch (Exception e){
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

    public void filterList(ArrayList<OpportunityViewModel> filterllist) {
        // below line is to add our filtered
        // list in our course array list.
        items = filterllist;
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged();
    }

    public void setData(List<OpportunityData> data) {
        items.clear();
        for (int index = 0; index < data.size(); index++) {
            OpportunityViewModel opportunityViewModel = new OpportunityViewModel();
            opportunityViewModel.clone(data.get(index));
            items.add(opportunityViewModel);
        }
    }

    public OpportunityViewModel getItem(int position) {
        return items.get(position);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ItemRecyclerOpportunityBinding mItemRecyclerOpportunityBinding;

        public ViewHolder(ItemRecyclerOpportunityBinding mItemRecyclerOpportunityBinding) {
            super(mItemRecyclerOpportunityBinding.getRoot());
            this.mItemRecyclerOpportunityBinding = mItemRecyclerOpportunityBinding;
        }
    }

}


