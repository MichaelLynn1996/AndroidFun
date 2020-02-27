package xyz.sealynn.androidfun.module.webcollection;

import java.util.List;

import xyz.sealynn.androidfun.base.BasePresenterImpl;
import xyz.sealynn.androidfun.model.Result;
import xyz.sealynn.androidfun.model.WebCollection;
import xyz.sealynn.androidfun.net.RequestApi;
import xyz.sealynn.androidfun.net.RetrofitManager;
import xyz.sealynn.androidfun.utils.ToastUtils;

/**
 * Created by MichaelLynn on 2019/4/19 16:36
 * <p>
 * Email: sealynndev@gamil.com
 */
public class WebCollectionPresenter extends BasePresenterImpl<WebCollectionContract.View> implements WebCollectionContract.Presenter {


    WebCollectionPresenter(WebCollectionContract.View view) {
        super(view);
    }

    @Override
    public void onResponse(Object t, int what) {
        if (what == 1) {
            Result<List<WebCollection>> requestBody = Result.cast(t);
            if (requestBody.getErrorCode() == RequestApi.SUCC)
                getView().setUpChipGroup(requestBody.getData());
            else ToastUtils.shortToast(getView().getContext(), requestBody.getErrorMsg());
            getView().setRefreshing(false);
        }
    }

    @Override
    public void getWebs() {
        domine(RetrofitManager.getInstance().createReq().getWebCollection(), 1);
    }
}
