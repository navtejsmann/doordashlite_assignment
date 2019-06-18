package com.navtej.doordashlite.di;

import com.navtej.doordashlite.viewmodel.RestaurantListViewModel;
import com.navtej.doordashlite.viewmodel.RestaurantViewModel;
import com.navtej.doordashlite.viewmodel.RestaurantViewModelFactory;

import dagger.Subcomponent;

/**
 * A sub component to create ViewModels. It is called by the
 * {@link RestaurantViewModelFactory}.
 */
@Subcomponent
public interface ViewModelSubComponent {
    @Subcomponent.Builder
    interface Builder {
        ViewModelSubComponent build();
    }

    RestaurantListViewModel restaurantListViewModel();
    RestaurantViewModel restaurantViewModel();
}
