package xyz.sealynn.androidfun.module.home;

import xyz.sealynn.androidfun.base.BasePresenterImpl;

/**
 * Created by SeaLynn0 on 2018/12/6 19:54
 * <p>
 * Email：sealynndev@gmail.com
 */
public class HomePresenter extends BasePresenterImpl<HomeContract.View> implements HomeContract.Presenter {

    public HomePresenter(HomeContract.View view) {
        super(view);
    }
}
