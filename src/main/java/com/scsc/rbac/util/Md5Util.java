package com.scsc.rbac.util;

import org.springframework.util.DigestUtils;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * MD5工具类
 * @author qing
 * @date 2019/4/19 10:07
 */
public class Md5Util {
    private static final String slat = "scsc.tech";
    public static String getMD5String(String str) {
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8位字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            //一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方）
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 获取加盐的MD5字符串
     */
    public static String getMD5WithSalt(String content ,String salt) {
        return getMD5String(getMD5String(content) + salt);
    }
    public static String getMD5(String str) {
        String base = str + slat;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }
    public static void main(String[] args) {
        String str = "adminadmin";
        String md5= getMD5(str);
        System.out.println(md5);
    }
}
