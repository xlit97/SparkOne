package com.maxwellscore.kotlinexample.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.maxwellscore.kotlinexample.databinding.KotlinexampleActivityAllBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: KotlinexampleActivityAllBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = KotlinexampleActivityAllBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // Сначала создаем подписку на изменение состояния экрана
        viewModel.liveData.observe(this) { state ->
            binding.allMaterialtoolbarTopbar.title = state.title
            binding.allTextviewTemperature.text = state.temperature
            binding.allTextviewWeather.setText(state.weatherTextId)
            binding.root.setBackgroundColor(state.backgroundColor)
        }
        binding.root.setOnClickListener {
            viewModel.onScreenClicked(applicationContext)
        }
        // Затем вызываем у viewModel колбэк onCreated оповещая viewModel что подготовительные работы завершены
        // Эта проверка на savedInstanceState здесь нужна чтобы onInitiallyCreated вызвался только при первом старте экрана
        if (savedInstanceState == null) {
            viewModel.onInitiallyCreated(applicationContext)
        }
    }
}