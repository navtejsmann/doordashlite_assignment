package com.navtej.doordashlite.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.annotation.NonNull;


import com.navtej.doordashlite.model.DoorDashRepository;
import com.navtej.doordashlite.model.RestaurantDetail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

public class RestaurantListViewModel extends AndroidViewModel {
    private final LiveData<List<RestaurantDetail>> mRestaurantListObservable;

    @Inject
    public RestaurantListViewModel(@NonNull DoorDashRepository doorDashRepository, @NonNull Application application) {
        super(application);

        Map<String, String> options = new HashMap<>();
        options.put("lat", "37.422740");
        options.put("lng", "-122.139956");
        options.put("offset", "0");
        options.put("limit", "50");
        mRestaurantListObservable = doorDashRepository.getRestaurantList(options);
    }

    /**
     * Expose the LiveData Restaurant query so the UI can observe it.
     */
    public LiveData<List<RestaurantDetail>> getRestaurantListObservable() {
        return mRestaurantListObservable;
    }
}
