/**
 * @ProjectName: AndroidFun
 * @Package: cn.mlynn.androidfun.module
 * @ClassName: RefreshRecyclerFragment
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/7/10 22:12
 */
package cn.mlynn.androidfun.module;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import cn.mlynn.androidfun.base.BaseFragment;
import cn.mlynn.androidfun.base.BaseViewModel;
import cn.mlynn.androidfun.databinding.LayoutRefreshRecyclerBinding;

public abstract class RefreshRecyclerFragment<VM extends BaseViewModel>
        extends BaseFragment<VM, LayoutRefreshRecyclerBinding> {

    @Override
    protected LayoutRefreshRecyclerBinding initBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return LayoutRefreshRecyclerBinding.inflate(inflater, container, false);
    }

    @Override
    protected final void dismissLoading() {
        if (getBinding() != null && getBinding().refresh.isRefreshing())
            getBinding().refresh.setRefreshing(false);
    }

    @Override
    protected final void startLoading() {
        if (getBinding() != null && !getBinding().refresh.isRefreshing())
            getBinding().refresh.setRefreshing(true);
    }
}
