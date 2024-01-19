package com.webapp.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebappInitializer extends AbstractAnnotationConfigDispatcherServletInitializer  {

	 @Override
	    protected Class[] getRootConfigClasses() {
	        return new Class[] { WebConfig.class};
	    }
	  
	    @Override
	    protected Class[] getServletConfigClasses() {
	        return null;
	    }
	  
	    @Override
	    protected String[] getServletMappings() {
	        return new String[] { "/" };
	    }

	}