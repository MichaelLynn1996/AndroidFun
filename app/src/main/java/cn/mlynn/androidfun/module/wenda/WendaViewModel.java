/**
 * @ProjectName: AndroidFun
 * @Package: cn.mlynn.androidfun.module.wenda
 * @ClassName: WendaViewModel
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/7/9 21:32
 */
package cn.mlynn.androidfun.module.wenda;

import androidx.hilt.Assisted;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.PagingLiveData;

import cn.mlynn.androidfun.base.BaseViewModel;
import cn.mlynn.androidfun.model.wan.Article;

public class WendaViewModel extends BaseViewModel {

    WendaRepository repository;

    private LiveData<PagingData<Article>> wendaLiveData;

    @ViewModelInject
    public WendaViewModel(WendaRepository repository, @Assisted SavedStateHandle savedStateHandle) {
        super(savedStateHandle);
        this.repository = repository;
    }

    public LiveData<PagingData<Article>> getWendaLiveData() {
        return wendaLiveData;
    }

    public void initWendaLiveData(Lifecycle lifecycle) {
        wendaLiveData = repository.initWendaLiveData(lifecycle);
    }
}
