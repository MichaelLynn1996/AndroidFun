/**
 * @ProjectName: AndroidFun
 * @Package: cn.mlynn.androidfun.module.navigation
 * @ClassName: NavigationViewModel
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/7/5 0:32
 */
package cn.mlynn.androidfun.module.navigation;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LiveData;

import java.util.List;

import cn.mlynn.androidfun.base.BaseViewModel;
import cn.mlynn.androidfun.model.wan.NavigationRoot;

public class NavigationViewModel extends BaseViewModel {

    private NavigationRepository repository = new NavigationRepository();

    private LiveData<List<NavigationRoot>> navigationLiveData = repository.getNavigationLiveData();

    private boolean isFirstLoaded = false;

    public LiveData<List<NavigationRoot>> getNavigationLiveData() {
        return navigationLiveData;
    }

    public void loadNavigation(Lifecycle lifecycle) {
        repository.queryNavigation(lifecycle, new ViewControlHelper());
    }

    public boolean isFirstLoaded() {
        return isFirstLoaded;
    }

    public void setFirstLoaded(boolean firstLoaded) {
        isFirstLoaded = firstLoaded;
    }
}
