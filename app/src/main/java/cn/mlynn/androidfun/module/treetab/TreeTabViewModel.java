package cn.mlynn.androidfun.module.treetab;

import androidx.hilt.Assisted;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.SavedStateHandle;

import cn.mlynn.androidfun.base.BaseViewModel;

/**
 * @ProjectName: AndroidFun
 * @Package: cn.mlynn.androidfun.module.treetab
 * @ClassName: TreeTabViewModel
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/7/30 21:24
 */
public class TreeTabViewModel extends BaseViewModel {

    private TreeTabRepository repository;

    @ViewModelInject
    public TreeTabViewModel(@Assisted SavedStateHandle savedStateHandle, TreeTabRepository repository) {
        super(savedStateHandle);
        this.repository = repository;
    }
}
