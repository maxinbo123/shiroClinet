package com.kenan.shrio.utils;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * Created by maxb on 2020/2/20.
 */
public class UuidSessionIdGenerator implements SessionIdGenerator {

    @Override
    public Serializable generateId(Session session) {
        HttpServletRequest request = (HttpServletRequest)session.getAttribute("req");
        Serializable res = "180518585838825118_9d6d4261090a434580f9b0820fa40752";
        session.removeAttribute("req");
        session.removeAttribute("res");
        return res;
    }
}
