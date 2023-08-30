package com.example.dhlee128.filter;

import org.springframework.stereotype.Component;
import org.springframework.util.PatternMatchUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class AuthFilter implements Filter {

    private static final String[] whitelist = {"/", "/login", "/logout", "/join", "/css/*", "/js/*", "/images/*"};
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = ((HttpServletRequest)request).getRequestURI();
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        if(isLoginCheckPath(requestURI)) {
            HttpSession session = httpRequest.getSession(false);
            if(session == null || session.getAttribute("sessionMember") == null) {
                httpResponse.sendRedirect("/login?redirectURL="+requestURI);
                return;
            }
        }

        chain.doFilter(request, response);
    }

    private boolean isLoginCheckPath(String requestURI) {
        return !PatternMatchUtils.simpleMatch(whitelist, requestURI);
    }
}
