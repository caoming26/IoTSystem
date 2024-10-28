package edu.tdtu.iot.iotsystem.Config;

import edu.tdtu.iot.iotsystem.Entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class InterceptorConfig implements HandlerInterceptor {
    @Override
    public void postHandle(@NonNull HttpServletRequest request,@NonNull HttpServletResponse response,@NonNull Object handler, ModelAndView modelAndView) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (modelAndView != null){
            if (!authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ANONYMOUS"))) {
                User principal = (User) authentication.getPrincipal();
                modelAndView.addObject("name", principal.getName());
                modelAndView.addObject("username", principal.getUsername());
                modelAndView.addObject("phone", principal.getPhone());
                modelAndView.addObject("email", principal.getEmail());
                
                modelAndView.addObject("isLogined", true);
            } else {
                modelAndView.addObject("isLogined", false);
            }
        }else {
            System.out.println("ModelAndView is null!");
        }
    }
}
