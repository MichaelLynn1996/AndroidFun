package xyz.sealynn.androidfun.module.year;

import android.animation.ValueAnimator;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatTextView;
import android.widget.ProgressBar;

import butterknife.BindString;
import butterknife.BindView;
import xyz.sealynn.androidfun.NotificationClickReceiver;
import xyz.sealynn.androidfun.R;
import xyz.sealynn.androidfun.base.BaseActivity;
import xyz.sealynn.androidfun.utils.DateUtils;
import xyz.sealynn.androidfun.utils.NotificationUtils;
import xyz.sealynn.androidfun.utils.ShareUtils;

/**
 * Created by SeaLynn0 on 2018/10/13 15:57
 * <p>
 * Email：sealynndev@gmail.com
 */
public class YearProgressActivity extends BaseActivity<YearProgressContract.Presenter>
        implements YearProgressContract.View {

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.tv_percent)
    AppCompatTextView percent;
    @BindView(R.id.tv_year)
    AppCompatTextView year;
    @BindView(R.id.bt_back)
    AppCompatImageButton back;
    @BindView(R.id.bt_copy)
    AppCompatImageButton copy;
    @BindView(R.id.bt_share)
    AppCompatImageButton share;

    @BindString(R.string.progress_chanel)
    String name;

    @Override
    protected YearProgressContract.Presenter createPresenter() {
        return new YearProgressPresenter(this);
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_year_progress;
    }

    @Override
    protected void prepareData() {

    }

    @Override
    protected void initView() {
        year.setText(DateUtils.getYear());

        final ValueAnimator animator = ValueAnimator.ofInt(0, DateUtils.getIntOfTheYearPassed());
        animator.setDuration(2000);
        animator.addUpdateListener(animation -> {
            Integer value = (Integer) animation.getAnimatedValue();
            progressBar.setProgress(value);
            percent.setText(String.valueOf(value + "%"));
        });
        animator.start();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initEvent() {
        back.setOnClickListener(v -> finish());
        copy.setOnClickListener(v -> {
            String text = year.getText() + " is "
                    + percent.getText()
                    + " complete! - Shared Via WanAndroid - Year Progress";
            ShareUtils.copyText(YearProgressActivity.this, text);
        });
        share.setOnClickListener(v -> {
            String text = year.getText() + " is "
                    + percent.getText()
                    + " complete! - Shared Via WanAndroid - Year Progress";
            ShareUtils.shareText(YearProgressActivity.this, text);
        });

        Intent intent = new Intent(YearProgressActivity.this, NotificationClickReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(YearProgressActivity.this, 0, intent, 0);
        NotificationUtils notificationUtils = new NotificationUtils(this, "progress_channel", name);
        notificationUtils.sendNotificationWithIntent("YearProgress", DateUtils.getYear() + " is "
                + DateUtils.getPercentsofTheYearPassed()
                + " complete!", pendingIntent);
    }
}
