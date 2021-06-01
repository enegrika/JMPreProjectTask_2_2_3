package org.springMVChibernateCRUD.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

@Configuration
public class AppInit extends AbstractAnnotationConfigDispatcherServletInitializer {


    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    // set VIEWRESOLVER class for view jsp etc.
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebViewContextConfig.class};
    }


    // SET URL where we base our APP
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
