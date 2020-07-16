package cn.mlynn.androidfun.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.View;

public final class BitmapUtils {

    private BitmapUtils(){
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * View转换为Bitmap图片
     *
     * @param view 要转成Bitmap的View
     * @return Bitmap
     */
    public static Bitmap convertViewToBitmap(View view) {
        int w = view.getWidth();
        int h = view.getHeight();

        Bitmap bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bmp);

//        c.drawColor(Color.WHITE);
        /* 如果不设置canvas画布为白色，则生成透明 */

//        view.layout(0, 0, w, h);
        view.draw(c);

        return bmp;
    }
}
