package xyz.sealynn.androidfun.module.login;

import android.app.Activity;

import xyz.sealynn.androidfun.R;
import xyz.sealynn.androidfun.base.BasePresenterImpl;
import xyz.sealynn.androidfun.model.User;
import xyz.sealynn.androidfun.model.Result;
import xyz.sealynn.androidfun.net.RetrofitManager;
import xyz.sealynn.androidfun.utils.ToastUtils;

/**
 * Created by SeaLynn0 on 2018/12/25 17:48
 * <p>
 * Email：sealynndev@gmail.com
 */
public class LoginPresenter extends BasePresenterImpl<LoginContract.View> implements LoginContract.Presenter {

    private static final int LOGIN = 0;

    public LoginPresenter(LoginContract.View view) {
        super(view);
    }

    @Override
    public void login(String u, String p) {
        domine(RetrofitManager.getInstance().createReq().login(u, p), LOGIN);
    }

    @Override
    public void onResponse(Object t, int what) {
        switch (what) {
            case LOGIN:
                Result<User> requestBody = Result.cast(t);
                if (requestBody.getErrorCode() == 0) {
                    ToastUtils.shortToast(getView().getContext(), R.string.login_success);
                    Result.putResultBean(getView().getContext(), "login", requestBody);
                    Activity activity = (Activity) getView().getContext();
                    activity.setResult(Activity.RESULT_OK, null);
                    activity.finish();
                } else {
                    ToastUtils.shortToast(getView().getContext(), requestBody.getErrorMsg());
                }
                getView().unFreeze();
                break;
        }
    }
}
