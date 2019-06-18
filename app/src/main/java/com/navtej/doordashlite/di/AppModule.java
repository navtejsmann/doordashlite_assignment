package com.navtej.doordashlite.di;

import android.util.Log;

import com.navtej.doordashlite.model.DoorDashService;
import com.navtej.doordashlite.viewmodel.RestaurantViewModelFactory;

import javax.inject.Singleton;

import androidx.lifecycle.ViewModelProvider;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(subcomponents = ViewModelSubComponent.class)
class AppModule {
    @Singleton @Provides
    DoorDashService provideDoorDashService() {

        DoorDashService doorDashService = new Retrofit.Builder()
                .baseUrl(DoorDashService.HTTPS_API_DOORDASH_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(DoorDashService.class);

        return doorDashService;
    }

    @Singleton
    @Provides
    ViewModelProvider.Factory provideViewModelFactory(
            ViewModelSubComponent.Builder viewModelSubComponent) {

        return new RestaurantViewModelFactory(viewModelSubComponent.build());
    }
}
