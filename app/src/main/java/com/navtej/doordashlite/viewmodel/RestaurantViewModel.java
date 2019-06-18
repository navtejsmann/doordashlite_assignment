package com.navtej.doordashlite.viewmodel;

import android.app.Application;
import android.util.Log;

import com.navtej.doordashlite.model.DoorDashRepository;
import com.navtej.doordashlite.model.RestaurantDetail;
import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

public class RestaurantViewModel extends AndroidViewModel {
    private static final String TAG = RestaurantViewModel.class.getName();
    private static final MutableLiveData ABSENT = new MutableLiveData();
    {
        //noinspection unchecked
        ABSENT.setValue(null);
    }

    private final LiveData<RestaurantDetail> restaurantObservable;
    private final MutableLiveData<String> restaurantId;

    public ObservableField<RestaurantDetail> restaurant = new ObservableField<>();

    @Inject
    public RestaurantViewModel(@NonNull DoorDashRepository doorDashRepository, @NonNull Application application) {
        super(application);

        this.restaurantId = new MutableLiveData<>();

        restaurantObservable = Transformations.switchMap(restaurantId, input -> {
            if (input.isEmpty()) {
                Log.i(TAG, "RestaurantViewModel restaurantId is absent!!!");
                return ABSENT;
            }

            Log.i(TAG,"RestaurantViewModel restaurantID is " + restaurantId.getValue());

            return doorDashRepository.getRestaurantDetails(restaurantId.getValue());
        });
    }

    public LiveData<RestaurantDetail> getObservableRestaurant() {
        return restaurantObservable;
    }

    public void setRestaurantDetail(RestaurantDetail restaurantDetail) {
        this.restaurant.set(restaurantDetail);
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId.setValue(restaurantId);
    }
}
