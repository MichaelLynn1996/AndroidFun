package xyz.sealynn.androidfun.module;

import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import xyz.sealynn.androidfun.databinding.ActivitySplashBinding;
import xyz.sealynn.androidfun.module.main.MainActivity;
import xyz.sealynn.androidfun.utils.ActivityUtils;

/**
 * Created by SeaLynn0 on 2018/10/13 1:36
 * <p>
 * Email：sealynndev@gmail.com
 */
public class SplashActivity extends AppCompatActivity {

    private ActivitySplashBinding binding;

    private AlphaAnimation alphaAnimation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        alphaAnimation = new AlphaAnimation(0.3F, 1.0F);
        alphaAnimation.setDuration(600);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                jumpToMain();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        binding.layoutSplash.startAnimation(alphaAnimation);
    }

    private void jumpToMain() {
        ActivityUtils.startActivity(this, MainActivity.class);
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
