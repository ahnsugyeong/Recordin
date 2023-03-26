package com.example.toyproject;

import com.example.toyproject.domain.Board;
import com.example.toyproject.domain.Member;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
public class SignInCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute(SessionConst.SIGN_IN_MEMBER) == null) {
            log.info("미인증 사용자 요청");
            response.sendRedirect("/member/sign-in?redirectURL=" + requestURI);
            return false;
        }
        return true;
    }

}
