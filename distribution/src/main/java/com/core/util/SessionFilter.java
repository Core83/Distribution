package com.core.util;

import com.core.WeChat.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by core on 15/11/16.
 */
public class SessionFilter implements HandlerInterceptor {
    private static final Logger log = LoggerFactory.getLogger(SessionFilter.class);
    private List<String> excludes;

    public int getSize() {
        return this.excludes.size();
    }

    public List<String> getExcludes() {
        return excludes;
    }

    public void setExcludes(List<String> excludes) {
        this.excludes = excludes;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        if (excludes != null) {
            if (log.isInfoEnabled()) {
                log.info(request.getRequestURI());
            }
            for(int i = 0; i < getSize(); i++) {
                String exclude = excludes.get(i);
                if (request.getRequestURI().endsWith(exclude)) {
                    return true;
                } else {
                    if (i < getSize() - 1)
                        continue;
                    response.addHeader("P3P", "CP=CAO PSA OUR");
                    if (request.getSession().getAttribute(Config.SESSION_FRAME) == null) {
                        if (log.isInfoEnabled()) {
                            log.info("---no logon, redirct logon web---");
                        }
                        StringBuffer sb = new StringBuffer();
                        sb.append("http://").append(request.getServerName()).append(":").append(request.getServerPort()).append(request.getContextPath()).append("/login");
                       log.debug("==============="+sb.toString()+"===============");
                        response.sendRedirect(sb.toString());
                        return false;
                    } else {

                        return true;
                    }

                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
