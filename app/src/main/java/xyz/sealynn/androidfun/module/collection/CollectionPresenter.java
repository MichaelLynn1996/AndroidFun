package xyz.sealynn.androidfun.module.collection;

import xyz.sealynn.androidfun.base.BasePresenterImpl;

/**
 * Created by SeaLynn0 on 2018/12/25 17:48
 * <p>
 * Email：sealynndev@gmail.com
 */
public class CollectionPresenter extends BasePresenterImpl implements CollectionContract.Presenter {

    private final CollectionContract.View mView;
    public CollectionPresenter(CollectionContract.View view) {
        mView = view;
        this.mView.setPresenter(this);
    }
}
