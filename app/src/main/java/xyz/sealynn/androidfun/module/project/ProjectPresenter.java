package xyz.sealynn.androidfun.module.project;

import xyz.sealynn.androidfun.base.BasePresenterImpl;

/**
 * Created by SeaLynn0 on 2018/12/6 20:18
 * <p>
 * Email：sealynndev@gmail.com
 */
public class ProjectPresenter extends BasePresenterImpl implements ProjectContract.Presenter {

    private final ProjectContract.View mView;

    public ProjectPresenter(ProjectContract.View view) {
        mView = view;
        this.mView.setPresenter(this);
    }
}
