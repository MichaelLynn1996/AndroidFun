package cn.mlynn.androidfun.module.mine;

import androidx.hilt.Assisted;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.SavedStateHandle;

import cn.mlynn.androidfun.base.BaseViewModel;

public class MineViewModel extends BaseViewModel {

    MineRepository repository;

    @ViewModelInject
    public MineViewModel(MineRepository repository, @Assisted SavedStateHandle savedStateHandle) {
        super(savedStateHandle);
        this.repository = repository;
    }
}
