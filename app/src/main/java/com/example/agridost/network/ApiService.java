package com.example.agridost.network;

import com.example.agridost.models.DiseaseResponse;
import com.example.agridost.models.LoginRequest;
import com.example.agridost.models.LoginResponse;
import com.example.agridost.models.MarketPrice;
import com.example.agridost.models.SignupRequest;
import com.example.agridost.models.SignupResponse;
import com.example.agridost.models.WeatherResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @POST("/api/auth/login")
    Call<LoginResponse> login(@Body LoginRequest request);

    @POST("/api/auth/signup")
    Call<SignupResponse> signup(@Body SignupRequest request);

    @GET("/api/weather")
    Call<WeatherResponse> getWeather();

    @GET("/api/market-prices")
    Call<List<MarketPrice>> getMarketPrices();

    @POST("/api/predict-disease")
    Call<DiseaseResponse> predictDisease();
}
