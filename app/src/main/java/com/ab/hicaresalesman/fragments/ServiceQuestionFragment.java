package com.ab.hicaresalesman.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ab.hicaresalesman.BaseFragment;
import com.ab.hicaresalesman.R;
import com.ab.hicaresalesman.databinding.FragmentServiceQuestionBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ServiceQuestionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ServiceQuestionFragment extends BaseFragment {
FragmentServiceQuestionBinding mFragmentServiceQuestionBinding;

    public ServiceQuestionFragment() {
        // Required empty public constructor
    }

    public static ServiceQuestionFragment newInstance() {
        ServiceQuestionFragment fragment = new ServiceQuestionFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

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
    }
}