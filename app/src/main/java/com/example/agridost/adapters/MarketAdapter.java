package com.example.agridost.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agridost.R;
import com.example.agridost.models.MarketPrice;

import java.util.List;

public class MarketAdapter extends RecyclerView.Adapter<MarketAdapter.MarketViewHolder> {

    private List<MarketPrice> prices;

    public MarketAdapter(List<MarketPrice> prices) {
        this.prices = prices;
    }

    @NonNull
    @Override
    public MarketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_market_price, parent, false);
        return new MarketViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MarketViewHolder holder, int position) {
        holder.bind(prices.get(position));
    }

    @Override
    public int getItemCount() {
        return prices.size();
    }

    static class MarketViewHolder extends RecyclerView.ViewHolder {
        TextView tvCrop, tvPrice, tvForecast;
        ImageView ivTrend;

        public MarketViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCrop = itemView.findViewById(R.id.tvCrop);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvForecast = itemView.findViewById(R.id.tvForecast);
            ivTrend = itemView.findViewById(R.id.ivTrend);
        }

        public void bind(MarketPrice price) {
            tvCrop.setText(price.getCrop());
            tvPrice.setText("₹" + price.getPrice() + "/q");
            tvForecast.setText("Forecast: ₹" + price.getForecast());
            
            if ("up".equals(price.getTrend())) {
                ivTrend.setImageResource(android.R.drawable.arrow_up_float);
                ivTrend.setColorFilter(0xFF2E7D32); // Green
            } else if ("down".equals(price.getTrend())) {
                ivTrend.setImageResource(android.R.drawable.arrow_down_float);
                ivTrend.setColorFilter(0xFFC62828); // Red
            } else {
                ivTrend.setImageResource(android.R.drawable.ic_menu_sort_by_size); // Stable
                ivTrend.setColorFilter(0xFFF57F17); // Orange
            }
        }
    }
}
