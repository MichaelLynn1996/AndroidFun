package xyz.sealynn.androidfun.module.knowledgetree;

import android.os.Bundle;
import android.view.View;

import xyz.sealynn.androidfun.R;
import xyz.sealynn.androidfun.base.BaseFragment;
import xyz.sealynn.androidfun.module.BaseMainFragment;

/**
 * Created by SeaLynn0 on 2018/12/6 20:05
 * <p>
 * Email：sealynndev@gmail.com
 */
public class KnowledgeTreeFragment extends BaseMainFragment<KnowledgeTreeContarct.Presenter> implements KnowledgeTreeContarct.View {

    @Override
    protected KnowledgeTreeContarct.Presenter createPresenter() {
        return new KnowledgeTreePresenter(this);
    }

    @Override
    protected void prepareData(Bundle savedInstanceState) {

    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_knowledge_tree;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected int getTitle() {
        return R.string.knowledge_tree;
    }

    @Override
    protected void initEvent() {

    }
}
