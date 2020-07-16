package cn.mlynn.androidfun.module.setting;

import android.content.Context;

import com.danielstone.materialaboutlibrary.MaterialAboutFragment;
import com.danielstone.materialaboutlibrary.items.MaterialAboutActionItem;
import com.danielstone.materialaboutlibrary.model.MaterialAboutCard;
import com.danielstone.materialaboutlibrary.model.MaterialAboutList;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;

import cn.mlynn.androidfun.R;
import cn.mlynn.androidfun.module.setting.OpenSourceLicenceFragment;
import cn.mlynn.androidfun.utils.ActivityUtils;
import cn.mlynn.androidfun.utils.AppUtils;
import cn.mlynn.androidfun.utils.SharedUtils;

/**
 * Created by SeaLynn0 on 2018/10/13 2:54
 * <p>
 * Email：sealynndev@gmail.com
 */
public class AboutFragment extends MaterialAboutFragment {
    @NonNull
    @Override
    protected MaterialAboutList getMaterialAboutList(@NonNull Context context) {
        MaterialAboutCard author = new MaterialAboutCard.Builder()
                // Configure card here
                .title(R.string.author)
                .addItem(new MaterialAboutActionItem.Builder()
                        .text("Michael Lynn")
                        .subText("SeaLynn0")
                        .icon(R.drawable.ic_action_person)
                        .setOnClickAction(() -> {
                            ActivityUtils.startWebActivity(requireContext()
                                    , "https://github.com/SeaLynn0/AndroidFun");
                        })
                        .build())
                .addItem(new MaterialAboutActionItem.Builder()
                        .text(R.string.open_in_github)
                        .icon(R.drawable.ic_action_github)
                        .setOnClickAction(() -> {
                            ActivityUtils.startWebActivity(requireContext()
                                    , "https://github.com/SeaLynn0/AndroidFun");
                        })
                        .build())
                .addItem(new MaterialAboutActionItem.Builder()
                        .text(R.string.mail_to_develop)
                        .subText("sealynndev@gamil.com")
                        .icon(R.drawable.ic_action_email)
                        .setOnClickAction(() ->
                                SharedUtils.mailToDeveloper(requireContext(), "", ""))
                        .build())
                .build();

        MaterialAboutCard androidFun = new MaterialAboutCard.Builder()
                // Configure card here
                .title(R.string.about)
                .addItem(new MaterialAboutActionItem.Builder()
                        .text(R.string.current_version)
                        .subText(AppUtils.getVersionCode(requireContext()) + "(" +
                                AppUtils.getVersionName(requireContext()) + ")")
                        .icon(R.drawable.ic_action_version)
                        .setOnClickAction(() -> {
                            //TODO:检查更新
                        })
                        .build())
                .addItem(new MaterialAboutActionItem.Builder()
                        .text(R.string.comment_for_app)
                        .icon(R.drawable.ic_action_comment)
                        .setOnClickAction(() -> SharedUtils.goToMarket(requireContext()))
                        .build())
                .addItem(new MaterialAboutActionItem.Builder()
                        .text(R.string.feedback)
                        .icon(R.drawable.ic_action_feedback)
                        .setOnClickAction(() -> SharedUtils.mailToDeveloper(requireContext(), "【反馈】", ""))
                        .build())
                .addItem(new MaterialAboutActionItem.Builder()
                        .text(R.string.open_source_licence)
                        .icon(R.drawable.ic_action_github)
                        .setOnClickAction(() ->
                                Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigate(R.id.openSourceLicenceFragment))
                        .build())
                .build();

        MaterialAboutCard icon = new MaterialAboutCard.Builder()
                .title("LOGO")
                .addItem(new MaterialAboutActionItem.Builder()
                        .text("material.io")
                        .icon(R.drawable.ic_action_google)
                        .setOnClickAction(() -> {
                            ActivityUtils.startWebActivity(requireContext()
                                    , "https://material.io/tools/icons/?style=baseline");
                        })
                        .build())
                .addItem(new MaterialAboutActionItem.Builder()
                        .text("iconfont")
                        .icon(R.drawable.ic_action_alibaba)
                        .setOnClickAction(() -> {
                            ActivityUtils.startWebActivity(requireContext()
                                    , "https://www.iconfont.cn/");
                        })
                        .build())
                .build();
        return new MaterialAboutList.Builder()
                .addCard(androidFun)
                .addCard(author)
                .addCard(icon)
                .build();
    }

//    @Nullable
//    @Override
//    protected CharSequence getActivityTitle() {
//        return getString(R.string.app_name);
//    }


}
