package xyz.sealynn.androidfun.module.login;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import androidx.appcompat.widget.AppCompatButton;
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
    AppCompatButton btGo;
    @BindView(R.id.fab)
    FloatingActionButton fab;

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
//            Explode explode = new Explode();
//            explode.setDuration(500);
//
//            getWindow().setExitTransition(explode);
//            getWindow().setEnterTransition(explode);
//            ActivityOptions oc2 = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this);
//            Intent i2 = new Intent(LoginActivity.this,AActivityThree.class);
//            startActivity(i2, oc2.toBundle());
            finish();
        });
        fab.setOnClickListener(view -> {
            getWindow().setExitTransition(null);
            getWindow().setEnterTransition(null);
            ActivityOptions oc2 = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this);
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class), oc2.toBundle());
        });
    }

    @SuppressLint("RestrictedApi")
    @Override
    protected void onRestart() {
        super.onRestart();
        fab.setVisibility(View.GONE);
    }

    @SuppressLint("RestrictedApi")
    @Override
    protected void onResume() {
        super.onResume();
        fab.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
