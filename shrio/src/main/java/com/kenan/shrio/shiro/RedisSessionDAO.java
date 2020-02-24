package com.kenan.shrio.shiro;

import com.kenan.shrio.config.JedisUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.Serializable;

/**
 * Created by maxb on 2019/12/17.
 */
public class RedisSessionDAO extends CachingSessionDAO {


    private static final Logger log = LoggerFactory.getLogger(UserRealm.class);

    // 会话key
    private final static String SHIRO_SESSION_ID = "kenan:";
    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = session.getId();
        if(session.getId() == null){
            sessionId = generateSessionId(session);
            assignSessionId(session, sessionId);
        }
        JedisUtils.setObjectValue(SHIRO_SESSION_ID + "_" + sessionId, session, (int) session.getTimeout() / 1000);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        Session session = (Session) JedisUtils.getObjectValue(SHIRO_SESSION_ID + "_" + sessionId);
        return session;
    }

    @Override
    protected void doUpdate(Session session) {
        if(session instanceof ValidatingSession && !((ValidatingSession)session).isValid()) {
            return;
        }
        JedisUtils.setObjectValue(SHIRO_SESSION_ID + "_" + session.getId(), session, (int) session.getTimeout() / 1000);
    }

    @Override
    protected void doDelete(Session session) {
        String sessionId = session.getId().toString();
        // 删除session
        JedisUtils.del(SHIRO_SESSION_ID + "_" + sessionId);
    }
    @Override
    public Session readSession(Serializable sessionId) throws UnknownSessionException {
        return doReadSession(sessionId);
    }

}
