package cn.xy.library.util.resource;

import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.RawRes;
import android.support.v4.content.ContextCompat;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.List;

import cn.xy.library.XApp;
import cn.xy.library.util.string.XString;
import cn.xy.library.util.convert.XConvert;
import cn.xy.library.util.file.XFileIO;

/**
 * <pre>
 *     author: Xy
 *     time  : 23:43 2020/12/12
 *     desc  : utils about resource
 * </pre>
 */

/**
 * getDrawable        : 获取 Drawable
 * getIdByName        : 根据名字获取 ID
 * getStringIdByName  : 根据名字获取 string ID
 * getColorIdByName   : 根据名字获取 color ID
 * getDimenIdByName   : 根据名字获取 dimen ID
 * getDrawableIdByName: 根据名字获取 dimen ID
 * getMipmapIdByName  : 根据名字获取 mipmap ID
 * getLayoutIdByName  : 根据名字获取 playview_layout ID
 * getStyleIdByName   : 根据名字获取 style ID
 * getAnimIdByName    : 根据名字获取 anim ID
 * getMenuIdByName    : 根据名字获取 menu ID
 * copyFileFromAssets : 从 assets 中拷贝文件
 * readAssets2String  : 从 assets 中读取字符串
 * readAssets2List    : 从 assets 中按行读取字符串
 * copyFileFromRaw    : 从 raw 中拷贝文件
 * readRaw2String     : 从 raw 中读取字符串
 * readRaw2List       : 从 raw 中按行读取字符串
 */
public final class XResource {

    private static final int BUFFER_SIZE = 8192;

    private XResource() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * Return the drawable by identifier.
     *
     * @param id The identifier.
     * @return the drawable by identifier
     */
    public static Drawable getDrawable(@DrawableRes int id) {
        return ContextCompat.getDrawable(XApp.getApp(), id);
    }

    /**
     * Return the id identifier by name.
     *
     * @param name The name of id.
     * @return the id identifier by name
     */
    public static int getIdByName(String name) {
        return XApp.getApp().getResources().getIdentifier(name, "id", XApp.getApp().getPackageName());
    }

    /**
     * Return the string identifier by name.
     *
     * @param name The name of string.
     * @return the string identifier by name
     */
    public static int getStringIdByName(String name) {
        return XApp.getApp().getResources().getIdentifier(name, "string", XApp.getApp().getPackageName());
    }

    /**
     * Return the color identifier by name.
     *
     * @param name The name of color.
     * @return the color identifier by name
     */
    public static int getColorIdByName(String name) {
        return XApp.getApp().getResources().getIdentifier(name, "color", XApp.getApp().getPackageName());
    }

    /**
     * Return the dimen identifier by name.
     *
     * @param name The name of dimen.
     * @return the dimen identifier by name
     */
    public static int getDimenIdByName(String name) {
        return XApp.getApp().getResources().getIdentifier(name, "dimen", XApp.getApp().getPackageName());
    }

    /**
     * Return the drawable identifier by name.
     *
     * @param name The name of drawable.
     * @return the drawable identifier by name
     */
    public static int getDrawableIdByName(String name) {
        return XApp.getApp().getResources().getIdentifier(name, "drawable", XApp.getApp().getPackageName());
    }

    /**
     * Return the mipmap identifier by name.
     *
     * @param name The name of mipmap.
     * @return the mipmap identifier by name
     */
    public static int getMipmapIdByName(String name) {
        return XApp.getApp().getResources().getIdentifier(name, "mipmap", XApp.getApp().getPackageName());
    }

    /**
     * Return the playview_layout identifier by name.
     *
     * @param name The name of playview_layout.
     * @return the playview_layout identifier by name
     */
    public static int getLayoutIdByName(String name) {
        return XApp.getApp().getResources().getIdentifier(name, "layout", XApp.getApp().getPackageName());
    }

    /**
     * Return the style identifier by name.
     *
     * @param name The name of style.
     * @return the style identifier by name
     */
    public static int getStyleIdByName(String name) {
        return XApp.getApp().getResources().getIdentifier(name, "style", XApp.getApp().getPackageName());
    }

    /**
     * Return the anim identifier by name.
     *
     * @param name The name of anim.
     * @return the anim identifier by name
     */
    public static int getAnimIdByName(String name) {
        return XApp.getApp().getResources().getIdentifier(name, "anim", XApp.getApp().getPackageName());
    }

    /**
     * Return the menu identifier by name.
     *
     * @param name The name of menu.
     * @return the menu identifier by name
     */
    public static int getMenuIdByName(String name) {
        return XApp.getApp().getResources().getIdentifier(name, "menu", XApp.getApp().getPackageName());
    }

    /**
     * Copy the file from assets.
     *
     * @param assetsFilePath The path of file in assets.
     * @param destFilePath   The path of destination file.
     * @return {@code true}: success<br>{@code false}: fail
     */
    public static boolean copyFileFromAssets(final String assetsFilePath, final String destFilePath) {
        boolean res = true;
        try {
            String[] assets = XApp.getApp().getAssets().list(assetsFilePath);
            if (assets != null && assets.length > 0) {
                for (String asset : assets) {
                    res &= copyFileFromAssets(assetsFilePath + "/" + asset, destFilePath + "/" + asset);
                }
            } else {
                res = XFileIO.writeFileFromIS(
                        destFilePath,
                        XApp.getApp().getAssets().open(assetsFilePath)
                );
            }
        } catch (IOException e) {
            e.printStackTrace();
            res = false;
        }
        return res;
    }

    /**
     * Return the content of assets.
     *
     * @param assetsFilePath The path of file in assets.
     * @return the content of assets
     */
    public static String readAssets2String(final String assetsFilePath) {
        return readAssets2String(assetsFilePath, null);
    }

    /**
     * Return the content of assets.
     *
     * @param assetsFilePath The path of file in assets.
     * @param charsetName    The name of charset.
     * @return the content of assets
     */
    public static String readAssets2String(final String assetsFilePath, final String charsetName) {
        try {
            InputStream is = XApp.getApp().getAssets().open(assetsFilePath);
            byte[] bytes = XConvert.inputStream2Bytes(is);
            if (bytes == null) return "";
            if (XString.isSpace(charsetName)) {
                return new String(bytes);
            } else {
                try {
                    return new String(bytes, charsetName);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    return "";
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * Return the content of file in assets.
     *
     * @param assetsPath The path of file in assets.
     * @return the content of file in assets
     */
    public static List<String> readAssets2List(final String assetsPath) {
        return readAssets2List(assetsPath, "");
    }

    /**
     * Return the content of file in assets.
     *
     * @param assetsPath  The path of file in assets.
     * @param charsetName The name of charset.
     * @return the content of file in assets
     */
    public static List<String> readAssets2List(final String assetsPath,
                                               final String charsetName) {
        try {
            return XConvert.inputStream2Lines(XApp.getApp().getResources().getAssets().open(assetsPath), charsetName);
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }


    /**
     * Copy the file from raw.
     *
     * @param resId        The resource id.
     * @param destFilePath The path of destination file.
     * @return {@code true}: success<br>{@code false}: fail
     */
    public static boolean copyFileFromRaw(@RawRes final int resId, final String destFilePath) {
        return XFileIO.writeFileFromIS(
                destFilePath,
                XApp.getApp().getResources().openRawResource(resId)
        );
    }

    /**
     * Return the content of resource in raw.
     *
     * @param resId The resource id.
     * @return the content of resource in raw
     */
    public static String readRaw2String(@RawRes final int resId) {
        return readRaw2String(resId, null);
    }

    /**
     * Return the content of resource in raw.
     *
     * @param resId       The resource id.
     * @param charsetName The name of charset.
     * @return the content of resource in raw
     */
    public static String readRaw2String(@RawRes final int resId, final String charsetName) {
        InputStream is = XApp.getApp().getResources().openRawResource(resId);
        byte[] bytes = XConvert.inputStream2Bytes(is);
        if (bytes == null) return null;
        if (XString.isSpace(charsetName)) {
            return new String(bytes);
        } else {
            try {
                return new String(bytes, charsetName);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return "";
            }
        }
    }

    /**
     * Return the content of resource in raw.
     *
     * @param resId The resource id.
     * @return the content of file in assets
     */
    public static List<String> readRaw2List(@RawRes final int resId) {
        return readRaw2List(resId, "");
    }

    /**
     * Return the content of resource in raw.
     *
     * @param resId       The resource id.
     * @param charsetName The name of charset.
     * @return the content of file in assets
     */
    public static List<String> readRaw2List(@RawRes final int resId,
                                            final String charsetName) {
        return XConvert.inputStream2Lines(XApp.getApp().getResources().openRawResource(resId), charsetName);
    }
}