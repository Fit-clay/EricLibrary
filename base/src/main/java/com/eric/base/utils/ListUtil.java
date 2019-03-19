package com.eric.base.utils;

import android.text.TextUtils;


import com.eric.javabase.ListUtils;

import java.util.List;

public class ListUtil extends ListUtils {

    /**
     * join list to string, separator is ","
     *
     * <pre>
     * join(null)      =   "";
     * join({})        =   "";
     * join({a,b})     =   "a,b";
     * </pre>
     *
     * @param list
     * @return join list to string, separator is ",". if list is empty, return ""
     */
    public static String join(List<String> list) {
        return join(list, DEFAULT_JOIN_SEPARATOR);
    }

    /**
     * join list to string
     *
     * <pre>
     * join(null, '#')     =   "";
     * join({}, '#')       =   "";
     * join({a,b,c}, ' ')  =   "abc";
     * join({a,b,c}, '#')  =   "a#b#c";
     * </pre>
     *
     * @param list
     * @param separator
     * @return join list to string. if list is empty, return ""
     */
    public static String join(List<String> list, char separator) {
        return join(list, new String(new char[]{separator}));
    }

    /**
     * join list to string. if separator is null, use {@link #DEFAULT_JOIN_SEPARATOR}
     *
     * <pre>
     * join(null, "#")     =   "";
     * join({}, "#$")      =   "";
     * join({a,b,c}, null) =   "a,b,c";
     * join({a,b,c}, "")   =   "abc";
     * join({a,b,c}, "#")  =   "a#b#c";
     * join({a,b,c}, "#$") =   "a#$b#$c";
     * </pre>
     *
     * @param list
     * @param separator
     * @return join list to string with separator. if list is empty, return ""
     */
    public static String join(List<String> list, String separator) {
        //TODO join源码阅读
        return list == null ? "" : TextUtils.join(separator, list);
    }
}
