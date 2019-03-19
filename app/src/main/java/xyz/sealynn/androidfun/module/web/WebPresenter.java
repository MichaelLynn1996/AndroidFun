package xyz.sealynn.androidfun.module.web;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.Toast;

import xyz.sealynn.androidfun.base.BasePresenterImpl;
import xyz.sealynn.androidfun.base.BasePresenter;

/**
 * Created by SeaLynn0 on 2018/9/5 0:33
 * <p>
 * Email：sealynndev@gmail.com
 */
class WebPresenter extends BasePresenterImpl<WebContract.View> implements WebContract.Presenter {

    WebPresenter(WebContract.View view) {
        super(view);
    }

    /**
     * 打开浏览器
     *
     * @param targetUrl 外部浏览器打开的地址
     */
    public void openBrowser(String targetUrl) {
        if (TextUtils.isEmpty(targetUrl) || targetUrl.startsWith("file://")) {
            Toast.makeText(getView().getContext(), targetUrl + " 该链接无法使用浏览器打开。", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri mUri = Uri.parse(targetUrl);
        intent.setData(mUri);
        getView().getContext().startActivity(intent);
    }
}
