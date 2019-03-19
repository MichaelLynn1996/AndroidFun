package xyz.sealynn.androidfun.module.login;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.textfield.TextInputEditText;

import androidx.appcompat.widget.AppCompatButton;
import butterknife.BindView;
import xyz.sealynn.androidfun.R;
import xyz.sealynn.androidfun.base.ToolbarBaseActivity;
import xyz.sealynn.androidfun.module.register.RegisterActivity;
import xyz.sealynn.androidfun.utils.ActivityUtils;

public class LoginActivity extends ToolbarBaseActivity<LoginContract.Presenter> implements LoginContract.View {

    @BindView(R.id.et_username)
    TextInputEditText etUsername;
    @BindView(R.id.et_password)
    TextInputEditText etPassword;
    @BindView(R.id.bt_go)
    AppCompatButton btGo;
    @BindView(R.id.fab)
    AppCompatButton fab;

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
            if (etUsername.getText() != null && etPassword.getText() != null)
                getPresenter().login(etUsername.getText().toString().trim()
                        , etPassword.getText().toString().trim());
//            finish();
        });
        fab.setOnClickListener(view -> ActivityUtils.startActivity(this, RegisterActivity.class));
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
}
