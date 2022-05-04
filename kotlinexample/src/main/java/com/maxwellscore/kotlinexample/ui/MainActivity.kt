package com.maxwellscore.kotlinexample.ui

import android.Manifest
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.maxwellscore.kotlinexample.AppComponentProvider
import com.maxwellscore.kotlinexample.databinding.KotlinexampleActivityAllBinding
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: KotlinexampleActivityAllBinding
    @Inject
    lateinit var viewModelFactory: MainViewModel.Factory
    private val viewModel: MainViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = KotlinexampleActivityAllBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val component = (applicationContext as AppComponentProvider).component
        component.inject(this)
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
            viewModel.onInitiallyCreated(component, applicationContext)
        }
        binding.allButtonService.setOnClickListener {
            viewModel.onServiceButtonClicked(applicationContext)
        }
    }

    override fun onStart() {
        super.onStart()
        // TODO добавить проверку на наличие пермишена до запроса диалога.
        val isPermissionGranted = false
        if (!isPermissionGranted) {
            val locationPermissionRequest = registerForActivityResult(
                ActivityResultContracts.RequestMultiplePermissions()
            ) { permissions ->
                when {
                    permissions[Manifest.permission.ACCESS_FINE_LOCATION] ?: false -> {
                        Toast.makeText(this, "Спасибо за разрешение!", Toast.LENGTH_SHORT).show()
                    }
                    permissions[Manifest.permission.ACCESS_COARSE_LOCATION] ?: false -> {
                        Toast.makeText(this, "Примерно - это неплохо, но хотелось бы точнее", Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        Toast.makeText(this, "Нам очень нужен это разрешение", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            locationPermissionRequest.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
        } else {
            // TODO продолжаем работу приложения
        }
    }
}