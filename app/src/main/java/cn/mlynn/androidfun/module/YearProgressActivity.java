package cn.mlynn.androidfun.module;

import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.io.IOException;
import java.util.Objects;

import cn.mlynn.androidfun.R;
import cn.mlynn.androidfun.base.ExBaseActivity;
import cn.mlynn.androidfun.databinding.ActivityYearProgressBinding;
import cn.mlynn.androidfun.databinding.DialogShareYearBinding;
import cn.mlynn.androidfun.utils.BitmapUtils;
import cn.mlynn.androidfun.utils.DateUtils;
import cn.mlynn.androidfun.utils.SharedUtils;

/**
 * Created by SeaLynn0 on 2018/10/13 15:57
 * <p>
 * Email：sealynndev@gmail.com
 */
public class YearProgressActivity extends ExBaseActivity<ActivityYearProgressBinding> {

    BottomSheetDialog dialog;

    ValueAnimator animator;

    Bitmap bm;

    @Override
    protected void init(Bundle savedInstanceState) {
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        getBinding().tvYear.setText(DateUtils.getYear());

        animator = ValueAnimator.ofInt(0, DateUtils.getIntOfTheYearPassed());
        animator.setDuration(1000);
        animator.addUpdateListener(animation -> {
            Integer value = (Integer) animation.getAnimatedValue();
//            if (getBinding().progressBar != null && getBinding().tvPercent != null) {
            getBinding().progressBar.setProgress(value);
            getBinding().tvPercent.setText(String.format(getResources().getString(R.string.percent), value));
//            }
        });
        animator.start();

        /*
         * 不在Java层写这一句 会有默认展开的bug
         */
        getBinding().sheet.setVisibility(View.INVISIBLE);
//        getBinding().btBack.setOnClickListener(v -> finish());
        dialog = new BottomSheetDialog(this);
        dialog.setContentView(DialogShareYearBinding.inflate(getLayoutInflater()).getRoot());
    }

    @Override
    protected ActivityYearProgressBinding initBinding() {
        return ActivityYearProgressBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (animator != null) {
            animator.cancel();
            animator = null;
        }
        if (dialog != null) {
            dialog.cancel();
            dialog = null;
        }
    }

    public void showPopup(View view) {
        getBinding().fab.setExpanded(true);
    }

    public void shareText(View view) {
        String text = getBinding().tvYear.getText() + " is "
                + getBinding().tvPercent.getText()
                + " completed! - Shared Via WanAndroid - Year Progress";
        SharedUtils.shareText(YearProgressActivity.this, text);
        getBinding().fab.setExpanded(false);
    }

    public void copyText(View view) {
        String text = getBinding().tvYear.getText() + " is "
                + getBinding().tvPercent.getText()
                + " completed! - Shared Via WanAndroid - Year Progress";
        SharedUtils.copyText(YearProgressActivity.this, text);
        getBinding().fab.setExpanded(false);
    }

    public void shareAsImage(View view) {
        bm = BitmapUtils.convertViewToBitmap(findViewById(R.id.card));
        Glide.with(this).load(bm).into((AppCompatImageView) Objects.requireNonNull(dialog.findViewById(R.id.preview)));
        dialog.show();
        getBinding().fab.setExpanded(false);
    }

    @Override
    public void onBackPressed() {
        if (getBinding().fab.isExpanded()) {
            getBinding().fab.setExpanded(false);
        } else {
            super.onBackPressed();
        }
    }

    public void onScrimClick(View view) {
        getBinding().fab.setExpanded(false);
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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}