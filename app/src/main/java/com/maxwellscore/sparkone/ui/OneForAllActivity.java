package com.maxwellscore.sparkone.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;
import com.maxwellscore.sparkone.R;
import com.maxwellscore.sparkone.data.DataConstants;
import com.maxwellscore.sparkone.data.network.WeatherApi;

import org.json.JSONException;
import org.json.JSONObject;

public class OneForAllActivity extends AppCompatActivity {

    private final static String CITY_MASK = "{city}";

    private ViewGroup root;
    private MaterialToolbar toolbar;
    private TextView temperatureTextView;
    private TextView weatherTextView;

//    private BadWeatherProvider weatherProvider = new BadWeatherProvider();
    private WeatherApi api = new WeatherApi();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_activity_all);
        root = findViewById(R.id.all_constraintlayout_root);
        toolbar = findViewById(R.id.all_materialtoolbar_topbar);
        temperatureTextView = findViewById(R.id.all_textview_temperature);
        weatherTextView = findViewById(R.id.all_textview_weather);

        // делаем типа запрос в сеть
        JSONObject response = api.getWeather();
        String cityName = null;
        String temperature = null;
        int weatherTextResId = 0;
        int backgroundColorResId = 0;
        try {
            // создаем заголовок
            cityName = response.getString(DataConstants.KEY_CITY_NAME);
            JSONObject dataTemperature = response.getJSONObject(DataConstants.KEY_TEMPERATURE_DATA);
            String temperatureValue = dataTemperature.getString(DataConstants.KEY_TEMPERATURE);
            String temperatureUnit = dataTemperature.getString(DataConstants.KEY_UNIT);
            temperature = String.format("%s°%s", temperatureValue, temperatureUnit);
            String weatherTypeData = response.getString(DataConstants.KEY_WEATHER_TYPE);
            switch (weatherTypeData) {
                case DataConstants.CLOUDY_TYPE:
                    weatherTextResId = R.string.cloudy_weather;
                    backgroundColorResId = R.color.light_blue;
                    break;
                case DataConstants.SUNNY_TYPE:
                    weatherTextResId = R.string.sunny_weather;
                    backgroundColorResId = R.color.orange;
                    break;
                case DataConstants.RAIN_TYPE:
                    weatherTextResId = R.string.rain;
                    backgroundColorResId = R.color.dark_light_blue;
                    break;
                default:
                    throw new IllegalArgumentException("такой погоды не существует");
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        String weatherIn = getString(R.string.weather_in_mask);
        String title = weatherIn.replace(CITY_MASK, cityName);
        toolbar.setTitle(title);

        // Устанавливаем температуру
        temperatureTextView.setText(temperature);

        // Устанавливаем погоду
        int bgColor = ContextCompat.getColor(this, backgroundColorResId);
        root.setBackgroundColor(bgColor);
        weatherTextView.setText(weatherTextResId);
    }
}