package xyz.sealynn.androidfun.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import razerdp.basepopup.BasePopupWindow;
import xyz.sealynn.androidfun.R;
import xyz.sealynn.androidfun.base.Constants;
import xyz.sealynn.androidfun.module.setting.SettingsActivity;
import xyz.sealynn.androidfun.utils.NightModeUtils;

import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;

/**
 * Created by SeaLynn0 on 2019/1/14 2:59
 * <p>
 * Email：sealynndev@gmail.com
 */
public class NightModeBottomPopup extends BasePopupWindow implements View.OnClickListener {

    private AppCompatActivity activity;

    public NightModeBottomPopup(AppCompatActivity activity) {
        this((Context) activity);
        this.activity = activity;
    }

    public NightModeBottomPopup(Context context) {
        super(context);
        setPopupGravity(Gravity.BOTTOM);
        bindEvent();
    }

    private void bindEvent() {
        findViewById(R.id.bt_on).setOnClickListener(this);
        findViewById(R.id.bt_off).setOnClickListener(this);
        findViewById(R.id.bt_by_sunset).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(Constants.NIGHT_MODE_CHANGE_INTENT);
        switch (v.getId()) {
            case R.id.bt_on:
                NightModeUtils.setNightModeState(activity, AppCompatDelegate.MODE_NIGHT_YES);
                activity.sendBroadcast(intent);
                dismiss();
                break;
            case R.id.bt_off:
                NightModeUtils.setNightModeState(activity, AppCompatDelegate.MODE_NIGHT_NO);
                activity.sendBroadcast(intent);
                dismiss();
                break;
            case R.id.bt_by_sunset:
                NightModeUtils.setNightModeState(activity, AppCompatDelegate.MODE_NIGHT_AUTO);
                activity.sendBroadcast(intent);
                dismiss();
                break;
            default:
                break;
        }
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.popup_night_mode);
    }

    @Override
    protected Animation onCreateShowAnimation() {
        return getTranslateVerticalAnimation(1f, 0, 500);
    }

    @Override
    protected Animation onCreateDismissAnimation() {
        return getTranslateVerticalAnimation(0, 1f, 500);
    }
}
