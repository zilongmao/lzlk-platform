package com.lzlk.admin.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.Connector;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.util.unit.DataSize;

import javax.annotation.Resource;
import javax.servlet.MultipartConfigElement;

/**
 * @author 邻座旅客
 * @Description TODO
 * @Date 2019/5/29 13:38
 * @Created by 湖南达联
 */
@Configuration
@Slf4j
public class TomcatConfig {

    @Resource
    private ApplicationContext applicationContext;

    private String port = "8080";

    private String acceptorThreadCount = "2";

    private String minSpareThreads ="25";

    private String maxSpareThreads = "50";

    private String maxThreads = "200";

    private String maxConnections = "10000";

    private String protocol = "org.apache.coyote.http11.Http11AprProtocol";

    private String redirectPort = "8443";

    private String compression = "off";

    private String connectionTimeout = "20000";

    private String maxFileSize = "2MB";

    private String maxRequestSize = "2MB";

    @Bean
    public ServletWebServerFactory servletContainer() {
        this.init();
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        tomcat.addConnectorCustomizers(new GwsTomcatConnectionCustomizer());
        log.info("==========tomcat  配置成功============");
        log.info("tomcat config : {}",this.toString());
        return tomcat;
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        this.init();
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //  单个数据大小
        factory.setMaxFileSize(DataSize.parse(maxFileSize)); // KB,MB
        /// 总上传数据大小
        factory.setMaxRequestSize(DataSize.parse(maxRequestSize));
        log.info("==========tomcat MultipartConfigElement 配置成功============");
        return factory.createMultipartConfig();
    }


    private void init(){
         Environment environment = applicationContext.getEnvironment();
         String port = environment.getProperty("spring.boot.tomcat.server.port");
         if(StringUtils.isNotBlank(port)){
            this.port = port;
         }

        String acceptorThreadCount = environment.getProperty("spring.boot.tomcat.server.acceptorThreadCount");
        if(StringUtils.isNotBlank(acceptorThreadCount)){
            this.acceptorThreadCount = acceptorThreadCount;
        }

        String minSpareThreads = environment.getProperty("spring.boot.tomcat.server.minSpareThreads");
        if(StringUtils.isNotBlank(minSpareThreads)){
            this.minSpareThreads = minSpareThreads;
        }

        String maxSpareThreads = environment.getProperty("spring.boot.tomcat.server.maxSpareThreads");
        if(StringUtils.isNotBlank(maxSpareThreads)){
            this.maxSpareThreads = maxSpareThreads;
        }

        String maxThreads = environment.getProperty("spring.boot.tomcat.server.maxThreads");
        if(StringUtils.isNotBlank(maxThreads)){
            this.maxThreads = maxThreads;
        }

        String maxConnections = environment.getProperty("spring.boot.tomcat.server.maxConnections");
        if(StringUtils.isNotBlank(maxConnections)){
            this.maxConnections = maxConnections;
        }

        String protocol = environment.getProperty("spring.boot.tomcat.server.protocol");
        if(StringUtils.isNotBlank(protocol)){
            this.protocol = protocol;
        }

        String redirectPort = environment.getProperty("spring.boot.tomcat.server.redirectPort");
        if(StringUtils.isNotBlank(redirectPort)){
            this.redirectPort = redirectPort;
        }

        String compression = environment.getProperty("spring.boot.tomcat.server.compression");
        if(StringUtils.isNotBlank(compression)){
            this.compression = compression;
        }

        String connectionTimeout = environment.getProperty("spring.boot.tomcat.server.connectionTimeout");
        if(StringUtils.isNotBlank(connectionTimeout)){
            this.connectionTimeout = connectionTimeout;
        }

        String maxFileSize = environment.getProperty("spring.boot.tomcat.server.maxFileSize");
        if(StringUtils.isNotBlank(maxFileSize)){
            this.maxFileSize = maxFileSize;
        }

        String maxRequestSize = environment.getProperty("spring.boot.tomcat.server.maxRequestSize");
        if(StringUtils.isNotBlank(maxRequestSize)){
            this.maxRequestSize = maxRequestSize;
        }

    }

    @Override
    public String toString() {
        return "TomcatConfig{" +
                "port='" + port + '\'' +
                ", acceptorThreadCount='" + acceptorThreadCount + '\'' +
                ", minSpareThreads='" + minSpareThreads + '\'' +
                ", maxSpareThreads='" + maxSpareThreads + '\'' +
                ", maxThreads='" + maxThreads + '\'' +
                ", maxConnections='" + maxConnections + '\'' +
                ", protocol='" + protocol + '\'' +
                ", redirectPort='" + redirectPort + '\'' +
                ", compression='" + compression + '\'' +
                ", connectionTimeout='" + connectionTimeout + '\'' +
                ", MaxFileSize='" + maxFileSize + '\'' +
                ", MaxRequestSize='" + maxRequestSize + '\'' +
                '}';
    }

    /**
     *
     * 默认http连接
     *
     * @version
     * @author liuyi  2016年7月20日 下午7:59:41
     *
     */
     class GwsTomcatConnectionCustomizer implements TomcatConnectorCustomizer {

        public GwsTomcatConnectionCustomizer() {
        }

        @Override
        public void customize(Connector connector) {
            connector.setPort(Integer.valueOf(port));
            connector.setAttribute("connectionTimeout", connectionTimeout);
            connector.setAttribute("acceptorThreadCount", acceptorThreadCount);
            connector.setAttribute("minSpareThreads", minSpareThreads);
            connector.setAttribute("maxSpareThreads", maxSpareThreads);
            connector.setAttribute("maxThreads", maxThreads);
            connector.setAttribute("maxConnections", maxConnections);
            connector.setAttribute("protocol", protocol);
            connector.setAttribute("redirectPort", "redirectPort");
            connector.setAttribute("compression", "compression");
        }


    }
}

