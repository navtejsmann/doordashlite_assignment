package com.navtej.doordashlite.view.ui;

import android.os.Bundle;

import com.navtej.doordashlite.R;
import com.navtej.doordashlite.model.RestaurantDetail;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class MainActivity extends AppCompatActivity implements HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Add restaurant list fragment if this is first creation
        if (savedInstanceState == null) {
            RestaurantListFragment fragment = new RestaurantListFragment();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, fragment, RestaurantListFragment.TAG).commit();
        }
    }

    /** Shows the restaurant detail fragment */
    public void show(RestaurantDetail restaurantDetail) {
        RestaurantFragment restaurantFragment = RestaurantFragment.forRestaurant(restaurantDetail.getId());

        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack("restaurant")
                .replace(R.id.fragment_container,
                        restaurantFragment, null).commit();
    }

    @Override
    public DispatchingAndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }
}
