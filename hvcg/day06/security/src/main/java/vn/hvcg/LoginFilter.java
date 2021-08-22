package vn.hvcg.security;

import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;

public class LoginFilter extends GenericFilterBean {

    private static final Set<String> authenticatedSession = new HashSet<>();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String sessionId = ((HttpServletRequest)servletRequest).getSession().getId();
        if (authenticatedSession.contains(sessionId)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            // Base64.encode(username:password");
            String basicAuthenBase64 = ((HttpServletRequest) servletRequest).getHeader("authorization");
            String[] usernameAndPassword = new String(Base64.getDecoder().decode(basicAuthenBase64.getBytes())).split(":");
            String username = usernameAndPassword[0];
            String password = usernameAndPassword[1];

            if (isOK(username, password)) {
                authenticatedSession.add(sessionId);
            } else {
                // return immediately !
                ((HttpServletResponse)servletResponse).sendError(403);
                return;
            }
        }
    }

    private boolean isOK(String username, String password) {
        return false;
    }
}
