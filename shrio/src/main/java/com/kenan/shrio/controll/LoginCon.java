package com.kenan.shrio.controll;

import com.kenan.shrio.config.ShiroUtils;
import com.kenan.shrio.entity.UserData;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by maxb on 2020/2/16.
 */
@Controller
public class LoginCon extends BaseController{

    @GetMapping("/login")
    @ResponseBody
    public String login(HttpServletRequest request, HttpServletResponse response){
        Map<String, String> param = getParametersFromPage(request);
        UsernamePasswordToken token = new UsernamePasswordToken(param.get("name"), param.get("pass"));
        UserData userData = new UserData(param.get("name"),param.get("pass"));
        //Subject subject = SecurityUtils.getSubject();
        request.setAttribute("userId","180518585838825118_9kenan4261090a434580f9b0820fa40752");
        try {
            ShiroUtils.loginToken(request,response,userData);
            return "成功";
        }catch (Exception e){
            return "异常";
        }
    }
    @GetMapping("/getInfo")
    @ResponseBody
    public String getInfo(HttpServletRequest request, HttpServletResponse response){
        Map<String, String> param = getParametersFromPage(request);
     return param.get("param");
    }

    @GetMapping("/logout")
    @ResponseBody
    public String logout(HttpServletRequest request, HttpServletResponse response){
        Map<String, String> param = getParametersFromPage(request);
        return param.get("param");
    }

    @GetMapping("/cha")
    @RequiresPermissions("query")
    @ResponseBody
    public String query(HttpServletRequest request, HttpServletResponse response){
        Map<String, String> param = getParametersFromPage(request);
        return param.get("param");
    }
    @RequiresPermissions("delete")
    @GetMapping("/delete")
    @ResponseBody
    public String delete(HttpServletRequest request, HttpServletResponse response){
        Map<String, String> param = getParametersFromPage(request);
        return param.get("param");
    }
}
