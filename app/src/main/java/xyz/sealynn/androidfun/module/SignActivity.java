package xyz.sealynn.androidfun.module;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import xyz.sealynn.androidfun.databinding.ActivitySignBinding;

public class SignActivity extends AppCompatActivity {

    ActivitySignBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}
