package com.ab.hicaresalesman.network;

import android.app.Service;
import android.os.Build;

import com.ab.hicaresalesman.BaseApplication;
import com.ab.hicaresalesman.BaseFragment;
import com.ab.hicaresalesman.R;
import com.ab.hicaresalesman.network.models.BaseResponse;
import com.ab.hicaresalesman.network.models.activity.ActivityResponse;
import com.ab.hicaresalesman.network.models.activity.AddActivityRequest;
import com.ab.hicaresalesman.network.models.activity.AddActivityResponse;
import com.ab.hicaresalesman.network.models.area.AddAreaRequest;
import com.ab.hicaresalesman.network.models.area.AreaResponse;
import com.ab.hicaresalesman.network.models.cost_service_list.CostServiceList;
import com.ab.hicaresalesman.network.models.frequency.FrequencyRequest;
import com.ab.hicaresalesman.network.models.frequency.FrequencyResponse;
import com.ab.hicaresalesman.network.models.image_upload.ImageUploadRequest;
import com.ab.hicaresalesman.network.models.image_upload.ImageUploadResponse;
import com.ab.hicaresalesman.network.models.login.LoginResponse;
import com.ab.hicaresalesman.network.models.opportunity.OpportunityResponse;
import com.ab.hicaresalesman.network.models.pest_service.AddServiceRequest;
import com.ab.hicaresalesman.network.models.pest_service.ServiceResponse;
import com.ab.hicaresalesman.network.models.question.QuestionResponse;
import com.ab.hicaresalesman.network.models.question.SaveAnswerRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

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

    public void login(String username, String password) {

        mContext.showProgressDialog();

        BaseApplication.getRetrofitAPI(false)
                .getLogin(username, password)
                .enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        mContext.dismissProgressDialog();

                        if (response != null) {
                            if (response.body() != null) {
                                mListner.onResponse(response.body().getData());
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

    public void getRecentOpportunity(final String userId) {
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
                                    mListner.onResponse(response.body().getData());

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

    public void getSearchOpportunity(final String accountNo, final String userId) {
        try {
            mContext.showProgressDialog();
            BaseApplication.getRetrofitAPI(false)
                    .getSearchOpportunity(accountNo, userId)
                    .enqueue(new Callback<OpportunityResponse>() {
                        @Override
                        public void onResponse(Call<OpportunityResponse> call,
                                               Response<OpportunityResponse> response) {
                            mContext.dismissProgressDialog();

                            if (response != null) {
                                if (response.body() != null) {
                                    mListner.onResponse(response.body().getData());

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

    public void getActivityList(final String opportunityId) {
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
                                    mListner.onResponse(response.body().getData());

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

    public void addActivity(AddActivityRequest request) {
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
                                    mListner.onResponse(response.body());
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

    public void cloneActivity(HashMap<String, Object> request){
        BaseApplication.getRetrofitAPI(true)
                .cloneActivity(request)
                .enqueue(new Callback<BaseResponse>() {
                    @Override
                    public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                        if (response != null) {
                            if (response.body() != null) {
                                mListner.onResponse(response.body());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<BaseResponse> call, Throwable t) {
                        mListner.onFailure();
                    }
                });
    }

    public void getServiceByActivity(final int activityId) {
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
                                    mListner.onResponse(response.body().getData());

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

    public void getQuestionByActivity(final int activityId) {
        try {
//            mContext.showProgressDialog();
            BaseApplication.getRetrofitAPI(false)
                    .getQuestionsByActivity(activityId)
                    .enqueue(new Callback<QuestionResponse>() {
                        @Override
                        public void onResponse(Call<QuestionResponse> call,
                                               Response<QuestionResponse> response) {
//                            mContext.dismissProgressDialog();

                            if (response != null) {
                                if (response.body() != null) {
                                    mListner.onResponse(response.body().getData());

                                } else if (response.errorBody() != null) {
                                    try {
                                        JSONObject jObjError = new JSONObject(response.errorBody().string());
//                                        mContext.showServerError(jObjError.getString("Message"));
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            } else {
//                                mContext.showServerError();
                            }
                        }

                        @Override
                        public void onFailure(Call<QuestionResponse> call, Throwable t) {
//                            mContext.dismissProgressDialog();
//                            mContext.showServerError(mContext.getString(R.string.something_went_wrong));
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getActivityBySubArea(final int activityId) {
        try {
//            mContext.showProgressDialog();
            BaseApplication.getRetrofitAPI(false)
                    .getSubArea(activityId)
                    .enqueue(new Callback<AreaResponse>() {
                        @Override
                        public void onResponse(Call<AreaResponse> call,
                                               Response<AreaResponse> response) {
//                            mContext.dismissProgressDialog();

                            if (response != null) {
                                if (response.body() != null) {
                                    mListner.onResponse(response.body().getData());

                                } else if (response.errorBody() != null) {
                                    try {
                                        JSONObject jObjError = new JSONObject(response.errorBody().string());
//                                        mContext.showServerError(jObjError.getString("Message"));
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            } else {
//                                mContext.showServerError();
                            }
                        }

                        @Override
                        public void onFailure(Call<AreaResponse> call, Throwable t) {
//                            mContext.dismissProgressDialog();
//                            mContext.showServerError(mContext.getString(R.string.something_went_wrong));

                        }
                    });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void uploadImage(ImageUploadRequest request) {
        try {
//            mContext.showProgressDialog();
            BaseApplication.getRetrofitAPI(true)
                    .uploadImage(request)
                    .enqueue(new Callback<ImageUploadResponse>() {
                        @Override
                        public void onResponse(Call<ImageUploadResponse> call, Response<ImageUploadResponse> response) {
//                            mContext.dismissProgressDialog();
                            if (response != null) {
                                if (response.body() != null) {
                                    mListner.onResponse(response.body().getData());
                                } else if (response.errorBody() != null) {
                                    try {
//                                        JSONObject jObjError = new JSONObject(response.errorBody().string());
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
                        public void onFailure(Call<ImageUploadResponse> call, Throwable t) {
//                            mContext.dismissProgressDialog();
//                            mContext.showServerError(mContext.getString(R.string.something_went_wrong));
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void addServiceByActivity(List<AddServiceRequest> request) {
        try {
            mContext.showProgressDialog();
            BaseApplication.getRetrofitAPI(true)
                    .addServiceByActivity(request)
                    .enqueue(new Callback<BaseResponse>() {
                        @Override
                        public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                            mContext.dismissProgressDialog();
                            if (response != null) {
                                if (response.body() != null) {
                                    mListner.onResponse(response.body());
                                } else if (response.errorBody() != null) {
                                    try {
                                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                                        mContext.showServerError(jObjError.getString("ErrorMessage"));
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            } else {
                                mContext.showServerError();
                            }
                        }

                        @Override
                        public void onFailure(Call<BaseResponse> call, Throwable t) {
                            mContext.dismissProgressDialog();
                            mContext.showServerError(mContext.getString(R.string.something_went_wrong));
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void saveAnswers(List<SaveAnswerRequest> request) {
        try {
            mContext.showProgressDialog();
            BaseApplication.getRetrofitAPI(true)
                    .saveAnswers(request)
                    .enqueue(new Callback<BaseResponse>() {
                        @Override
                        public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                            mContext.dismissProgressDialog();
                            if (response != null) {
                                if (response.body() != null) {
                                    mListner.onResponse(response.body());
                                } else if (response.errorBody() != null) {
                                    try {
                                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                                        mContext.showServerError(jObjError.getString("ErrorMessage"));
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            } else {
                                mContext.showServerError();
                            }
                        }

                        @Override
                        public void onFailure(Call<BaseResponse> call, Throwable t) {
                            mContext.dismissProgressDialog();
                            mContext.showServerError(mContext.getString(R.string.something_went_wrong));
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void addSubArea(List<AddAreaRequest> request) {
        try {
            mContext.showProgressDialog();
            BaseApplication.getRetrofitAPI(true)
                    .addSubArea(request)
                    .enqueue(new Callback<BaseResponse>() {
                        @Override
                        public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                            mContext.dismissProgressDialog();
                            if (response != null) {
                                if (response.body() != null) {
                                    mListner.onResponse(response.body());
                                } else if (response.errorBody() != null) {
                                    try {
                                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                                        mContext.showServerError(jObjError.getString("ErrorMessage"));
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            } else {
                                mContext.showServerError();
                            }
                        }

                        @Override
                        public void onFailure(Call<BaseResponse> call, Throwable t) {
                            mContext.dismissProgressDialog();
                            mContext.showServerError(mContext.getString(R.string.something_went_wrong));
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void getFrequencyList(final int activityId) {
        try {
//            mContext.showProgressDialog();
            BaseApplication.getRetrofitAPI(false)
                    .getFrequencyList(activityId)
                    .enqueue(new Callback<FrequencyResponse>() {
                        @Override
                        public void onResponse(Call<FrequencyResponse> call,
                                               Response<FrequencyResponse> response) {
//                            mContext.dismissProgressDialog();

                            if (response != null) {
                                if (response.body() != null) {
                                    mListner.onResponse(response.body().getData());

                                } else if (response.errorBody() != null) {
                                    try {
                                        JSONObject jObjError = new JSONObject(response.errorBody().string());
//                                        mContext.showServerError(jObjError.getString("Message"));
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            } else {
//                                mContext.showServerError();
                            }
                        }

                        @Override
                        public void onFailure(Call<FrequencyResponse> call, Throwable t) {
//                            mContext.dismissProgressDialog();
//                            mContext.showServerError(mContext.getString(R.string.something_went_wrong));

                        }
                    });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void addFrequency(List<FrequencyRequest> request) {
        try {
            mContext.showProgressDialog();
            BaseApplication.getRetrofitAPI(true)
                    .addFrequency(request)
                    .enqueue(new Callback<BaseResponse>() {
                        @Override
                        public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                            mContext.dismissProgressDialog();
                            if (response != null) {
                                if (response.body() != null) {
                                    mListner.onResponse(response.body());
                                } else if (response.errorBody() != null) {
                                    try {
                                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                                        mContext.showServerError(jObjError.getString("ErrorMessage"));
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            } else {
                                mContext.showServerError();
                            }
                        }

                        @Override
                        public void onFailure(Call<BaseResponse> call, Throwable t) {
                            mContext.dismissProgressDialog();
                            mContext.showServerError(mContext.getString(R.string.something_went_wrong));
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void getServiceCostList(final int activityId) {
        try {
            mContext.showProgressDialog();
            BaseApplication.getRetrofitAPI(false)
                    .getActivityServiceList(activityId)
                    .enqueue(new Callback<CostServiceList>() {
                        @Override
                        public void onResponse(Call<CostServiceList> call,
                                               Response<CostServiceList> response) {
                            mContext.dismissProgressDialog();

                            if (response != null) {
                                if (response.body() != null) {
                                    mListner.onResponse(response.body().getData());

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
                        public void onFailure(Call<CostServiceList> call, Throwable t) {
                            mContext.dismissProgressDialog();
                            mContext.showServerError(mContext.getString(R.string.something_went_wrong));
                        }
                    });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
