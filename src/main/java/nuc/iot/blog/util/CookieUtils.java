package nuc.iot.blog.util;

import nuc.iot.blog.model.User;

import javax.servlet.http.Cookie;

public class CookieUtils {
    public static String getValue(Cookie[] cookies, String key) {
        if (cookies == null) {
            return null;
        }

        for (Cookie cookie :
                cookies) {
            if (cookie.getName().equals(key)) {
                return cookie.getValue();
            }
        }

        return null;
    }
}
