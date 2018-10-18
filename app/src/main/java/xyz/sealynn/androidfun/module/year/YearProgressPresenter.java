package xyz.sealynn.androidfun.module.year;

import xyz.sealynn.androidfun.base.BasePresenterImpl;

/**
 * Created by SeaLynn0 on 2018/10/13 15:55
 * <p>
 * Email：sealynndev@gmail.com
 */
class YearProgressPresenter extends BasePresenterImpl implements YearProgressContract.Presenter {

    private final YearProgressContract.View mView;

    YearProgressPresenter(YearProgressContract.View view) {
        mView = view;
        this.mView.setPresenter(this);
    }
}
