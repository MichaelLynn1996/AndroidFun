/**
 * @ProjectName: AndroidFun
 * @Package: xyz.sealynn.androidfun.base2
 * @ClassName: ExBaseFragment
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/6/2 20:06
 */
package cn.mlynn.androidfun.base;

import android.content.Context;
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
import timber.log.Timber;

public abstract class ExBaseFragment<VB extends ViewBinding> extends Fragment {

    /**
     * ViewBinding
     */
    private VB binding;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Timber.d("%s%s",this.toString(), "onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.d("%s%s",this.toString(), "onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = initBinding(inflater, container);
        initToolbar(binding.getRoot());
        initView(savedInstanceState);
        Timber.d("%s%s",this.toString(), "onCreateView");
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Timber.d("%s%s",this.toString(), "onActivityCreated");
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Timber.d("%s%s",this.toString(), "onViewCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Timber.d("%s%s",this.toString(), "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Timber.d("%s%s",this.toString(), "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Timber.d("%s%s",this.toString(), "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Timber.d("%s%s",this.toString(), "onStop");
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
        Timber.d("%s%s",this.toString(), "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Timber.d("%s%s",this.toString(), "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Timber.d("%s%s", this.toString(), "onDestroy");
    }

    /**
     * 初始化视图View的操作，不包括数据的操作
     *
     * @param savedInstanceState savedInstanceState
     */
    protected abstract void initView(Bundle savedInstanceState);

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


}
