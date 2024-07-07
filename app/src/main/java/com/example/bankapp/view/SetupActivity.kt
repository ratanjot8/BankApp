package com.example.bankapp.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.bankapp.R
import com.example.bankapp.databinding.ActivitySetupBinding
import com.example.bankapp.viewmodel.SetupViewModel
import com.example.bankapp.viewmodel.SetupViewState

class SetupActivity : AppCompatActivity() {
    lateinit var binding: ActivitySetupBinding
    lateinit var setupViewModel: SetupViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this,R.layout.activity_setup)
        setupViewModel = ViewModelProvider(this).get(SetupViewModel::class.java)
        binding.setupViewModel = setupViewModel
        binding.lifecycleOwner = this
        binding.nextButton.setOnClickListener {
            Toast.makeText(this, "Details submitted successfully", Toast.LENGTH_LONG).show()
            finish()
        }
        binding.noPanButton.setOnClickListener {
            finish()
        }
    }
}