package com.ab.hicaresalesman.handler;

/**
 * Created by Arjun Bhatt on 5/1/2021.
 */
public interface OnQuestionClickedHandler {
    void onCameraClicked(int parentPosition, int childPosition);
    void onImageCancelled(int parentPosition, int childPosition);
}
