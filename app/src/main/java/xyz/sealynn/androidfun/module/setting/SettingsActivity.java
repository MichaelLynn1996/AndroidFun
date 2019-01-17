package xyz.sealynn.androidfun.module.setting;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.orhanobut.logger.Logger;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatTextView;
import butterknife.BindView;
import xyz.sealynn.androidfun.R;
import xyz.sealynn.androidfun.base.BaseActivity;
import xyz.sealynn.androidfun.base.Constants;
import xyz.sealynn.androidfun.module.about.AboutActivity;
import xyz.sealynn.androidfun.module.about.OpenSourceLicenceActivity;
import xyz.sealynn.androidfun.utils.ActivityUtils;
import xyz.sealynn.androidfun.utils.SharedPreferencesUtils;
import xyz.sealynn.androidfun.view.NightModeBottomPopup;

public class SettingsActivity extends BaseActivity<SettingsContract.Presenter> implements SettingsContract.View {

    @BindView(R.id.item_night_mode)
    LinearLayout nightMode;
    @BindView(R.id.night_mode)
    AppCompatTextView TVNightMode;
    @BindView(R.id.about)
    AppCompatTextView about;
    @BindView(R.id.licence)
    AppCompatTextView licence;
    @BindView(R.id.switch1)
    Switch aSwitch;
    @BindView(R.id.item_year_progress)
    LinearLayout yearProgress;


    @Override
    protected SettingsContract.Presenter createPresenter() {
        return new SettingsPresenter(this);
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_settings;
    }

    @Override
    protected void prepareData() {

    }

    @Override
    protected void initView() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);//左侧添加一个默认的返回图标
            getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        }

        //  夜间模式选项
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            TVNightMode.setText(R.string.ON);
        } else if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO) {
            TVNightMode.setText(R.string.OFF);
        } else if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_AUTO) {
            TVNightMode.setText(R.string.ON_BY_SUNSET);
        }
        //  设置推送开关的状况
        aSwitch.setChecked((boolean) SharedPreferencesUtils.getDefaultConfig(this
                , Constants.SHARED_PREFERENCE_KEY.YEAR_PROGRESS_MODE, false));
    }


    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initEvent() {
        nightMode.setOnClickListener(v -> new NightModeBottomPopup(SettingsActivity.this).showPopupWindow());
        about.setOnClickListener(v -> ActivityUtils.startActivity(SettingsActivity.this, AboutActivity.class));
        licence.setOnClickListener(v -> ActivityUtils.startActivity(SettingsActivity.this, OpenSourceLicenceActivity.class));
        yearProgress.setOnClickListener(v -> aSwitch.setChecked(!aSwitch.isChecked()));
        aSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> mPresenter.saveYearProgressStatus(isChecked));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
