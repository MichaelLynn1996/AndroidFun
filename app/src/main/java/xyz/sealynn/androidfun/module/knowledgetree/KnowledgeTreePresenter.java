package xyz.sealynn.androidfun.module.knowledgetree;

import xyz.sealynn.androidfun.base.BasePresenterImpl;

/**
 * Created by SeaLynn0 on 2018/12/6 20:03
 * <p>
 * Email：sealynndev@gmail.com
 */
public class KnowledgeTreePresenter extends BasePresenterImpl implements KnowledgeTreeContarct.Presenter {

    private final KnowledgeTreeContarct.View mView;
    public KnowledgeTreePresenter(KnowledgeTreeContarct.View view) {
        mView = view;
        this.mView.setPresenter(this);
    }
}
