package com.navtej.doordashlite.view.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.navtej.doordashlite.R;
import com.navtej.doordashlite.databinding.FragmentRestaurantDetailsBinding;
import com.navtej.doordashlite.databinding.RestaurantListItemBinding;
import com.navtej.doordashlite.di.Injectable;
import com.navtej.doordashlite.model.RestaurantDetail;
import com.navtej.doordashlite.viewmodel.RestaurantViewModel;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;


public class RestaurantFragment extends Fragment implements Injectable {
    private static final String KEY_RESTAURANT_ID = "restaurant_id";
    private FragmentRestaurantDetailsBinding binding;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        // Inflate this data binding layout
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_restaurant_details, container, false);

        // Create and set the adapter for the RecyclerView.
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final RestaurantViewModel viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(RestaurantViewModel.class);

        viewModel.setRestaurantId(getArguments().getString(KEY_RESTAURANT_ID));

        binding.setRestaurantViewModel(viewModel);
        binding.setIsLoading(true);

        observeViewModel(viewModel);
    }

    private void observeViewModel(final RestaurantViewModel viewModel) {
        // Observe restaurant data
        viewModel.getObservableRestaurant().observe(this, new Observer<RestaurantDetail>() {
            @Override
            public void onChanged(@Nullable RestaurantDetail restaurantDetail) {
                if (restaurantDetail != null) {
                    binding.setIsLoading(false);
                    viewModel.setRestaurantDetail(restaurantDetail);
                }
            }
        });
    }

    /** Creates restaurant fragment for specific restaurant ID */
    public static RestaurantFragment forRestaurant(String restaurantId) {
        RestaurantFragment fragment = new RestaurantFragment();
        Bundle args = new Bundle();

        args.putString(KEY_RESTAURANT_ID, restaurantId);
        fragment.setArguments(args);

        return fragment;
    }
}
