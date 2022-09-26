package utils;

import java.nio.charset.StandardCharsets;

public class StringUtil {
    public static String getUTF8String(String value) {
        try {
            return new String(value.getBytes("ISO8859-1"), StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "unknown";
    }
}
