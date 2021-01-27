package cn.mlynn.androidfun.module;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.appbar.AppBarLayout;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.appcompat.app.ActionBar;

import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import android.webkit.WebView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.DefaultWebClient;
import com.just.agentweb.NestedScrollAgentWebView;
import com.just.agentweb.WebChromeClient;
import com.just.agentweb.WebViewClient;

import cn.mlynn.androidfun.R;
import cn.mlynn.androidfun.base.ExBaseActivity;
import cn.mlynn.androidfun.databinding.ActivityWebBinding;
import cn.mlynn.androidfun.utils.SharedUtils;

/**
 * Created by SeaLynn0 on 2018/8/28 13:58
 * <p>
 * Email：sealynndev@gmail.com
 */
public class WebActivity extends ExBaseActivity<ActivityWebBinding> {

    private ActionBar actionBar;

    private AgentWeb mAgentWeb;
    private Gson gson = new Gson();

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

//        @Override
//        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//            view.loadUrl(request.getUrl().toString());
//            return true;
//        }
    };

    @Override
    protected void init(Bundle savedInstanceState) {
        Intent intent = getIntent();
        url = intent.getStringExtra("url");

        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setTitle("");
        }

        NestedScrollAgentWebView webView = new NestedScrollAgentWebView(this);
//        webView.getSettings().setJavaScriptEnabled(true);
        CoordinatorLayout.LayoutParams lp = new CoordinatorLayout.LayoutParams(-1, -1);
        lp.setBehavior(new AppBarLayout.ScrollingViewBehavior());

        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(getBinding().webContain, 1, lp)//lp记得设置behavior属性
                .useDefaultIndicator()
                .setWebChromeClient(mWebChromeClient)
                .setWebViewClient(mWebViewClient)
//                .setPermissionInterceptor(mPermissionInterceptor) //权限拦截
                .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.DISALLOW)//打开其他页面时，弹窗质询用户前往其他应用
                .setWebView(webView)
                .createAgentWeb()
                .ready()
                .go(url);
    }

    @Override
    protected ActivityWebBinding initBinding() {
        return ActivityWebBinding.inflate(getLayoutInflater());
    }

//    protected PermissionInterceptor mPermissionInterceptor = new PermissionInterceptor() {
//
//        /**
//         * PermissionInterceptor 能达到 url1 允许授权， url2 拒绝授权的效果。
//         * @param url
//         * @param permissions
//         * @param action
//         * @return true 该Url对应页面请求权限进行拦截 ，false 表示不拦截。
//         */
//        @Override
//        public boolean intercept(String url, String[] permissions, String action) {
//            Logger.i("mUrl:" + url + "  permission:" + gson.toJson(permissions) + " action:" + action);
//            return false;
//        }
//    };

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
                openBrowser(getAgentWebView().getUrl());
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

    /**
     * 打开浏览器
     *
     * @param targetUrl 外部浏览器打开的地址
     */
    public void openBrowser(String targetUrl) {
        if (TextUtils.isEmpty(targetUrl) || targetUrl.startsWith("file://")) {
            Toast.makeText(WebActivity.this, targetUrl + " 该链接无法使用浏览器打开。", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri mUri = Uri.parse(targetUrl);
        intent.setData(mUri);
        startActivity(intent);
    }

}
