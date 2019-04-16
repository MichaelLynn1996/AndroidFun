package xyz.sealynn.androidfun.module;

import android.content.Context;
import android.content.Intent;

import com.danielstone.materialaboutlibrary.MaterialAboutActivity;
import com.danielstone.materialaboutlibrary.items.MaterialAboutActionItem;
import com.danielstone.materialaboutlibrary.model.MaterialAboutCard;
import com.danielstone.materialaboutlibrary.model.MaterialAboutList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import xyz.sealynn.androidfun.R;
import xyz.sealynn.androidfun.module.web.WebActivity;
import xyz.sealynn.androidfun.utils.ActivityUtils;
import xyz.sealynn.androidfun.utils.AppUtils;
import xyz.sealynn.androidfun.utils.SharedUtils;

/**
 * Created by SeaLynn0 on 2018/10/13 2:54
 * <p>
 * Email：sealynndev@gmail.com
 */
public class AboutActivity extends MaterialAboutActivity {
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
                            Intent intent = new Intent(AboutActivity.this, WebActivity.class);
                            intent.putExtra("url", "https://github.com/SeaLynn0");
                            startActivity(intent);
                        })
                        .build())
                .addItem(new MaterialAboutActionItem.Builder()
                        .text(R.string.open_in_github)
                        .icon(R.drawable.ic_action_github)
                        .setOnClickAction(() -> {
                            Intent intent = new Intent(AboutActivity.this, WebActivity.class);
                            intent.putExtra("url", "https://github.com/SeaLynn0/AndroidFun");
                            startActivity(intent);
                        })
                        .build())
                .addItem(new MaterialAboutActionItem.Builder()
                        .text(R.string.mail_to_develop)
                        .subText("sealynndev@gamil.com")
                        .icon(R.drawable.ic_action_email)
                        .setOnClickAction(() -> SharedUtils.mailToDeveloper(AboutActivity.this, "", ""))
                        .build())
                .build();

        MaterialAboutCard androidFun = new MaterialAboutCard.Builder()
                // Configure card here
                .title(R.string.about)
                .addItem(new MaterialAboutActionItem.Builder()
                        .text(R.string.current_version)
                        .subText(AppUtils.getVersionCode(AboutActivity.this) + "(" +
                                AppUtils.getVersionName(AboutActivity.this) + ")")
                        .icon(R.drawable.ic_action_version)
                        .setOnClickAction(() -> {
                            //TODO:检查更新
                        })
                        .build())
                .addItem(new MaterialAboutActionItem.Builder()
                        .text(R.string.comment_for_app)
                        .icon(R.drawable.ic_action_comment)
                        .setOnClickAction(() -> SharedUtils.goToMarket(AboutActivity.this))
                        .build())
                .addItem(new MaterialAboutActionItem.Builder()
                        .text(R.string.feedback)
                        .icon(R.drawable.ic_action_feedback)
                        .setOnClickAction(() -> SharedUtils.mailToDeveloper(AboutActivity.this, "【反馈】", ""))
                        .build())
                .addItem(new MaterialAboutActionItem.Builder()
                        .text(R.string.open_source_licence)
                        .icon(R.drawable.ic_action_github)
                        .setOnClickAction(() ->
                                ActivityUtils.startActivity(AboutActivity.this
                                        , OpenSourceLicenceActivity.class))
                        .build())
                .build();

        MaterialAboutCard icon = new MaterialAboutCard.Builder()
                .title("LOGO")
                .addItem(new MaterialAboutActionItem.Builder()
                        .text("material.io")
                        .icon(R.drawable.ic_action_google)
                        .setOnClickAction(() -> {
                            Intent intent = new Intent(AboutActivity.this, WebActivity.class);
                            intent.putExtra("url", "https://material.io/tools/icons/?style=baseline");
                            startActivity(intent);
                        })
                        .build())
                .addItem(new MaterialAboutActionItem.Builder()
                        .text("iconfont")
                        .icon(R.drawable.ic_action_alibaba)
                        .setOnClickAction(() -> {
                            Intent intent = new Intent(AboutActivity.this, WebActivity.class);
                            intent.putExtra("url", "https://www.iconfont.cn/");
                            startActivity(intent);
                        })
                        .build())
                .build();
        return new MaterialAboutList.Builder()
                .addCard(androidFun)
                .addCard(author)
                .addCard(icon)
                .build();
    }

    @Nullable
    @Override
    protected CharSequence getActivityTitle() {
        return getString(R.string.app_name);
    }


}
