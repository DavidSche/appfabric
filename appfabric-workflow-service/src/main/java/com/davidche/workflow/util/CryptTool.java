package com.davidche.workflow.util;

import org.apache.commons.lang3.StringUtils;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.net.URL;
import java.net.URLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

/**
 * 常用工具包。包括生成各种密码随机串，加密解密，编码解码，执行url等
 */
public class CryptTool {

    public static final String EMPTY = "";
    /**
     * <p>Random object used by random method. This has to be not local
     * to the random method so as to not return the same value in the
     * same millisecond.</p>
     */
    private static final Random RANDOM = new Random();

    /**
     * 生成密码.
     *
     * @param count   密码位数
     * @param letters 是否包含字符
     * @param numbers 是否包含数字
     * @return String password
     */
    private static String getPassword(int count, boolean letters, boolean numbers) {
        return random(count, letters, numbers);
    }

    /**
     * 生成字符数字混合的密码.
     *
     * @param count 密码位数
     * @return String password
     */
    public static String getPassword(int count) {
        return getPassword(count, true, true);
    }

    /**
     * 生成纯数字密码.
     *
     * @param count 密码位数
     * @return String password
     */
    public static String getPasswordOfNumber(int count) {
        return getPassword(count, false, true);
    }

    /**
     * 生成纯字符密码.
     *
     * @param count 密码位数
     * @return String password
     */
    public static String getPasswordOfCharacter(int count) {
        return getPassword(count, true, false);
    }

    /**
     * 生成3DES密钥.
     *
     * @param key_byte seed key
     * @return javax.crypto.SecretKey Generated DES key
     * @throws Exception
     */
    public static javax.crypto.SecretKey genDESKey(byte[] key_byte) throws Exception {
        SecretKey k = new SecretKeySpec(key_byte, "DESede");
        return k;
    }

    /**
     * 3DES 解密(byte[]).
     *
     * @param key   SecretKey
     * @param crypt byte[]
     * @return byte[]
     * @throws Exception
     */
    public static byte[] desDecrypt(javax.crypto.SecretKey key, byte[] crypt) throws Exception {
        javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance("DESede");
        cipher.init(javax.crypto.Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(crypt);
    }

    /**
     * 3DES 解密(String).
     *
     * @param key   SecretKey
     * @param crypt byte[]
     * @return byte[]
     * @throws Exception
     */
    public static String desDecrypt(javax.crypto.SecretKey key, String crypt) throws Exception {
        return new String(desDecrypt(key, crypt.getBytes()));
    }

    /**
     * 3DES加密(byte[]).
     *
     * @param key SecretKey
     * @param src byte[]
     * @return byte[]
     * @throws Exception
     */
    public static byte[] desEncrypt(javax.crypto.SecretKey key, byte[] src) throws Exception {
        javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance("DESede");
        cipher.init(javax.crypto.Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(src);
    }

    /**
     * 3DES加密(String).
     *
     * @param key SecretKey
     * @param src byte[]
     * @return byte[]
     * @throws Exception
     */
    public static String desEncrypt(javax.crypto.SecretKey key, String src) throws Exception {
        return new String(desEncrypt(key, src.getBytes()));
    }

    /**
     * MD5 摘要计算(byte[]).
     *
     * @param src byte[]
     * @return byte[] 16 bit digest
     * @throws Exception
     */
    public static byte[] md5Digest(byte[] src) throws Exception {
        java.security.MessageDigest alg = java.security.MessageDigest.getInstance("MD5");
        // MD5 is 16 bit message digest
        return alg.digest(src);
    }

    /**
     * MD5 摘要计算(String).
     *
     * @param src String
     * @return String
     * @throws Exception
     */
    public static String md5Digest(String src) throws Exception {
        return new String(md5Digest(src.getBytes()));
    }

//	/**
//	 * BASE64 编码.
//	 * @param src String inputed string
//	 * @return String returned string
//	 */
//	public static String base64Encode(String src) {
//		sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
//		return encoder.encode(src.getBytes());
//	}

//	/**
//	 * BASE64 编码(byte[]).
//	 * @param src byte[] inputed string
//	 * @return String returned string
//	 */
//	public static String base64Encode(byte[] src) {
//		sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
//		return encoder.encode(src);
//	}
//
//	/**
//	 * BASE64 解码.
//	 * @param src String inputed string
//	 * @return String returned string
//	 */
//	public static String base64Decode(String src) {
//		sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
//		try {
//			return new String(decoder.decodeBuffer(src));
//		} catch (Exception ex) {
//			return null;
//		}
//	}
//
//	/**
//	 * BASE64 解码(to byte[]).
//	 * @param src String inputed string
//	 * @return String returned string
//	 */
//	public static byte[] base64DecodeToBytes(String src) {
//		sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
//		try {
//			return decoder.decodeBuffer(src);
//		} catch (Exception ex) {
//			return null;
//		}
//	}

    /**
     * 对给定字符进行 URL 编码GB2312.
     *
     * @param src String
     * @return String
     */
    public static String urlEncode(String src) {
        return urlEncode(src, "GB2312");
    }

    /**
     * 对给定字符进行 URL 解码GB2312
     *
     * @param value 解码前的字符串
     * @return 解码后的字符串
     */
    public static String urlDecode(String value) {
        return urlDecode(value, "GB2312");
    }

    /**
     * 对给定字符进行 URL 编码.
     *
     * @param src   String
     * @param coder 字符编码格式（GB2312/GBK）
     * @return String
     */
    public static String urlEncode(String src, String coder) {
        try {
            src = java.net.URLEncoder.encode(src, coder);
            return src;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return src;
    }

    /**
     * 对给定字符进行 URL 解码
     *
     * @param value 解码前的字符串
     * @param coder 字符编码格式（GB2312/GBK）
     * @return 解码后的字符串
     */
    public static String urlDecode(String value, String coder) {
        try {
            return java.net.URLDecoder.decode(value, coder);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return value;
    }

    /**
     * 执行给定url
     *
     * @param urlString 给定的url
     * @return 返回值
     */
    public static String executeURL(String urlString) throws Exception {
        StringBuffer document = new StringBuffer();
        URL url = new URL(urlString);
        URLConnection conn = url.openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line = null;
        while ((line = reader.readLine()) != null)
            document.append(line + "\n");
        reader.close();
        return document.toString();
    }


    /**
     * <p>Creates a random string whose length is the number of characters
     * specified.</p>
     *
     * <p>Characters will be chosen from the set of alpha-numeric
     * characters as indicated by the arguments.</p>
     *
     * @param count   the length of random string to create
     * @param letters if {@code true}, generated string may include
     *                alphabetic characters
     * @param numbers if {@code true}, generated string may include
     *                numeric characters
     * @return the random string
     */
    public static String random(final int count, final boolean letters, final boolean numbers) {
        return random(count, 0, 0, letters, numbers);
    }

    /**
     * <p>Creates a random string whose length is the number of characters
     * specified.</p>
     *
     * <p>Characters will be chosen from the set of alpha-numeric
     * characters as indicated by the arguments.</p>
     *
     * @param count   the length of random string to create
     * @param start   the position in set of chars to start at
     * @param end     the position in set of chars to end before
     * @param letters if {@code true}, generated string may include
     *                alphabetic characters
     * @param numbers if {@code true}, generated string may include
     *                numeric characters
     * @return the random string
     */
    public static String random(final int count, final int start, final int end, final boolean letters, final boolean numbers) {
        return random(count, start, end, letters, numbers, null, RANDOM);
    }

    /**
     * <p>Creates a random string based on a variety of options, using
     * supplied source of randomness.</p>
     *
     * <p>If start and end are both {@code 0}, start and end are set
     * to {@code ' '} and {@code 'z'}, the ASCII printable
     * characters, will be used, unless letters and numbers are both
     * {@code false}, in which case, start and end are set to
     * {@code 0} and {@link Character#MAX_CODE_POINT}.
     *
     * <p>If set is not {@code null}, characters between start and
     * end are chosen.</p>
     *
     * <p>This method accepts a user-supplied {@link Random}
     * instance to use as a source of randomness. By seeding a single
     * {@link Random} instance with a fixed seed and using it for each call,
     * the same random sequence of strings can be generated repeatedly
     * and predictably.</p>
     *
     * @param count   the length of random string to create
     * @param start   the position in set of chars to start at (inclusive)
     * @param end     the position in set of chars to end before (exclusive)
     * @param letters only allow letters?
     * @param numbers only allow numbers?
     * @param chars   the set of chars to choose randoms from, must not be empty.
     *                If {@code null}, then it will use the set of all chars.
     * @param random  a source of randomness.
     * @return the random string
     * @throws ArrayIndexOutOfBoundsException if there are not
     *                                        {@code (end - start) + 1} characters in the set array.
     * @throws IllegalArgumentException       if {@code count} &lt; 0 or the provided chars array is empty.
     * @since 2.0
     */
    public static String random(int count, int start, int end, final boolean letters, final boolean numbers,
                                final char[] chars, final Random random) {
        if (count == 0) {
            return EMPTY;
        } else if (count < 0) {
            throw new IllegalArgumentException("Requested random string length " + count + " is less than 0.");
        }
        if (chars != null && chars.length == 0) {
            throw new IllegalArgumentException("The chars array must not be empty");
        }

        if (start == 0 && end == 0) {
            if (chars != null) {
                end = chars.length;
            } else {
                if (!letters && !numbers) {
                    end = Character.MAX_CODE_POINT;
                } else {
                    end = 'z' + 1;
                    start = ' ';
                }
            }
        } else {
            if (end <= start) {
                throw new IllegalArgumentException("Parameter end (" + end + ") must be greater than start (" + start + ")");
            }
        }

        final int zero_digit_ascii = 48;
        final int first_letter_ascii = 65;

        if (chars == null && (numbers && end <= zero_digit_ascii
                || letters && end <= first_letter_ascii)) {
            throw new IllegalArgumentException("Parameter end (" + end + ") must be greater then (" + zero_digit_ascii + ") for generating digits " +
                    "or greater then (" + first_letter_ascii + ") for generating letters.");
        }

        final StringBuilder builder = new StringBuilder(count);
        final int gap = end - start;

        while (count-- != 0) {
            int codePoint;
            if (chars == null) {
                codePoint = random.nextInt(gap) + start;

                switch (Character.getType(codePoint)) {
                    case Character.UNASSIGNED:
                    case Character.PRIVATE_USE:
                    case Character.SURROGATE:
                        count++;
                        continue;
                }

            } else {
                codePoint = chars[random.nextInt(gap) + start];
            }

            final int numberOfChars = Character.charCount(codePoint);
            if (count == 0 && numberOfChars > 1) {
                count++;
                continue;
            }

            if (letters && Character.isLetter(codePoint)
                    || numbers && Character.isDigit(codePoint)
                    || !letters && !numbers) {
                builder.appendCodePoint(codePoint);

                if (numberOfChars == 2) {
                    count--;
                }

            } else {
                count++;
            }
        }
        return builder.toString();
    }

    /**
     * <p>Creates a random string whose length is the number of characters
     * specified.</p>
     *
     * <p>Characters will be chosen from the set of characters
     * specified by the string, must not be empty.
     * If null, the set of all characters is used.</p>
     *
     * @param count the length of random string to create
     * @param chars the String containing the set of characters to use,
     *              may be null, but must not be empty
     * @return the random string
     * @throws IllegalArgumentException if {@code count} &lt; 0 or the string is empty.
     */
    public static String random(final int count, final String chars) {
        if (chars == null) {
            return random(count, 0, 0, false, false, null, RANDOM);
        }
        return random(count, chars.toCharArray());
    }

    /**
     * <p>Creates a random string whose length is the number of characters
     * specified.</p>
     *
     * <p>Characters will be chosen from the set of characters specified.</p>
     *
     * @param count the length of random string to create
     * @param chars the character array containing the set of characters to use,
     *              may be null
     * @return the random string
     * @throws IllegalArgumentException if {@code count} &lt; 0.
     */
    public static String random(final int count, final char... chars) {
        if (chars == null) {
            return random(count, 0, 0, false, false, null, RANDOM);
        }
        return random(count, 0, chars.length, false, false, chars, RANDOM);
    }

}