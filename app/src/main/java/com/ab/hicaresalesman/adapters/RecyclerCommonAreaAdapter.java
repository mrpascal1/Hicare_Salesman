package com.ab.hicaresalesman.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.ab.hicaresalesman.R;
import com.ab.hicaresalesman.databinding.ItemChildAreaAdapterBinding;
import com.ab.hicaresalesman.databinding.ItemRecyclerActivityAdapterBinding;
import com.ab.hicaresalesman.handler.OnAreaClickedHandler;
import com.ab.hicaresalesman.handler.OnItemDeleteClickHandler;
import com.ab.hicaresalesman.handler.OnListItemClickHandler;
import com.ab.hicaresalesman.network.models.activity.ActivityData;
import com.ab.hicaresalesman.network.models.area.AreaData;
import com.ab.hicaresalesman.network.models.question.OptionData;
import com.ab.hicaresalesman.utils.AppUtils;
import com.ab.hicaresalesman.utils.CustomTextWatcher;
import com.ab.hicaresalesman.viewmodel.ActivityViewModel;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static android.view.View.VISIBLE;

/**
 * Created by Arjun Bhatt on 4/30/2021.
 */
public class RecyclerCommonAreaAdapter extends RecyclerView.Adapter<RecyclerCommonAreaAdapter.ViewHolder> {

    private OnListItemClickHandler onItemClickHandler;
    private OnAreaClickedHandler mOnAreaClickedHandler;
    private final Context mContext;
    private static List<AreaData> items = null;
    private OnChanged onChanged;
    private boolean isCostGenerated;


    public RecyclerCommonAreaAdapter(Context context, List<AreaData> mDataList, boolean isCostGenerated, OnChanged onChanged) {
        items = mDataList;
        this.mContext = context;
        this.onChanged = onChanged;
        this.isCostGenerated = isCostGenerated;
    }

    @NotNull
    @Override
    public RecyclerCommonAreaAdapter.ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        ItemChildAreaAdapterBinding mItemChildAreaAdapterBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_child_area_adapter, parent, false);
        return new RecyclerCommonAreaAdapter.ViewHolder(mItemChildAreaAdapterBinding);
    }

    @Override
    public void onBindViewHolder(@NotNull final RecyclerCommonAreaAdapter.ViewHolder holder, final int position) {
        try {
            if(isCostGenerated){
                holder.mItemChildAreaAdapterBinding.edtArea.setEnabled(false);
                holder.mItemChildAreaAdapterBinding.edtUnit.setEnabled(false);
                holder.mItemChildAreaAdapterBinding.txtAutoFloor.setEnabled(false);
                holder.mItemChildAreaAdapterBinding.lnrViewActivity.setEnabled(false);
                holder.mItemChildAreaAdapterBinding.lnrClonee.setEnabled(false);
                holder.mItemChildAreaAdapterBinding.lnrDelete.setEnabled(false);
            }else {
                holder.mItemChildAreaAdapterBinding.edtArea.setEnabled(true);
                holder.mItemChildAreaAdapterBinding.edtUnit.setEnabled(true);
                holder.mItemChildAreaAdapterBinding.txtAutoFloor.setEnabled(true);
                holder.mItemChildAreaAdapterBinding.lnrViewActivity.setEnabled(true);
                holder.mItemChildAreaAdapterBinding.lnrClonee.setEnabled(true);
                holder.mItemChildAreaAdapterBinding.lnrDelete.setEnabled(true);

            }
            holder.mItemChildAreaAdapterBinding.txtName.setText(items.get(position).getSubareaName());
            holder.mItemChildAreaAdapterBinding.txtName.setTypeface(holder.mItemChildAreaAdapterBinding.txtName.getTypeface(), Typeface.BOLD);

            if (items.get(position).isCloned()) {
                holder.mItemChildAreaAdapterBinding.outerDelete.setVisibility(View.VISIBLE);
                holder.mItemChildAreaAdapterBinding.outerClone.setVisibility(View.GONE);
            } else {
                holder.mItemChildAreaAdapterBinding.outerDelete.setVisibility(View.GONE);
                holder.mItemChildAreaAdapterBinding.outerClone.setVisibility(VISIBLE);
            }

            if (items.get(position).getAreaSqFt() != null) {
                holder.mItemChildAreaAdapterBinding.edtArea.setText(items.get(position).getAreaSqFt());
            } else {
                holder.mItemChildAreaAdapterBinding.edtArea.setText("");
            }
            if (items.get(position).getTotalArea() != null) {
                holder.mItemChildAreaAdapterBinding.edtTotal.setText(items.get(position).getTotalArea());
            } else {
                holder.mItemChildAreaAdapterBinding.edtTotal.setText("");
            }
            holder.mItemChildAreaAdapterBinding.edtTotal.setEnabled(false);
            if (items.get(position).getUnit() != null) {
                holder.mItemChildAreaAdapterBinding.edtUnit.setText(items.get(position).getUnit());
            } else {
                holder.mItemChildAreaAdapterBinding.edtUnit.setText("");
            }
            if (items.get(position).getFloorNo() != null) {
                holder.mItemChildAreaAdapterBinding.txtAutoFloor.setText(items.get(position).getFloorNo());
            } else {
                holder.mItemChildAreaAdapterBinding.txtAutoFloor.setText("");
            }

            if (items.get(position).getUnit() != null && items.get(position).getFloorNo() != null && items.get(position).getAreaSqFt() != null) {
                if (!items.get(position).getUnit().equals("") && !items.get(position).getFloorNo().equals("") && !items.get(position).getAreaSqFt().equals("")) {
                    onChanged.onChanged(holder.getAdapterPosition(), items.get(holder.getAdapterPosition()).getSubareaId(), items.get(holder.getAdapterPosition()).getTowerNo(), "");
                }
            }

            holder.mItemChildAreaAdapterBinding.edtUnit.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable charSequence) {
                    items.get(holder.getAdapterPosition()).setUnit(charSequence.toString());
                    onChanged.onChanged(holder.getAdapterPosition(), items.get(holder.getAdapterPosition()).getSubareaId(), items.get(holder.getAdapterPosition()).getTowerNo(), charSequence.toString());
                    if (getTotalArea(charSequence.toString(), holder.mItemChildAreaAdapterBinding.edtArea.getText().toString()) == 0) {
                        holder.mItemChildAreaAdapterBinding.edtTotal.setText("");
                        items.get(holder.getAdapterPosition()).setTotalArea("");
                    } else {
                        holder.mItemChildAreaAdapterBinding.edtTotal.setText(String.valueOf(getTotalArea(charSequence.toString(), holder.mItemChildAreaAdapterBinding.edtArea.getText().toString())));
                        items.get(holder.getAdapterPosition()).setTotalArea(holder.mItemChildAreaAdapterBinding.edtTotal.getText().toString());
                    }
                }
            });


            holder.mItemChildAreaAdapterBinding.edtArea.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


                }

                @Override
                public void afterTextChanged(Editable charSequence) {
                    items.get(holder.getAdapterPosition()).setAreaSqFt(charSequence.toString());
                    onChanged.onChanged(holder.getAdapterPosition(), items.get(holder.getAdapterPosition()).getSubareaId(), items.get(holder.getAdapterPosition()).getTowerNo(), charSequence.toString());

                    if (getTotalArea(holder.mItemChildAreaAdapterBinding.edtUnit.getText().toString(), charSequence.toString()) == 0) {
                        holder.mItemChildAreaAdapterBinding.edtTotal.setText("");
                        items.get(holder.getAdapterPosition()).setTotalArea("");
                    } else {
                        holder.mItemChildAreaAdapterBinding.edtTotal.setText(String.valueOf(getTotalArea(holder.mItemChildAreaAdapterBinding.edtUnit.getText().toString(), charSequence.toString())));
                        items.get(holder.getAdapterPosition()).setTotalArea(holder.mItemChildAreaAdapterBinding.edtTotal.getText().toString());
                    }

                }
            });


            holder.mItemChildAreaAdapterBinding.txtAutoFloor.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable charSequence) {
                    items.get(holder.getAdapterPosition()).setFloorNo(charSequence.toString());
                    onChanged.onChanged(holder.getAdapterPosition(), items.get(holder.getAdapterPosition()).getSubareaId(), items.get(holder.getAdapterPosition()).getTowerNo(), charSequence.toString());
                }
            });

            holder.mItemChildAreaAdapterBinding.edtTotal.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable charSequence) {
                    items.get(holder.getAdapterPosition()).setTotalArea(holder.mItemChildAreaAdapterBinding.edtTotal.getText().toString());
                    onChanged.onChanged(holder.getAdapterPosition(), items.get(holder.getAdapterPosition()).getSubareaId(), items.get(holder.getAdapterPosition()).getTowerNo(), charSequence.toString());
                }
            });

            holder.mItemChildAreaAdapterBinding.lnrDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnAreaClickedHandler.onDeleteClicked(position, 0);
                }
            });

            holder.mItemChildAreaAdapterBinding.lnrClonee.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnAreaClickedHandler.onCloneClicked(position, 0);
                }
            });

            holder.mItemChildAreaAdapterBinding.lnrViewActivity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnAreaClickedHandler.onViewActivityClicked(position, 0);
                }
            });

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (holder.mItemChildAreaAdapterBinding.lnrMain.getVisibility() == VISIBLE) {
                        holder.mItemChildAreaAdapterBinding.lnrMain.setVisibility(View.GONE);
                        holder.mItemChildAreaAdapterBinding.imgArrow.setRotation(180);
                    } else {
                        holder.mItemChildAreaAdapterBinding.lnrMain.setVisibility(VISIBLE);
                        holder.mItemChildAreaAdapterBinding.imgArrow.setRotation(360);
                    }
                }
            });
            addFloorSpinner(holder.mItemChildAreaAdapterBinding.txtAutoFloor, holder.getAdapterPosition(), items.get(position).getFloorList());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addFloorSpinner(AutoCompleteTextView txtFloor, int position, List<String> floorNo) {
        try {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,
                    android.R.layout.simple_dropdown_item_1line, floorNo);
            txtFloor.setAdapter(adapter);
            txtFloor.setSelection(items.get(position).getFloorPos());

            txtFloor.setOnItemClickListener((parent, view, pos, l) -> {
                items.get(position).setFloorPos(pos);
                items.get(position).setFloorNo((String) parent.getSelectedItem());
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    private int getTotalArea(String unit, String area) {
        int Unit = 0;
        int Area = 0;
        int Total = 0;
        try {
            if (!unit.equals("") && !area.equals("")) {
                Unit = Integer.parseInt(unit);
                Area = Integer.parseInt(area);
                Total = Unit * Area;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Total;
    }

    public void setOnItemClickHandler(OnListItemClickHandler onItemClickHandler) {
        this.onItemClickHandler = onItemClickHandler;
    }

    public void setmOnAreaClickedHandler(OnAreaClickedHandler mOnAreaClickedHandler) {
        this.mOnAreaClickedHandler = mOnAreaClickedHandler;
    }

    public void setData(List<AreaData> data) {
        items.clear();
        items.addAll(data);
    }

    public AreaData getItem(int position) {
        return items.get(position);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ItemChildAreaAdapterBinding mItemChildAreaAdapterBinding;

        public ViewHolder(ItemChildAreaAdapterBinding mItemChildAreaAdapterBinding) {
            super(mItemChildAreaAdapterBinding.getRoot());
            this.mItemChildAreaAdapterBinding = mItemChildAreaAdapterBinding;
        }
    }

    public interface OnChanged {
        void onChanged(int position, int areaId, int towerId, String option);
    }

}


