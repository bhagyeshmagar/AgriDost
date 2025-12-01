package com.example.agridost;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.agridost.models.WeatherResponse;
import com.example.agridost.network.ApiService;
import com.example.agridost.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;

public class HomeActivity extends AppCompatActivity {

    private TextView tvWeatherTemp, tvWeatherCondition;
    private CardView cardAssistant, cardDisease, cardMarket, cardWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tvWeatherTemp = findViewById(R.id.tvWeatherTemp);
        tvWeatherCondition = findViewById(R.id.tvWeatherCondition);
        cardAssistant = findViewById(R.id.cardAssistant);
        cardDisease = findViewById(R.id.cardDisease);
        cardMarket = findViewById(R.id.cardMarket);
        cardWeather = findViewById(R.id.cardWeather);

        findViewById(R.id.ivProfile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, ProfileActivity.class));
            }
        });

        fetchWeather();

        cardAssistant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to Assistant
                Toast.makeText(HomeActivity.this, "Opening Assistant...", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(HomeActivity.this, AssistantActivity.class));
            }
        });

        cardDisease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this, "Opening Crop Doctor...", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(HomeActivity.this, DiseaseActivity.class));
            }
        });

        cardMarket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to Market
                Toast.makeText(HomeActivity.this, "Opening Market Prices...", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(HomeActivity.this, MarketActivity.class));
            }
        });
        
        cardWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this, "Opening Weather Alerts...", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(HomeActivity.this, WeatherActivity.class));
            }
        });
    }

    private void fetchWeather() {
        ApiService apiService = RetrofitClient.getApiService();
        Call<WeatherResponse> call = apiService.getWeather();
        
        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    WeatherResponse weather = response.body();
                    tvWeatherTemp.setText(weather.getTemp() + "°C");
                    tvWeatherCondition.setText(weather.getCondition());
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                tvWeatherCondition.setText("Error");
            }
        });
    }
}
