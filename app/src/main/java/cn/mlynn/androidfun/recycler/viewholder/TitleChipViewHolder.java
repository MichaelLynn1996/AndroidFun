/**
 * @ProjectName: AndroidFun
 * @Package: cn.mlynn.androidfun.recycler.viewholder
 * @ClassName: TreeViewHolder
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/7/10 20:55
 */
package cn.mlynn.androidfun.recycler.viewholder;

import androidx.annotation.NonNull;

import com.google.android.material.chip.Chip;

import java.util.List;

import cn.mlynn.androidfun.base.BaseViewHolder;
import cn.mlynn.androidfun.databinding.ItemTitleChipBinding;

public class TitleChipViewHolder extends BaseViewHolder<ItemTitleChipBinding, List<Chip>> {

    public TitleChipViewHolder(@NonNull ItemTitleChipBinding binding) {
        super(binding);
    }

    public TitleChipViewHolder(@NonNull ItemTitleChipBinding binding, int title) {
        super(binding);
        getBinding().title.setText(title);
    }

    public void bind(List<Chip> data, String title) {
        getBinding().title.setText(title);
        bind(data);
    }

    @Override
    public void bind(List<Chip> data) {
        getBinding().chips.removeAllViews();
        for (Chip c : data)
            getBinding().chips.addView(c);
    }
}
