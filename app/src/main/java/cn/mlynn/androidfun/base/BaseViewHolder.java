/**
 * @ProjectName: AndroidFun
 * @Package: xyz.sealynn.androidfun.recycler
 * @ClassName: ViewHolder
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/6/7 11:00
 */
package cn.mlynn.androidfun.base;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

public abstract class BaseViewHolder<VB extends ViewBinding, D> extends RecyclerView.ViewHolder {

    private VB binding;

    public abstract void bind(D data);

    public BaseViewHolder(@NonNull VB binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public VB getBinding() {
        return binding;
    }
}
