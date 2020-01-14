package xyz.sealynn.androidfun.module.project;

import android.os.Bundle;

import xyz.sealynn.androidfun.R;
import xyz.sealynn.androidfun.module.BaseMainFragment;

/**
 * Created by SeaLynn0 on 2018/12/6 20:19
 * <p>
 * Email：sealynndev@gmail.com
 */
public class ProjectFragment extends BaseMainFragment<ProjectContract.Presenter> implements ProjectContract.View {

    @Override
    protected ProjectContract.Presenter createPresenter() {
        return new ProjectPresenter(this);
    }

    @Override
    protected void prepareData(Bundle savedInstanceState) {

    }

    @Override
    protected int bindLayout() {
        return R.layout.frament_project;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected int getTitle() {
        return R.string.project;
    }
}
