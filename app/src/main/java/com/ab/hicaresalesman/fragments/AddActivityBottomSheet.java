package com.ab.hicaresalesman.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.ab.hicaresalesman.R;
import com.ab.hicaresalesman.databinding.FragmentAddActivityBottomSheetBinding;
import com.ab.hicaresalesman.handler.UserBottomSheetActivityHandler;
import com.ab.hicaresalesman.network.NetworkCallController;
import com.ab.hicaresalesman.network.NetworkResponseListner;
import com.ab.hicaresalesman.network.models.activity.AddActivityRequest;
import com.ab.hicaresalesman.network.models.activity.AddActivityResponse;
import com.ab.hicaresalesman.utils.AppUtils;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class AddActivityBottomSheet extends BottomSheetDialogFragment implements UserBottomSheetActivityHandler {
    FragmentAddActivityBottomSheetBinding mAddActivityBottomSheetBinding;
    private String opportunityId;
    private String industryName;
    private Context mContext;

    private IdialogDismissFragmentReload mIReminderAdded;

    public void setListener(IdialogDismissFragmentReload listener) {
        mIReminderAdded = listener;
    }

    public AddActivityBottomSheet(String opportunityId, String industryName) {
        this.industryName = industryName;
        this.opportunityId = opportunityId;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mAddActivityBottomSheetBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_activity_bottom_sheet, container, false);
        mAddActivityBottomSheetBinding.setHandler(this);
        return mAddActivityBottomSheetBinding.getRoot();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        bottomSheetDialog.setOnShowListener(dia -> {
            BottomSheetDialog dialog = (BottomSheetDialog) dia;
            FrameLayout bottomSheet = dialog.findViewById(com.google.android.material.R.id.design_bottom_sheet);
            BottomSheetBehavior.from(bottomSheet).setState(BottomSheetBehavior.STATE_EXPANDED);
            BottomSheetBehavior.from(bottomSheet).setSkipCollapsed(true);
            BottomSheetBehavior.from(bottomSheet).setHideable(true);
        });

        return bottomSheetDialog;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAddActivityBottomSheetBinding.txtTitle.setTypeface(mAddActivityBottomSheetBinding.txtTitle.getTypeface(), Typeface.BOLD);
        mAddActivityBottomSheetBinding.txtIndustry.setText("(Industry - " + industryName + ")");
    }

    @Override
    public void onActivityAddClicked(View view) {
        try {
            NetworkCallController controller = new NetworkCallController();
            AddActivityRequest request = new AddActivityRequest();
            request.setActivityCode("12323.98657");
            request.setActivityId(0);
            request.setActivityName(mAddActivityBottomSheetBinding.edtName.getText().toString());
            request.setIndustryId(0);
            request.setIndustryName(industryName);
            request.setOpportunityId(opportunityId);
            request.setIsDeleted(false);
            request.setCreatedByIdUser(0);
            request.setModifiedByIdUser(0);
            request.setCreatedOn(AppUtils.currentDateTime());
            request.setModifiedOn(AppUtils.currentDateTime());
            controller.setListner(new NetworkResponseListner<AddActivityResponse>() {
                @Override
                public void onResponse(AddActivityResponse response) {
                    if (response.getIsSuccess()) {
                        Toast.makeText(mContext, "Added Successfully", Toast.LENGTH_SHORT).show();
                        dismiss();
                    }
                }

                @Override
                public void onFailure() {

                }
            });
            controller.addActivity(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        mIReminderAdded.onDismisClick();
        dismiss();
    }

    public interface IdialogDismissFragmentReload {
        public void onDismisClick();
    }
}