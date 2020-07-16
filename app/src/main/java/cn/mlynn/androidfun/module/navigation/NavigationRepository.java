/**
 * @ProjectName: AndroidFun
 * @Package: cn.mlynn.androidfun.respository
 * @ClassName: NavigationRepository
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/7/10 18:44
 */
package cn.mlynn.androidfun.module.navigation;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LiveData;

import java.util.List;

import cn.mlynn.androidfun.APP;
import cn.mlynn.androidfun.base.BaseRepository;
import cn.mlynn.androidfun.dao.NavigationDao;
import cn.mlynn.androidfun.model.wan.NavigationRoot;
import cn.mlynn.androidfun.model.wan.Result;
import cn.mlynn.androidfun.net.RetrofitManager;
import io.reactivex.rxjava3.annotations.NonNull;

public class NavigationRepository extends BaseRepository {

    private NavigationDao dao = APP.getDataBase().getNavigationDao();

    public void queryNavigation(Lifecycle lifecycle, NavigationViewModel.ViewControlHelper helper) {
        query(lifecycle, RetrofitManager.getInstance().createReq().getNavigation(), new QueryCallBack<Result<List<NavigationRoot>>>() {
            @Override
            public void onSuccess(Result<List<NavigationRoot>> entity) {
                if (helper.isLoadSuccess(entity.getErrorCode())) {
                    getExecutor().execute(() -> {
                        dao.deleteAllNavigation();
                        dao.insertNavigation(entity.getData());
                    });
                    helper.dismissLoading();
                } else helper.onLoadFailed(entity.getErrorMsg());
            }

            @Override
            public void onFailure(@NonNull Throwable e) {
                helper.onLoadFailed(e);
            }
        });
    }

    public LiveData<List<NavigationRoot>> getNavigationLiveData() {
        return dao.loadAllNavigation();
    }
}
