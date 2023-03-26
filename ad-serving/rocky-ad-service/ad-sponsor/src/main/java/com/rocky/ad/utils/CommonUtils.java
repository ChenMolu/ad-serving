package com.rocky.ad.utils;

import com.rocky.ad.exception.AdException;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.time.DateUtils;

import java.util.Date;

public class CommonUtils {

    private static String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy/MM/dd", "yyyy.MM.dd"
    };

    /**
     * MD5加密
     * @param value
     * @return
     */
    public static String md5(String value) {

        return DigestUtils.md5Hex(value).toUpperCase();
    }

    /**
     * 将String类型转化成Date类型
     * @param dateString
     * @return
     * @throws AdException
     */
    public static Date parseStringDate(String dateString)
            throws AdException {

        try {
            return DateUtils.parseDate(
                    dateString, parsePatterns
            );
        } catch (Exception ex) {
            throw new AdException(ex.getMessage());
        }
    }
}
