package com.ab.hicaresalesman.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.appcompat.widget.AppCompatSpinner;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.ab.hicaresalesman.R;
import com.ab.hicaresalesman.databinding.ItemRecyclerChildQuestionAdapterBinding;
import com.ab.hicaresalesman.handler.OnQuestionClickedHandler;
import com.ab.hicaresalesman.network.models.question.OptionData;
import com.ab.hicaresalesman.network.models.question.Questions;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arjun Bhatt on 4/30/2021.
 */
class RecyclerQuestionChildAdapter extends RecyclerView.Adapter<RecyclerQuestionChildAdapter.ViewHolder> {
    OnQuestionClickedHandler mOnQuestionClickedHandler;
    private final Context mContext;
    private List<Questions> items = null;
    private int selectedPos = -1;
    private OnOptionClicked onOptionClicked;
    private String optionType;
    private int parentPos = 0;

    public RecyclerQuestionChildAdapter(Context mContext, List<Questions> data, int parentPosition, OnOptionClicked onOptionClicked) {
        items = data;
        this.onOptionClicked = onOptionClicked;
        this.mContext = mContext;
        this.parentPos = parentPosition;
    }

    @NotNull
    @Override
    public RecyclerQuestionChildAdapter.ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        ItemRecyclerChildQuestionAdapterBinding mItemRecyclerChildQuestionAdapterBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_recycler_child_question_adapter, parent, false);
        return new RecyclerQuestionChildAdapter.ViewHolder(mItemRecyclerChildQuestionAdapterBinding);
    }

    @Override
    public void onBindViewHolder(@NotNull RecyclerQuestionChildAdapter.ViewHolder holder, final int position) {

        try {
            holder.itemRecyclerChildQuestionAdapterBinding.txtQuestion.setText(items.get(position).getQuestionTitle());
            holder.itemRecyclerChildQuestionAdapterBinding.txtQuestion.setTypeface(holder.itemRecyclerChildQuestionAdapterBinding.txtQuestion.getTypeface(), Typeface.BOLD);
            holder.itemRecyclerChildQuestionAdapterBinding.spnOption.setOnItemSelectedListener(null);
            addSpinnerOptions(holder.itemRecyclerChildQuestionAdapterBinding.spnOption, position, items.get(position).getOptionList());


            if (items.get(position).getImageRequired()) {
                holder.itemRecyclerChildQuestionAdapterBinding.relPhoto.setVisibility(View.VISIBLE);
            } else {
                holder.itemRecyclerChildQuestionAdapterBinding.relPhoto.setVisibility(View.GONE);
            }
//            holder.itemRecyclerChildQuestionAdapterBinding.spnOption.setSelection(items.get(parentPos).getOptionList().get(position).getSpnPosition());

            holder.itemRecyclerChildQuestionAdapterBinding.imageCancel.setOnClickListener(v -> {
                items.get(position).setPictureUrl(null);
                holder.itemRecyclerChildQuestionAdapterBinding.lnrImage.setVisibility(View.GONE);
                holder.itemRecyclerChildQuestionAdapterBinding.lnrUpload.setVisibility(View.VISIBLE);
//                notifyDataSetChanged();
                mOnQuestionClickedHandler.onImageCancelled(parentPos, position);
            });

            if (items.get(position).getPictureUrl() != null && !items.get(position).getPictureUrl().equals("")) {
                Picasso.get().load(items.get(position).getPictureUrl()).into(holder.itemRecyclerChildQuestionAdapterBinding.imgUploadedCheque);
                holder.itemRecyclerChildQuestionAdapterBinding.lnrUpload.setVisibility(View.GONE);
                holder.itemRecyclerChildQuestionAdapterBinding.lnrImage.setVisibility(View.VISIBLE);
            } else {
                holder.itemRecyclerChildQuestionAdapterBinding.lnrUpload.setVisibility(View.VISIBLE);
                holder.itemRecyclerChildQuestionAdapterBinding.lnrImage.setVisibility(View.GONE);
            }
            holder.itemRecyclerChildQuestionAdapterBinding.lnrUpload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnQuestionClickedHandler.onCameraClicked(parentPos, position);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addSpinnerOptions(AppCompatSpinner spnOption, int position, List<OptionData> optionList) {
        try {

            List<OptionData> arr = new ArrayList<>();
            OptionData data = new OptionData();
            data.setOptionTitle("Select Answer");
            arr.add(data);
            arr.addAll(optionList);
            ArrayAdapter<OptionData> spnAdapter = new ArrayAdapter<OptionData>(mContext,
                    android.R.layout.simple_spinner_item, arr);
            spnAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spnAdapter.setDropDownViewResource(R.layout.spinner_popup);
            spnOption.setAdapter(spnAdapter);
            for (int i = 0; i < optionList.size(); i++) {
                if (optionList.get(i).getIsSelected()) {
                    if (optionList.get(i).getOptionTitle() != null) {
                        int spinnerPosition = spnAdapter.getPosition(optionList.get(i));
                        spnOption.setSelection(spinnerPosition);
                    }
                }

            }
//            spnOption.setSelection(items.get(position).getParentPos());
            spnOption.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                    if (pos > 0) {
                        onOptionClicked.onOptionClicked(position, (OptionData) parent.getSelectedItem());
                    }

                    items.get(position).setParentPos(pos);


                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onCameraClicked(OnQuestionClickedHandler mOnQuestionClickedHandler) {
        this.mOnQuestionClickedHandler = mOnQuestionClickedHandler;
    }

    public Questions getItem(int position) {
        return items.get(position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    //
    public void addData(List<Questions> data, String optionType) {
        items.clear();
        items.addAll(data);
        this.optionType = optionType;
        Log.i("Sel_Add ", "called");
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemRecyclerChildQuestionAdapterBinding itemRecyclerChildQuestionAdapterBinding;

        public ViewHolder(ItemRecyclerChildQuestionAdapterBinding itemRecyclerChildQuestionAdapterBinding) {
            super(itemRecyclerChildQuestionAdapterBinding.getRoot());
            this.itemRecyclerChildQuestionAdapterBinding = itemRecyclerChildQuestionAdapterBinding;
        }
    }

    public interface OnOptionClicked {
        void onOptionClicked(int position, OptionData item);
    }
}