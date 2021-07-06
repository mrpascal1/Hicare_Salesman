package com.ab.hicaresalesman.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ab.hicaresalesman.R;
import com.ab.hicaresalesman.databinding.ItemRecyclerParentQuestionAdapterBinding;
import com.ab.hicaresalesman.handler.OnQuestionClickedHandler;
import com.ab.hicaresalesman.network.models.question.OptionData;
import com.ab.hicaresalesman.network.models.question.QuestionData;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.VISIBLE;

/**
 * Created by Arjun Bhatt on 4/30/2021.
 */
public class RecyclerQuestionParentAdapter extends RecyclerView.Adapter<RecyclerQuestionParentAdapter.ViewHolder> implements OnQuestionClickedHandler {
    private AdapterView.OnItemClickListener onItemClickHandler;
    private OnQuestionClickedHandler mOnQuestionClickedHandler;
    private final Context mContext;
    private List<QuestionData> items = null;
    private OnOptionClicked onOptionClicked;
    private ArrayList<String> multileItems = null;
    private String strAnswer = "";
    private boolean isCostGenerated = false;

    public RecyclerQuestionParentAdapter(Context context, boolean isCostGenerated, OnOptionClicked onOptionClicked) {
        this.onOptionClicked = onOptionClicked;
        this.mContext = context;
        if (items == null) {
            items = new ArrayList<>();
        }
        if (multileItems == null) {
            multileItems = new ArrayList<>();
        }
        this.isCostGenerated = isCostGenerated;
    }

    @NotNull
    @Override
    public RecyclerQuestionParentAdapter.ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        ItemRecyclerParentQuestionAdapterBinding mItemRecyclerParentQuestionAdapterBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_recycler_parent_question_adapter, parent, false);
        return new RecyclerQuestionParentAdapter.ViewHolder(mItemRecyclerParentQuestionAdapterBinding);
    }

    @Override
    public void onBindViewHolder(@NotNull RecyclerQuestionParentAdapter.ViewHolder holder, final int position) {
        try {
            holder.mItemRecyclerParentQuestionAdapterBinding.txtHeader.setText(items.get(position).getServiceType());
            holder.mItemRecyclerParentQuestionAdapterBinding.txtHeader.setTypeface(holder.mItemRecyclerParentQuestionAdapterBinding.txtHeader.getTypeface(), Typeface.BOLD);
            RecyclerQuestionChildAdapter childAdapter = new RecyclerQuestionChildAdapter(mContext, isCostGenerated, items.get(position).getQuestions(), position, (position1, questionId, item) -> {
                onOptionClicked.onClicked(position, position1, questionId, item);
            });
            holder.mItemRecyclerParentQuestionAdapterBinding.recyclerChild.setLayoutManager(new LinearLayoutManager(mContext));
            holder.mItemRecyclerParentQuestionAdapterBinding.recyclerChild.setHasFixedSize(true);
            holder.mItemRecyclerParentQuestionAdapterBinding.recyclerChild.setClipToPadding(false);
            holder.mItemRecyclerParentQuestionAdapterBinding.recyclerChild.setAdapter(childAdapter);
            childAdapter.onCameraClicked(this);
            childAdapter.notifyDataSetChanged();

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (holder.mItemRecyclerParentQuestionAdapterBinding.recyclerChild.getVisibility() == VISIBLE) {
                        holder.mItemRecyclerParentQuestionAdapterBinding.recyclerChild.setVisibility(View.GONE);
                        holder.mItemRecyclerParentQuestionAdapterBinding.imgArrow.setRotation(180);
                    } else {
                        holder.mItemRecyclerParentQuestionAdapterBinding.recyclerChild.setVisibility(VISIBLE);
                        holder.mItemRecyclerParentQuestionAdapterBinding.imgArrow.setRotation(360);
                    }
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setOnItemClickHandler(AdapterView.OnItemClickListener onItemClickHandler) {
        this.onItemClickHandler = onItemClickHandler;
    }

    public void onCameraClicked(OnQuestionClickedHandler mOnQuestionClickedHandler) {
        this.mOnQuestionClickedHandler = mOnQuestionClickedHandler;
    }


    public void addData(List<QuestionData> data) {
        items.clear();
        items.addAll(data);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public QuestionData getItem(int position) {
        return items.get(position);
    }

    @Override
    public void onCameraClicked(int parentPosition, int childPosition) {
        mOnQuestionClickedHandler.onCameraClicked(parentPosition, childPosition);
    }

    @Override
    public void onImageCancelled(int parentPosition, int childPosition) {
        mOnQuestionClickedHandler.onImageCancelled(parentPosition, childPosition);
    }

    public void setOnQuestionClicked(OnQuestionClickedHandler mOnQuestionClickedHandler) {
        this.mOnQuestionClickedHandler = mOnQuestionClickedHandler;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemRecyclerParentQuestionAdapterBinding mItemRecyclerParentQuestionAdapterBinding;

        public ViewHolder(ItemRecyclerParentQuestionAdapterBinding mItemRecyclerParentQuestionAdapterBinding) {
            super(mItemRecyclerParentQuestionAdapterBinding.getRoot());
            this.mItemRecyclerParentQuestionAdapterBinding = mItemRecyclerParentQuestionAdapterBinding;
        }
    }

    public interface OnOptionClicked {
        void onClicked(int parentPosition, int childPos, int questionId, OptionData option);
    }


}
