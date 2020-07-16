package cn.mlynn.androidfun.module.setting;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import cn.mlynn.androidfun.R;
import cn.mlynn.androidfun.base.ExBaseActivity;
import cn.mlynn.androidfun.databinding.ActivitySettingBinding;

/**
 * Created by MichaelLynn on 2019/12/27 11:38
 * <p>
 * Email: sealynndev@gamil.com
 */
public class SettingsActivity extends ExBaseActivity<ActivitySettingBinding> {

    NavController navController;

    @Override
    protected void init(Bundle savedInstanceState) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);//左侧添加一个默认的返回图标
            getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
            getSupportActionBar().setTitle(R.string.settings);
        }
        //

        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            if (getSupportActionBar() != null)
                getSupportActionBar().setTitle(destination.getLabel());
        });
    }

    @Override
    protected ActivitySettingBinding initBinding() {
        return ActivitySettingBinding.inflate(getLayoutInflater());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {    //返回键
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
