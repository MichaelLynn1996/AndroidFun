package xyz.sealynn.androidfun.module.web;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import com.google.android.material.appbar.AppBarLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.appcompat.app.ActionBar;

import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.gson.Gson;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.DefaultWebClient;
import com.just.agentweb.NestedScrollAgentWebView;
import com.just.agentweb.PermissionInterceptor;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import xyz.sealynn.androidfun.R;
import xyz.sealynn.androidfun.base.BaseActivity;
import xyz.sealynn.androidfun.utils.SharedUtils;

/**
 * Created by SeaLynn0 on 2018/8/28 13:58
 * <p>
 * Email：sealynndev@gmail.com
 */
public class WebActivity extends BaseActivity<WebContract.Presenter> implements WebContract.View {

    @BindView(R.id.web_contain)
    CoordinatorLayout webContain;

    private ActionBar actionBar;

    private AgentWeb mAgentWeb;
    private Gson mGson = new Gson();

    String url;

    private WebChromeClient mWebChromeClient = new WebChromeClient() {
        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            actionBar.setTitle(title);
        }
    };

    private WebViewClient mWebViewClient = new WebViewClient() {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            actionBar.setTitle("加载中......");
        }
    };

    @Override
    protected WebContract.Presenter createPresenter() {
        return new WebPresenter(this);
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_web;
    }

    @Override
    protected void prepareData() {
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
    }

    @Override
    protected void initView() {
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setTitle("");
        }
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        NestedScrollAgentWebView webView = new NestedScrollAgentWebView(this);
        CoordinatorLayout.LayoutParams lp = new CoordinatorLayout.LayoutParams(-1, -1);
        lp.setBehavior(new AppBarLayout.ScrollingViewBehavior());

        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(webContain, 1, lp)//lp记得设置behavior属性
                .useDefaultIndicator()
                .setWebChromeClient(mWebChromeClient)
                .setWebViewClient(mWebViewClient)
                .setPermissionInterceptor(mPermissionInterceptor) //权限拦截
                .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.DISALLOW)//打开其他页面时，弹窗质询用户前往其他应用
                .setWebView(webView)
                .createAgentWeb()
                .ready()
                .go(url);
    }

    @Override
    protected void initEvent() {

    }

    protected PermissionInterceptor mPermissionInterceptor = new PermissionInterceptor() {

        /**
         * PermissionInterceptor 能达到 url1 允许授权， url2 拒绝授权的效果。
         * @param url
         * @param permissions
         * @param action
         * @return true 该Url对应页面请求权限进行拦截 ，false 表示不拦截。
         */
        @Override
        public boolean intercept(String url, String[] permissions, String action) {
            Logger.i("mUrl:" + url + "  permission:" + mGson.toJson(permissions) + " action:" + action);
            return false;
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar_web, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.share:
                String value = getAgentWebView().getTitle()
                        + " "
                        + getAgentWebView().getUrl();
                SharedUtils.shareText(WebActivity.this, value);
                return true;
            case android.R.id.home:
                finish();
                return true;
            case R.id.refresh:
                mAgentWeb.getUrlLoader().reload();
                return true;
            case R.id.open_external_browser:
                mPresenter.openBrowser(getAgentWebView().getUrl());
                return true;
            case R.id.copy:
                SharedUtils.copyText(WebActivity.this, getAgentWebView().getUrl());
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();

    }

    @Override
    protected void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        mAgentWeb.getWebLifeCycle().onDestroy();
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return mAgentWeb.handleKeyEvent(keyCode, event) || super.onKeyDown(keyCode, event);
    }

    private WebView getAgentWebView() {
        return mAgentWeb.getWebCreator().getWebView();
    }

}
