package rs.digitize.backend.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.ConverterRegistry;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import rs.digitize.backend.bean.converter.PageableConverter;
import rs.digitize.backend.bean.converter.SortConverter;
import rs.digitize.backend.bean.filter.PostCountFilter;
import rs.digitize.backend.bean.filter.UserCountFilter;
import rs.digitize.backend.search.GenericSpecificationConverter;

import javax.annotation.PostConstruct;

@Configuration
@EnableJpaAuditing
@RequiredArgsConstructor
public class Config {
	private final ConverterRegistry converterRegistry;

	@PostConstruct
	public void init() {
		registerConverters();
	}

	private void registerConverters() {
		converterRegistry.addConverter(new GenericSpecificationConverter());
		converterRegistry.addConverter(new SortConverter());
		converterRegistry.addConverter(PageableConverter.builder().build());
	}

	@Bean(name = "passwordEncoder")
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean(name = "errorMessages")
	public PropertiesFactoryBean errorMessages() {
		PropertiesFactoryBean bean = new PropertiesFactoryBean();
		bean.setLocation(new ClassPathResource("errors.properties"));
		return bean;
	}

	@Bean
	public FilterRegistrationBean<PostCountFilter> postCountFilterFilterRegistrationBean() {
		FilterRegistrationBean<PostCountFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new PostCountFilter());
		registrationBean.addUrlPatterns("/posts", "/posts/all", "/previews", "/previews/all");
		return registrationBean;
	}

	@Bean
	public FilterRegistrationBean<UserCountFilter> userCountFilterFilterRegistrationBean() {
		FilterRegistrationBean<UserCountFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new UserCountFilter());
		registrationBean.addUrlPatterns("/users");
		return registrationBean;
	}
}