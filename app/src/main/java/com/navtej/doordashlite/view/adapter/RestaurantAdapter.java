package com.navtej.doordashlite.view.adapter;

import androidx.databinding.DataBindingUtil;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.navtej.doordashlite.R;
import com.navtej.doordashlite.databinding.RestaurantListItemBinding;
import com.navtej.doordashlite.model.RestaurantDetail;
import com.navtej.doordashlite.view.callback.RestaurantClickCallback;

import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> {

    List<? extends RestaurantDetail> restaurantDetailList;

    @Nullable
    private final RestaurantClickCallback projectClickCallback;
    private final Context mContext;

    public RestaurantAdapter(@Nullable  Context context, @Nullable RestaurantClickCallback projectClickCallback) {
        mContext = context;
        this.projectClickCallback = projectClickCallback;
    }

    public void setProjectList(final List<? extends RestaurantDetail> restaurantDetailList) {
        if (this.restaurantDetailList == null) {
            this.restaurantDetailList = restaurantDetailList;
            notifyItemRangeInserted(0, restaurantDetailList.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return RestaurantAdapter.this.restaurantDetailList.size();
                }

                @Override
                public int getNewListSize() {
                    return restaurantDetailList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return RestaurantAdapter.this.restaurantDetailList.get(oldItemPosition).getId() ==
                            restaurantDetailList.get(newItemPosition).getId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    RestaurantDetail restaurantDetail = restaurantDetailList.get(newItemPosition);
                    RestaurantDetail old = restaurantDetailList.get(oldItemPosition);
                    return restaurantDetail.getId() == old.getId();
                }
            });
            this.restaurantDetailList = restaurantDetailList;
            result.dispatchUpdatesTo(this);
        }
    }

    @Override
    public RestaurantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RestaurantListItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.restaurant_list_item,
                        parent, false);

        binding.setCallback(projectClickCallback);

        return new RestaurantViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(RestaurantViewHolder holder, int pos) {
        holder.binding.setRestaurant(restaurantDetailList.get(pos));

        RestaurantDetail restaurantDetail  = restaurantDetailList.get(pos);
        Glide.with(mContext).load(restaurantDetail.getCoverURL())
                .fitCenter()
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.binding.coverImg);
        holder.binding.nameText.setText(restaurantDetail.getName());
        holder.binding.descText.setText(restaurantDetail.getShortDescription());
        holder.binding.statusText.setText(restaurantDetail.getStatus());
    }

    @Override
    public int getItemCount() {
        return restaurantDetailList == null ? 0 : restaurantDetailList.size();
    }

    static class RestaurantViewHolder extends RecyclerView.ViewHolder {

        final RestaurantListItemBinding binding;

        public RestaurantViewHolder(RestaurantListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
