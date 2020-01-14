package xyz.sealynn.androidfun.module.year;

import android.animation.ValueAnimator;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatTextView;

import android.widget.ProgressBar;

import butterknife.BindString;
import butterknife.BindView;
import xyz.sealynn.androidfun.R;
import xyz.sealynn.androidfun.base.BaseActivity;
import xyz.sealynn.androidfun.utils.DateUtils;
import xyz.sealynn.androidfun.utils.SharedUtils;

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

    ValueAnimator animator;

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

        animator = ValueAnimator.ofInt(0, (int) DateUtils.getIntOfTheYearPassed());
        animator.setDuration(1000);
        animator.addUpdateListener(animation -> {
            Integer value = (Integer) animation.getAnimatedValue();
            if (progressBar != null && percent != null) {
                progressBar.setProgress(value);
                percent.setText(DateUtils.getPercentsOfTheYearPassed());
            }
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
            SharedUtils.copyText(YearProgressActivity.this, text);
        });
        share.setOnClickListener(v -> {
            String text = year.getText() + " is "
                    + percent.getText()
                    + " complete! - Shared Via WanAndroid - Year Progress";
            SharedUtils.shareText(YearProgressActivity.this, text);
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (animator != null) {
            animator.cancel();
            animator = null;
        }
    }
}
