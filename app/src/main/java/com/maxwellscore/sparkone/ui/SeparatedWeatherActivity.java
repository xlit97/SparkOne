package com.maxwellscore.sparkone.ui;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.appbar.MaterialToolbar;
import com.maxwellscore.sparkone.R;

public class SeparatedWeatherActivity extends AppCompatActivity {

    private ViewGroup root;
    private MaterialToolbar toolbar;
    private TextView temperatureTextView;
    private TextView weatherTextView;

    WeatherViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(WeatherViewModel.class);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_activity_all);
        root = findViewById(R.id.all_constraintlayout_root);
        toolbar = findViewById(R.id.all_materialtoolbar_topbar);
        temperatureTextView = findViewById(R.id.all_textview_temperature);
        weatherTextView = findViewById(R.id.all_textview_weather);

        // Сначала создаем подписку на изменение состояния экрана
        viewModel.getWeatherLiveData().observe(this, (state) -> {
            toolbar.setTitle(state.getTitle());
            temperatureTextView.setText(state.getTemperature());
            weatherTextView.setText(state.getWeatherTextResId());
            root.setBackgroundColor(state.getBackgroundColor());
        });

        // Затем вызываем у viewModel колбэк onCreated оповещая viewModel что подготовительные работы завершены
        viewModel.onCreate(getApplicationContext());
    }
}
