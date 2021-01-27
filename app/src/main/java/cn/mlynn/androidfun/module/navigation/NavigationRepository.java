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

import java.util.List;

import javax.inject.Inject;

import cn.mlynn.androidfun.base.BaseRepository;
import cn.mlynn.androidfun.model.wan.NavigationRoot;
import cn.mlynn.androidfun.model.wan.Result;
import cn.mlynn.androidfun.net.RequestApi;

public class NavigationRepository extends BaseRepository {

    @Inject
    public NavigationRepository(RequestApi requestApi) {
        super(requestApi);
    }

    public void queryNavigation(Lifecycle lifecycle, QueryCallBack<Result<List<NavigationRoot>>> callBack) {
        query(lifecycle, getRequestApi().getNavigation(), callBack);
    }
}
