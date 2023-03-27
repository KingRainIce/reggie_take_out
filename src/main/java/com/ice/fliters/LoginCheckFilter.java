package com.ice.fliters;

import com.alibaba.fastjson.JSON;
import com.ice.common.R;
import com.ice.utils.BaseContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Title: LoginCheckFilter
 * @Auth: Ice
 * @Date: 2023/3/25 17:24
 * @Version: 1.0
 * @Desc:
 */

@Component
@WebFilter(urlPatterns = "/**")
@Slf4j
public class LoginCheckFilter implements Filter {

    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

//        1. Obtain the URI of this request
        String requestURI = request.getRequestURI();

        log.info("拦截到请求：{}", requestURI);

//        Define the request paths that do not need to be processed
        String[] urls = new String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**",
                "/common/**",
                "/user/sendMsg",
                "/user/login"
        };

//        2. Determine whether this request needs to be processed
        boolean check = check(urls, requestURI);

//        3. If no treatment is required, it will be released directly
        if (check) {
            log.info("本次请求{}不需要处理", requestURI);
            chain.doFilter(request, response);
            return;
        }

//        4. Determine the login status, and if you have logged in, let it go directly
        if (request.getSession().getAttribute("employee") != null) {
            log.info("用户已登录，用户id为：{}", request.getSession().getAttribute("employee"));

            Long employee = (Long) request.getSession().getAttribute("employee");
            BaseContext.setCurrentId(employee);

            chain.doFilter(request, response);
            return;
        }

        // 4.2、Determine the login status, and if you have logged in, you will be allowed to go directly
        if (request.getSession().getAttribute("user") != null) {
            log.info("用户已登录，用户id为：{}", request.getSession().getAttribute("user"));

            Long userId = (Long) request.getSession().getAttribute("user");

            BaseContext.setCurrentId(userId);

            chain.doFilter(request, response);
            return;
        }

        log.info("用户未登录");

//        5. If you are not logged in, the result of not logging in is returned, and the response data is streamed to the client page through the output
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
        return;
    }

    /**
     * @Auth: Ice
     * @Date: 2023/3/25 17:29
     * @Desc: to check whether the request needs to be released
     */
    public boolean check(String[] urls, String requestURI) {
        for (String url : urls) {
            boolean match = PATH_MATCHER.match(url, requestURI);
            if (match) {
                return true;
            }
        }
        return false;
    }
}
