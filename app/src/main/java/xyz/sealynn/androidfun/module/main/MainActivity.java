package xyz.sealynn.androidfun.module.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.ActivityNavigator;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;

import xyz.sealynn.androidfun.APP;
import xyz.sealynn.androidfun.R;
import xyz.sealynn.androidfun.base.BaseActivity;
import xyz.sealynn.androidfun.base.Constants;
import xyz.sealynn.androidfun.databinding.ActivityMainBinding;
import xyz.sealynn.androidfun.model.Result;
import xyz.sealynn.androidfun.model.User;
import xyz.sealynn.androidfun.module.AboutActivity;
import xyz.sealynn.androidfun.module.SignActivity;
import xyz.sealynn.androidfun.module.login.LoginFragment;
import xyz.sealynn.androidfun.module.search.SearchActivity;
import xyz.sealynn.androidfun.module.setting.SettingsActivity;
import xyz.sealynn.androidfun.module.todo.TodoActivity;
import xyz.sealynn.androidfun.module.year.YearProgressActivity;
import xyz.sealynn.androidfun.net.interceptor.SaveCookiesInterceptor;
import xyz.sealynn.androidfun.utils.ActivityUtils;
import xyz.sealynn.androidfun.utils.ToastUtils;

public class MainActivity extends BaseActivity<MainContract.Presenter, ActivityMainBinding>
        implements MainContract.View, NavigationView.OnNavigationItemSelectedListener {

    View headerView;
    AppCompatImageView avatar;
    AppCompatTextView username;
//    ActionBar actionBar;

//    NightModeChangeReceiver receiver;

    public static final int REQUEST_LOGIN = 1;
    public static final int ITEM_LOGOUT_ID = 120;

    @Override
    protected ActivityMainBinding initBinding() {
        return ActivityMainBinding.inflate(getLayoutInflater());
    }

    @Override
    protected MainContract.Presenter createPresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected void prepareData() {
    }

    @Override
    protected void initView() {
        initNavigationView();
        initBottomNavigationView();
    }

    /**
     * 检查登录状态
     */
    private void checkUser() {
        if (Result.getResultBean(this, "login", Result.class) != null) {
            Result<User> data = Result.getResultBean(MainActivity.this, "login", new TypeToken<Result<User>>() {
            }.getType());
            username.setText(data.getData().getUsername());
            //在Menu添加注销选项
            getBinding().navView.getMenu().addSubMenu(1, ITEM_LOGOUT_ID, 0, R.string.exit)
                    .add(1, ITEM_LOGOUT_ID, 0, R.string.logout)
                    .setIcon(R.drawable.ic_action_logout);
        }
    }

    /**
     * 初始化底部导航栏
     */
    private void initBottomNavigationView() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(getBinding().appBarMain.bottomNavigationView, navController);
    }

    /**
     * 初始化侧滑抽屉(R.id.bottomNavigationView);
     *         NavigationUI.setupWithNavController(bottomNavigationView,
     */
    private void initNavigationView() {
        headerView = getBinding().navView.getHeaderView(0);
        avatar = headerView.findViewById(R.id.imageView);
        username = headerView.findViewById(R.id.textView);
        getBinding().navView.setNavigationItemSelectedListener(this);
        checkUser();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initEvent() {
        headerView.setOnClickListener(v -> {
            if (!getContext().getSharedPreferences(Constants.CONFIG_DEFAULT, Context.MODE_PRIVATE).contains("login")) {
                startActivityForResult(new Intent(MainActivity.this, SignActivity.class), REQUEST_LOGIN);
            }
        });
    }

    /**
     * 重写返回键方法
     * 在DrawerLayout打开时收起DrawerLayout
     * 在主界面双击退出APP
     */
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
//            super.onBackPressed();
            APP.exitApp();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                ActivityUtils.startActivity(this, SearchActivity.class);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_LOGIN:
                if (resultCode == RESULT_OK)
                    checkUser();
                break;
        }
    }

    /**
     * 监听抽屉item点击事件
     *
     * @param menuItem
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        // Handle navigation view item clicks here.
        int id = menuItem.getItemId();

        if (id == R.id.nav_play_android) {
            getBinding().drawerLayout.closeDrawer(GravityCompat.START);
        } else if (id == R.id.nav_todo) {
            startActivityAfterCheckLogin(TodoActivity.class, null);
        } else if (id == R.id.nav_year_progress) {
            startActivity(new Intent(MainActivity.this, YearProgressActivity.class));
        } else if (id == R.id.nav_collection) {
            startActivityAfterCheckLogin(LoginFragment.class, REQUEST_LOGIN);
        } else if (id == R.id.nav_setting) {
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
        } else if (id == R.id.nav_about) {
            startActivity(new Intent(MainActivity.this, AboutActivity.class));
        } else if (id == ITEM_LOGOUT_ID) {
            Logger.d("注销");
            getPresenter().logout();
        }

        getBinding().drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }

    /**
     * @param cls         需要跳转的Activity
     * @param requestCode 请求码 可以为空 为空startActivity() 不为空 startActivityForResult()
     */
    private void startActivityAfterCheckLogin(Class<?> cls, Integer requestCode) {
        //需要检查登录
        if (!getContext().getSharedPreferences(Constants.CONFIG_DEFAULT, Context.MODE_PRIVATE).contains("login")) {
            ToastUtils.shortToast(MainActivity.this, R.string.login_first);
            startActivityForResult(new Intent(MainActivity.this, LoginFragment.class), REQUEST_LOGIN);
        } else {
            if (requestCode == null)
                startActivity(new Intent(MainActivity.this, TodoActivity.class));
            else
                startActivityForResult(new Intent(MainActivity.this, LoginFragment.class), REQUEST_LOGIN);
        }
    }

    @Override
    public void logout() {
        getContext()
                .getSharedPreferences(Constants.CONFIG_DEFAULT, Context.MODE_PRIVATE)
                .edit().remove("login").apply();
        SaveCookiesInterceptor.clearCookie(MainActivity.this);
        Logger.d(getBinding().navView.getMenu().getItem(4).getItemId());
        getBinding().navView.getMenu().removeGroup(1);
        username.setText(R.string.login_or_regist);
    }
}
