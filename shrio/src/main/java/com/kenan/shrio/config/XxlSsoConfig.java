package com.kenan.shrio.config;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xuxueli 2018-04-03 20:41:07
 */
@Configuration
public class XxlSsoConfig implements InitializingBean, DisposableBean {
    private String redisAdress="" ;

    private String redisPassword="";

    @Override
    public void afterPropertiesSet() throws Exception {
        JedisUtils.initJedisCluster(redisAdress,redisPassword);
    }

    @Override
    public void destroy() throws Exception {
        JedisUtils.close();
    }

}
