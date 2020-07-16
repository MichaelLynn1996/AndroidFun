/**
 * @ProjectName: AndroidFun
 * @Package: cn.mlynn.androidfun.recycler.viewholder
 * @ClassName: UserInformationViewHolder
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/7/16 22:40
 */
package cn.mlynn.androidfun.recycler.viewholder;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;

import cn.mlynn.androidfun.R;
import cn.mlynn.androidfun.base.BaseViewHolder;
import cn.mlynn.androidfun.databinding.ItemIconTwolineBinding;
import cn.mlynn.androidfun.model.wan.User;

public class UserInformationViewHolder extends BaseViewHolder<ItemIconTwolineBinding, User> {
    public UserInformationViewHolder(@NonNull ItemIconTwolineBinding binding) {
        super(binding);
    }

    @Override
    public void bind(User data) {
        getBinding().getRoot().setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(R.id.signActivity);
        });
    }
}
