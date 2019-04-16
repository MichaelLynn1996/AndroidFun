package xyz.sealynn.androidfun.module.login;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import androidx.annotation.Nullable;
import butterknife.BindView;
import xyz.sealynn.androidfun.R;
import xyz.sealynn.androidfun.base.BaseActivity;
import xyz.sealynn.androidfun.module.register.RegisterActivity;

public class LoginActivity extends BaseActivity<LoginContract.Presenter> implements LoginContract.View {

    @BindView(R.id.et_username)
    TextInputEditText etUsername;
    @BindView(R.id.et_password)
    TextInputEditText etPassword;
    @BindView(R.id.bt_go)
    MaterialButton btGo;
    @BindView(R.id.fab)
    MaterialButton fab;
    @BindView(R.id.til_username)
    TextInputLayout TILUsername;
    @BindView(R.id.til_password)
    TextInputLayout TILPassword;

    @BindView(R.id.root)
    View content;//根布局对象（用来控制整个布局）
    @BindView(R.id.view_puppet)
    View mPuppet0;//揭露层对象
    private int centerX;
    private int centerY;

    public static final int REQUEST_REGISTER = 1;

    @Override
    protected LoginContract.Presenter createPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_login;
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
    }


    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initEvent() {
        btGo.setOnClickListener(view -> {
            if (etUsername.getText() != null && etPassword.getText() != null) {
                String u = etUsername.getText().toString().trim();
                String p = etPassword.getText().toString().trim();
                if (u.isEmpty()) {
                    TILUsername.setErrorEnabled(true);
                    TILUsername.setError(getText(R.string.USERNAME_CANT_BE_NUL));
                }
                if (p.isEmpty()) {
                    TILPassword.setErrorEnabled(true);
                    TILPassword.setError(getText(R.string.PASSWORD_CANT_BE_NUL));
                }
                if (!u.isEmpty() && !p.isEmpty()) {
                    getPresenter().login(u, p);
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
        fab.setOnClickListener(view -> {
            int[] vLocation = new int[2];
            fab.getLocationInWindow(vLocation);
            centerX = vLocation[0] + fab.getMeasuredWidth() / 2;
            centerY = vLocation[1] + fab.getMeasuredHeight() / 2;
            Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
            intent.putExtra("cx",centerX);
            intent.putExtra("cy",centerY);
            startActivityForResult(intent,REQUEST_REGISTER);
//            ActivityUtils.startActivity(this, RegisterActivity.class);
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void freeze() {
        etUsername.setEnabled(false);
        etPassword.setEnabled(false);
        btGo.setEnabled(false);
        fab.setEnabled(false);
    }

    @Override
    public void unFreeze() {
        etUsername.setEnabled(true);
        etPassword.setEnabled(true);
        btGo.setEnabled(true);
        fab.setEnabled(true);
    }

    private Animator createRevealAnimator(int x, int y) {
        int startRadius = (int) Math.hypot(content.getHeight(), content.getWidth());
        int endRadius = fab.getMeasuredWidth() / 2 ;

        //注意揭露动画开启时是用根布局作为操作对象，关闭时用揭露层作为操作对象
        Animator animator = ViewAnimationUtils.createCircularReveal(
                mPuppet0, x, y,
                startRadius,
                endRadius);
        animator.setDuration(500);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());//设置插值器
        animator.addListener(animatorListener0);
        return animator;
    }

    //定义动画状态监听器_按下返回键版
    private Animator.AnimatorListener animatorListener0 = new Animator.AnimatorListener() {
        @Override
        public void onAnimationStart(Animator animation) {
            mPuppet0.setVisibility(View.VISIBLE);//按下返回键时，动画开启，揭露层设置为可见
            fab.setVisibility(View.INVISIBLE);
        }
        @Override
        public void onAnimationEnd(Animator animation) {
            mPuppet0.setVisibility(View.INVISIBLE);
            fab.setVisibility(View.VISIBLE);
        }
        @Override
        public void onAnimationCancel(Animator animation) {
        }
        @Override
        public void onAnimationRepeat(Animator animation) {
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case REQUEST_REGISTER:
                //动画需要依赖于某个视图才可启动，
                // 这里依赖于根布局对象，并且开辟一个子线程，充分利用资源
                content.post(() -> {
                    int[] vLocation = new int[2];
                    fab.getLocationInWindow(vLocation);
                    centerX = vLocation[0] + fab.getMeasuredWidth() / 2;
                    centerY = vLocation[1] + fab.getMeasuredHeight() / 2;
                    Animator animator = createRevealAnimator(centerX, centerY);
                    animator.start();

                    animator.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            if (resultCode == RESULT_OK){
                                LoginActivity.this.setResult(RESULT_OK, null);
                                finish();
                            }
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    });
                });
                break;
        }
    }
}
