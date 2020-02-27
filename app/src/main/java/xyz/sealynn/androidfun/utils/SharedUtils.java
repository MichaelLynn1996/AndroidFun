package xyz.sealynn.androidfun.utils;

import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;

import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import xyz.sealynn.androidfun.R;

/**
 * Created by SeaLynn0 on 2018/10/13 17:58
 * <p>
 * Email：sealynndev@gmail.com
 */
public class SharedUtils {

    private SharedUtils() {
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
        ClipData mClipData = ClipData.newPlainText("LabelSide", text);
        // 将ClipData内容放到系统剪贴板里。
        if (cm != null && cm.getPrimaryClip() != null) {
            cm.setPrimaryClip(mClipData);
            Logger.d(cm.getPrimaryClip().toString().equals(text));
            Logger.d(cm.getPrimaryClip().getItemAt(0).getText().toString());
            if (cm.getPrimaryClip().getItemAt(0).getText().toString().equals(text))
                ToastUtils.shortToast(context, R.string.copy_succ);
        }
    }

    public static void goToMarket(Context context) {
        Uri uri = Uri.parse("market://details?id=" + AppUtils.getPackageName(context));
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        try {
            context.startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void mailToDeveloper(Context context, String title, String content) {
        Intent data = new Intent(Intent.ACTION_SENDTO);
        data.setData(Uri.parse("mailto:sealynndev@gamil.com"));
        data.putExtra(Intent.EXTRA_SUBJECT, title);
        data.putExtra(Intent.EXTRA_TEXT, content);
        context.startActivity(data);
    }

    /**
     * 将Bitmap作为图片分享
     *
     * @param context 上下文联系
     * @param bm      要分享的BitMap
     */
    public static void shareImage(Context context, Bitmap bm) {
        Uri uri = Uri.parse(MediaStore.Images.Media.insertImage(context.getContentResolver(), bm, null, null));
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);//设置分享行为
        intent.setType("image/*");//设置分享内容的类型
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        intent = Intent.createChooser(intent, "分享");
        context.startActivity(intent);
    }

    /**
     * 保存bitmap到本地
     *
     * @param bitmap Bitmap
     */
    public static void saveBitmap(Context context, Bitmap bitmap) throws IOException {
//        boolean saved;
        OutputStream fos = null;
        String name = String.valueOf(System.currentTimeMillis());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            ContentResolver resolver = context.getContentResolver();
            ContentValues contentValues = new ContentValues();
            contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, name);
            contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/png");
            contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH, "Pictures/WanAndroid");
            Uri imageUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
            if (imageUri != null) {
                fos = resolver.openOutputStream(imageUri);
            }
        } else {
            String imagesDir = Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_PICTURES).toString() + File.separator + "WanAndroid";

            File file = new File(imagesDir);

            if (!file.exists()) {
                file.mkdir();
            }

            File image = new File(imagesDir, name + ".png");
            fos = new FileOutputStream(image);

        }

        if (fos != null) {
//            saved =
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
        }
    }
}
