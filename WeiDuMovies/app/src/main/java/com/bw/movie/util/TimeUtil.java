package com.bw.movie.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: 张伟成
 * @CreateDate: 2019/10/17 20:23
 */
public class TimeUtil {

    /**
     * 获取当前时间的毫秒数
     */
    public static long getTimeLong() {
        return System.currentTimeMillis();
    }

    /**
     * 获取当时时间的年，月，日，时分秒
     * 这里获得的时int类型的数据，要输入对应的格式
     * 比如要获得现在时间的天数值，
     * 使用getTime（“MM”）,如果现在是2016年5月20日，取得的数字是5；
     */
    public static int getTimeInt(String filter) {
        //
        SimpleDateFormat format = new SimpleDateFormat(filter);
        String time = format.format(new Date());
        return Integer.parseInt(time);
    }



    /**
     * 获取当前时间的完整显示字符串
     */
    public static final String getTimeString() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date(getTimeLong()));
    }

    /**
     * 获得某个时间的完整格式的字符串
     * 输入的是某个时间的毫秒数，
     * 有些时候文件保存的时间是毫秒数，这时就需要转换来查看时间了！
     */
    public static final String getTimeString(long time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date(time));
    }

    /**
     * 获得自定义格式的时间字符串
     * 输入的是某个时间的毫秒数，显示的可以是时间字符串的某一部分
     * 比如想要获得某一个时间的月和日，getTimeString(1111111111111,"MM-dd");
     */
    public static final String getTimeString(long time, String filter) {
        SimpleDateFormat format = new SimpleDateFormat(filter);
        return format.format(new Date(time));
    }

    /**
     * 获得自定义格式的当前的时间的字符串
     * 比如当前时间2016年8月8日12时8分21秒，getTimeString("yyyy-MM-dd"),可以得到 2016-8-12
     */
    public static final String getTimeString(String filter) {
        SimpleDateFormat format = new SimpleDateFormat(filter);
        return format.format(new Date(getTimeLong()));
    }

    /*
     * 将时间戳转换为时间
     *
     * s就是时间戳
     */

    public static String stampToDate(String s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        //如果它本来就是long类型的,则不用写这一步
        long lt = new Long(s);
        Date date = new Date(lt * 1000);
        res = simpleDateFormat.format(date);
        return res;
    }

    /*
     * 将时间转换为时间戳
     */
    public static String dateToStamp(String s) throws ParseException {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }


}
