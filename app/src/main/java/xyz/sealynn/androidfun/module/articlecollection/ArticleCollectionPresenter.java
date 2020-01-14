package xyz.sealynn.androidfun.module.articlecollection;

import android.content.Context;

import java.util.List;

import xyz.sealynn.androidfun.base.BasePresenterImpl;
import xyz.sealynn.androidfun.base.Constants;
import xyz.sealynn.androidfun.model.ArticleCollection;
import xyz.sealynn.androidfun.model.Result;
import xyz.sealynn.androidfun.net.RequestApi;
import xyz.sealynn.androidfun.net.RetrofitManager;
import xyz.sealynn.androidfun.net.interceptor.SaveCookiesInterceptor;

/**
 * Created by MichaelLynn on 2019/4/19 16:30
 * <p>
 * Email: sealynndev@gamil.com
 */
public class ArticleCollectionPresenter extends BasePresenterImpl<ArticleCollectionContract.View> implements ArticleCollectionContract.Presenter {

    private static final int GET_ARTICLE = 0;

    public ArticleCollectionPresenter(ArticleCollectionContract.View view) {
        super(view);
    }

    @Override
    public void onResponse(Object t, int what) {
        switch (what){
            case GET_ARTICLE:
                Result<List<ArticleCollection>> requestBody = Result.cast(t);
                if (requestBody.getErrorCode() == RequestApi.SUCC){
                    getView().refreshList(requestBody.getData());
                }else if (requestBody.getErrorCode() == RequestApi.LOGIN_EXPIRE){
                    getView().getContext()
                            .getSharedPreferences(Constants.CONFIG_DEFAULT, Context.MODE_PRIVATE)
                            .edit().remove("login").apply();
                    SaveCookiesInterceptor.clearCookie(getView().getContext());
                }
                break;
        }
    }

    @Override
    public void getArticleCollectionList(int page) {
        domine(RetrofitManager.getInstance().createReq().getArticleCollection(page),GET_ARTICLE);
    }
}
