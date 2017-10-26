package com.minazg.configuration;

import com.minazg.converter.RoleToUserRoleConverter;
import com.minazg.formatter.SprintFormatter;
import com.minazg.formatter.TaskFormatter;
import com.minazg.formatter.UserFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.Locale;

@Configuration
@Import(JpaConfiguration.class)
@EnableWebMvc
@EnableSpringDataWebSupport
@ComponentScan(basePackages = "com.minazg")
public class AppConfig extends WebMvcConfigurerAdapter{

	@Autowired
	private UserFormatter userFormatter;

	@Autowired
	private SprintFormatter sprintFormatter;

	@Autowired
	private TaskFormatter taskFormatter;

	
	final RoleToUserRoleConverter roleToUserRoleConverter;

	@Autowired
	public AppConfig(RoleToUserRoleConverter roleToUserRoleConverter) {
		this.roleToUserRoleConverter = roleToUserRoleConverter;
	}

	/**
     * Configure ViewResolvers to deliver preferred views.
     */
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {

		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		viewResolver.setOrder(1);
		registry.viewResolver(viewResolver);
	}
	
	/**
     * Configure ResourceHandlers to serve static resources like CSS/ Javascript etc...
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
    }
    
    /**
     * Configure Converter to be used.
     * In our example, we need a converter to convert string values[Roles] to UserProfiles in newUser.jsp
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(roleToUserRoleConverter);
        registry.addFormatter(sprintFormatter);
        registry.addFormatter(userFormatter);
        registry.addFormatter(taskFormatter);
    }
	

    /**
     * Configure MessageSource to lookup any validation/error message in internationalized property files
     */
    @Bean
	public MessageSource messageSource() {
//	    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		//messageSource.setBasenames("messages","errorMessages");
		messageSource.setBasename("classpath:messages");
		messageSource.setDefaultEncoding("UTF-8");
	    return messageSource;
	}
    
    /**Optional. It's only required when handling '.' in @PathVariables which otherwise ignore everything after last '.' in @PathVaidables argument.
     * It's a known bug in Spring [https://jira.spring.io/browse/SPR-6164], still present in Spring 4.3.0.
     * This is a workaround for this issue.
     */
    
    @Override
    public void configurePathMatch(PathMatchConfigurer matcher) {
        matcher.setUseRegisteredSuffixPatternMatch(true);
    }


    /* This is the validation bean*/

//	@Bean(name = "validator")
//	public LocalValidatorFactoryBean validator() {
//		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
//		bean.setValidationMessageSource(messageSource());
//		return bean;
//	}
//
//	@Override
//	public Validator getValidator(){
//		return validator();
//	}

	@Bean
	public MessageSourceAccessor messageAccessor(){
		MessageSourceAccessor sourceAccessor = new MessageSourceAccessor(messageSource());
		return sourceAccessor;
	}

	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver resolver = new SessionLocaleResolver();
		resolver.setDefaultLocale(new Locale("en"));
		return resolver;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		LocaleChangeInterceptor localeChangeInteceptor = new LocaleChangeInterceptor();
		localeChangeInteceptor.setParamName("language");
		registry.addInterceptor(localeChangeInteceptor);
	}

	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver resolver=new CommonsMultipartResolver();
		resolver.setDefaultEncoding("utf-8");
		return resolver;
	}

}

