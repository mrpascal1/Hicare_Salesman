package com.ab.hicaresalesman.network;

import android.app.Service;
import android.os.Build;

import com.ab.hicaresalesman.BaseApplication;
import com.ab.hicaresalesman.BaseFragment;
import com.ab.hicaresalesman.R;
import com.ab.hicaresalesman.network.models.activity.ActivityResponse;
import com.ab.hicaresalesman.network.models.activity.AddActivityRequest;
import com.ab.hicaresalesman.network.models.activity.AddActivityResponse;
import com.ab.hicaresalesman.network.models.login.LoginResponse;
import com.ab.hicaresalesman.network.models.opportunity.OpportunityResponse;
import com.ab.hicaresalesman.network.models.pest_service.ServiceResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Arjun Bhatt on 4/21/2021.
 */
public class NetworkCallController {
    private final BaseFragment mContext;
    private NetworkResponseListner mListner;

    public NetworkCallController(BaseFragment context) {
        this.mContext = context;
    }

    public NetworkCallController() {
        this.mContext = null;
    }

    public void setListner(NetworkResponseListner listner) {
        this.mListner = listner;
    }

    public void login(final int requestCode, String username, String password) {

        mContext.showProgressDialog();

        BaseApplication.getRetrofitAPI(false)
                .getLogin(username, password)
                .enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        mContext.dismissProgressDialog();

                        if (response != null) {
                            if (response.body() != null) {
                                mListner.onResponse(requestCode, response.body().getData());
                            } else if (response.errorBody() != null) {
                                try {
                                    JSONObject jObjError = new JSONObject(response.errorBody().string());
                                    mContext.showServerError(response.errorBody().string());

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        } else {
                            mContext.showServerError(response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        mContext.dismissProgressDialog();
                        mContext.showServerError("Something went wrong, please try again !!!");
                    }
                });
    }

    public void getRecentOpportunity(final int requestCode, final String userId) {
        try {
            mContext.showProgressDialog();
            BaseApplication.getRetrofitAPI(false)
                    .getRecentOpportunity(userId)
                    .enqueue(new Callback<OpportunityResponse>() {
                        @Override
                        public void onResponse(Call<OpportunityResponse> call,
                                               Response<OpportunityResponse> response) {
                            mContext.dismissProgressDialog();

                            if (response != null) {
                                if (response.body() != null) {
                                    mListner.onResponse(requestCode, response.body().getData());

                                } else if (response.errorBody() != null) {
                                    try {
                                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                                        mContext.showServerError(jObjError.getString("Message"));
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            } else {
                                mContext.showServerError();
                            }
                        }

                        @Override
                        public void onFailure(Call<OpportunityResponse> call, Throwable t) {
                            mContext.dismissProgressDialog();
                            mContext.showServerError(mContext.getString(R.string.something_went_wrong));

                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void getSearchOpportunity(final int requestCode, final String accountNo, final String userId ) {
        try {
            mContext.showProgressDialog();
            BaseApplication.getRetrofitAPI(false)
                    .getSearchOpportunity(accountNo,userId)
                    .enqueue(new Callback<OpportunityResponse>() {
                        @Override
                        public void onResponse(Call<OpportunityResponse> call,
                                               Response<OpportunityResponse> response) {
                            mContext.dismissProgressDialog();

                            if (response != null) {
                                if (response.body() != null) {
                                    mListner.onResponse(requestCode, response.body().getData());

                                } else if (response.errorBody() != null) {
                                    try {
                                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                                        mContext.showServerError(jObjError.getString("Message"));
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            } else {
                                mContext.showServerError();
                            }
                        }

                        @Override
                        public void onFailure(Call<OpportunityResponse> call, Throwable t) {
                            mContext.dismissProgressDialog();
                            mContext.showServerError(mContext.getString(R.string.something_went_wrong));

                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void getActivityList(final int requestCode, final String opportunityId) {
        try {
            mContext.showProgressDialog();
            BaseApplication.getRetrofitAPI(false)
                    .getActivityList(opportunityId)
                    .enqueue(new Callback<ActivityResponse>() {
                        @Override
                        public void onResponse(Call<ActivityResponse> call,
                                               Response<ActivityResponse> response) {
                            mContext.dismissProgressDialog();

                            if (response != null) {
                                if (response.body() != null) {
                                    mListner.onResponse(requestCode, response.body().getData());

                                } else if (response.errorBody() != null) {
                                    try {
                                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                                        mContext.showServerError(jObjError.getString("Message"));
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            } else {
                                mContext.showServerError();
                            }
                        }

                        @Override
                        public void onFailure(Call<ActivityResponse> call, Throwable t) {
                            mContext.dismissProgressDialog();
                            mContext.showServerError(mContext.getString(R.string.something_went_wrong));

                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void addActivity(final int requestCode, AddActivityRequest request) {
        try {
//            mContext.showProgressDialog();
            BaseApplication.getRetrofitAPI(true)
                    .addActivity(request)
                    .enqueue(new Callback<AddActivityResponse>() {
                        @Override
                        public void onResponse(Call<AddActivityResponse> call, Response<AddActivityResponse> response) {
//                            mContext.dismissProgressDialog();
                            if (response != null) {
                                if (response.body() != null) {
                                    mListner.onResponse(requestCode, response.body());
                                } else if (response.errorBody() != null) {
                                    try {
                                        JSONObject jObjError = new JSONObject(response.errorBody().string());
//                                        mContext.showServerError(jObjError.getString("ErrorMessage"));
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            } else {
//                                mContext.showServerError();
                            }
                        }

                        @Override
                        public void onFailure(Call<AddActivityResponse> call, Throwable t) {
//                            mContext.dismissProgressDialog();
//                            mContext.showServerError(mContext.getString(R.string.something_went_wrong));
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void getServiceByActivity(final int requestCode, final int activityId) {
        try {
            mContext.showProgressDialog();
            BaseApplication.getRetrofitAPI(false)
                    .getServiceByActivity(activityId)
                    .enqueue(new Callback<ServiceResponse>() {
                        @Override
                        public void onResponse(Call<ServiceResponse> call,
                                               Response<ServiceResponse> response) {
                            mContext.dismissProgressDialog();

                            if (response != null) {
                                if (response.body() != null) {
                                    mListner.onResponse(requestCode, response.body().getData());

                                } else if (response.errorBody() != null) {
                                    try {
                                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                                        mContext.showServerError(jObjError.getString("Message"));
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            } else {
                                mContext.showServerError();
                            }
                        }

                        @Override
                        public void onFailure(Call<ServiceResponse> call, Throwable t) {
                            mContext.dismissProgressDialog();
                            mContext.showServerError(mContext.getString(R.string.something_went_wrong));

                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
