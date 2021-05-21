package com.ab.hicaresalesman.fragments;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ab.hicaresalesman.BaseFragment;
import com.ab.hicaresalesman.R;
import com.ab.hicaresalesman.activities.Camera2Activity;
import com.ab.hicaresalesman.adapters.RecyclerQuestionParentAdapter;
import com.ab.hicaresalesman.databinding.FragmentServiceQuestionBinding;
import com.ab.hicaresalesman.handler.OnQuestionClickedHandler;
import com.ab.hicaresalesman.network.NetworkCallController;
import com.ab.hicaresalesman.network.NetworkResponseListner;
import com.ab.hicaresalesman.network.models.BaseResponse;
import com.ab.hicaresalesman.network.models.image_upload.ImageUploadData;
import com.ab.hicaresalesman.network.models.image_upload.ImageUploadRequest;
import com.ab.hicaresalesman.network.models.pest_service.AddServiceRequest;
import com.ab.hicaresalesman.network.models.question.QuestionData;
import com.ab.hicaresalesman.network.models.question.Questions;
import com.ab.hicaresalesman.network.models.question.SaveAnswerRequest;
import com.ab.hicaresalesman.utils.AppUtils;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import es.dmoral.toasty.Toasty;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ServiceQuestionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ServiceQuestionFragment extends BaseFragment implements OnQuestionClickedHandler {
    FragmentServiceQuestionBinding mFragmentServiceQuestionBinding;
    public static final String ARGS_ACTIVITY = "ARGS_ACTIVITY";
    RecyclerQuestionParentAdapter mAdapter;
    RecyclerView.LayoutManager layoutManager;
    private static final int REQUEST_CODE = 1234;
    private boolean mPermissions;
    private int parentPos = 0;
    private int childPos = 0;
    private List<SaveAnswerRequest> mAnswerList = new ArrayList<>();
    private List<QuestionData> questionData = new ArrayList<>();
    private ViewPager viewPager;
    private int activityId = 0;
    private HashMap<Integer, SaveAnswerRequest> mMap = new HashMap<>();

    public ServiceQuestionFragment() {
        // Required empty public constructor
    }

    public static ServiceQuestionFragment newInstance(int activityId) {
        ServiceQuestionFragment fragment = new ServiceQuestionFragment();
        Bundle args = new Bundle();
        args.putInt(ARGS_ACTIVITY, activityId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            activityId = getArguments().getInt(ARGS_ACTIVITY);
        }
        AppUtils.CAMERA_SCREEN = "QUESTIONS";
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(mMessageReceiver,
                new IntentFilter(AppUtils.CAMERA_SCREEN));
    }

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            String base64 = intent.getStringExtra("base64");
            uploadOnsiteImage(base64);
            Log.d("receiver", "Got message: " + base64);
        }
    };

    private void uploadOnsiteImage(String base64) {
        try {

            ImageUploadRequest request = new ImageUploadRequest();
            request.setFileUrl("");
            request.setFileName("");
            request.setFileContent(base64);
            request.setActivityId(String.valueOf(activityId));

            NetworkCallController controller = new NetworkCallController();
            controller.setListner(new NetworkResponseListner<ImageUploadData>() {
                @Override
                public void onResponse(ImageUploadData response) {
                    try {
                        mAdapter.getItem(parentPos).getQuestions().get(childPos).setPictureUrl(response.getFileUrl());
                        mAdapter.notifyItemChanged(parentPos);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onFailure() {

                }
            });
            controller.uploadImage(request);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mFragmentServiceQuestionBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_service_question, container, false);
        return mFragmentServiceQuestionBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPager = getActivity().findViewById(R.id.viewPager);
        mFragmentServiceQuestionBinding.recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        mFragmentServiceQuestionBinding.recyclerView.setLayoutManager(layoutManager);
        mAdapter = new RecyclerQuestionParentAdapter(getActivity(), (position, childPos, questionId, option) -> {
            SaveAnswerRequest request = new SaveAnswerRequest();
            request.setActivityId(activityId);
            if (option.getOptionTitle().equals("Select Answer")) {
                request.setAnswerText("");
            } else {
                request.setAnswerText(option.getOptionTitle());
            }
            request.setNetScore(option.getNetScore());
            request.setQuestionId(option.getQuestionId());
            request.setId(0);
            request.setCreatedByIdUser(0);
            request.setImageRequired(mAdapter.getItem(position).getQuestions().get(childPos).getImageRequired());
            request.setRiskLevel(option.getCategoryName());
            request.setModifiedOn(AppUtils.currentDateTime());
            request.setServiceName(mAdapter.getItem(position).getServiceType());
            request.setPicture_Url(questionData.get(position).getQuestions().get(childPos).getPictureUrl());
            mAnswerList.add(request);
            mMap.put(questionId, request);
        });
        mFragmentServiceQuestionBinding.recyclerView.setAdapter(mAdapter);
        mAdapter.setOnQuestionClicked(this);
        getQuestionsByActivity();
    }


    private void getQuestionsByActivity() {
        try {
            NetworkCallController controller = new NetworkCallController(this);
            controller.setListner(new NetworkResponseListner<List<QuestionData>>() {

                @Override
                public void onResponse(List<QuestionData> items) {
                    if (items != null && items.size() > 0) {
                        questionData = items;
                        mAdapter.addData(items);
                        mAdapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onFailure() {
                }
            });
            controller.getQuestionByActivity(activityId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCameraClicked(int parentPos, int childPos) {
        this.parentPos = parentPos;
        this.childPos = childPos;
        init();
    }

    @Override
    public void onImageCancelled(int parentPosition, int childPosition) {
//        mAdapter.getItem(parentPos).getQuestions().get(childPos).setPictureUrl(null);
//        mAdapter.notifyItemChanged(parentPos);
    }

    private void init() {
        if (mPermissions) {
            if (checkCameraHardware(getActivity())) {
                startCamera2();
            } else {
                showSnackBar("You need a camera to use this application", Snackbar.LENGTH_INDEFINITE);
            }
        } else {
            verifyPermissions();
        }
    }

    private boolean checkCameraHardware(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            return false;
        }
    }

    private void startCamera2() {
        Intent intent = new Intent(getActivity(), Camera2Activity.class);
        intent.putExtra(AppUtils.CAMERA_ORIENTATION, "BACK");
        startActivity(intent);
    }

    private void showSnackBar(final String text, final int length) {
        View view = getActivity().findViewById(android.R.id.content).getRootView();
        Snackbar.make(view, text, length).show();
    }

    public void verifyPermissions() {
        Log.d("TAG", "verifyPermissions: asking user for permissions.");
        String[] permissions = {
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA};
        if (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(),
                permissions[0]) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(getActivity().getApplicationContext(),
                permissions[1]) == PackageManager.PERMISSION_GRANTED) {
            mPermissions = true;
            init();
        } else {
            ActivityCompat.requestPermissions(
                    getActivity(),
                    permissions,
                    REQUEST_CODE
            );
        }
    }

    public void refresh() {
        if (mMap != null) {
            mMap.clear();
        }
        getQuestionsByActivity();
    }

    private boolean isListChecked(List<SaveAnswerRequest> listData) {
        for (SaveAnswerRequest data : listData) {
            if ((data.getAnswerText().equals(""))) {
                return false;
            }
        }
        return true;
    }

    private boolean isImgChecked(List<SaveAnswerRequest> imgList) {
        boolean isRequired = true;
        for (SaveAnswerRequest data : imgList) {
            if (data.isImageRequired()) {
                if (data.getPicture_Url() != null && !data.getPicture_Url().equals("")) {
                    isRequired = true;
                } else {
                    isRequired = false;
                    break;
                }
            }
        }
        return isRequired;
    }


    public void saveAnswers() {
        try {
            mAnswerList = new ArrayList<>(mMap.values());
            if (mAnswerList.size() > 0) {
                if (isListChecked(mAnswerList)) {
                    if (isImgChecked(mAnswerList)) {
                        NetworkCallController controller = new NetworkCallController(this);
                        controller.setListner(new NetworkResponseListner<BaseResponse>() {
                            @Override
                            public void onResponse(BaseResponse response) {
                                if (response.getIsSuccess()) {
//                            mAnswerList.clear();
                                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
                                }
                            }

                            @Override
                            public void onFailure() {

                            }
                        });
                        controller.saveAnswers(mAnswerList);
                    } else {
                        Toasty.error(getActivity(), "Image Required!", Toasty.LENGTH_SHORT).show();
                    }
                } else {
                    Toasty.error(getActivity(), "Please select answer!", Toasty.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(getActivity(), "Please Select Options", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}