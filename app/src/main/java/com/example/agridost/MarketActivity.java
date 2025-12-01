package com.example.agridost;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agridost.adapters.MarketAdapter;
import com.example.agridost.models.MarketPrice;
import com.example.agridost.network.ApiService;
import com.example.agridost.network.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MarketActivity extends AppCompatActivity {

    private RecyclerView rvMarket;
    private ImageView btnBack;
    private MarketAdapter adapter;
    private List<MarketPrice> marketPrices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);

        rvMarket = findViewById(R.id.rvMarket);
        btnBack = findViewById(R.id.btnBackMarket);

        marketPrices = new ArrayList<>();
        adapter = new MarketAdapter(marketPrices);
        rvMarket.setLayoutManager(new LinearLayoutManager(this));
        rvMarket.setAdapter(adapter);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        fetchMarketPrices();
    }

    private void fetchMarketPrices() {
        ApiService apiService = RetrofitClient.getApiService();
        Call<List<MarketPrice>> call = apiService.getMarketPrices();

        call.enqueue(new Callback<List<MarketPrice>>() {
            @Override
            public void onResponse(Call<List<MarketPrice>> call, Response<List<MarketPrice>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    marketPrices.clear();
                    marketPrices.addAll(response.body());
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(MarketActivity.this, "Failed to load prices", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<MarketPrice>> call, Throwable t) {
                Toast.makeText(MarketActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
