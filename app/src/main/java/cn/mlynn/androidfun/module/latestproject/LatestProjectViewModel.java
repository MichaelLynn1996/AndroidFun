package cn.mlynn.androidfun.module.latestproject;

import androidx.hilt.Assisted;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.paging.PagingData;

import cn.mlynn.androidfun.base.BaseViewModel;
import cn.mlynn.androidfun.model.wan.Article;

/**
 * @ProjectName: AndroidFun
 * @Package: cn.mlynn.androidfun.module.latestproject
 * @ClassName: LatestProjectViewModel
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/8/31 11:41
 */
public class LatestProjectViewModel extends BaseViewModel {

    private LatestProjectRepository repository;

    private LiveData<PagingData<Article>> projectLiveData;

    @ViewModelInject
    public LatestProjectViewModel(LatestProjectRepository repository, @Assisted SavedStateHandle savedStateHandle) {
        super(savedStateHandle);
        this.repository = repository;
    }

    public LiveData<PagingData<Article>> getProjectLiveData() {
        return projectLiveData;
    }

    public void initProjectLiveData(Lifecycle lifecycle) {
        projectLiveData = repository.initProjectLiveData(lifecycle);
    }
}
