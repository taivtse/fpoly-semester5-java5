package vn.edu.fpt.util;

import org.springframework.util.DigestUtils;

import java.text.Normalizer;
import java.util.Random;
import java.util.regex.Pattern;

public class StringUtil {

    public static String randomString(int length) {
        String textSample = "ABCDEFGHIJKLMNOPQRSTUVWXYZ01234567890123456789abcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        String randomString = "";
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(textSample.length());
            randomString += textSample.charAt(index);
        }
        return randomString;
    }

    public static String randomNumber(int length) {
        String textSample = "0123456789";
        Random random = new Random();
        String randomString = "";
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(textSample.length());
            randomString += textSample.charAt(index);
        }
        return randomString;
    }

    public static String covertUnicodeToASCIIString(String str) {
        try {
            String temp = Normalizer.normalize(str, Normalizer.Form.NFD);
            Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
            return pattern.matcher(temp).replaceAll("").toLowerCase().replaceAll("đ", "d");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

    public static String generateMd5Password(int length) {
        String password = randomString(length);
        return DigestUtils.md5DigestAsHex(password.getBytes());
    }
}