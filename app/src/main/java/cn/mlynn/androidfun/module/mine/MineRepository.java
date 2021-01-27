package cn.mlynn.androidfun.module.mine;

import javax.inject.Inject;

import cn.mlynn.androidfun.base.BaseRepository;
import cn.mlynn.androidfun.net.RequestApi;
/**
 * @ProjectName: AndroidFun
 * @Package: cn.mlynn.androidfun.module.mine
 * @ClassName: MineRepository
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/7/25 0:06
 */
public class MineRepository extends BaseRepository {

    @Inject
    public MineRepository(RequestApi requestApi) {
        super(requestApi);
    }
}
