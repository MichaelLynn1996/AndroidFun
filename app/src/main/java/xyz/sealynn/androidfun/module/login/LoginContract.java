package xyz.sealynn.androidfun.module.login;

import xyz.sealynn.androidfun.base.BasePresenter;
import xyz.sealynn.androidfun.base.BaseView;

/**
 * Created by SeaLynn0 on 2018/12/25 17:48
 * <p>
 * Email：sealynndev@gmail.com
 */
public interface LoginContract {

    interface View extends BaseView {
        void freeze();
        void unFreeze();
    }

    interface Presenter extends BasePresenter<View> {
        void login(String u, String p);
    }
}
