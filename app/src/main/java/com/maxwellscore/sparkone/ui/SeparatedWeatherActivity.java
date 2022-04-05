package com.maxwellscore.sparkone.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.appbar.MaterialToolbar;
import com.maxwellscore.sparkone.R;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Пример кода когда вся логика распределена по слоям. В данном случае это UI слой, отвечающий только
 * за отображение каких-то данных на экране
 */
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
        if (savedInstanceState == null) {
            viewModel.onInitiallyCreated(getApplicationContext());
        }
    }
}
