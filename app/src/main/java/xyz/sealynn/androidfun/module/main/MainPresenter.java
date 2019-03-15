package xyz.sealynn.androidfun.module.main;

import android.app.PendingIntent;
import android.content.Intent;
import android.preference.PreferenceManager;

import xyz.sealynn.androidfun.R;
import xyz.sealynn.androidfun.base.BasePresenterImpl;
import xyz.sealynn.androidfun.base.Constants;
import xyz.sealynn.androidfun.receiver.NotificationClickReceiver;
import xyz.sealynn.androidfun.utils.DateUtils;
import xyz.sealynn.androidfun.utils.NotificationUtils;
import xyz.sealynn.androidfun.utils.SharedPreferencesUtils;

/**
 * Created by SeaLynn0 on 2018/9/5 11:43
 * <p>
 * Email：sealynndev@gmail.com
 */
public class MainPresenter extends BasePresenterImpl<MainContract.View> implements MainContract.Presenter {

    MainPresenter(MainContract.View view) {
        super(view);
    }

    @Override
    public void checkYearProgress() {
//        Logger.d(PreferenceManager.getDefaultSharedPreferences(getView().getContext()).getBoolean("notifications_year_progress",true));
        //  通知开启还是关闭
        if (PreferenceManager.getDefaultSharedPreferences(getView().getContext())
                .getBoolean("notifications_year_progress",true)) {
            //  现在的半分比和存储中的是否相等
            if (!SharedPreferencesUtils.getDefaultConfig(getView().getContext()
                    , Constants.YEAR_PROGRESS_PERCENT, "0%")
                    .equals(DateUtils.getPercentsOfTheYearPassed())) {
                //  不相等就通知
                Intent intent = new Intent(getView().getContext(), NotificationClickReceiver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(getView().getContext(), 0, intent, 0);
                NotificationUtils notificationUtils = new NotificationUtils(getView().getContext(), "progress_channel"
                        , getView().getContext().getString(R.string.progress_chanel));
                notificationUtils.sendNotificationWithIntent("YearProgress", DateUtils.getYear() + " is "
                        + DateUtils.getPercentsOfTheYearPassed()
                        + " complete!", pendingIntent);
                //  保存新的半分比
                SharedPreferencesUtils.editDefaultConfig(getView().getContext()
                        , Constants.YEAR_PROGRESS_PERCENT
                        , DateUtils.getPercentsOfTheYearPassed());
                //  保存通知模式
                SharedPreferencesUtils.editDefaultConfig(getView().getContext()
                        , Constants.YEAR_PROGRESS_MODE
                        , true);
            }
        }
    }
}
