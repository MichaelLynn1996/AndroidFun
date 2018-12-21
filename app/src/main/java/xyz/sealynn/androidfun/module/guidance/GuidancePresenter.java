package xyz.sealynn.androidfun.module.guidance;

import xyz.sealynn.androidfun.base.BasePresenterImpl;

/**
 * Created by SeaLynn0 on 2018/12/6 20:09
 * <p>
 * Email：sealynndev@gmail.com
 */
public class GuidancePresenter extends BasePresenterImpl implements GuidanceContract.Presenter {

    private final GuidanceContract.View mView;
    public GuidancePresenter(GuidanceContract.View view) {
        mView = view;
        this.mView.setPresenter(this);
    }
}
