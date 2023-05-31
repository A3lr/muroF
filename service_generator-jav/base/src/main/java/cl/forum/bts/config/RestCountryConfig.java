package cl.forum.bts.config;

import java.nio.charset.StandardCharsets;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import cl.forum.bts.interceptor.RestTemplateHeaderModifierInterceptor;
import org.springframework.http.converter.StringHttpMessageConverter;

@Configuration
public class Rest####Country####Config {

	@Bean
	public RestTemplate restTemplate(Properties properties1) {
		RestTemplate restTemplate = new RestTemplate();
		
		restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));

		List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
		interceptors.add(new RestTemplateHeaderModifierInterceptor());
		restTemplate.setInterceptors(interceptors);
		return restTemplate;
	}
}
