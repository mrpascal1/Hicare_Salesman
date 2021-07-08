package com.ab.hicaresalesman.network;

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

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IRetrofit {

    String BASE_URL = "http://connect.hicare.in/b2bwow/api/";

    /*[User Login]*/
    @GET("User/LoginAsync")
    Call<LoginResponse> getLogin(@Query("userName") String username, @Query("password") String password);


    /*[Opportunity/RecentOpportunityAsync]*/
    @GET("Opportunity/RecentOpportunityAsync")
    Call<OpportunityResponse> getRecentOpportunity(@Query("userId") String userId);

    /*[Opportunity/RecentOpportunityAsync]*/
    @GET("Opportunity/SearchOpportunityAsync")
    Call<OpportunityResponse> getSearchOpportunity(@Query("accountNo") String accountNo, @Query("userId") String userId);

    /*[Activity/GetActivityList]*/
    @GET("Activity/GetActivityList")
    Call<ActivityResponse> getActivityList(@Query("opportunityId") String userId);

    /*[Activity/AddActivity]*/
    @POST("Activity/AddActivity")
    Call<AddActivityResponse> addActivity(@Body AddActivityRequest request);

    /*[ServiceMaster/GetServiceByActivity]*/
    @GET("ServiceMaster/GetServiceByActivity")
    Call<ServiceResponse> getServiceByActivity(@Query("activityId") int activityId);

    /*[QuestionMaster/GetQuestionListByActivity]*/
    @GET("QuestionMaster/GetQuestionListByActivity")
    Call<QuestionResponse> getQuestionsByActivity(@Query("activityId") int activityId);


    /*[QuestionMaster/GetQuestionListByActivity]*/
    @GET("IndustrySubAreaMaster/GetActivitySubAreaList")
    Call<AreaResponse> getSubArea(@Query("activityId") int activityId);

    /*[Attachment/UploadAttachment]*/
    @POST("Attachment/UploadAttachment")
    Call<ImageUploadResponse> uploadImage(@Body ImageUploadRequest request);


//    @POST("Task/UploadOnsiteImage")
//    Call<CovidResponse> uploadOnsiteImage(@Body CovidRequest request);

    /*[ServiceMaster/AddServiceByActivity]*/
    @POST("ServiceMaster/AddServiceByActivity")
    Call<BaseResponse> addServiceByActivity(@Body List<AddServiceRequest> request);

    /*[QuestionMaster/SaveAnswers]*/
    @POST("QuestionMaster/SaveAnswers")
    Call<BaseResponse> saveAnswers(@Body List<SaveAnswerRequest> request);

    /*[IndustrySubAreaMaster/AddActivitySubArea]*/
    @POST("IndustrySubAreaMaster/AddActivitySubArea")
    Call<BaseResponse> addSubArea(@Body List<AddAreaRequest> request);

    /*[RecommendedFrequency/GetRecommendedFrequencyList]*/
    @GET("RecommendedFrequency/GetRecommendedFrequencyList")
    Call<FrequencyResponse> getFrequencyList(@Query("activityId") int activityId);

    /*[RecommendedFrequency/AddRecommendedFrequency]*/
    @POST("RecommendedFrequency/AddRecommendedFrequency")
    Call<BaseResponse> addFrequency(@Body List<FrequencyRequest> request);

    /*[RecommendedFrequency/GetRecommendedFrequencyList]*/
    @GET("Activity/GetActivityServiceList")
    Call<CostServiceList> getActivityServiceList(@Query("activityId") int activityId);

    @POST("Activity/CloneActivity")
    Call<BaseResponse> cloneActivity(@Body HashMap<String, Object> clone);
}
