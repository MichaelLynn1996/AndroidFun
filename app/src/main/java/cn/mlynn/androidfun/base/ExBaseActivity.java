/**
 * @ProjectName: AndroidFun
 * @Package: cn.mlynn.androidfun.base2
 * @ClassName: ExBaseActivity
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/7/5 0:48
 */
package cn.mlynn.androidfun.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

import cn.mlynn.androidfun.R;

public abstract class ExBaseActivity<VB extends ViewBinding> extends AppCompatActivity {

    /**
     * ViewBinding
     */
    private VB binding;

    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = initBinding();
        setContentView(binding.getRoot());
        initToolbar();
        init(savedInstanceState);
    }

    protected abstract void init(Bundle savedInstanceState);

    protected abstract VB initBinding();

    /**
     * 初始化Toolbar
     */
    private void initToolbar() {
        if (findViewById(R.id.toolbar) != null) {
            setSupportActionBar(findViewById(R.id.toolbar));
        }
    }

    public VB getBinding() {
        return binding;
    }
}
