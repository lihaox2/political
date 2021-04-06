/**
 * 
 */
package com.bayee.political;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import com.bayee.political.configuration.DispatcherConfig;
import com.bayee.political.utils.DingTalkUtils;

/**
 * @author shawnkuo
 *
 */
@Configuration
public class WebInitializer implements WebApplicationInitializer {

	/* (non-Javadoc)
	 * @see org.springframework.web.WebApplicationInitializer#onStartup(javax.servlet.ServletContext)
	 */
	@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {

		// Create the 'root' Spring application context
        AnnotationConfigWebApplicationContext rootContext =
                new AnnotationConfigWebApplicationContext();
        rootContext.register(AppConfiguration.class);

        // Manage the lifecycle of the root application context
        servletContext.addListener(new ContextLoaderListener(rootContext));
        
     // Create the dispatcher servlet's Spring application context
        AnnotationConfigWebApplicationContext dispatcherContext =
                new AnnotationConfigWebApplicationContext();
        dispatcherContext.register(DispatcherConfig.class);
        
        // Create the ''
        FilterRegistration.Dynamic fr = servletContext.addFilter("encodingFilter",  
        	      new CharacterEncodingFilter());
		   fr.setInitParameter("encoding", "UTF-8");
		   fr.setInitParameter("forceEncoding", "true");
		   fr.addMappingForUrlPatterns(null, true, "/*");
		  
        // Register and map the dispatcher servlet
        ServletRegistration.Dynamic dispatcher =
        		servletContext.addServlet("dispatcher", new DispatcherServlet(dispatcherContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
	}

}
