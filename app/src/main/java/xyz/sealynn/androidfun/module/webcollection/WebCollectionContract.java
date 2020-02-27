package xyz.sealynn.androidfun.module.webcollection;

import java.util.List;

import xyz.sealynn.androidfun.base.BasePresenter;
import xyz.sealynn.androidfun.base.BaseView;
import xyz.sealynn.androidfun.model.WebCollection;

/**
 * Created by MichaelLynn on 2019/4/19 16:35
 * <p>
 * Email: sealynndev@gamil.com
 */
class WebCollectionContract {

    interface View extends BaseView {
        void setUpChipGroup(List<WebCollection> collectionList);
        void setRefreshing(Boolean isRefreshing);
    }

    interface Presenter extends BasePresenter<View> {
        void getWebs();
    }
}
