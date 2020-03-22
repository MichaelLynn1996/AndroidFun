package xyz.sealynn.androidfun.module.project;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import xyz.sealynn.androidfun.R;
import xyz.sealynn.androidfun.databinding.FramentProjectBinding;
import xyz.sealynn.androidfun.module.BaseMainFragment;

/**
 * Created by SeaLynn0 on 2018/12/6 20:19
 * <p>
 * Email：sealynndev@gmail.com
 */
public class ProjectFragment extends BaseMainFragment<ProjectContract.Presenter, FramentProjectBinding> implements ProjectContract.View {

    @Override
    protected FramentProjectBinding initBinding(LayoutInflater inflater, ViewGroup container) {
        return FramentProjectBinding.inflate(inflater, container,false);
    }

    @Override
    protected ProjectContract.Presenter createPresenter() {
        return new ProjectPresenter(this);
    }

    @Override
    protected void prepareData(Bundle savedInstanceState) {

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
