package com.eric.javabase;

public class StringFromatUtil {

    /**
     * 身份证有效期格式化
     * @param psd
     * @return
     */
    public static String formtPeriod(String psd) {
        if (psd.length() > 8) {
            psd = psd.substring(0, 4) + "." + psd.substring(4, 6) + "." + psd.substring(6, 8);
        }
        return psd;
    }

    /**
     * 银行卡号码格式化
     * @param psd
     * @return
     */
    public static String formtBankNo(String psd) {
        StringBuffer stringBuffer=new StringBuffer();
        if (psd.length() > 7) {
            for (int i = 0; i < psd.length() - 7; i++) {
                stringBuffer.append("*");
            }
            psd = psd.substring(0, 4) + stringBuffer+ psd.substring(psd.length()-3, psd.length());
        }
        return  psd.replaceAll(".{4}(?!$)", "$0 ");
    }
}
