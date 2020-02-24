package com.kenan.shrio.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by maxb on 2020/2/21.
 */
public class HttpReqUtils {

    /**
     * 获取url请求前缀
     * @explain http://localhost:8080/test
     * @param request request对象
     * @return
     */
    public static String getRequestPrefix (HttpServletRequest request) {
        // 网络协议
        String networkProtocol = request.getScheme();
        // 网络ip
        String ip = request.getServerName();
        // 端口号
        int port = request.getServerPort();
        // 项目发布名称
        String webApp = request.getContextPath();
        String urlPrefix = networkProtocol + "://" + ip + ":" + port + webApp;
        return urlPrefix;
    }

    public static  Map<String, String> getParametersFromPage(HttpServletRequest request) {
        Map<String, String> param = new HashMap<String, String>();
        String varName = "default";
        String varValue = null;
        StringBuilder log = new StringBuilder("参数打印:");
        Enumeration e = request.getParameterNames();
        while (e.hasMoreElements()) { // 循环获取参数
            Object obj = e.nextElement();
            varName = obj.toString();
            varValue = request.getParameter(varName);
            param.put(varName, varValue);
            log.append(varName).append("=").append(varValue);
        }

        return param;
    }
}
