package com.kenan.shrio.shiro;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.session.mgt.DelegatingSession;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.WebSessionKey;
import org.apache.shiro.web.session.mgt.WebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

/**
 * Created by maxb on 2020/2/20.
 */
public class DefaultHeaderSessionManager extends DefaultSessionManager implements WebSessionManager {

    private static final Logger log  = LoggerFactory.getLogger(DefaultHeaderSessionManager.class);

    @Override
    protected Session doCreateSession(SessionContext context) {
        Session s = this.newSessionInstance(context);
        if(log.isTraceEnabled()) {
            log.trace("Creating session for host {}", s.getHost());
        }
        HttpServletRequest request = WebUtils.getHttpRequest(context);
        HttpServletResponse response = WebUtils.getHttpResponse(context);
        s.setAttribute("req",request);
        this.create(s);
        return s;
    }

    @Override
    public boolean isServletContainerSessions() {
        return false;
    }
}
