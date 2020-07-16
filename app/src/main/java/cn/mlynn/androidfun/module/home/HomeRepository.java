/**
 * @ProjectName: AndroidFun
 * @Package: xyz.sealynn.androidfun.respository
 * @ClassName: BannerRepository
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/6/8 8:28
 */
package cn.mlynn.androidfun.module.home;

import androidx.lifecycle.Lifecycle;

import java.util.List;

import cn.mlynn.androidfun.base.BaseRepository;
import cn.mlynn.androidfun.model.wan.Article;
import cn.mlynn.androidfun.model.wan.Result;
import cn.mlynn.androidfun.model.wan.Banner;
import cn.mlynn.androidfun.net.RetrofitManager;

public class HomeRepository extends BaseRepository {

    public void queryBanners(Lifecycle lifecycle, QueryCallBack<Result<List<Banner>>> callBack) {
        query(lifecycle, RetrofitManager.getInstance().createReq().getBanner(), callBack);
//        RetrofitManager.getInstance().createReq().getBanner();
    }

    public void queryTopArticles(Lifecycle lifecycle,QueryCallBack<Result<List<Article>>> callBack) {
        query(lifecycle, RetrofitManager.getInstance().createReq().getTopArticle(), callBack);
    }
}
