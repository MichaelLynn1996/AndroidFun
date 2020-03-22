package xyz.sealynn.androidfun.module;

import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.viewbinding.ViewBinding;

import com.orhanobut.logger.Logger;

import xyz.sealynn.androidfun.R;
import xyz.sealynn.androidfun.base.BaseFragment;
import xyz.sealynn.androidfun.base.BasePresenter;

/**
 * Created by MichaelLynn on 2020/1/2 11:46
 * <p>
 * Email: sealynndev@gamil.com
 */
public abstract class BaseMainFragment<P extends BasePresenter, B extends ViewBinding> extends BaseFragment<P, B> {

    private DrawerLayout drawer;
    private ActionBar actionBar;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void initView(View rootView) {
//            mToolbar = root.findViewById(R.id.toolbar);
        Logger.d(getActivity() != null);
        if (getActivity() != null) {
            //获取抽屉
            Logger.d(getActivity().findViewById(R.id.drawer_layout) != null);
            if (getActivity().findViewById(R.id.drawer_layout) != null)
                drawer = ((AppCompatActivity) getActivity()).findViewById(R.id.drawer_layout);

            //设置ActionBar
            if (((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
                actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
                if (actionBar != null)
                    actionBar.setTitle(getTitle());
            }
            toggle = new ActionBarDrawerToggle(
                    getActivity(), drawer, rootView.findViewById(R.id.toolbar)
                    , R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.addDrawerListener(toggle);
            toggle.syncState();
        }
    }

    protected abstract int getTitle();

    public ActionBar getActionBar() {
        return actionBar;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        drawer.removeDrawerListener(toggle);    // 解除drawer对toggle的引用，避免内存泄漏
    }
}
