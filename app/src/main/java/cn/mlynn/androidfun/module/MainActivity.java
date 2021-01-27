package cn.mlynn.androidfun.module;

import android.os.Bundle;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import cn.mlynn.androidfun.R;
import cn.mlynn.androidfun.base.ExBaseActivity;
import cn.mlynn.androidfun.databinding.ActivityMainBinding;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends ExBaseActivity<ActivityMainBinding> {
    NavController navController;

    @Override
    protected void init(Bundle savedInstanceState) {
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(getBinding().bottomNavigationView, navController);
    }

    @Override
    protected ActivityMainBinding initBinding() {
        return ActivityMainBinding.inflate(getLayoutInflater());
    }
}


