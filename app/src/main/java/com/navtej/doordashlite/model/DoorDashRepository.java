package com.navtej.doordashlite.model;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class DoorDashRepository {
    private static final String TAG = "DoorDashRepository";

    private DoorDashService mDoorDashRepository;

    @Inject
    public DoorDashRepository(DoorDashService doorDashService){
        this.mDoorDashRepository = doorDashService;
    }

    public LiveData<List<RestaurantDetail>> getRestaurantList(Map<String, String> options){
        final MutableLiveData<List<RestaurantDetail>> data = new MutableLiveData<>();

        mDoorDashRepository.getRestaurantList(options).enqueue(new Callback<List<RestaurantDetail>>() {
            @Override
            public void onResponse(Call<List<RestaurantDetail>> call, Response<List<RestaurantDetail>> response) {

                Log.i(TAG, response.body().toString());

                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<RestaurantDetail>> call, Throwable t) {
                //TODO handle error gracefully
                data.setValue(null);
            }
        });

        return data;
    }


    public LiveData<RestaurantDetail> getRestaurantDetails(String restaurantId){
        final MutableLiveData<RestaurantDetail> data = new MutableLiveData<>();

        mDoorDashRepository.getRestaurantDetails(restaurantId).enqueue(new Callback<RestaurantDetail>() {
            @Override
            public void onResponse(Call<RestaurantDetail> call, Response<RestaurantDetail> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<RestaurantDetail> call, Throwable t) {
                //TODO handle error gracefully
                data.setValue(null);
            }
        });

        return data;

    }
}
