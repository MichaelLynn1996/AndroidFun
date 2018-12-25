package xyz.sealynn.androidfun.module.register;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.cardview.widget.CardView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import butterknife.BindView;
import xyz.sealynn.androidfun.R;
import xyz.sealynn.androidfun.base.BaseActivity;

public class RegisterActivity extends BaseActivity<RegisterContract.Presenter> implements RegisterContract.View {

    //    @BindView(R.id.fab)
//    FloatingActionButton fab;
//    @BindView(R.id.cv_add)
//    CardView cvAdd;
    @BindView(R.id.root)
    CoordinatorLayout root;

    @Override
    protected RegisterContract.Presenter createPresenter() {
        return new RegisterPresenter(this);
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void prepareData() {

    }

    @Override
    protected void initView() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);//左侧添加一个默认的返回图标
            getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        }
        ShowEnterAnimation();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initEvent() {
//        fab.setOnClickListener(v -> animateRevealClose());
    }

    private void ShowEnterAnimation() {
        Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.fabtransition);
        getWindow().setSharedElementEnterTransition(transition);

        transition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
                root.setVisibility(View.GONE);
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                transition.removeListener(this);
                animateRevealShow();
            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }


        });
    }

    public void animateRevealShow() {
        Animator mAnimator = ViewAnimationUtils.createCircularReveal(root, root.getWidth() - 44, 44, 28, root.getWidth());
        mAnimator.setDuration(500);
        mAnimator.setInterpolator(new AccelerateInterpolator());
        mAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }

            @Override
            public void onAnimationStart(Animator animation) {
                root.setVisibility(View.VISIBLE);
                super.onAnimationStart(animation);
            }
        });
        mAnimator.start();
    }

    public void animateRevealClose() {
        Animator mAnimator = ViewAnimationUtils.createCircularReveal(root, root.getWidth(), root.getHeight(), root.getWidth(), 28);
        mAnimator.setDuration(500);
        mAnimator.setInterpolator(new AccelerateInterpolator());
        mAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                root.setVisibility(View.INVISIBLE);
                super.onAnimationEnd(animation);
//                fab.setImageResource(R.drawable.ic_action_add_person);
                RegisterActivity.super.onBackPressed();
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }
        });
        mAnimator.start();
    }

    @Override
    public void onBackPressed() {
        animateRevealClose();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                animateRevealClose();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
