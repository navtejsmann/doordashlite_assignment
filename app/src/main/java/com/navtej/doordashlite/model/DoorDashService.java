package com.navtej.doordashlite.model;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface DoorDashService {
    String HTTPS_API_DOORDASH_URL = "https://api.doordash.com";

    @GET("/v2/restaurant/")
    Call<List<RestaurantDetail>> getRestaurantList(@QueryMap Map<String, String> options);

    @GET("/v2/restaurant/{restaurant_id}/")
    Call<RestaurantDetail> getRestaurantDetails(@Path("restaurant_id") String restaurantId);
}
