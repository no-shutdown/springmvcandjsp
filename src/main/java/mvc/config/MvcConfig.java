package mvc.config;

import mvc.interceptors.MvcInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.List;


/**
 * mvc 配置
 * @author xinLin.huang
 * @date 2023/7/24 14:54
 */
@Configuration
@ComponentScan({"mvc.controller"})
@EnableWebMvc
public class MvcConfig {

	@Bean
	public WebMvcConfigurer webMvcConfigurer() {
		return new WebMvcConfig();
	}

	static class WebMvcConfig implements WebMvcConfigurer {

		//配置视图解析器
		@Override
		public void configureViewResolvers(ViewResolverRegistry registry) {
			InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
			viewResolver.setPrefix("/");
			viewResolver.setSuffix(".jsp");
			registry.viewResolver(viewResolver);
		}

		//配置消息转化器
		@Override
		public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
			converters.add(new MappingJackson2HttpMessageConverter());
		}

		//配置拦截器
		@Override
		public void addInterceptors(InterceptorRegistry registry) {
			registry.addInterceptor(new MvcInterceptor());
		}
	}

}
