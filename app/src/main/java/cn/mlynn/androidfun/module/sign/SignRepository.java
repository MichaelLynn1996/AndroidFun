/**
 * @ProjectName: AndroidFun
 * @Package: cn.mlynn.androidfun.module.sign
 * @ClassName: SignRepository
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/7/16 23:36
 */
package cn.mlynn.androidfun.module.sign;

import androidx.lifecycle.Lifecycle;

import javax.inject.Inject;

import cn.mlynn.androidfun.base.BaseRepository;
import cn.mlynn.androidfun.model.wan.Result;
import cn.mlynn.androidfun.model.wan.User;
import cn.mlynn.androidfun.net.RequestApi;

public class SignRepository extends BaseRepository {

    @Inject
    public SignRepository(RequestApi requestApi) {
        super(requestApi);
    }

    public void login(Lifecycle lifecycle, String u, String p, QueryCallBack<Result<User>> callBack) {
        query(lifecycle, getRequestApi().login(u, p), callBack);
    }
}
