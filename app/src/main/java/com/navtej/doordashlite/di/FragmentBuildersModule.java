package com.navtej.doordashlite.di;

import com.navtej.doordashlite.view.ui.RestaurantFragment;
import com.navtej.doordashlite.view.ui.RestaurantListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract RestaurantFragment contributeRestaurantFragment();

    @ContributesAndroidInjector
    abstract RestaurantListFragment contributeRestaurantListFragment();
}
