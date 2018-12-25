package xyz.sealynn.androidfun.module.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.view.GravityCompat;
import androidx.core.widget.NestedScrollView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import xyz.sealynn.androidfun.APP;
import xyz.sealynn.androidfun.R;
import xyz.sealynn.androidfun.SettingsActivity;
import xyz.sealynn.androidfun.adapter.ViewPagerAdapter;
import xyz.sealynn.androidfun.base.BaseActivity;
import xyz.sealynn.androidfun.module.about.AboutActivity;
import xyz.sealynn.androidfun.module.guidance.GuidanceFragment;
import xyz.sealynn.androidfun.module.home.HomeFragment;
import xyz.sealynn.androidfun.module.knowledgetree.KnowledgeTreeFragment;
import xyz.sealynn.androidfun.module.login.LoginActivity;
import xyz.sealynn.androidfun.module.project.ProjectFragment;
import xyz.sealynn.androidfun.module.web.WebActivity;
import xyz.sealynn.androidfun.module.wechat.WeChatFragment;
import xyz.sealynn.androidfun.module.year.YearProgressActivity;
import xyz.sealynn.androidfun.utils.NightModeUtils;
import xyz.sealynn.androidfun.view.CustomViewPager;

public class MainActivity extends BaseActivity<MainContract.Presenter>
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
    @BindView(R.id.scroll_view)
    NestedScrollView scrollView;
    @BindView(R.id.viewpager)
    CustomViewPager viewPager;
    @BindView(R.id.bottomNavigationView)
    BottomNavigationView bottomNavigationView;

    View headerView;
    AppCompatImageView avater;
    AppCompatTextView TVusername;

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
        initViewPager();
        initBottomNavigationView();
        initHeader();

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            nightMode.setText(R.string.day_mode);
            nightMode.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_action_day_mode), null, null, null);
        } else if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO) {
            nightMode.setText(R.string.night_mode);
            nightMode.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_action_night_mode), null, null, null);
        }
    }

    private void initHeader() {
        headerView = navigationView.getHeaderView(0);
        avater = headerView.findViewById(R.id.imageView);
        TVusername = headerView.findViewById(R.id.textView);

        avater.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, LoginActivity.class)));
    }

    private void initViewPager() {
//向ViewPager添加各页面
        List<Fragment> listFragment = new ArrayList<>();
        listFragment.add(new HomeFragment());
        listFragment.add(new KnowledgeTreeFragment());
        listFragment.add(new WeChatFragment());
        listFragment.add(new GuidanceFragment());
        listFragment.add(new ProjectFragment());
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), this, listFragment);
        viewPager.setScrollingEnable(false);
        viewPager.setAdapter(adapter);
    }

    private void initBottomNavigationView() {
        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
            int id = menuItem.getItemId();

            if (id == R.id.home) {
                viewPager.setCurrentItem(0);
            } else if (id == R.id.knowledge_tree) {
                viewPager.setCurrentItem(1);
            } else if (id == R.id.wechat) {
                viewPager.setCurrentItem(2);
            } else if (id == R.id.guidance) {
                viewPager.setCurrentItem(3);
            } else if (id == R.id.project) {
                viewPager.setCurrentItem(4);
            }
            return true;
        });
    }

    private void initNavigationView() {
        navigationView.setNavigationItemSelectedListener(menuItem -> {
            // Handle navigation view item clicks here.
            int id = menuItem.getItemId();

            if (id == R.id.nav_play_android) {
                // Handle the camera action
            } else if (id == R.id.nav_todo) {

            } else if (id == R.id.nav_year_progress) {
                startActivity(new Intent(MainActivity.this, YearProgressActivity.class));
            } else if (id == R.id.nav_collection) {

            } else if (id == R.id.nav_setting) {
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            } else if (id == R.id.nav_about_us) {
                startActivity(new Intent(MainActivity.this, AboutActivity.class));
            }

//        DrawerLayout drawer = findViewById(R.id.drawer_layout);
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
            if (NightModeUtils.getNightModeState(this)) {
                Logger.d(true);
                NightModeUtils.setNightModeOff(this);
                nightMode.setText(R.string.night_mode);
            } else {
                Logger.d(false);
                NightModeUtils.setNightModeOn(this);
                nightMode.setText(R.string.day_mode);
            }
        });
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
