package com.davidche.appfabric.uaa.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 操作字符串的工具类
 */
@Slf4j
public final class MyStringUtils {

    /**
     * Represents a failed index search.
     *
     * @since 2.1
     */
    public static final int INDEX_NOT_FOUND = -1;

//    private final static String DEFAULT_CHARSET = "GBK";

    /**
     * constructor
     */
    private MyStringUtils() {

    }

    /**
     * 判断字符串是否为Null
     *
     * @param str 字符串
     * @return boolean
     */
    public static boolean isNull(String str) {
        if (str == null) {
            return true;
        }
        return false;
    }

    /**
     * 判断字符串是否为Null
     *
     * @param strs 字符串数组
     * @return boolean
     */
    public static boolean isNull(String... strs) {
        for (String s : strs) {
            if (isNull(s)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断字符串为空或Null
     *
     * @param str 字符串
     * @return boolean
     */
    public static boolean isEmpty(String str) {
        return StringUtils.isEmpty(str);
    }

    /**
     * 判断字符串是否为空，null或者‘’均为空
     *
     * @param str 字符串
     * @return boolean 如果不为空的话返回true，如果为空的话返回false
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 判断字符串为空，或者空白字符或者Null
     *
     * @param str 字符串
     * @return boolean
     */
    public static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(str.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断字符串不为空（不是空字符,或则空白字符串，且不为Null）
     *
     * @param str 字符串
     * @return boolean
     */
    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    /**
     * 去掉字符串前后的空白字符
     *
     * @param str 字符串
     * @return boolean
     */
    public static String trim(String str) {
        return str == null ? null : str.trim();
    }

    /**
     * 将null的字符串转化为空字符串
     *
     * @param str 字符串
     * @return str
     */
    public static String nullToBlank(String str) {
        if (str == null) {
            return "";
        }
        return str;
    }

    /**
     * 如果为null，就转换为‘’
     *
     * @param str 字符串
     * @return str 如果字符串为null，就返回‘’，否则原样返回
     */
    public static String nullToBlank(Object str) {
        if (str == null) {
            return "";
        }
        return str.toString();
    }

    /**
     * 将空白字符串转化为null
     *
     * @param str 字符串
     * @return str
     */
    public static String blankToNull(String str) {
        if (str == null || "".equals(str.trim())) {
            return null;
        }
        return str;
    }

    /**
     * 判断2个字符串是否相等
     *
     * @param str1 字符串1
     * @param str2 字符串2
     * @return boolean
     */
    public static boolean equals(String str1, String str2) {
        return str1 == null ? str2 == null : str1.equals(str2);
    }

    /**
     * 忽略字符串大小写，判断2个字符串是否相等，
     *
     * @param str1 字符串1
     * @param str2 字符串2
     * @return boolean
     */
    public static boolean equalsIgnoreCase(String str1, String str2) {
        return str1 == null ? str2 == null : str1.equalsIgnoreCase(str2);
    }

    /**
     * 获取字符的起始位置，字符串str可为Null
     * <pre>
     * StringUtils.indexOf(null, *)         = -1
     * StringUtils.indexOf("", *)           = -1
     * StringUtils.indexOf("aabaabaa", 'a') = 0
     * StringUtils.indexOf("aabaabaa", 'b') = 2
     * </pre>
     *
     * @param str        字符串
     * @param searchChar 搜索字符
     * @return int
     */
    public static int indexOf(String str, char searchChar) {
        if (isEmpty(str)) {
            return INDEX_NOT_FOUND;
        }
        return str.indexOf(searchChar);
    }

    /**
     * 获取字符串searchStr在字符串str的起始位置
     * <pre>
     * StringUtils.indexOf(null, *)          = -1
     * StringUtils.indexOf(*, null)          = -1
     * StringUtils.indexOf("", "")           = 0
     * StringUtils.indexOf("aabaabaa", "a")  = 0
     * StringUtils.indexOf("aabaabaa", "b")  = 2
     * StringUtils.indexOf("aabaabaa", "ab") = 1
     * StringUtils.indexOf("aabaabaa", "")   = 0
     * </pre>
     *
     * @param str       字符串
     * @param searchStr 搜索字符
     * @return int
     */
    public static int indexOf(String str, String searchStr) {
        if (str == null || searchStr == null) {
            return INDEX_NOT_FOUND;
        }
        return str.indexOf(searchStr);
    }

    /**
     * 获取字符串searchStr在字符串str中最后一个位置的index
     * <pre>
     * StringUtils.lastIndexOf(null, *)          = -1
     * StringUtils.lastIndexOf(*, null)          = -1
     * StringUtils.lastIndexOf("", "")           = 0
     * StringUtils.lastIndexOf("aabaabaa", "a")  = 0
     * StringUtils.lastIndexOf("aabaabaa", "b")  = 2
     * StringUtils.lastIndexOf("aabaabaa", "ab") = 1
     * StringUtils.lastIndexOf("aabaabaa", "")   = 8
     * </pre>
     *
     * @param str       字符串
     * @param searchStr 搜索字符
     * @return int
     */
    public static int lastIndexOf(String str, String searchStr) {
        if (str == null || searchStr == null) {
            return INDEX_NOT_FOUND;
        }
        return str.lastIndexOf(searchStr);
    }

    /**
     * <p>
     * Escapes the characters in a <code>String</code> using HTML entities.
     * </p>
     *
     * @param str 字符串
     * @return str
     */
//    public static String escapeHtml(String str) {
//        return StringEscapeUtils.escapeHtml(str);
//    }

    /**
     * <p>
     * Unescapes a string containing entity escapes to a string containing the actual Unicode characters corresponding
     * to the escapes. Supports HTML 4.0 entities.
     * </p>
     *
     * @param str 字符串
     * @return str
     */
//    public static String unEscapeHtml(String str) {
//        return StringEscapeUtils.unescapeHtml(str);
//    }
    public static String[] split(String str) {
        return split(str, null, -1);
    }

    public static String[] split(String str, String separatorChars, int max) {
        return splitWorker(str, separatorChars, max, false);
    }

    /**
     * <p>
     * Splits the provided text into an array, separators specified. This is an alternative to using StringTokenizer.
     * </p>
     * <pre>
     * StringUtils.split(null, *)         = null
     * StringUtils.split("", *)           = []
     * StringUtils.split("abc def", null) = ["abc", "def"]
     * StringUtils.split("abc def", " ")  = ["abc", "def"]
     * StringUtils.split("abc  def", " ") = ["abc", "def"]
     * StringUtils.split("ab:cd:ef", ":") = ["ab", "cd", "ef"]
     * </pre>
     *
     * @param str            字符串
     * @param separatorChars 分隔符
     * @return String[]
     */
    public static String[] split(String str, String separatorChars) {
        return splitWorker(str, separatorChars, -1, false);
    }

    private static String[] splitWorker(String str, String separatorChars, int max, boolean preserveAllTokens) {
        // Performance tuned for 2.0 (JDK1.4)
        // Direct code is quicker than StringTokenizer.
        // Also, StringTokenizer uses isSpace() not isWhitespace()

        if (str == null) {
            return null;
        }
        int len = str.length();
        if (len == 0) {
            return ArrayUtils.EMPTY_STRING_ARRAY;
        }
        List list = new ArrayList();
        int sizePlus1 = 1;
        int i = 0, start = 0;
        boolean match = false;
        boolean lastMatch = false;
        if (separatorChars == null) {
            // Null separator means use whitespace
            while (i < len) {
                if (Character.isWhitespace(str.charAt(i))) {
                    if (match || preserveAllTokens) {
                        lastMatch = true;
                        if (sizePlus1++ == max) {
                            i = len;
                            lastMatch = false;
                        }
                        list.add(str.substring(start, i));
                        match = false;
                    }
                    start = ++i;
                    continue;
                }
                lastMatch = false;
                match = true;
                i++;
            }
        } else if (separatorChars.length() == 1) {
            // Optimise 1 character case
            char sep = separatorChars.charAt(0);
            while (i < len) {
                if (str.charAt(i) == sep) {
                    if (match || preserveAllTokens) {
                        lastMatch = true;
                        if (sizePlus1++ == max) {
                            i = len;
                            lastMatch = false;
                        }
                        list.add(str.substring(start, i));
                        match = false;
                    }
                    start = ++i;
                    continue;
                }
                lastMatch = false;
                match = true;
                i++;
            }
        } else {
            // standard case
            while (i < len) {
                if (separatorChars.indexOf(str.charAt(i)) >= 0) {
                    if (match || preserveAllTokens) {
                        lastMatch = true;
                        if (sizePlus1++ == max) {
                            i = len;
                            lastMatch = false;
                        }
                        list.add(str.substring(start, i));
                        match = false;
                    }
                    start = ++i;
                    continue;
                }
                lastMatch = false;
                match = true;
                i++;
            }
        }
        if (match || (preserveAllTokens && lastMatch)) {
            list.add(str.substring(start, i));
        }
        return (String[]) list.toArray(new String[list.size()]);
    }

    private static String[] splitWorker(String str, char separatorChar, boolean preserveAllTokens) {
        // Performance tuned for 2.0 (JDK1.4)

        if (str == null) {
            return null;
        }
        int len = str.length();
        if (len == 0) {
            return ArrayUtils.EMPTY_STRING_ARRAY;
        }
        List list = new ArrayList();
        int i = 0, start = 0;
        boolean match = false;
        boolean lastMatch = false;
        while (i < len) {
            if (str.charAt(i) == separatorChar) {
                if (match || preserveAllTokens) {
                    list.add(str.substring(start, i));
                    match = false;
                    lastMatch = true;
                }
                start = ++i;
                continue;
            }
            lastMatch = false;
            match = true;
            i++;
        }
        if (match || (preserveAllTokens && lastMatch)) {
            list.add(str.substring(start, i));
        }
        return (String[]) list.toArray(new String[list.size()]);
    }

    /**
     * 将字符串转换成Boolean
     *
     * @param str 字符串
     * @return boolean
     */
//    public static boolean toBoolean(String str) {
//        return "TRUE".equalsIgnoreCase(str) || "Y".equalsIgnoreCase(str) || "YES".equalsIgnoreCase(str);
//    }

    /**
     * 得到字符串的长度，英文占一个字符，中文占两个字符
     *
     * @param str 字符串
     * @return int
     */
//    public static int getStringLength(String str) {
//        int len = 0;
//        if (isEmpty(str)) {
//            len = 0;
//        } else {
//            byte[] b = str.getBytes(Charset.forName(DEFAULT_CHARSET));
//            len = b.length;
//        }
//        return len;
//    }

    /**
     * <p>
     * Checks if String contains a search String, handling <code>null</code>. This method uses
     * {@link String#indexOf(String)}.
     * </p>
     * <pre>
     * StringUtils.contains(null, *)     = false
     * StringUtils.contains(*, null)     = false
     * StringUtils.contains("", "")      = true
     * StringUtils.contains("abc", "")   = true
     * StringUtils.contains("abc", "a")  = true
     * StringUtils.contains("abc", "z")  = false
     * </pre>
     *
     * @param str    字符串
     * @param subStr 截取字符
     * @return boolean
     */
//    public static boolean contains(String str, String subStr) {
//        return org.apache.commons.lang.StringUtils.contains(str, subStr);
//    }

    /**
     * <p>
     * Capitalizes a String changing the first letter to title case as per {@link Character#toTitleCase(char)}. No other
     * letters are changed.
     * </p>
     * <pre>
     * StringUtils.capitalize(null)  = null
     * StringUtils.capitalize("")    = ""
     * StringUtils.capitalize("cat") = "Cat"
     * StringUtils.capitalize("cAt") = "CAt"
     * </pre>
     *
     * @param str 字符串
     * @return str
     */
//    public static String capitalize(String str) {
//        return org.apache.commons.lang.StringUtils.capitalize(str);
//    }

    /**
     * <p>
     * Uncapitalizes a String changing the first letter to title case as per {@link Character#toLowerCase(char)}. No
     * other letters are changed.
     * </p>
     * <pre>
     * StringUtils.unCapitalize(null)  = null
     * StringUtils.unCapitalize("")    = ""
     * StringUtils.unCapitalize("Cat") = "cat"
     * StringUtils.unCapitalize("CAT") = "cAT"
     * </pre>
     *
     * @param str 字符串
     * @return str
     */
//    public static String unCapitalize(String str) {
//        return org.apache.commons.lang.StringUtils.uncapitalize(str);
//    }

    /**
     * <p>
     * Converts a String to upper case as per {@link String#toUpperCase()}.
     * </p>
     * <p>
     * A <code>null</code> input String returns <code>null</code>.
     * </p>
     * <pre>
     * StringUtils.upperCase(null)  = null
     * StringUtils.upperCase("")    = ""
     * StringUtils.upperCase("aBc") = "ABC"
     * </pre>
     *
     * @param str 字符串
     * @return str
     */
//    public static String upperCase(String str) {
//        return org.apache.commons.lang.StringUtils.upperCase(str);
//    }

    /**
     * <p>
     * Converts a String to lower case as per {@link String#toLowerCase()}.
     * </p>
     * <p>
     * A <code>null</code> input String returns <code>null</code>.
     * </p>
     * <pre>
     * StringUtils.lowerCase(null)  = null
     * StringUtils.lowerCase("")    = ""
     * StringUtils.lowerCase("aBc") = "abc"
     * </pre>
     *
     * @param str 字符串
     * @return str
     */
//    public static String lowerCase(String str) {
//        return org.apache.commons.lang.StringUtils.lowerCase(str);
//    }

    /**
     * <p>
     * Checks whether the String a valid Java number.
     * </p>
     *
     * @param str 字符串
     * @return boolean
     */
//    public static boolean isNumber(String str) {
//        return org.apache.commons.lang.math.NumberUtils.isNumber(str);
//    }

    /**
     * 判断字符串是否是整数
     *
     * @param str 字符串
     * @return boolean
     */
//    public static boolean isInteger(String str) {
//        if (str != null && !"".equals(str) && isDigits(str) && !"0".startsWith(str)) {
//            return true;
//        }
//        return false;
//    }

    /**
     * <p>
     * Checks whether the <code>String</code> contains only digit characters.
     * </p>
     *
     * @param str 字符串
     * @return boolean
     */
//    public static boolean isDigits(String str) {
//        return org.apache.commons.lang.math.NumberUtils.isDigits(str);
//    }

    /**
     * <p>
     * Replaces all occurrences of a String within another String.
     * </p>
     * <p>
     * A <code>null</code> reference passed to this method is a no-op.
     * </p>
     * <pre>
     * StringUtils.replace(null, *, *)        = null
     * StringUtils.replace("", *, *)          = ""
     * StringUtils.replace("any", null, *)    = "any"
     * StringUtils.replace("any", *, null)    = "any"
     * StringUtils.replace("any", "", *)      = "any"
     * StringUtils.replace("aba", "a", null)  = "aba"
     * StringUtils.replace("aba", "a", "")    = "b"
     * StringUtils.replace("aba", "a", "z")   = "zbz"
     * </pre>
     *
     * @param text 文本
     * @param repl 替换
     * @param with 到
     * @return String
     */
//    public static String replace(String text, String repl, String with) {
//        return org.apache.commons.lang.StringUtils.replace(text, repl, with);
//    }

    /**
     * 把一个字符串列表用指定的分隔符连接在一起
     *
     * @param list      字符串列表
     * @param separator 分隔符
     * @return sb
     */
    public static String join(List<String> list, String separator) {
        StringBuilder sb = new StringBuilder();
        int idx = 0;
        int size = list.size();
        for (String s : list) {
            sb.append(s);
            if (idx < size - 1) {
                sb.append(separator);
            }
            idx++;
        }
        return sb.toString();
    }

    /**
     * 返回小写字符串
     *
     * @param str 字符串
     * @return str
     */
    public static String toLowerCase(String str) {
        if (isBlank(str)) {
            return "";
        }
        return str.toLowerCase();
    }

    /**
     * 返回大写字符串
     *
     * @param str 字符串
     * @return str
     */
    public static String toUpperCase(String str) {
        if (isBlank(str)) {
            return "";
        }
        return str.toUpperCase();
    }

    /**
     * 获取字符串str含有separatorChar的个数
     *
     * @param str           字符串
     * @param separatorChar 分隔符
     * @return num
     */
    public static int getSeparatorCharNum(String str, char separatorChar) {
        if (isBlank(str)) {
            return 0;
        }
        int num = 0;
        char[] ch = str.toCharArray();
        for (char c : ch) {
            if (c == separatorChar) {
                num++;
            }
        }
        return num;
    }

    /**
     * 对象转string,可转null为""
     *
     * @param obj 对象
     * @return obj
     */
    public static String convertToString(Object obj) {
        if (obj == null) {
            return "";
        } else {
            return obj.toString();
        }
    }

    /**
     * 转义SQL通配字符
     *
     * @param str SQL
     * @return str
     */
    public static String wildcardEscape(String str) {
        if (isNotBlank(str)) {
            str = str.replace("%", "/%");
            str = str.replace("_", "/_");
            str = str.replace("/", "//");
        }
        return str;
    }

    /**
     * 把用下划线(_)分割的字符串变成驼峰显示: FOO_BAR -> fooBar; foo_bar -> fooBar
     *
     * @param name 字符串
     * @return 驼峰显示字符串
     */
    public static String camelCase(String name) {
        if (name == null) {
            return null;
        }
        StringTokenizer tokenizer = new StringTokenizer(name, "_");
        StringBuilder sb = new StringBuilder();
        boolean firstTime = true;
        while (tokenizer.hasMoreTokens()) {
            String word = tokenizer.nextToken();
            if (firstTime) {
                sb.append(word.toLowerCase());
                firstTime = false;
            } else {
                String lowercase = word.toLowerCase();
                sb.append(lowercase.substring(0, 1).toUpperCase());
                sb.append(lowercase.substring(1));
            }
        }
        return sb.toString();
    }

    /**
     * 按照字节码长度来截取字符串
     *
     * @param text        原始字符串
     * @param maxDataSize 最大字符串长度
     * @return 截取字符串
     */
    public static String limitStrOfBytes(String text, int maxDataSize) {
        byte[] bytes = new byte[0];
        try {
            bytes = nullToBlank(text).getBytes("UTF-8");
            if (bytes.length > maxDataSize) {
                byte[] newBytes = Arrays.copyOf(bytes, maxDataSize);
                return new String(newBytes, "UTF-8");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace(); // To change body of catch statement use File | Settings | File Templates.
        }
        return text;
    }

    /**
     * 截取字符串长度，若str长度大于maxSize则取maxSize长度，若str长度小于maxSize则取str原长度
     *
     * @param str     字符串
     * @param maxSize 最大
     * @return str
     */
    public static String limitStr(String str, int maxSize) {
        String limitStr = null;
        if (isBlank(str) || maxSize < 0) {
            limitStr = "";
        } else {
            limitStr = trim(str).length() >= maxSize ? trim(str).substring(0, maxSize) : trim(str);
        }
        return limitStr;
    }

    /**
     * 获取异常栈信息
     *
     * @param e           Throwable
     * @param maxByteSize 最大
     * @return result
     */
    public static String getExceptionStackTrace(Throwable e, Integer maxByteSize) {
        StringWriter sw = null;
        PrintWriter pw = null;
        try {
            sw = new StringWriter();
            pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            pw.flush();
            String result = sw.getBuffer().toString();
            if (maxByteSize != null) {
                result = limitStrOfBytes(result, maxByteSize);
            }
            return result;
        } finally {
            try {
                if (sw != null) {
                    sw.close();
                }
            } catch (IOException e1) {
                log.error(e1.getMessage(), e1);
            }
            if (pw != null) {
                pw.close();
            }
        }
    }

    /**
     * 指定字符串是否以指定的文字结尾
     *
     * @param txt 要检查的字符串
     * @param end 要比较的结尾符
     * @return 是否以指定的文字结尾
     */
    public static boolean endsWith(String txt, String... end) {
        for (String ed : end) {
            if (txt.endsWith(ed)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 是否包含中文
     *
     * @param word 文本
     * @return boolean
     */
    public static boolean isContainChinese(String word) {
        if (isNotBlank(word)) {
            Pattern p = Pattern.compile("[\u4e00-\u9fa5]+");
            Matcher m = p.matcher(word);
            return m.find();
        }
        return false;
    }

    /**
     * 判断是否字符串仅仅包含英文字母和数字
     *
     * @param str 字符串
     * @return 只包含英文字母和数字
     */
    public static boolean onlyLettersAndNumbers(String str) {
        if (isNotBlank(str)) {
            Pattern p = Pattern.compile("^[0-9a-zA-Z]+$");
            Matcher m = p.matcher(str);
            return m.find();
        }
        return false;
    }

    /**
     * 反转String
     *
     * @param str 字符串
     * @return str
     */
    public static String reverse(String str) {
        String rtnStr = null;
        if (isNotEmpty(str)) {
            StringBuffer sb = new StringBuffer(str);
            rtnStr = sb.reverse().toString();
        }
        return rtnStr;
    }

    /**
     * 左补白：如 lpad("abc",5,"x"),结果为"xxabc"
     *
     * @param str     源字符串
     * @param length  长度，字符串填补后的总长
     * @param replace 在源字符串左边补充的字符
     * @return str
     */
    public static String lpad(String str, int length, String replace) {
        if (str == null) {
            str = "";
        }
        while (getLength(str) < length) {
            str = replace + str;
        }
        return str;
    }

    /**
     * 右补白:如 rpad("abc",5,"x")结果为"abcxx"
     *
     * @param str     源字符串
     * @param length  长度，字符串填补后的总长
     * @param replace 在源字符串右边补充的字符
     * @return str
     */
    public static String rpad(String str, int length, String replace) {
        if (str == null) {
            str = "";
        }
        while (getLength(str) < length) {
            str = str + replace;
        }
        return str;
    }

    /**
     * 返回第一个不为空白字符串的字符串
     *
     * @param args 参数
     * @return String
     */
    public static String getNotBlankStr(String... args) {
        for (String arg : args) {
            if (isNotBlank(arg)) {
                return arg;
            }
        }
        return null;
    }

    /**
     * 是否中文
     *
     * @param c 参数
     * @return boolean
     * @throws UnsupportedEncodingException 编码异常
     */
    public static boolean isChineseChar(char c) throws UnsupportedEncodingException {
        // 如果字节数大于1，是汉字
        return String.valueOf(c).getBytes("UTF-8").length > 1;
    }

    /**
     * 中文处理
     *
     * @param str 字符
     * @param len 长度
     * @return result
     * @throws UnsupportedEncodingException 编码异常
     */
    public static String substringByByte(String str, int len) throws UnsupportedEncodingException {
        String result = str;
        // 原始字符不为null，也不是空字符串, 要截取的字节数大于0，且小于原始字符串的字节数
        if (isNotBlank(str) && len > 0 && len < str.getBytes("GBK").length) {
            StringBuilder buff = new StringBuilder();
            char c;
            for (int i = 0; i < len; i++) {
                c = str.charAt(i);
                if (isChineseChar(c)) {
                    --len;
                }
                if (len > i) {
                    buff.append(c);
                }
            }
            result = buff.toString();
        }
        return result;
    }

    /**
     * 将对应位置的{n}替换为对应params中的数值
     *
     * @param str    要替换的字符串
     * @param params 参数列表，对应的param[1]替换{1},param[2]替换{2}...
     * @return 替换后结果
     */
    public static String replaceStringParam(String str, List<String> params) {
        for (int i = 0; i < params.size(); i++) {
            str = str.replace("{" + i + "}", params.get(i));
        }
        return str;
    }

    /**
     * 将对应位置的{n}替换为对应params中的数值
     *
     * @param str    要替换的字符串
     * @param params 参数列表，对应的param[1]替换{1},param[2]替换{2}...
     * @return 替换后结果
     */
    public static String replaceStringParam(String str, String... params) {
        for (int i = 0; i < params.length; i++) {
            str = str.replace("{" + i + "}", params[i]);
        }
        return str;
    }

    /**
     * 计算字符串长度（中文2个字符字母1个字符）
     */
    public static int getLength(String str) {
        return str.replaceAll("[^\\x00-\\xff]", "**").length();
    }

    /**
     * 匹配
     *
     * @param regex
     *            正则表达式
     * @param str
     *            字符串
     * @return 匹配结果
     */
    public static Boolean matches(String str, String regex) {
        if (regex == null || str == null) {
            return false;
        }
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    /**
     * 分割
     *
     * @param regex
     *            正则表达式
     * @param str
     *            字符串
     * @return 分割后的字符串数组
     */
    public static String[] splitByRegex(String str, String regex) {
        if (regex == null || str == null) {
            return new String[0];
        }
        return str.split(regex, -1);
        // 不加limit，会丢失尾部的空字符串
        // return str.split(regex);
    }

    /**
     * 替换
     *
     * @param regex
     *            正则表达式
     * @param str
     *            字符串
     * @param replacement
     *            替换字符串
     * @return 替换后的字符串
     */
    public static String replaceAll(String str, String regex, String replacement) {
        if (regex == null || str == null || replacement == null) {
            return "";
        }
        return str.replaceAll(regex, replacement);
    }

    /**
     * 查找
     *
     * @param regex
     *            正则表达式
     * @param str
     *            字符串
     * @return 查找到的字符串数组
     */
    public static String[] find(String str, String regex) {
        if (regex == null || str == null) {
            return new String[0];
        }
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        List<String> list = new ArrayList<>();
        while (matcher.find()) {
            list.add(matcher.group());
        }
        return list.toArray(new String[list.size()]);
    }

    /**
     * 替换掉HTML标签方法
     */
    public static String replaceHtml(String html) {
        if (isBlank(html)){
            return "";
        }
        String regEx = "<.+?>";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(html);
        String s = m.replaceAll("");
        return s;
    }

    /**
     * 替换为手机识别的HTML，去掉样式及属性，保留回车。
     * @param html
     * @return
     */
    public static String replaceMobileHtml(String html){
        if (html == null){
            return "";
        }
        return html.replaceAll("<([a-z]+?)\\s+?.*?>", "<$1>");
    }


}
