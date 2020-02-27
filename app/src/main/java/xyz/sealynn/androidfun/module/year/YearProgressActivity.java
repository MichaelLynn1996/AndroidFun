package xyz.sealynn.androidfun.module.year;

import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.circularreveal.cardview.CircularRevealCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.Objects;

import butterknife.BindView;
import xyz.sealynn.androidfun.R;
import xyz.sealynn.androidfun.base.BaseActivity;
import xyz.sealynn.androidfun.utils.BitmapUtils;
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
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.scrim)
    View scrim;
    @BindView(R.id.sheet)
    CircularRevealCardView sheet;

    BottomSheetDialog dialog;

    ValueAnimator animator;

    Bitmap bm;

    @Override
    protected YearProgressContract.Presenter createPresenter() {
        return new YearProgressPresenter(this);
    }

    @Override
    protected int bindView() {
        return R.layout.activity_year_progress;
    }

    @Override
    protected void prepareData() {

    }

    @Override
    protected void initView() {
        year.setText(DateUtils.getYear());

        animator = ValueAnimator.ofInt(0, DateUtils.getIntOfTheYearPassed());
        animator.setDuration(1000);
        animator.addUpdateListener(animation -> {
            Integer value = (Integer) animation.getAnimatedValue();
            if (progressBar != null && percent != null) {
                progressBar.setProgress(value);
                percent.setText(String.format(getResources().getString(R.string.percent), value));
            }
        });
        animator.start();

        /*
        * 不在Java层写这一句 会有默认展开的bug
        */
        sheet.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initEvent() {
        back.setOnClickListener(v -> finish());
        dialog = new BottomSheetDialog(this);
        dialog.setContentView(R.layout.dialog_share_year);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (animator != null) {
            animator.cancel();
            animator = null;
        }
    }

    public void showPopup(View view) {
        fab.setExpanded(true);
    }

    public void shareText(View view) {
        String text = year.getText() + " is "
                + percent.getText()
                + " completed! - Shared Via WanAndroid - Year Progress";
        SharedUtils.shareText(YearProgressActivity.this, text);
        fab.setExpanded(false);
    }

    public void copyText(View view) {
        String text = year.getText() + " is "
                + percent.getText()
                + " completed! - Shared Via WanAndroid - Year Progress";
        SharedUtils.copyText(YearProgressActivity.this, text);
        fab.setExpanded(false);
    }

    public void shareAsImage(View view) {
        bm = BitmapUtils.convertViewToBitmap(findViewById(R.id.card));
        Glide.with(this).load(bm).into((AppCompatImageView) Objects.requireNonNull(dialog.findViewById(R.id.preview)));
        dialog.show();
        fab.setExpanded(false);
    }

    @Override
    public void onBackPressed() {
        if (fab.isExpanded()) {
            fab.setExpanded(false);
        } else {
            super.onBackPressed();
        }
    }

    public void onScrimClick(View view) {
        fab.setExpanded(false);
    }

    public void shareImage(View view) {
        SharedUtils.shareImage(this, bm);
        try {
            SharedUtils.saveBitmap(this, bm);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void dismiss(View view) {
        dialog.dismiss();
    }
}