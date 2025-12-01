package com.example.agridost.models;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class WeatherResponse {
    @SerializedName("main")
    public Main main;
    @SerializedName("weather")
    public List<Weather> weather;
    @SerializedName("wind")
    public Wind wind;

    public class Main {
        public float temp;
        public int humidity;
    }

    public class Weather {
        public String main;
        public String description;
    }

    public class Wind {
        public float speed;
    }

    public String getCondition() {
        if (weather != null && !weather.isEmpty()) {
            return weather.get(0).main;
        }
        return "";
    }

    public int getTemp() {
        return (int) (main.temp - 273.15); // Convert Kelvin to Celsius
    }

    public int getHumidity() {
        return main.humidity;
    }

    public int getWindSpeed() {
        return (int) (wind.speed * 3.6); // Convert m/s to km/h
    }
}
