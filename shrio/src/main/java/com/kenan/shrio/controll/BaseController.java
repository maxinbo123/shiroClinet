package com.kenan.shrio.controll;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings({ "rawtypes" })
public class BaseController {

    private static final Logger logger = LoggerFactory.getLogger("csopmweb"); // 信息日志

    /**
     * 处理页面传入参数
     * @param request
     * @return Map<String, String>:页面传参的集合
     */
    public Map<String, String> getParametersFromPage(HttpServletRequest request) {
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
        if (logger.isDebugEnabled()) {
            logger.debug(log.toString());
        }
        return param;
    }

}
