package rs.digitize.backend.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cache.annotation.EnableCaching;
import rs.digitize.backend.bean.converter.PageableConverter;
import rs.digitize.backend.bean.filter.PostCountFilter;
import rs.digitize.backend.search.GenericSpecificationConverter;
import rs.digitize.backend.bean.converter.SortConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.ConverterRegistry;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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
	public static PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean(name = "errorMessages")
	public static PropertiesFactoryBean errorMessages() {
		PropertiesFactoryBean bean = new PropertiesFactoryBean();
		bean.setLocation(new ClassPathResource("errors.properties"));
		return bean;
	}

	@Bean
	public FilterRegistrationBean<PostCountFilter> postCountFilterFilterRegistrationBean() {
		FilterRegistrationBean<PostCountFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new PostCountFilter());
		registrationBean.addUrlPatterns("/posts");
		registrationBean.setOrder(1);
		return registrationBean;
	}
}