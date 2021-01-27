/**
 * @ProjectName: AndroidFun
 * @Package: cn.mlynn.androidfun.respository
 * @ClassName: TreeRepository
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/7/10 21:06
 */
package cn.mlynn.androidfun.module.tree;

import androidx.lifecycle.Lifecycle;

import java.util.List;

import javax.inject.Inject;

import cn.mlynn.androidfun.base.BaseRepository;
import cn.mlynn.androidfun.model.wan.Chapter;
import cn.mlynn.androidfun.model.wan.Result;
import cn.mlynn.androidfun.net.RequestApi;

public class TreeRepository extends BaseRepository {

    @Inject
    public TreeRepository(RequestApi requestApi) {
        super(requestApi);
    }

    public void queryTree(Lifecycle lifecycle, QueryCallBack<Result<List<Chapter>>> callBack) {
        query(lifecycle, getRequestApi().getTree(), callBack);
    }
}
