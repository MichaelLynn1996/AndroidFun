package xyz.sealynn.androidfun.module.setting;

import android.content.Intent;
import android.widget.Switch;

import com.orhanobut.logger.Logger;

import xyz.sealynn.androidfun.base.BasePresenterImpl;
import xyz.sealynn.androidfun.base.Constants;
import xyz.sealynn.androidfun.utils.SharedPreferencesUtils;

/**
 * Created by SeaLynn0 on 2018/12/25 17:48
 * <p>
 * Email：sealynndev@gmail.com
 */
public class SettingsPresenter extends BasePresenterImpl implements SettingsContract.Presenter {

    private final SettingsContract.View mView;
    public SettingsPresenter(SettingsContract.View view) {
        mView = view;
        this.mView.setPresenter(this);
    }

    @Override
    public void saveYearProgressStatus(boolean isChecked) {
        SharedPreferencesUtils.editDefaultConfig(mView.getContext(),Constants.SHARED_PREFERENCE_KEY.YEAR_PROGRESS_MODE,isChecked);
        Logger.d(SharedPreferencesUtils.getDefaultConfig(mView.getContext()
                , Constants.SHARED_PREFERENCE_KEY.YEAR_PROGRESS_MODE, false));
    }
}
