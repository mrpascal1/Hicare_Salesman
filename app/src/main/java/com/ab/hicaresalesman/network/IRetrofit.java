package com.ab.hicaresalesman.network;

import com.ab.hicaresalesman.network.models.activity.ActivityResponse;
import com.ab.hicaresalesman.network.models.activity.AddActivityRequest;
import com.ab.hicaresalesman.network.models.activity.AddActivityResponse;
import com.ab.hicaresalesman.network.models.login.LoginResponse;
import com.ab.hicaresalesman.network.models.opportunity.OpportunityResponse;
import com.ab.hicaresalesman.network.models.pest_service.ServiceResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IRetrofit {

    String BASE_URL = "http://connect.hicare.in/b2bwow/api/";

    /*[User Login]*/
    @GET("User/LoginAsync")
    Call<LoginResponse> getLogin(@Query("username") String username, @Query("password") String password);


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
    Call<ServiceResponse> getServiceByActivity(@Query("activityId") int userId);

}
