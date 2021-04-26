package com.ab.hicaresalesman.network;

/**
 * Created by arjun on 10/12/19.
 */

 public interface NetworkResponseListner<T> {
  void onResponse(int requestCode, T response);

  void onFailure(int requestCode);
}
