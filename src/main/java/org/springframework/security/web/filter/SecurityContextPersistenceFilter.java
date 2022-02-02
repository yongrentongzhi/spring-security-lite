package org.springframework.security.web.filter;


import org.springframework.security.context.SecurityContext;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.context.SecurityContextRepository;
import org.springframework.security.context.impl.HttpSessionSecurityContextRepository;
import org.springframework.security.web.HttpRequestResponseHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 在一个请求到来时，使用配置的SecurityContextRepository从httpSession中获得SecurityContext并存入SecurityContextHolder ，
 * 在请求完成后，从SecurityContextHolder中获取SecurityContext并存入HttpSession中，同时清除SecurityContextHolder中的登录用户信息。
 * 默认情况下，它使用HttpSessionSecurityContextRepository 。
 */
public class SecurityContextPersistenceFilter extends GenericFilterBean {

    static final String FILTER_APPLIED = "__spring_security_scpf_applied";

    private SecurityContextRepository repo;

    private boolean forceEagerSessionCreation = false;

    public SecurityContextPersistenceFilter() {
        this(new HttpSessionSecurityContextRepository());
    }

    public SecurityContextPersistenceFilter(SecurityContextRepository repo) {
        this.repo = repo;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        // 确保每个请求只应用一次过滤器
        if (request.getAttribute(FILTER_APPLIED) != null) {

            chain.doFilter(request, response);
            return;
        }

        final boolean debug = logger.isDebugEnabled();

        request.setAttribute(FILTER_APPLIED, Boolean.TRUE);

        if (forceEagerSessionCreation) {
            HttpSession session = request.getSession();

            if (debug && session.isNew()) {
                logger.debug("Eagerly created session: " + session.getId());
            }
        }

        HttpRequestResponseHolder holder = new HttpRequestResponseHolder(request,
                response);
        SecurityContext contextBeforeChainExecution = repo.loadContext(holder);

        try {
            SecurityContextHolder.setContext(contextBeforeChainExecution);

            chain.doFilter(holder.getRequest(), holder.getResponse());

        } finally {
            SecurityContext contextAfterChainExecution = SecurityContextHolder
                    .getContext();
            // Crucial removal of SecurityContextHolder contents - do this before anything
            // else.
            SecurityContextHolder.clearContext();
            repo.saveContext(contextAfterChainExecution, holder.getRequest(),
                    holder.getResponse());
            request.removeAttribute(FILTER_APPLIED);

            if (debug) {
                logger.debug("SecurityContextHolder now cleared, as request processing completed");
            }
        }
    }

    public void setForceEagerSessionCreation(boolean forceEagerSessionCreation) {
        this.forceEagerSessionCreation = forceEagerSessionCreation;
    }
}
