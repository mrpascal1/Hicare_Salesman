package com.ab.hicaresalesman.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ab.hicaresalesman.R;
import com.ab.hicaresalesman.databinding.ItemRecyclerParentAreaAdapterBinding;
import com.ab.hicaresalesman.handler.OnAreaClickedHandler;
import com.ab.hicaresalesman.handler.OnItemCloneClickHandler;
import com.ab.hicaresalesman.network.models.area.TowerData;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Created by Arjun Bhatt on 4/29/2021.
 */
public class RecyclerRegularParentAdapter extends RecyclerView.Adapter<RecyclerRegularParentAdapter.ViewHolder> implements OnAreaClickedHandler {
    private OnAreaClickedHandler mOnAreaClickedHandler;
    private OnItemClickListener onItemClickHandler;
    private OnItemCloneClickHandler mOnItemCloneClickHandler;
    private final Context mContext;
    private List<TowerData> items = null;
    private OnClicked onClicked;
    private boolean isCostGenerated;

    public RecyclerRegularParentAdapter(Context context, List<TowerData> items, boolean isCostGenerated, OnClicked onClicked) {
        this.items = items;
        this.onClicked = onClicked;
        this.mContext = context;
        this.isCostGenerated = isCostGenerated;
    }

    @NotNull
    @Override
    public RecyclerRegularParentAdapter.ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        ItemRecyclerParentAreaAdapterBinding mItemRecyclerParentAreaAdapterBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_recycler_parent_area_adapter, parent, false);
        return new RecyclerRegularParentAdapter.ViewHolder(mItemRecyclerParentAreaAdapterBinding);
    }

    @Override
    public void onBindViewHolder(@NotNull RecyclerRegularParentAdapter.ViewHolder holder, final int position) {
        try {
            if (items.get(position).getTowerName() != null) {
                holder.mItemRecyclerParentAreaAdapterBinding.txtTower.setText(items.get(position).getTowerName());
            } else {
                holder.mItemRecyclerParentAreaAdapterBinding.txtTower.setText("Tower No. " + String.valueOf(items.get(position).getTower()));
            }
            holder.mItemRecyclerParentAreaAdapterBinding.txtTower.setTypeface(holder.mItemRecyclerParentAreaAdapterBinding.txtTower.getTypeface(), Typeface.BOLD);
            holder.mItemRecyclerParentAreaAdapterBinding.txtIndustry.setText(items.get(position).getData().get(0).getIndustryName());
            holder.mItemRecyclerParentAreaAdapterBinding.txtIndustry.setTypeface(holder.mItemRecyclerParentAreaAdapterBinding.txtTower.getTypeface(), Typeface.BOLD);

            RecyclerRegularChildAdapter childAdapter = new RecyclerRegularChildAdapter(mContext, items.get(position).getData(), position, isCostGenerated, (position1, subId, value) -> {
                onClicked.onClicked(holder.getAdapterPosition(), position1, subId, items.get(holder.getAdapterPosition()).getTower(), value);
            });

            holder.mItemRecyclerParentAreaAdapterBinding.recycleChild.setLayoutManager(new LinearLayoutManager(mContext));
            holder.mItemRecyclerParentAreaAdapterBinding.recycleChild.setHasFixedSize(true);
            holder.mItemRecyclerParentAreaAdapterBinding.recycleChild.setClipToPadding(false);
            holder.mItemRecyclerParentAreaAdapterBinding.recycleChild.setAdapter(childAdapter);
            childAdapter.onCloneClicked(this);
            childAdapter.onDeleteClicked(this);
            childAdapter.onViewActivityClicked(this);
            childAdapter.notifyDataSetChanged();

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (holder.mItemRecyclerParentAreaAdapterBinding.recycleChild.getVisibility() == VISIBLE) {
                        holder.mItemRecyclerParentAreaAdapterBinding.recycleChild.setVisibility(View.GONE);
                        holder.mItemRecyclerParentAreaAdapterBinding.imgArrow.setRotation(180);
                    } else {
                        holder.mItemRecyclerParentAreaAdapterBinding.recycleChild.setVisibility(VISIBLE);
                        holder.mItemRecyclerParentAreaAdapterBinding.imgArrow.setRotation(360);
                    }
                }
            });
            if (isCostGenerated) {
                holder.mItemRecyclerParentAreaAdapterBinding.lnrClone.setEnabled(false);
                holder.mItemRecyclerParentAreaAdapterBinding.lnrDelete.setEnabled(false);
            } else {
                holder.mItemRecyclerParentAreaAdapterBinding.lnrClone.setEnabled(true);
                holder.mItemRecyclerParentAreaAdapterBinding.lnrDelete.setEnabled(true);
            }

            holder.mItemRecyclerParentAreaAdapterBinding.lnrClone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemCloneClickHandler.onCloneAreaClicked(position);
                }
            });

            holder.mItemRecyclerParentAreaAdapterBinding.lnrDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemCloneClickHandler.onTowerDeleteClicked(position);
                }
            });
            if (items.get(position).isCloned()) {
                holder.mItemRecyclerParentAreaAdapterBinding.outerDelete.setVisibility(View.VISIBLE);
                holder.mItemRecyclerParentAreaAdapterBinding.lnrClone.setVisibility(View.GONE);
            } else {
                holder.mItemRecyclerParentAreaAdapterBinding.outerDelete.setVisibility(View.GONE);
                holder.mItemRecyclerParentAreaAdapterBinding.lnrClone.setVisibility(VISIBLE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setOnItemClickHandler(OnItemClickListener onItemClickHandler) {
        this.onItemClickHandler = onItemClickHandler;
    }

    public void setOnCloneClickHandler(OnItemCloneClickHandler mOnCloneClickHandler) {
        this.mOnItemCloneClickHandler = mOnCloneClickHandler;
    }


    public void setOnAreaClickedHandler(OnAreaClickedHandler mOnAreaClickedHandler) {
        this.mOnAreaClickedHandler = mOnAreaClickedHandler;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public TowerData getItem(int position) {
        return items.get(position);
    }

    @Override
    public void onCloneClicked(int parentPosition, int childPosition) {
        mOnAreaClickedHandler.onCloneClicked(parentPosition, childPosition);
    }


    @Override
    public void onDeleteClicked(int parentPosition, int childPosition) {
        mOnAreaClickedHandler.onDeleteClicked(parentPosition, childPosition);
    }

    @Override
    public void onViewActivityClicked(int parentPosition, int childPosition) {
        mOnAreaClickedHandler.onViewActivityClicked(parentPosition, childPosition);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemRecyclerParentAreaAdapterBinding mItemRecyclerParentAreaAdapterBinding;

        public ViewHolder(ItemRecyclerParentAreaAdapterBinding mItemRecyclerParentAreaAdapterBinding) {
            super(mItemRecyclerParentAreaAdapterBinding.getRoot());
            this.mItemRecyclerParentAreaAdapterBinding = mItemRecyclerParentAreaAdapterBinding;
        }
    }

    public interface OnClicked {
        void onClicked(int parentPos, int childPos, int uniqueId, int towerId, String option);
    }
}
