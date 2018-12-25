package xyz.sealynn.androidfun.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by SeaLynn0 on 2018/12/5 15:01
 * <p>
 * Email：sealynndev@gmail.com
 */
public abstract class BaseFragment<P extends BasePresenter> extends Fragment
        implements BaseView<P> {

    private Unbinder mUnbinder;
    protected P mPresenter;

    @Nullable
    @Override
    public final View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.onStart();
        }
        View root = inflater.inflate(bindLayout(), container, false);
        mUnbinder = ButterKnife.bind(this, root);
        prepareData(savedInstanceState);
        // 初始化视图
        initView(root);
        initData(savedInstanceState);
        initEvent();
        return root;
    }


    protected abstract P createPresenter();


    @Override
    public void setPresenter(P presenter) {
        mPresenter = presenter;
    }

    /**
     * 准备数据
     *
     * @param savedInstanceState
     */
    protected abstract void prepareData(Bundle savedInstanceState);

    /**
     * 绑定fragment的布局文件
     *
     * @return
     */
    protected abstract int bindLayout();

    /**
     * 初始化数据
     *
     * @param savedInstanceState
     */
    protected abstract void initData(Bundle savedInstanceState);

    /**
     * 初始化界面
     *
     * @param rootView
     */
    protected abstract void initView(View rootView);

    /**
     * 初始化事件监听器
     */
    protected abstract void initEvent();


    @Override
    public void onDestroyView() {
        if (mPresenter != null) {
            mPresenter.onDestroy();
        }
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public Context getContext() {
        return super.getContext();
    }
}
