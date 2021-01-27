package cn.mlynn.androidfun.module.treetab;

import javax.inject.Inject;

import cn.mlynn.androidfun.base.BaseRepository;
import cn.mlynn.androidfun.net.RequestApi;

/**
 * @ProjectName: AndroidFun
 * @Package: cn.mlynn.androidfun.module.treetab
 * @ClassName: TreeTabRepository
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/7/30 21:25
 */
public class TreeTabRepository extends BaseRepository {

    @Inject
    public TreeTabRepository(RequestApi requestApi) {
        super(requestApi);
    }
}
