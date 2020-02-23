package com.kenan.shrio.config;

import com.kenan.shrio.entity.UserData;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ThreadContext;
import org.apache.shiro.web.subject.WebSubject;
import org.springframework.beans.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by maxb on 2020/2/20.
 */
public class ShiroUtils {


    public static void loginToken(HttpServletRequest request, HttpServletResponse response, UserData userData){
        PrincipalCollection principals = new SimplePrincipalCollection(
                userData, "shiroUserRealm");
        WebSubject.Builder builder = new WebSubject.Builder(request, response);
        builder.principals(principals);
        // 已认证
        builder.authenticated(true);
        // 保证shiro创建的sessionId与原有的sessionId不一致，统一取原有sessionId
//        String sessionId = request.getSession().getId();
        builder.sessionId("shiro:session:_2626ccae-6522-49b8-81ae-0ee5a38e9746");
        WebSubject subject = builder.buildWebSubject();
        ThreadContext.bind(subject);
    }
}
