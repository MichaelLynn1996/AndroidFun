package xyz.sealynn.androidfun.module.articlecollection;

import java.util.List;

import xyz.sealynn.androidfun.base.BasePresenter;
import xyz.sealynn.androidfun.base.BaseView;
import xyz.sealynn.androidfun.model.ArticleCollection;

/**
 * Created by MichaelLynn on 2019/4/19 16:28
 * <p>
 * Email: sealynndev@gamil.com
 */
public interface ArticleCollectionContract {

    interface View extends BaseView {
        void refreshList(List<ArticleCollection> data);
    }

    interface Presenter extends BasePresenter<View> {
        void getArticleCollectionList(int page);
    }
}
