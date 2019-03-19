package xyz.sealynn.androidfun.module.login;

import android.app.Activity;

import xyz.sealynn.androidfun.base.BasePresenterImpl;
import xyz.sealynn.androidfun.model.Login;
import xyz.sealynn.androidfun.model.Result;
import xyz.sealynn.androidfun.net.RetrofitManager;
import xyz.sealynn.androidfun.utils.ToastUtils;

/**
 * Created by SeaLynn0 on 2018/12/25 17:48
 * <p>
 * Email：sealynndev@gmail.com
 */
public class LoginPresenter extends BasePresenterImpl<LoginContract.View> implements LoginContract.Presenter {

    public LoginPresenter(LoginContract.View view) {
        super(view);
    }

    @Override
    public void login(String u, String p) {
        domine(RetrofitManager.getInstance(getView().getContext()).createReq().login(u, p), 1);
    }

    @Override
    public void onResponse(Object t, int what) {
        super.onResponse(t, what);
        switch (what) {
            case 1:
                Result<Login> requestBody = Result.cast(t);
                if (requestBody.getErrorCode() == 0) {
                    ToastUtils.shortToast(getView().getContext(), "登陆成功");
                    Result.putResultBean(getView().getContext(), "login", requestBody);
                    Activity activity = (Activity) getView().getContext();
                    activity.finish();
                } else {
                    ToastUtils.shortToast(getView().getContext(), requestBody.getErrorMsg());
                }
                break;
        }
    }
}
