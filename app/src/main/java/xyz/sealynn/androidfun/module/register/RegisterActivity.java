package xyz.sealynn.androidfun.module.register;

import android.animation.Animator;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import butterknife.BindView;
import xyz.sealynn.androidfun.R;
import xyz.sealynn.androidfun.base.BaseActivity;

public class RegisterActivity extends BaseActivity<RegisterContract.Presenter> implements RegisterContract.View {

    @BindView(R.id.bt_go)
    MaterialButton btGo;
    @BindView(R.id.et_username)
    TextInputEditText etUsername;
    @BindView(R.id.et_password)
    TextInputEditText etPassword;
    @BindView(R.id.et_repeat_password)
    TextInputEditText etRePassword;
    @BindView(R.id.til_username)
    TextInputLayout TILUsername;
    @BindView(R.id.til_password)
    TextInputLayout TILPassword;
    @BindView(R.id.til_repeat_password)
    TextInputLayout TILRePassword;

//    @BindView(R.id.root)
//    View content;//根布局对象（用来控制整个布局）
//    @BindView(R.id.view_puppet)
//    View mPuppet;//揭露层对象
//    private int mX;
//    private int mY;

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
        //动画需要依赖于某个视图才可启动，
        // 这里依赖于根布局对象，并且开辟一个子线程，充分利用资源
//        content.post(() -> {
//            mX = getIntent().getIntExtra("cx", 0);
//            mY = getIntent().getIntExtra("cy", 0);
//            Animator animator = createRevealAnimator(mX, mY);
//            animator.start();
//        });
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);//左侧添加一个默认的返回图标
            getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        }
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initEvent() {
        btGo.setOnClickListener(view -> {
            if (etUsername.getText() != null
                    && etPassword.getText() != null
                    && etRePassword.getText() != null) {
                String u = etUsername.getText().toString().trim();
                String p = etPassword.getText().toString().trim();
                String rp = etRePassword.getText().toString().trim();
                if (u.isEmpty()) {
                    TILUsername.setErrorEnabled(true);
                    TILUsername.setError(getText(R.string.USERNAME_CANT_BE_NUL));
                }
                if (p.isEmpty()) {
                    TILPassword.setErrorEnabled(true);
                    TILPassword.setError(getText(R.string.PASSWORD_CANT_BE_NUL));
                }
                if (rp.isEmpty()) {
                    TILRePassword.setErrorEnabled(true);
                    TILRePassword.setError(getText(R.string.PASSWORD_CANT_BE_NUL));
                } else if (!p.equals(rp)) {
                    TILRePassword.setErrorEnabled(true);
                    TILRePassword.setError(getText(R.string.PASS_AND_REPASS_IS_NOT_EQUAL));
                } else if (!u.isEmpty() && !p.isEmpty() && !rp.isEmpty()) {
                    getPresenter().register(u, p, rp);
                    freeze();
                }
            }
        });
        etUsername.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus && TILUsername.isErrorEnabled())
                TILUsername.setErrorEnabled(false);
        });
        etPassword.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus && TILPassword.isErrorEnabled())
                TILPassword.setErrorEnabled(false);
        });
        etRePassword.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus && TILRePassword.isErrorEnabled())
                TILRePassword.setErrorEnabled(false);
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

//    private Animator createRevealAnimator(int x, int y) {
//        int startRadius = 0;
//        int endRadius = (int) Math.hypot(content.getHeight(), content.getWidth());
//
//        Animator animator = ViewAnimationUtils.createCircularReveal(
//                content, x, y,
//                startRadius,
//                endRadius);
//        animator.setDuration(660);
//        animator.setInterpolator(new AccelerateDecelerateInterpolator());
//        //判断标志位reversed，true则为添加返回键版动画监听器，false则为跳转动画开启版
//        // if (!reversed)
//        animator.addListener(animatorListener1);
//        return animator;
//    }

//    //定义动画状态监听器_跳转动画开启版
//    private Animator.AnimatorListener animatorListener1 = new Animator.AnimatorListener() {
//        @Override
//        public void onAnimationStart(Animator animation) {
////            content.setVisibility(View.VISIBLE);//跳转进来时，（因为finish之前会将之设置为不可见，）
//            // 根布局要设置为可见，与finish部分的不可见相对应
////            mPuppet.setAlpha(1);
////            mPuppet.setVisibility(View.VISIBLE);
//        }
//
//        @Override
//        public void onAnimationEnd(Animator animation) {
//            mPuppet.startAnimation(createAlphaAnimation());
////            mPuppet.setVisibility(View.INVISIBLE);//动画结束时，揭露动画设置为不可见
//        }
//
//        @Override
//        public void onAnimationCancel(Animator animation) {
//        }
//
//        @Override
//        public void onAnimationRepeat(Animator animation) {
//        }
//    };
//
//    private AlphaAnimation createAlphaAnimation() {
//        AlphaAnimation aa = new AlphaAnimation(1, 0);
//        aa.setDuration(400);
//        aa.setInterpolator(new AccelerateDecelerateInterpolator());//设置插值器
//        return aa;
//    }

    @Override
    public void freeze() {
        etUsername.setEnabled(false);
        etPassword.setEnabled(false);
        btGo.setEnabled(false);
        etRePassword.setEnabled(false);
    }

    @Override
    public void unFreeze() {
        etUsername.setEnabled(true);
        etPassword.setEnabled(true);
        btGo.setEnabled(true);
        etRePassword.setEnabled(true);
    }
}
