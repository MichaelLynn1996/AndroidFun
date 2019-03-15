package xyz.sealynn.androidfun.utils;

import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

import com.orhanobut.logger.Logger;

import xyz.sealynn.androidfun.R;

/**
 * Created by SeaLynn0 on 2018/10/13 17:58
 * <p>
 * Email：sealynndev@gmail.com
 */
public class SharedUtils {

    private SharedUtils(){
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static void shareText(Context context, String text) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);

        // 指定发送的内容
        sendIntent.putExtra(Intent.EXTRA_TEXT, text);
        // 指定发送内容的类型
        sendIntent.setType("text/plain");
        context.startActivity(Intent.createChooser(sendIntent, "分享到..."));
    }

    public static void copyText(Context context, String text) {
        //获取剪贴板管理器：
        ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        // 创建普通字符型ClipData
        ClipData mClipData = ClipData.newPlainText("Label", text);
        // 将ClipData内容放到系统剪贴板里。
        if (cm != null && cm.getPrimaryClip() != null) {
            cm.setPrimaryClip(mClipData);
            Logger.d(cm.getPrimaryClip().toString().equals(text));
            Logger.d(cm.getPrimaryClip().getItemAt(0).getText().toString());
            if (cm.getPrimaryClip().getItemAt(0).getText().toString().equals(text))
                ToastUtils.shortToast(context, R.string.copy_succ);
        }
    }

    public static void goToMarket(Context context){
        Uri uri = Uri.parse("market://details?id=" + AppUtils.getPackageName(context));
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        try {
            context.startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void mailToDeveloper(Context context){
        Intent data=new Intent(Intent.ACTION_SENDTO);
        data.setData(Uri.parse("mailto:sealinxy@outlook.com"));
        data.putExtra(Intent.EXTRA_SUBJECT, "这是标题");
        data.putExtra(Intent.EXTRA_TEXT, "这是内容");
        context.startActivity(data);
    }
}
