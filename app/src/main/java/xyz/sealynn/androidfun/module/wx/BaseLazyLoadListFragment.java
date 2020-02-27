package xyz.sealynn.androidfun.module.wx;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;
import butterknife.ButterKnife;
import xyz.sealynn.androidfun.R;

/**
 * @author Michael Lynn
 * @title: BaseLazyLoadListFragment
 * @projectName AndroidFun
 * @description: TODO
 * @date 2019/4/920:20
 */
public abstract class BaseLazyLoadListFragment extends ListFragment {
    public View view;

    public Activity mContext;

    /**
     * 是否初始化过布局
     */
    protected boolean isViewInitiated;
    /**
     * 当前界面是否可见
     */
    protected boolean isVisibleToUser;
    /**
     * 是否加载过数据
     */
    protected boolean isDataInitiated;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, view);
        mContext = getActivity();

        this.initData();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isViewInitiated=true;
        //加载数据
        prepareFetchData();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        if (isVisibleToUser) {
            prepareFetchData();
        }
    }

    public void prepareFetchData() {
        prepareFetchData(false);
    }

    /**
     * 判断懒加载条件
     *
     * @param forceUpdate 强制更新，好像没什么用？
     */
    public void prepareFetchData(boolean forceUpdate) {
        if (isVisibleToUser && isViewInitiated && (!isDataInitiated || forceUpdate)) {
            loadData();
            isDataInitiated = true;
        }
    }

    /**
     * 懒加载
     */
    protected abstract void loadData();

    /**
     * 数据初始化操作
     */
    protected abstract void initData();


    @Override
    public void onDestroy() {
        super.onDestroy();
        this.view = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.bind(mContext).unbind();
    }
}
