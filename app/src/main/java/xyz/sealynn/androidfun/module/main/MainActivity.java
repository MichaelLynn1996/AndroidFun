package xyz.sealynn.androidfun.module.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.AppCompatButton;
import android.view.Menu;
import android.view.MenuItem;

import com.orhanobut.logger.Logger;

import butterknife.BindView;
import xyz.sealynn.androidfun.APP;
import xyz.sealynn.androidfun.R;
import xyz.sealynn.androidfun.base.BaseActivity;
import xyz.sealynn.androidfun.module.about.AboutActivity;
import xyz.sealynn.androidfun.module.web.WebActivity;
import xyz.sealynn.androidfun.module.year.YearProgressActivity;
import xyz.sealynn.androidfun.utils.DateUtils;
import xyz.sealynn.androidfun.utils.NightModeUtils;
import xyz.sealynn.androidfun.utils.NotificationUtils;

public class MainActivity extends BaseActivity<MainContract.Presenter>
        implements NavigationView.OnNavigationItemSelectedListener, MainContract.View {

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

        navigationView.setNavigationItemSelectedListener(this);

        if (AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES){
            nightMode.setText(R.string.day_mode);
            nightMode.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_action_day_mode),null,null,null);
        }else if (AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_NO){
            nightMode.setText(R.string.night_mode);
            nightMode.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_action_night_mode),null,null,null);
        }
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
            }else {
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_play_android) {
            // Handle the camera action
        } else if (id == R.id.nav_todo) {

        } else if (id == R.id.nav_year_progress) {
            startActivity(new Intent(MainActivity.this, YearProgressActivity.class));
        } else if (id == R.id.nav_collection) {

        } else if (id == R.id.nav_setting) {

        } else if (id == R.id.nav_about_us) {
            startActivity(new Intent(MainActivity.this, AboutActivity.class));
        }

//        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
