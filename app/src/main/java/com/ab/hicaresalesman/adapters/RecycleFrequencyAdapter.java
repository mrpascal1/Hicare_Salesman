package com.ab.hicaresalesman.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.ab.hicaresalesman.R;
import com.ab.hicaresalesman.databinding.ItemRecycleFrequencyAdapterBinding;
import com.ab.hicaresalesman.databinding.ItemRecyclerSelectServiceAdapterBinding;
import com.ab.hicaresalesman.handler.OnListItemClickHandler;
import com.ab.hicaresalesman.network.models.frequency.FrequencyData;
import com.ab.hicaresalesman.network.models.frequency.RecommendedFrequency;
import com.ab.hicaresalesman.network.models.pest_service.ServiceData;
import com.ab.hicaresalesman.network.models.question.OptionData;
import com.ab.hicaresalesman.viewmodel.FrequencyViewModel;
import com.ab.hicaresalesman.viewmodel.ServiceViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arjun Bhatt on 5/7/2021.
 */
public class RecycleFrequencyAdapter extends RecyclerView.Adapter<RecycleFrequencyAdapter.ViewHolder> {

    private OnListItemClickHandler onItemClickHandler;
    private final Context mContext;
    private static List<FrequencyViewModel> items = null;
    private OnOptionClicked onOptionClicked;
    private ArrayList<String> multileItems = null;
    private boolean isCostGenerated = false;

    public RecycleFrequencyAdapter(Context context, boolean isCostGenerated, OnOptionClicked onOptionClicked) {
        if (items == null) {
            items = new ArrayList<>();
        }
        if (multileItems == null) {
            multileItems = new ArrayList<>();
        }
        this.onOptionClicked = onOptionClicked;
        this.mContext = context;
        this.isCostGenerated = isCostGenerated;
    }

    @NotNull
    @Override
    public RecycleFrequencyAdapter.ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        ItemRecycleFrequencyAdapterBinding mItemRecycleFrequencyAdapterBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_recycle_frequency_adapter, parent, false);
        return new RecycleFrequencyAdapter.ViewHolder(mItemRecycleFrequencyAdapterBinding);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NotNull final RecycleFrequencyAdapter.ViewHolder holder, final int position) {

        holder.mItemRecycleFrequencyAdapterBinding.txtName.setText(items.get(position).getServiceType() + "(" + items.get(position).getServiceCode() + ")");
        holder.mItemRecycleFrequencyAdapterBinding.txtInfestation.setTypeface(holder.mItemRecycleFrequencyAdapterBinding.txtInfestation.getTypeface(), Typeface.BOLD);
        holder.mItemRecycleFrequencyAdapterBinding.txtName.setTypeface(holder.mItemRecycleFrequencyAdapterBinding.txtName.getTypeface(), Typeface.BOLD);
        if (items.get(position).getCategoryName() != null) {
            holder.mItemRecycleFrequencyAdapterBinding.txtInfestation.setText(items.get(position).getCategoryName().toUpperCase());
            if (items.get(position).getCategoryName().equalsIgnoreCase("high")) {
                holder.mItemRecycleFrequencyAdapterBinding.txtInfestation.setTextColor(mContext.getColor(R.color.lightRed));
                holder.mItemRecycleFrequencyAdapterBinding.view.setBackgroundColor(mContext.getColor(R.color.lightRed));
            } else if (items.get(position).getCategoryName().equalsIgnoreCase("low")) {
                holder.mItemRecycleFrequencyAdapterBinding.txtInfestation.setTextColor(mContext.getColor(R.color.karmaGreen));
                holder.mItemRecycleFrequencyAdapterBinding.view.setBackgroundColor(mContext.getColor(R.color.karmaGreen));
            } else {
                holder.mItemRecycleFrequencyAdapterBinding.txtInfestation.setTextColor(mContext.getColor(R.color.orange));
                holder.mItemRecycleFrequencyAdapterBinding.view.setBackgroundColor(mContext.getColor(R.color.orange));
            }
        } else {
            holder.mItemRecycleFrequencyAdapterBinding.txtInfestation.setText("N/A");
        }

        if (items.get(position).getRecommendedFrequency() != null) {
            holder.mItemRecycleFrequencyAdapterBinding.txtRecommended.setText(items.get(position).getRecommendedFrequency());
        } else {
            holder.mItemRecycleFrequencyAdapterBinding.txtRecommended.setText("N/A");
        }
        holder.mItemRecycleFrequencyAdapterBinding.spnFreq.setOnItemSelectedListener(null);
        setSpinner(holder.mItemRecycleFrequencyAdapterBinding.spnFreq, items.get(position).getFrequencyList(), position);

    }

    private void setSpinner(AppCompatSpinner spnFreq, List<RecommendedFrequency> frequencyList, int position) {
        try {
            List<RecommendedFrequency> arr = new ArrayList<>();
            RecommendedFrequency data = new RecommendedFrequency();
            data.setFrequencyName("Select Option");
            arr.add(data);
            arr.addAll(frequencyList);
            ArrayAdapter<RecommendedFrequency> spnAdapter = new ArrayAdapter<RecommendedFrequency>(mContext,
                    android.R.layout.simple_spinner_item, arr);
            spnAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnAdapter.setDropDownViewResource(R.layout.spinner_popup);
            spnFreq.setAdapter(spnAdapter);
            if (isCostGenerated) {
                spnFreq.setEnabled(false);
            } else {
                spnFreq.setEnabled(true);
            }
//            for (int i = 0; i < frequencyList.size(); i++) {
//                if (frequencyList.get(i).getSelected()) {
//                    if (frequencyList.get(i).getFrequencyName() != null) {
//                        int spinnerPosition = spnAdapter.getPosition(frequencyList.get(i));
//                        spnFreq.setSelection(spinnerPosition);
//                    }
//                }
//
//            }
            spnFreq.setSelection(items.get(position).getOptionPos());
            onOptionClicked.onClicked(position, (RecommendedFrequency) spnFreq.getSelectedItem(), items.get(position).getRecommendedFrequency(), items.get(position).getRecommendedFrequencyId());

            spnFreq.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                    try {
                        onOptionClicked.onClicked(position, (RecommendedFrequency) parent.getSelectedItem(), items.get(position).getRecommendedFrequency(), items.get(position).getRecommendedFrequencyId());
                        items.get(position).setOptionPos(pos);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
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


    public void setData(List<FrequencyData> data) {
        items.clear();
        for (int index = 0; index < data.size(); index++) {
            FrequencyViewModel frequencyViewModel = new FrequencyViewModel();
            frequencyViewModel.clone(data.get(index));
            items.add(frequencyViewModel);
        }
    }

    public FrequencyViewModel getItem(int position) {
        return items.get(position);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ItemRecycleFrequencyAdapterBinding mItemRecycleFrequencyAdapterBinding;

        public ViewHolder(ItemRecycleFrequencyAdapterBinding mItemRecycleFrequencyAdapterBinding) {
            super(mItemRecycleFrequencyAdapterBinding.getRoot());
            this.mItemRecycleFrequencyAdapterBinding = mItemRecycleFrequencyAdapterBinding;
        }
    }

    public interface OnOptionClicked {
        void onClicked(int position, RecommendedFrequency option, String freqName, int freqId);
    }

}



