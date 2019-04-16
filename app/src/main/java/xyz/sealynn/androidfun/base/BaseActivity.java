package xyz.sealynn.androidfun.base;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import xyz.sealynn.androidfun.R;

/**
 * Created by SeaLynn0 on 2018/9/4 23:56
 * <p>
 * Email：sealynndev@gmail.com
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity
        implements BaseView {

    private Unbinder bind;

    /**
     * 泛型确定Presenter
     */
    private P mPresenter;

    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(bindLayout());

        // ButterKnife绑定布局
        bind = ButterKnife.bind(this);

        mPresenter = createPresenter();
//        if (mPresenter != null) {
//            // 调用Presenter初始化方法
//            mPresenter.onStart();
//        }

        getLifecycle().addObserver(mPresenter);

        // 准备数据
        prepareData();
        // 初始化标题栏
        initToolbar();
        // 初始化视图
        initView();
        // 初始化数据
        initData(savedInstanceState);
        // 初始化事件监听
        initEvent();
    }

    /**
     * 初始化Toolbar
     */
    private void initToolbar() {
        if (findViewById(R.id.toolbar) != null) {
            setSupportActionBar(findViewById(R.id.toolbar));
        }
    }

    /**
     * 创建Presenter
     *
     * @return 泛型Presenter
     */
    protected abstract P createPresenter();

    /**
     * 绑定布局
     *
     * @return 布局文件的资源ID
     */
    protected abstract int bindLayout();

    /**
     * 准备数据（从Intent获取上一个界面传过来的数据或其他需要初始化的数据）
     */
    protected abstract void prepareData();

    /**
     * 初始化视图，findViewById等等
     */
    protected abstract void initView();

    /**
     * 初始化数据，从本地或服务器开始获取数据
     *
     * @param savedInstanceState 界面非正常销毁时保存的数据
     */
    protected abstract void initData(Bundle savedInstanceState);

    /**
     * 初始化事件监听，setOnClickListener等等
     */
    protected abstract void initEvent();

    /**
     * Activity销毁时清理资源
     */
    @Override
    protected void onDestroy() {
        // ButterKnife解除绑定
        bind.unbind();
        super.onDestroy();
    }

    @Override
    public Context getContext() {
        return this;
    }

    protected P getPresenter() {
        return mPresenter;
    }
}
