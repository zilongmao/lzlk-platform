package com.lzlk.base.utils.string;

import org.apache.commons.lang3.math.NumberUtils;

import java.util.*;

/**
 * <pre>
 *
 * 【标题】: String查关工具类
 * 【描述】:
 * 【版权】: 湖南达联
 * 【作者】: 邻座旅客
 * 【时间】: 2017年6月5日 下午2:01:36
 * </pre>
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    private static String string = "1234567890abcdefghijklmnopqrstuvwxyABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static final String DEFAULT_DIGITS = "0";

    private static final String FIRST_DEFAULT_DIGITS = "1";

    private static int RANDOM_STR_SIZE = 10;

    private static int getRandom(int count) {
        return (int) Math.round(Math.random() * (count));
    }

    public static String getRandomString() {
        return getRandomString(RANDOM_STR_SIZE);
    }

    public static String getRandomString(Integer length) {
        StringBuffer sb = new StringBuffer();
        int len = string.length();
        for (int i = 0; i < length; i++) {
            sb.append(string.charAt(getRandom(len - 1)));
        }
        return sb.toString();
    }


    /**
     * 将字符串分割转换为长整数列表
     *
     * @param str      需要分割转换的字符串
     * @param splitKey 分隔符
     * @return 长整数列表
     */
    public static List<Long> strToIdList(String str, String splitKey) {
        List<Long> ids = new ArrayList<Long>();

        if (StringUtils.isNotBlank(str) && StringUtils.isNotBlank(splitKey)) {

            String[] strIds = str.split(splitKey);

            if (strIds != null) {
                for (String strId : strIds) {
                    try {
                        Long id = Long.parseLong(strId);
                        ids.add(id);
                    } catch (Exception e) {

                    }

                }
            }
        }

        return ids;

    }

    /**
     * null值处理
     *
     * @param str
     * @return
     * @Description
     * @author 张国栋
     */
    public static String nvl(String str) {
        if (str == null) {
            return "";
        } else {
            return str;
        }
    }

    /**
     * toString，对于null值不做处理
     *
     * @param obj
     * @return
     * @Description
     * @author 张国栋
     */
    public static String valueOf(Object obj) {
        return obj == null ? null : obj.toString();
    }

    /**
     * 将驼峰结构转化为下划线结构
     *
     * @param str
     * @return
     * @Description
     * @author 邻座旅客
     */
    public static String convertHumpToUnderline(String str) {
        char[] cArray = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char c : cArray) {
            if (!Character.isUpperCase(c)) {
                sb.append(c);
            } else {
                sb.append("_").append(Character.toLowerCase(c));
            }
        }
        return sb.toString();
    }

    /**
     * 将版本号转未Int
     *
     * @param version
     * @return
     */
    public static Integer stringToInteger(String version) {
        if (StringUtils.isBlank(version)) {
            return 0;
        }
        return Integer.valueOf(version.replace(".", ""));
    }

    /**
     * 截取内容，只保留10个字多余用...替换<br/>
     * exp: 内容“123456789012345”
     *
     * @param content 输入的内容
     * @return 返回被截取后的内容
     */
    public static String subContent(String content) {
        if (StringUtils.isBlank(content)) {
            return content;
        }
        if (content.length() > 20) {
            return content.substring(0, 20) + "...";
        }
        return content;
    }


    /**
     * 截取内容，只保留length个字多余用...替换<br/>
     * exp: 内容“123456789012345”
     *
     * @param content 输入的内容
     * @return 返回被截取后的内容
     */
    public static String subContent(String content, Integer length) {
        if (StringUtils.isBlank(content)) {
            return content;
        }
        if (content.length() > length) {
            return content.substring(0, length) + "...";
        }
        return content;
    }

    /**
     * 过滤emoji 或者 其他非文字类型的字符
     * //emoji正则"[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]"
     *
     * @param source 源字符
     * @return
     */
    public static String filterEmoji(String source) {
        if (isBlank(source)) {
            return source;
        }
        int len = source.length();
        StringBuilder buf = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
            if (isNotEmojiCharacter(codePoint)) {
                buf.append(codePoint);
            } else {
                buf.append("*");
            }
        }
        return buf.toString();
    }

    /**
     * 人工智障公众号回复
     *
     * @param content
     * @return
     */
    public static String artificialRetardation(String content) {
        char[] chars = content.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '你') {
                chars[i] = '我';
            } else {
                if (chars[i] == '我') {
                    chars[i] = '你';
                }
            }
        }
        content = String.valueOf(chars);
        content = content.replace("吗", "");
        content = content.replace("？", "！");
        content = content.replace("?", "!");
        return content;
    }

    /**
     * 获取指定字符串的次数
     *
     * @param str
     * @param tag
     * @return
     */
    public static int getStrCount(String str, String tag) {
        int index = 0;
        int count = 0;
        while ((index = str.indexOf(tag)) != -1) {
            str = str.substring(index + tag.length());
            count++;
        }
        return count;
    }

    /**
     * @param codePoint
     * @return
     */
    private static boolean isNotEmojiCharacter(char codePoint) {
        return (codePoint == 0x0) ||
                (codePoint == 0x9) ||
                (codePoint == 0xA) ||
                (codePoint == 0xD) ||
                ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) ||
                ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) ||
                ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
    }

    /**
     * 去掉特殊字符 \ufeff<br/>
     * 该特殊符号是由于bom产生，
     *
     * @param str 原始字符串
     * @return 去掉特殊字符后的字符串
     */
    public static String removeFEFF(String str) {
        return str.replace("\ufeff", "");
    }

    /**
     * 从字符串中提出数字
     * 这个方法有个明显的缺点，只能把数字全部提取到一起，不能分别提取。
     * 1a2b3c -> 123
     *
     * @param str
     * @return
     */
    public static Long getNumber(String str) {
        str = str.trim();
        String str2 = "";
        if (str != null && !"".equals(str)) {
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) >= 48 && str.charAt(i) <= 57) {
                    str2 += str.charAt(i);
                }
            }
        }
        return Long.valueOf(str2);
    }

    /**
     * 逗号分割转换为set<Long>
     *
     * @param str 需要转换的字符串
     * @return
     */
    public static Set<Long> commaToSet(String str) {
        //保证有序
        Set<Long> set = new LinkedHashSet<>();
        if (isEmpty(str)) {
            return set;
        }
        List<String> list = Arrays.asList(str.split(","));
        list.forEach(val -> set.add(NumberUtils.toLong(val)));
        return set;
    }

    /**
     * 将字符串转换为Unicode
     *
     * @param str 需要转换的字符串
     * @return
     */
    public static String chinaToUnicode(String str) {
        String result = "";
        for (int i = 0; i < str.length(); i++) {
            int chr1 = (char) str.charAt(i);
            if (chr1 >= 19968 && chr1 <= 171941) {// 汉字范围 \u4e00-\u9fa5 (中文)
                result += "\\u" + Integer.toHexString(chr1);
            } else {
                result += str.charAt(i);
            }
        }
        return result;
    }

    /**
     * @param target 目标数字
     * @param length 需要补充到的位数, 补充默认数字[0], 第一位默认补充[1]
     * @return 补充后的结果
     */
    public static String makeUpNewData(String target, int length) {
        return makeUpNewData(target, length, DEFAULT_DIGITS);
    }

    /**
     * @param target 目标数字
     * @param length 需要补充到的位数
     * @param add    需要补充的数字, 补充默认数字[0], 第一位默认补充[1]
     * @return 补充后的结果
     */
    public static String makeUpNewData(String target, int length, String add) {
        if (target.startsWith("-")) target.replace("-", "");
        if (target.length() >= length) return target.substring(0, length);
        StringBuffer sb = new StringBuffer(FIRST_DEFAULT_DIGITS);
        for (int i = 0; i < length - (1 + target.length()); i++) {
            sb.append(add);
        }
        return sb.append(target).toString();
    }

    /**
     * 生产一个随机的指定位数的字符串数字
     *
     * @param length
     * @return
     */
    public static String randomDigitNumber(int length) {
        int start = Integer.parseInt(makeUpNewData("", length));//1000+8999=9999
        int end = Integer.parseInt(makeUpNewData("", length + 1)) - start;//9000
        return (int) (Math.random() * end) + start + "";
    }

    /**
     * 以毫微秒做基础计数, 返回唯一有序增长ID
     * <pre>System.nanoTime()</pre>
     * <pre>
     *  线程数量:   100
     *  执行次数:   1000
     *  平均耗时:   222 ms
     *  数组长度:   100000
     *  Map Size:   100000
     * </pre>
     *
     * @return ID长度32位
     */
    public static String getPropCode() {
        return makeUpNewData(Thread.currentThread().hashCode() + "", 3) + randomDigitNumber(7);                                          //随机7位数
    }

    public static void main(String[] args) {
       /* //
        System.out.println(StringUtils.isBlank("\ufeff"));
        System.out.println("\ufeff");*/
        // commaToSet("1,2,3,4")

    }
}
