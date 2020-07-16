/**
 * @ProjectName: AndroidFun
 * @Package: xyz.sealynn.androidfun.base2
 * @ClassName: ExBaseFragment
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/6/2 20:06
 */
package cn.mlynn.androidfun.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;

import cn.mlynn.androidfun.R;

public abstract class ExBaseFragment<VB extends ViewBinding> extends Fragment {

    /**
     * ViewBinding
     */
    private VB binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = initBinding(inflater, container);
        initToolbar(binding.getRoot());
        init(savedInstanceState);
        return binding.getRoot();
    }

    protected abstract void init(Bundle savedInstanceState);

    protected abstract VB initBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container);

    /**
     * 初始化Toolbar
     *
     * @param root Fragment的视图
     */
    private void initToolbar(View root) {
        if (root.findViewById(R.id.toolbar) != null) {
//            mToolbar = root.findViewById(R.id.toolbar);
            if (getActivity() != null)
                ((AppCompatActivity) getActivity()).setSupportActionBar(root.findViewById(R.id.toolbar));
        }
    }

    public VB getBinding() {
        return binding;
    }

    @Override
    public void onDestroyView() {
        if (binding.getRoot().findViewById(R.id.toolbar) != null) {
//            mToolbar = root.findViewById(R.id.toolbar);
            if (getActivity() != null)
                ((AppCompatActivity) getActivity()).setSupportActionBar(null);
        }
        binding = null;
        super.onDestroyView();
    }
}
