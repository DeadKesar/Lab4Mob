package com.example.lab4mob

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.lab4mob.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // Получение с помощью делегата viewModels
    private val viewModel by viewModels<MainViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.actionButton.setOnClickListener {
            viewModel.onButtonClicked()
        }
        // запускаем еще корутину для ЖЦ MainActivity
        lifecycleScope.launch {
            // в рамках ЖЦ: Start - Pause будем подписываться на состояние
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                // подписываемся на состояние
                viewModel.state.collect {
                    data ->
                    // обновляем view
                    binding.result.text = data
                }
            }
        }


    }
}
