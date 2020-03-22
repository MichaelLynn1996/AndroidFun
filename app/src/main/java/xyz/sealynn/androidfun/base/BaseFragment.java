package xyz.sealynn.androidfun.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;

import com.orhanobut.logger.Logger;

import xyz.sealynn.androidfun.R;

/**
 * Created by SeaLynn0 on 2018/12/5 15:01
 * <p>
 * Email：sealynndev@gmail.com
 */
public abstract class BaseFragment<P extends BasePresenter, B extends ViewBinding> extends Fragment
        implements BaseView {

    private P mPresenter;
    private B binding;

    @NonNull
    @Override
    public final View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = initBinding(inflater, container);
        mPresenter = createPresenter();
        getLifecycle().addObserver(mPresenter);

        prepareData(savedInstanceState);
        initToolbar(binding.getRoot());
        // 初始化视图
        initView(binding.getRoot());
        initData(savedInstanceState);
        initEvent();
        return binding.getRoot();
    }

    protected abstract B initBinding(LayoutInflater inflater, ViewGroup container);


    protected abstract P createPresenter();

    /**
     * 准备数据
     *
     * @param savedInstanceState
     */
    protected abstract void prepareData(Bundle savedInstanceState);

    /**
     * 初始化Toolbar
     *
     * @param root
     */
    private void initToolbar(View root) {
        Logger.d(root.findViewById(R.id.toolbar));
        if (root.findViewById(R.id.toolbar) != null) {
//            mToolbar = root.findViewById(R.id.toolbar);
            if (getActivity() != null)
                ((AppCompatActivity) getActivity()).setSupportActionBar(root.findViewById(R.id.toolbar));
        }
    }

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
    public Context getContext() {
        return super.getContext();
    }

    protected P getPresenter() {
        return mPresenter;
    }

    public B getBinding() {
        return binding;
    }
}
