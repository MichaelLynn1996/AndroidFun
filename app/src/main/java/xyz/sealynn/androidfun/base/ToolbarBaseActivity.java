package xyz.sealynn.androidfun.base;

import androidx.appcompat.widget.Toolbar;
import xyz.sealynn.androidfun.R;

/**
 * Created by SeaLynn0 on 2019/3/6 13:10
 * <p>
 * Email：sealynndev@gmail.com
 */
public abstract class ToolbarBaseActivity<P extends BasePresenter> extends BaseActivity<P> {

    private Toolbar mToolbar;

    /**
     * 初始化Toolbar
     */
    @Override
    protected void initToolbar() {
        if (findViewById(R.id.toolbar) != null) {
            mToolbar = findViewById(R.id.toolbar);
            setSupportActionBar(mToolbar);
        }
    }

    protected Toolbar getToolbar() {
        return mToolbar;
    }
}
