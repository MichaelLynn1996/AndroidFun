package cn.mlynn.androidfun.module;

import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import cn.mlynn.androidfun.base.ExBaseActivity;
import cn.mlynn.androidfun.databinding.ActivitySplashBinding;
import cn.mlynn.androidfun.utils.ActivityUtils;

/**
 * Created by SeaLynn0 on 2018/10/13 1:36
 * <p>
 * Email：sealynndev@gmail.com
 */
public class SplashActivity extends ExBaseActivity<ActivitySplashBinding> {

    private AlphaAnimation alphaAnimation;

    @Override
    protected void init(Bundle savedInstanceState) {
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
        getBinding().layoutSplash.startAnimation(alphaAnimation);
    }

    @Override
    protected ActivitySplashBinding initBinding() {
        return ActivitySplashBinding.inflate(getLayoutInflater());
    }

    private void jumpToMain() {
        ActivityUtils.startActivity(this, MainActivity.class);
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
