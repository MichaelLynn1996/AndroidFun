package xyz.sealynn.androidfun.module.wxlist;

import androidx.lifecycle.LifecycleOwner;
import androidx.paging.PagedList;
import androidx.paging.RxPagedListBuilder;

import io.reactivex.Observable;
import xyz.sealynn.androidfun.base.BasePresenterImpl;
import xyz.sealynn.androidfun.dao.WxDao;
import xyz.sealynn.androidfun.model.Result;
import xyz.sealynn.androidfun.model.wxarticle.WxActicle;
import xyz.sealynn.androidfun.net.RequestApi;
import xyz.sealynn.androidfun.net.RetrofitManager;
import xyz.sealynn.androidfun.utils.ToastUtils;

/**
 * Created by MichaelLynn on 2020/2/4 0:12
 * <p>
 * Email: sealynndev@gamil.com
 */
public class WxListPresenter extends BasePresenterImpl<WxListContract.View> implements WxListContract.Presenter {

    private WxDao wxDao;
    private final Observable<PagedList<WxActicle>> wxActicle;

    private static final int GET_WX_CHAPTER_ARTICLES = 0;

    WxListPresenter(WxListContract.View view) {
        super(view);
        wxActicle = new RxPagedListBuilder<>(wxDao.queryWxActicleByPage(), 1).buildObservable();
    }

    @Override
    public void onResponse(Object t, int what) {
        if (what == GET_WX_CHAPTER_ARTICLES) {
            Result<WxActicle> requestBody = Result.cast(t);
            if (requestBody.getErrorCode() == RequestApi.SUCC) {
                getView().loadDatas(requestBody.getData().getDatas());
            } else {
                ToastUtils.shortToast(getView().getContext(), requestBody.getErrorMsg());
            }
            getView().setLoading(false);
        }
    }

    @Override
    public void ChapterArticles(int id, int page) {
        getView().setLoading(true);
        domine(RetrofitManager.getInstance().createReq().getChapterArticles(id, page), GET_WX_CHAPTER_ARTICLES);
    }

    @Override
    public void onStart(LifecycleOwner owner) {
        super.onStart(owner);
    }
}