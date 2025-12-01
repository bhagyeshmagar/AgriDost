package com.example.agridost;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.agridost.models.WeatherResponse;
import com.example.agridost.network.WeatherApiClient;
import com.example.agridost.network.WeatherApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherActivity extends AppCompatActivity {

    private ImageView btnBack;
    private TextView tvCurrentTemp, tvCondition, tvHumidity, tvWind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        btnBack = findViewById(R.id.btnBackWeather);
        tvCurrentTemp = findViewById(R.id.tvCurrentTemp);
        tvCondition = findViewById(R.id.tvCondition);
        tvHumidity = findViewById(R.id.tvHumidity);
        tvWind = findViewById(R.id.tvWind);

        btnBack.setOnClickListener(v -> finish());

        fetchWeather();
    }

    private void fetchWeather() {
        WeatherApiService apiService = WeatherApiClient.getApiService();
        // TODO: Replace with user's API Key
        String apiKey = "381d5722cf6da273368dcf512df6ea87";
        String city = "Pune,IN";

        Call<WeatherResponse> call = apiService.getWeather(city, apiKey);

        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    WeatherResponse weather = response.body();
                    tvCurrentTemp.setText(weather.getTemp() + "°C");
                    tvCondition.setText(weather.getCondition());
                    tvHumidity.setText(weather.getHumidity() + "%");
                    tvWind.setText(weather.getWindSpeed() + " km/h");
                } else {
                    Toast.makeText(WeatherActivity.this, "Failed to load weather: " + response.message(),
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                Toast.makeText(WeatherActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
