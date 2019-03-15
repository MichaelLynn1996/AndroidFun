package xyz.sealynn.androidfun.module.main;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.ContentFrameLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;
import xyz.sealynn.androidfun.APP;
import xyz.sealynn.androidfun.R;
import xyz.sealynn.androidfun.base.Constants;
import xyz.sealynn.androidfun.base.ToolbarBaseActivity;
import xyz.sealynn.androidfun.model.Login;
import xyz.sealynn.androidfun.model.Result;
import xyz.sealynn.androidfun.module.SettingsActivity;
import xyz.sealynn.androidfun.module.collection.CollectionActivity;
import xyz.sealynn.androidfun.module.guidance.GuidanceFragment;
import xyz.sealynn.androidfun.module.home.HomeFragment;
import xyz.sealynn.androidfun.module.knowledgetree.KnowledgeTreeFragment;
import xyz.sealynn.androidfun.module.login.LoginActivity;
import xyz.sealynn.androidfun.module.project.ProjectFragment;
import xyz.sealynn.androidfun.module.search.SearchActivity;
import xyz.sealynn.androidfun.module.todo.TodoActivity;
import xyz.sealynn.androidfun.module.web.WebActivity;
import xyz.sealynn.androidfun.module.wechat.WeChatFragment;
import xyz.sealynn.androidfun.module.year.YearProgressActivity;
import xyz.sealynn.androidfun.receiver.NightModeChangeReceiver;
import xyz.sealynn.androidfun.utils.ActivityUtils;
import xyz.sealynn.androidfun.utils.NightModeUtils;
import xyz.sealynn.androidfun.utils.ToastUtils;

public class MainActivity extends ToolbarBaseActivity<MainContract.Presenter>
        implements MainContract.View {

    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.bt_exit)
    AppCompatButton exit;
    @BindView(R.id.bt_night_mode)
    AppCompatButton nightMode;
    @BindView(R.id.content)
    ContentFrameLayout content;
    @BindView(R.id.bottomNavigationView)
    BottomNavigationView bottomNavigationView;

    View headerView;

    NightModeChangeReceiver receiver;

    List<Fragment> listFragment = new ArrayList<>();
    private int lastFragment;   //用于记录上个选择的Fragment

    @Override
    protected MainContract.Presenter createPresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void prepareData() {
    }

    @Override
    protected void initView() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, getToolbar(), R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        initNavigationView();
        initFragments();
        initBottomNavigationView();
        initHeader();

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            nightMode.setText(R.string.day_mode);
            nightMode.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_action_day_mode), null, null, null);
        } else if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO) {
            nightMode.setText(R.string.night_mode);
            nightMode.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_action_night_mode), null, null, null);
        } else if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_AUTO) {
            nightMode.setText(R.string.auto);
            nightMode.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_action_night_mode_auto), null, null, null);
        }
    }

    private void initHeader() {
        headerView = navigationView.getHeaderView(0);
        headerView.setOnClickListener(v -> {
            if (getContext().getSharedPreferences(Constants.CONFIG_DEFAULT, Context.MODE_PRIVATE).contains("login")){
                ToastUtils.shortToast(getContext(),"已登录");
            }else {
                ActivityUtils.startActivity(MainActivity.this, LoginActivity.class);
            }
        });
        if (Result.getResultBean(this, "login", Result.class) != null) {
            Result<Login> data = Result.getResultBean(MainActivity.this, "login", new TypeToken<Result<Login>>() {
            }.getType());
            AppCompatTextView textView = headerView.findViewById(R.id.textView);
            textView.setText(data.getData().getUsername());
        }
    }

    private void initFragments() {
        listFragment.add(new HomeFragment());
        listFragment.add(new KnowledgeTreeFragment());
        listFragment.add(new WeChatFragment());
        listFragment.add(new GuidanceFragment());
        listFragment.add(new ProjectFragment());

        getToolbar().setTitle(R.string.home);
        ActivityUtils.replaceFragmentToActivity(getSupportFragmentManager(), listFragment.get(0), R.id.content);
    }

    private void initBottomNavigationView() {
        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
            int id = menuItem.getItemId();

            switch (id) {
                case R.id.home:
                    switchFragment(0);
                    getToolbar().setTitle(R.string.home);
                    return true;
                case R.id.knowledge_tree:
                    switchFragment(1);
                    getToolbar().setTitle(R.string.knowledge_tree);
                    return true;
                case R.id.wechat:
                    switchFragment(2);
                    getToolbar().setTitle(R.string.wechat);
                    return true;
                case R.id.guidance:
                    switchFragment(3);
                    getToolbar().setTitle(R.string.guidance);
                    return true;
                case R.id.project:
                    switchFragment(4);
                    getToolbar().setTitle(R.string.project);
                    return true;
            }
            return false;
        });
    }

    /**
     * 切换Fragment
     *
     * @param index
     */
    private void switchFragment(int index) {
        if (lastFragment != index) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.hide(listFragment.get(lastFragment));//隐藏上个Fragment
            if (!listFragment.get(index).isAdded()) {
                transaction.add(R.id.content, listFragment.get(index));
            }
            transaction.show(listFragment.get(index)).commit();
            lastFragment = index;
        }
    }

    private void initNavigationView() {
        navigationView.setNavigationItemSelectedListener(menuItem -> {
            // Handle navigation view item clicks here.
            int id = menuItem.getItemId();

            if (id == R.id.nav_play_android) {
                drawer.closeDrawer(GravityCompat.START);
            } else if (id == R.id.nav_todo) {
                ActivityUtils.startActivity(MainActivity.this, TodoActivity.class);
            } else if (id == R.id.nav_year_progress) {
                ActivityUtils.startActivity(MainActivity.this, YearProgressActivity.class);
            } else if (id == R.id.nav_collection) {
                ActivityUtils.startActivity(MainActivity.this, CollectionActivity.class);
            } else if (id == R.id.nav_setting) {
                ActivityUtils.startActivity(MainActivity.this, SettingsActivity.class);
            }

            drawer.closeDrawer(GravityCompat.START);
            return true;
        });
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initEvent() {
        fab.setOnClickListener(view -> {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            Intent intent = new Intent(this, WebActivity.class);
            intent.putExtra("url", "http://android.myapp.com/");
            startActivity(intent);
        });
        exit.setOnClickListener(v -> APP.exitApp());
        nightMode.setOnClickListener(v -> {
            if (NightModeUtils.getNightModeState(this) == AppCompatDelegate.MODE_NIGHT_YES) {
//                Logger.d(true);
                NightModeUtils.setNightModeState(this, AppCompatDelegate.MODE_NIGHT_NO);
                nightMode.setText(R.string.night_mode);
            } else if (NightModeUtils.getNightModeState(this) == AppCompatDelegate.MODE_NIGHT_NO) {
//                Logger.d(false);
                NightModeUtils.setNightModeState(this, AppCompatDelegate.MODE_NIGHT_YES);
                nightMode.setText(R.string.day_mode);
            } else if (NightModeUtils.getNightModeState(this) == AppCompatDelegate.MODE_NIGHT_AUTO) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle(R.string.NIGHT_MODE_OR_NOT)
                        .setMessage(R.string.NIGHT_MODE_DISCUBUTION)
                        .setPositiveButton(R.string.OFF,
                                (dialog, which) -> NightModeUtils.setNightModeState(this, AppCompatDelegate.MODE_NIGHT_YES))
                        .setNegativeButton(R.string.TEMP_NOT,
                                (dialog, which) -> dialog.dismiss())
                        .show();
            }
        });
        getPresenter().checkYearProgress();

        //创建广播
        receiver = new NightModeChangeReceiver(this);
        //注册广播
        registerReceiver(receiver, new IntentFilter(Constants.NIGHT_MODE_CHANGE_INTENT));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
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
    protected void onDestroy() {
        unregisterReceiver(receiver);
        super.onDestroy();
    }
}
