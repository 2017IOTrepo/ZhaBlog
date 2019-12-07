package nuc.iot.blog.interceptor;

import nuc.iot.blog.mapper.UserMapper;
import nuc.iot.blog.model.User;
import nuc.iot.blog.util.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by codedrinker on 2019/5/16.
 */
@Service
public class SessionInterceptor implements HandlerInterceptor {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 没有登录的时候也可以查看导航
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0) {
            String ans = CookieUtils.getValue(cookies, "user_id");
            User user = userMapper.findById(Integer.valueOf(ans));
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {

    }
}
