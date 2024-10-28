package com.example.myproject.Config;

import org.springframework.lang.NonNull;
import org.springframework.lang.NonNullApi;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class InterceptorConfiguration implements HandlerInterceptor {
    @Override
    public void postHandle(@NonNull HttpServletRequest request,@NonNull HttpServletResponse response,@NonNull Object handler, ModelAndView modelAndView) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (modelAndView != null){
            if (!authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ANONYMOUS"))) {
                CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
                modelAndView.addObject("fullname", principal.getUser().getFullname());
                modelAndView.addObject("username", principal.getUser().getUsername());
                modelAndView.addObject("phone", principal.getUser().getPhone());
                modelAndView.addObject("dob", principal.getUser().getDob());
                modelAndView.addObject("gender", principal.getUser().getGender());
                modelAndView.addObject("isLogined", true);
            } else {
                modelAndView.addObject("isLogined", false);
            }
        }else {
            System.out.println("ModelAndView is null!");
        }
    }
}
