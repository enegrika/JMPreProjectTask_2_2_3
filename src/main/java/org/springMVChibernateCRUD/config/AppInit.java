package org.springMVChibernateCRUD.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.EnumSet;

public class AppInit extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{AppConfigDatabase.class};
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

// down is the FILTER CODE - to read HIDDEN Fields from HTML form,
    // to change "POST" request into "PATCH DELETE and PUT" HTTP requests !!!!

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
        registerCharacterEncodingFilter(servletContext);
        registerHiddenFieldFilter(servletContext);
    }

    private void registerHiddenFieldFilter(ServletContext servletContext) {
        servletContext.addFilter("hiddenHttpMethodFilter",
                new HiddenHttpMethodFilter())
                .addMappingForUrlPatterns(null,
                        true, "/*");
    }

    ////////// RUSSIAN ENCODING
    //    РУССКАЯ КОДИРОВКА для СЕРВЛЕТа в СПРИНГЕ
//  ENCODING FILTER for UTF-8 (russian etc.)

    private void registerCharacterEncodingFilter(ServletContext servletContext) {
        EnumSet<DispatcherType> dispatcherTypes = EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD);

        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);

        FilterRegistration.Dynamic characterEncoding = servletContext.addFilter("characterEncoding", characterEncodingFilter);
        characterEncoding.addMappingForUrlPatterns(dispatcherTypes, true, "/*");
    }


}