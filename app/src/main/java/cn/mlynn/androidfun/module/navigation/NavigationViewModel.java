/**
 * @ProjectName: AndroidFun
 * @Package: cn.mlynn.androidfun.module.navigation
 * @ClassName: NavigationViewModel
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/7/5 0:32
 */
package cn.mlynn.androidfun.module.navigation;

import androidx.hilt.Assisted;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;

import java.util.List;

import cn.mlynn.androidfun.base.BaseRepository;
import cn.mlynn.androidfun.base.BaseViewModel;
import cn.mlynn.androidfun.model.wan.NavigationRoot;
import cn.mlynn.androidfun.model.wan.Result;
import io.reactivex.rxjava3.annotations.NonNull;

public class NavigationViewModel extends BaseViewModel {

    private NavigationRepository repository;

    private MutableLiveData<List<NavigationRoot>> navigationLiveData;

    @ViewModelInject
    public NavigationViewModel(NavigationRepository repository, @Assisted SavedStateHandle savedStateHandle) {
        super(savedStateHandle);
        this.repository = repository;
        navigationLiveData  = new MutableLiveData<>();
    }

    public MutableLiveData<List<NavigationRoot>> getNavigationLiveData() {
        return navigationLiveData;
    }

    public void loadNavigation(Lifecycle lifecycle) {
        repository.queryNavigation(lifecycle, new BaseRepository.QueryCallBack<Result<List<NavigationRoot>>>() {
            @Override
            public void onSuccess(Result<List<NavigationRoot>> entity) {
                if (getViewControlHelper().isLoadSuccess(entity.getErrorCode())) {
                    navigationLiveData.setValue(entity.getData());
                    getViewControlHelper().dismissLoading();
                } else getViewControlHelper().onLoadFailed(entity.getErrorMsg());
            }

            @Override
            public void onFailure(@NonNull Throwable e) {
                getViewControlHelper().onLoadFailed(e);
            }
        });
    }
}
