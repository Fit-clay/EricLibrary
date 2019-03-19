package com.eric.base.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.view.View;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class ImageUtils {


    private ImageUtils() {
        throw new AssertionError();
    }

    /**
     * convert Bitmap to byte array
     *
     * @param b
     * @return
     */
    public static byte[] bitmapToByte(Bitmap b) {
        if (b == null) {
            return null;
        }

        ByteArrayOutputStream o = new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.PNG, 100, o);
        return o.toByteArray();
    }

    /**
     * convert byte array to Bitmap
     *
     * @param b
     * @return
     */
    public static Bitmap byteToBitmap(byte[] b) {
        return (b == null || b.length == 0) ? null : BitmapFactory.decodeByteArray(b, 0, b.length);
    }

    /**
     * convert Drawable to Bitmap
     *
     * @param d
     * @return
     */
    public static Bitmap drawableToBitmap(Drawable d) {
        return d == null ? null : ((BitmapDrawable)d).getBitmap();
    }

    /**
     * convert Bitmap to Drawable
     *
     * @param b
     * @return
     */
    public static Drawable bitmapToDrawable(Bitmap b) {
        return b == null ? null : new BitmapDrawable(b);
    }

    /**
     * convert Drawable to byte array
     *
     * @param d
     * @return
     */
    public static byte[] drawableToByte(Drawable d) {
        return bitmapToByte(drawableToBitmap(d));
    }

    /**
     * convert byte array to Drawable
     *
     * @param b
     * @return
     */
    public static Drawable byteToDrawable(byte[] b) {
        return bitmapToDrawable(byteToBitmap(b));
    }

    /**
     * scale image
     *
     * @param org
     * @param newWidth
     * @param newHeight
     * @return
     */
    public static Bitmap scaleImageTo(Bitmap org, int newWidth, int newHeight) {
        return scaleImage(org, (float) newWidth / org.getWidth(), (float) newHeight / org.getHeight());
    }
    /**
     * scale image
     *
     * @param org
     * @param scaleWidth sacle of width
     * @param scaleHeight scale of height
     * @return
     */
    public static Bitmap scaleImage(Bitmap org, float scaleWidth, float scaleHeight) {
        if (org == null) {
            return null;
        }
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        return Bitmap.createBitmap(org, 0, 0, org.getWidth(), org.getHeight(), matrix, true);
    }


    /**
     * 质量压缩(由于png是无损压缩，所以设置quality无效)
     *
     * @param path  图片地址
     * @param format  图片格式 jpeg,png,webp
     * @param quality 图片的质量,0-100,数值越小质量越差
     */
    public static void compress(String path,Bitmap.CompressFormat format, int quality) {
//        File sdFile = Environment.getExternalStorageDirectory();
        File originFile = new File(path);
        Bitmap originBitmap = BitmapFactory.decodeFile(originFile.getAbsolutePath());
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        originBitmap.compress(format, quality, bos);
        try {
            FileOutputStream fos = new FileOutputStream(new File(path));
            fos.write(bos.toByteArray());
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
/**
 *采样率压缩
 * @param inSampleSize  可以根据需求计算出合理的inSampleSize
 */
        public static void compress(String path,int inSampleSize){
//            File sdFile = Environment.getExternalStorageDirectory();
            File originFile = new File(path);
            BitmapFactory.Options options = new BitmapFactory.Options();
            //设置此参数是仅仅读取图片的宽高到options中，不会将整张图片读到内存中，防止oom
            options.inJustDecodeBounds = true;
            Bitmap emptyBitmap = BitmapFactory.decodeFile(originFile.getAbsolutePath(), options);

            options.inJustDecodeBounds = false;
            options.inSampleSize = inSampleSize;
            Bitmap resultBitmap = BitmapFactory.decodeFile(originFile.getAbsolutePath(), options);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            resultBitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            try {
                FileOutputStream fos = new FileOutputStream(new File(path));
                fos.write(bos.toByteArray());
                fos.flush();
                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    public void compress(View v) {
        File sdFile = Environment.getExternalStorageDirectory();
        File originFile = new File(sdFile, "originImg.jpg");
        Bitmap bitmap = BitmapFactory.decodeFile(originFile.getAbsolutePath());
        //设置缩放比
        int radio = 8;
        Bitmap result = Bitmap.createBitmap(bitmap.getWidth() / radio, bitmap.getHeight() / radio, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(result);
        RectF rectF = new RectF(0, 0, bitmap.getWidth() / radio, bitmap.getHeight() / radio);
        //将原图画在缩放之后的矩形上
        canvas.drawBitmap(bitmap, null, rectF, null);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        result.compress(Bitmap.CompressFormat.JPEG, 100, bos);
        try {
            FileOutputStream fos = new FileOutputStream(new File(sdFile, "sizeCompress.jpg"));
            fos.write(bos.toByteArray());
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 按比例压缩（缩放图）
     * @param path  图片地址
     * @param radio 压缩比例
     */
    public void imgCompress(String path,int radio) {
//        File sdFile = Environment.getExternalStorageDirectory();
        File originFile = new File(path);
        Bitmap bitmap = BitmapFactory.decodeFile(originFile.getAbsolutePath());
        //设置缩放比
        Bitmap result = Bitmap.createBitmap(bitmap.getWidth() / radio, bitmap.getHeight() / radio, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(result);
        RectF rectF = new RectF(0, 0, bitmap.getWidth() / radio, bitmap.getHeight() / radio);
        //将原图画在缩放之后的矩形上
        canvas.drawBitmap(bitmap, null, rectF, null);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        result.compress(Bitmap.CompressFormat.JPEG, 100, bos);
        try {
            FileOutputStream fos = new FileOutputStream(new File(path));
            fos.write(bos.toByteArray());
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


//TODO NDK压缩




}
