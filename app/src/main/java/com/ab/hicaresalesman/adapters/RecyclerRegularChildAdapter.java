package com.ab.hicaresalesman.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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
import com.ab.hicaresalesman.handler.OnAreaClickedHandler;
import com.ab.hicaresalesman.handler.OnQuestionClickedHandler;
import com.ab.hicaresalesman.network.models.area.AreaData;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arjun Bhatt on 4/29/2021.
 */
public class RecyclerRegularChildAdapter extends RecyclerView.Adapter<RecyclerRegularChildAdapter.ViewHolder> {
    private OnAreaClickedHandler mOnAreaClickedHandler;
    private final Context mContext;
    private List<AreaData> items = null;
    private int selectedPos = -1;
    private OnValueChanged onValueChanged;
    private String optionType;
    private int parentPos;
    private boolean isCostGenerated = false;

    public RecyclerRegularChildAdapter(Context mContext, List<AreaData> data, int position, boolean isCostGenerated, OnValueChanged onValueChanged) {
//        if (items == null) {
//            items = new ArrayList<>();
//        }
        parentPos = position;
        items = data;
        this.onValueChanged = onValueChanged;
        this.mContext = mContext;
        this.isCostGenerated = isCostGenerated;
    }

    @NotNull
    @Override
    public RecyclerRegularChildAdapter.ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        ItemChildAreaAdapterBinding mItemChildAreaAdapterBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_child_area_adapter, parent, false);
        return new RecyclerRegularChildAdapter.ViewHolder(mItemChildAreaAdapterBinding);
    }

    @Override
    public void onBindViewHolder(@NotNull RecyclerRegularChildAdapter.ViewHolder holder, final int position) {
        try {
            if (isCostGenerated) {
                holder.mItemChildAreaAdapterBinding.edtUnit.setEnabled(false);
                holder.mItemChildAreaAdapterBinding.edtArea.setEnabled(false);
                holder.mItemChildAreaAdapterBinding.txtAutoFloor.setEnabled(false);
                holder.mItemChildAreaAdapterBinding.lnrClonee.setEnabled(false);
                holder.mItemChildAreaAdapterBinding.lnrViewActivity.setEnabled(false);
                holder.mItemChildAreaAdapterBinding.lnrDelete.setEnabled(false);
            } else {
                holder.mItemChildAreaAdapterBinding.edtUnit.setEnabled(true);
                holder.mItemChildAreaAdapterBinding.edtArea.setEnabled(true);
                holder.mItemChildAreaAdapterBinding.txtAutoFloor.setEnabled(true);
                holder.mItemChildAreaAdapterBinding.lnrClonee.setEnabled(true);
                holder.mItemChildAreaAdapterBinding.lnrViewActivity.setEnabled(true);
                holder.mItemChildAreaAdapterBinding.lnrDelete.setEnabled(true);
            }
            holder.mItemChildAreaAdapterBinding.txtName.setText(items.get(position).getSubareaName());
            holder.mItemChildAreaAdapterBinding.txtName.setTypeface(holder.mItemChildAreaAdapterBinding.txtName.getTypeface(), Typeface.BOLD);
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
                    onValueChanged.onChanged(holder.getAdapterPosition(), items.get(holder.getAdapterPosition()).getSubareaId(), "");
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
                public void afterTextChanged(Editable editable) {
                    items.get(holder.getAdapterPosition()).setUnit(editable.toString());
                    onValueChanged.onChanged(holder.getAdapterPosition(), items.get(holder.getAdapterPosition()).getSubareaId(), editable.toString());
                    if (getTotalArea(editable.toString(), holder.mItemChildAreaAdapterBinding.edtArea.getText().toString()) == 0) {
                        holder.mItemChildAreaAdapterBinding.edtTotal.setText("");
                        items.get(holder.getAdapterPosition()).setTotalArea("");

                    } else {
                        holder.mItemChildAreaAdapterBinding.edtTotal.setText(String.valueOf(getTotalArea(editable.toString(), holder.mItemChildAreaAdapterBinding.edtArea.getText().toString())));
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
                public void afterTextChanged(Editable editable) {
                    items.get(holder.getAdapterPosition()).setAreaSqFt(editable.toString());
                    onValueChanged.onChanged(holder.getAdapterPosition(), items.get(holder.getAdapterPosition()).getSubareaId(), editable.toString());

                    if (getTotalArea(holder.mItemChildAreaAdapterBinding.edtUnit.getText().toString(), editable.toString()) == 0) {
                        holder.mItemChildAreaAdapterBinding.edtTotal.setText("");
                        items.get(holder.getAdapterPosition()).setTotalArea("");
                    } else {
                        holder.mItemChildAreaAdapterBinding.edtTotal.setText(String.valueOf(getTotalArea(holder.mItemChildAreaAdapterBinding.edtUnit.getText().toString(), editable.toString())));
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
                    onValueChanged.onChanged(holder.getAdapterPosition(), items.get(holder.getAdapterPosition()).getSubareaId(), charSequence.toString());
                }
            });
            holder.mItemChildAreaAdapterBinding.edtTotal.setEnabled(false);
            holder.mItemChildAreaAdapterBinding.lnrViewActivity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnAreaClickedHandler.onViewActivityClicked(parentPos, position);
                }
            });

            holder.mItemChildAreaAdapterBinding.lnrClonee.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnAreaClickedHandler.onCloneClicked(parentPos, position);
                }
            });

            holder.mItemChildAreaAdapterBinding.lnrDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnAreaClickedHandler.onDeleteClicked(parentPos, position);
                }
            });
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (holder.mItemChildAreaAdapterBinding.lnrMain.getVisibility() == View.VISIBLE) {
                        holder.mItemChildAreaAdapterBinding.lnrMain.setVisibility(View.GONE);
                        holder.mItemChildAreaAdapterBinding.imgArrow.setRotation(180);
                    } else {
                        holder.mItemChildAreaAdapterBinding.lnrMain.setVisibility(View.VISIBLE);
                        holder.mItemChildAreaAdapterBinding.imgArrow.setRotation(360);
                    }
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
                public void afterTextChanged(Editable editable) {
                    items.get(holder.getAdapterPosition()).setUnit(holder.mItemChildAreaAdapterBinding.edtUnit.getText().toString());
                    items.get(holder.getAdapterPosition()).setFloorNo(holder.mItemChildAreaAdapterBinding.txtAutoFloor.getText().toString());
                    items.get(holder.getAdapterPosition()).setAreaSqFt(holder.mItemChildAreaAdapterBinding.edtArea.getText().toString());
                    items.get(holder.getAdapterPosition()).setTotalArea(holder.mItemChildAreaAdapterBinding.edtTotal.getText().toString());
                    onValueChanged.onChanged(holder.getAdapterPosition(), items.get(holder.getAdapterPosition()).getSubareaId(), editable.toString());
                }
            });

            holder.mItemChildAreaAdapterBinding.lnrName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (holder.mItemChildAreaAdapterBinding.lnrMain.getVisibility() == View.VISIBLE) {
                        holder.mItemChildAreaAdapterBinding.lnrMain.setVisibility(View.GONE);
                        holder.mItemChildAreaAdapterBinding.imgArrow.setRotation(180);
                    } else {
                        holder.mItemChildAreaAdapterBinding.lnrMain.setVisibility(View.VISIBLE);
                        holder.mItemChildAreaAdapterBinding.imgArrow.setRotation(360);
                    }
                }
            });
            if (items.get(position).isCloned()) {
                holder.mItemChildAreaAdapterBinding.outerDelete.setVisibility(View.VISIBLE);
                holder.mItemChildAreaAdapterBinding.outerClone.setVisibility(View.GONE);
            } else {
                holder.mItemChildAreaAdapterBinding.outerDelete.setVisibility(View.GONE);
                holder.mItemChildAreaAdapterBinding.outerClone.setVisibility(View.VISIBLE);
            }
            addFloorSpinner(holder.mItemChildAreaAdapterBinding.txtAutoFloor, position, items.get(position).getFloorList());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private int getTotalArea(String unit, String area) {
        int Unit = 0;
        int Area = 0;
        int Total = 0;
        if (!unit.equals("") && !area.equals("")) {
            Unit = Integer.parseInt(unit);
            Area = Integer.parseInt(area);
            Total = Unit * Area;
        }
        return Total;
    }

    private void addFloorSpinner(AutoCompleteTextView txtFloor, int position, List<String> floorNo) {
        try {

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,
                    android.R.layout.simple_dropdown_item_1line, floorNo);
            txtFloor.setAdapter(adapter);
            txtFloor.setSelection(items.get(position).getFloorPos());
            txtFloor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int pos, long l) {
                    items.get(position).setFloorPos(pos);
                    items.get(position).setFloorNo((String) parent.getSelectedItem());
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public AreaData getItem(int position) {
        return items.get(position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void onDeleteClicked(OnAreaClickedHandler mOnAreaClickedHandler) {
        this.mOnAreaClickedHandler = mOnAreaClickedHandler;
    }

    public void onCloneClicked(OnAreaClickedHandler mOnAreaClickedHandler) {
        this.mOnAreaClickedHandler = mOnAreaClickedHandler;
    }

    public void onViewActivityClicked(OnAreaClickedHandler mOnAreaClickedHandler) {
        this.mOnAreaClickedHandler = mOnAreaClickedHandler;
    }


    public void addData(List<AreaData> data, String optionType) {
        items.clear();
        items.addAll(data);
        this.optionType = optionType;
        Log.i("Sel_Add ", "called");
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemChildAreaAdapterBinding mItemChildAreaAdapterBinding;

        public ViewHolder(ItemChildAreaAdapterBinding mItemChildAreaAdapterBinding) {
            super(mItemChildAreaAdapterBinding.getRoot());
            this.mItemChildAreaAdapterBinding = mItemChildAreaAdapterBinding;
        }
    }

    public interface OnValueChanged {
        void onChanged(int position, int subId, String value);
    }
}
