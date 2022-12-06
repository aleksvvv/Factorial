package com.example.factorial

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.factorial.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val viewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        observeViewModel()
        binding.button.setOnClickListener {
            viewModel.calculateFactorial(binding.inputET.text.toString())
        }
    }

    private fun observeViewModel() {

        viewModel.error.observe(this) {
            if (it) {
                Toast.makeText(this, "Input number", Toast.LENGTH_SHORT).show()
                binding.progressCircular.visibility = View.GONE
            }
        }
        viewModel.factorial.observe(this) {
            binding.tvResultFactorial.text = it.toString()
        }
        viewModel.progressBar.observe(this) {

            if (it) {
                binding.progressCircular.visibility = View.VISIBLE
                binding.button.isEnabled = false
            } else {
                binding.progressCircular.visibility = View.GONE
                binding.button.isEnabled = true
            }
        }

    }

}