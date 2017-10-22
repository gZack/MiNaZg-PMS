package com.minazg.configuration.security;

import com.minazg.model.UserRoleType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.omg.PortableInterceptor.ServerRequestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final Log logger = LogFactory.getLog(this.getClass());

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    private final static String ROLE_PREFIX = "ROLE_";

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {

        handle(request,response,authentication);
        clearAuthenticationAttributes(request);

    }

    private void handle(final HttpServletRequest request, final HttpServletResponse response,
                        final Authentication authentication) throws IOException{
        final String targetUrl = determineTargetUrl(authentication);

        if(response.isCommitted()){
            logger.debug("response has already been committed. Unable to redirect to " + targetUrl);
            return;
        }

        redirectStrategy.sendRedirect(request,response,targetUrl);

    }

    private String determineTargetUrl(final Authentication authentication){

        String targetUrl = null;

        final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        List<String> roles = new ArrayList<>();

        for(final GrantedAuthority grantedAuthority : authorities){
            roles.add(grantedAuthority.getAuthority());
        }

        if (isAdmin(roles)) {
            targetUrl = "/list";
        } else if(isPM(roles)){
            targetUrl = "/project";
        } else if (isDeveloper(roles)){
            targetUrl = "/task";
        } else {
            targetUrl = "/Access_Denied";
        }

        return targetUrl;
    }

    private boolean isAdmin(List<String> roles){
        return roles.contains(ROLE_PREFIX + UserRoleType.ADMIN.getUserRoleType());
    }

    private boolean isPM(List<String> roles){
        return roles.contains(ROLE_PREFIX + UserRoleType.PROJECT_MANAGER.getUserRoleType());
    }

    private boolean isDeveloper(List<String> roles){
        return roles.contains(ROLE_PREFIX + UserRoleType.PROJECT_MANAGER.getUserRoleType());
    }

    private boolean isClient(List<String> roles){
        return roles.contains(ROLE_PREFIX + UserRoleType.CLIENT.getUserRoleType());
    }

    private final void clearAuthenticationAttributes(final HttpServletRequest request){

        final HttpSession httpSession = request.getSession(false);

        if(httpSession == null){
            return;
        }

        httpSession.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }

    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }

    public RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }
}
