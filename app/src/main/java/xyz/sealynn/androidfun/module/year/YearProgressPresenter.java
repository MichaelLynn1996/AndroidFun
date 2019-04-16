package xyz.sealynn.androidfun.module.year;

import xyz.sealynn.androidfun.base.BasePresenterImpl;

/**
 * Created by SeaLynn0 on 2018/10/13 15:55
 * <p>
 * Email：sealynndev@gmail.com
 */
class YearProgressPresenter extends BasePresenterImpl<YearProgressContract.View> implements YearProgressContract.Presenter {

    YearProgressPresenter(YearProgressContract.View view) {
        super(view);
    }

    @Override
    public void onResponse(Object t, int what) {

    }
}
