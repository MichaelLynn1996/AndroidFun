package xyz.sealynn.androidfun.module.register;

import android.app.Activity;

import xyz.sealynn.androidfun.R;
import xyz.sealynn.androidfun.base.BasePresenterImpl;
import xyz.sealynn.androidfun.model.User;
import xyz.sealynn.androidfun.model.Result;
import xyz.sealynn.androidfun.net.RetrofitManager;
import xyz.sealynn.androidfun.utils.ToastUtils;

/**
 * Created by SeaLynn0 on 2018/12/25 18:10
 * <p>
 * Email：sealynndev@gmail.com
 */
public class RegisterPresenter extends BasePresenterImpl<RegisterContract.View> implements RegisterContract.Presenter {

    private static final int REGISTER = 0;

    RegisterPresenter(RegisterContract.View view) {
        super(view);
    }

    @Override
    public void register(String u, String p, String rp) {
        domine(RetrofitManager.getInstance().createReq().register(u, p, rp), REGISTER);
    }

    @Override
    public void onResponse(Object t, int what) {
        switch (what) {
            case REGISTER:
                Result<User> requestBody = Result.cast(t);
                if (requestBody.getErrorCode() == 0) {
                    ToastUtils.shortToast(getView().getContext(), R.string.register_success);
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
