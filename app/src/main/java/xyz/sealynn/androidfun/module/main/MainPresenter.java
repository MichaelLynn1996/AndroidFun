package xyz.sealynn.androidfun.module.main;

import android.app.PendingIntent;
import android.content.Intent;

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
public class MainPresenter extends BasePresenterImpl implements MainContract.Presenter {

    private final MainContract.View mView;

    MainPresenter(MainContract.View view) {
        mView = view;
        this.mView.setPresenter(this);
    }

    @Override
    public void checkYearProgress() {
        //  通知开启还是关闭
        if ((boolean) SharedPreferencesUtils.getDefaultConfig(mView.getContext()
                , Constants.SHARED_PREFERENCE_KEY.YEAR_PROGRESS_MODE, true)) {
            //  现在的半分比和存储中的是否相等
            if (!SharedPreferencesUtils.getDefaultConfig(mView.getContext()
                    , Constants.SHARED_PREFERENCE_KEY.YEAR_PROGRESS_PERCENT, "0%")
                    .equals(DateUtils.getPercentsOfTheYearPassed())) {
                //  不相等就通知
                Intent intent = new Intent(mView.getContext(), NotificationClickReceiver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(mView.getContext(), 0, intent, 0);
                NotificationUtils notificationUtils = new NotificationUtils(mView.getContext(), "progress_channel"
                        , mView.getContext().getString(R.string.progress_chanel));
                notificationUtils.sendNotificationWithIntent("YearProgress", DateUtils.getYear() + " is "
                        + DateUtils.getPercentsOfTheYearPassed()
                        + " complete!", pendingIntent);
                //  保存新的半分比
                SharedPreferencesUtils.editDefaultConfig(mView.getContext()
                        , Constants.SHARED_PREFERENCE_KEY.YEAR_PROGRESS_PERCENT
                        , DateUtils.getPercentsOfTheYearPassed());
                //  保存通知模式
                SharedPreferencesUtils.editDefaultConfig(mView.getContext()
                        , Constants.SHARED_PREFERENCE_KEY.YEAR_PROGRESS_MODE
                        , true);
            }
        }
    }
}
