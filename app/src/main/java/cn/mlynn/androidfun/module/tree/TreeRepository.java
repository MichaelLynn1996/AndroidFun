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
import androidx.lifecycle.LiveData;

import java.util.List;

import cn.mlynn.androidfun.APP;
import cn.mlynn.androidfun.base.BaseRepository;
import cn.mlynn.androidfun.dao.ChildrenDao;
import cn.mlynn.androidfun.model.wan.Children;
import cn.mlynn.androidfun.model.wan.Result;
import cn.mlynn.androidfun.net.RetrofitManager;
import io.reactivex.rxjava3.annotations.NonNull;

public class TreeRepository extends BaseRepository {

    private ChildrenDao dao = APP.getDataBase().getChildrenDao();

    public void queryTree(Lifecycle lifecycle, QueryCallBack<Result<List<Children>>> callBack) {
        query(lifecycle, RetrofitManager.getInstance().createReq().getTree(), callBack);
    }

    public void queryTree(Lifecycle lifecycle, TreeViewModel.ViewControlHelper helper) {
        query(lifecycle, RetrofitManager.getInstance().createReq().getTree(), new QueryCallBack<Result<List<Children>>>() {
            @Override
            public void onSuccess(Result<List<Children>> entity) {
                if (helper.isLoadSuccess(entity.getErrorCode())) {
                    getExecutor().execute(() -> {
                        dao.deleteAllChildren();
                        dao.insertChildren(entity.getData());
                    });
                    helper.dismissLoading();
                }else   helper.onLoadFailed(entity.getErrorMsg());
            }

            @Override
            public void onFailure(@NonNull Throwable e) {
                helper.onLoadFailed(e);
            }
        });
    }

    LiveData<List<Children>> getChildrenLiveData() {
        return dao.loadAllChildren();
    }
}
