package com.navtej.doordashlite.view.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.navtej.doordashlite.R;
import com.navtej.doordashlite.databinding.FragmentRestaurantListBinding;
import com.navtej.doordashlite.di.Injectable;
import com.navtej.doordashlite.model.RestaurantDetail;
import com.navtej.doordashlite.view.adapter.RestaurantAdapter;
import com.navtej.doordashlite.view.callback.RestaurantClickCallback;
import com.navtej.doordashlite.viewmodel.RestaurantListViewModel;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

public class RestaurantListFragment extends Fragment implements Injectable {
    public static final String TAG = "RestaurantListFragment";
    private RestaurantAdapter restaurantAdapter;
    private FragmentRestaurantListBinding binding;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_restaurant_list, container, false);

        restaurantAdapter = new RestaurantAdapter(getActivity(), restaurantClickCallback);
        binding.projectList.setAdapter(restaurantAdapter);
        binding.setIsLoading(true);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final RestaurantListViewModel viewModel = ViewModelProviders.of(this,
                viewModelFactory).get(RestaurantListViewModel.class);

        observeViewModel(viewModel);
    }

    private void observeViewModel(RestaurantListViewModel viewModel) {
        // Update the list when the data changes
        viewModel.getRestaurantListObservable().observe(this, new Observer<List<RestaurantDetail>>() {
            @Override
            public void onChanged(@Nullable List<RestaurantDetail> restaurantDetailList) {
                if (restaurantDetailList != null) {
                    binding.setIsLoading(false);
                    restaurantAdapter.setProjectList(restaurantDetailList);
                }
            }
        });
    }

    private final RestaurantClickCallback restaurantClickCallback = new RestaurantClickCallback() {
        @Override
        public void onClick(RestaurantDetail restaurantDetail) {
            if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                ((MainActivity) getActivity()).show(restaurantDetail);
            }
        }
    };
}
