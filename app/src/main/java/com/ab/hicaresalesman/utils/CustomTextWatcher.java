package com.ab.hicaresalesman.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Created by Arjun Bhatt on 5/18/2021.
 */
public class CustomTextWatcher implements TextWatcher {
    private int position;
    private Listener listener;

    public void setNewPosition(int position,Listener listener) {
        this.position = position;
        this.listener = listener;
     }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        listener.onTextChanged(position,charSequence.toString());
     }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    public int getPosition() {
        return position;
    }

    public interface Listener{
        public void onTextChanged(int pos,String text);
    }
}
