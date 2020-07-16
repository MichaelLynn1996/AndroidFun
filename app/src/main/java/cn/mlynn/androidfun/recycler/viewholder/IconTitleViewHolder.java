/**
 * @ProjectName: AndroidFun
 * @Package: cn.mlynn.androidfun.recycler.viewholder
 * @ClassName: IconTitleViewHolder
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/7/12 0:18
 */
package cn.mlynn.androidfun.recycler.viewholder;

import android.view.View;

import androidx.annotation.NonNull;

import cn.mlynn.androidfun.base.BaseViewHolder;
import cn.mlynn.androidfun.databinding.ItemIconTitleBinding;

public class IconTitleViewHolder extends BaseViewHolder<ItemIconTitleBinding, IconTitleViewHolder.Data> {

    private onItemClickListener listener;

    public IconTitleViewHolder(@NonNull ItemIconTitleBinding binding) {
        super(binding);
    }

    public IconTitleViewHolder(@NonNull ItemIconTitleBinding binding, onItemClickListener listener) {
        super(binding);
        this.listener = listener;
    }

    @Override
    public void bind(Data data) {
        getBinding().title.setText(data.title);
        getBinding().icon.setImageResource(data.getIcon());
        getBinding().getRoot().setOnClickListener(v -> {
            if (listener != null)
                listener.onItemClick(v, data);
        });
    }

    public onItemClickListener getOnItemClickListener() {
        return listener;
    }

    public void setOnItemClickListener(onItemClickListener listener) {
        this.listener = listener;
    }

    public static class Data {

        private int icon;
        private int title;

        public Data(int icon, int title) {
            this.icon = icon;
            this.title = title;
        }

        public int getIcon() {
            return icon;
        }

        public void setIcon(int icon) {
            this.icon = icon;
        }

        public int getTitle() {
            return title;
        }

        public void setTitle(int title) {
            this.title = title;
        }
    }

    public interface onItemClickListener {
        void onItemClick(View view, Data data);
    }
}
