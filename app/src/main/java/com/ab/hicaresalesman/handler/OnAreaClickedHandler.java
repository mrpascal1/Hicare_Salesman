package com.ab.hicaresalesman.handler;

/**
 * Created by Arjun Bhatt on 5/5/2021.
 */
public interface OnAreaClickedHandler {
    void onCloneClicked(int parentPosition, int childPosition);

    void onDeleteClicked(int parentPosition, int childPosition);

    void onViewActivityClicked(int parentPosition, int childPosition);
}
