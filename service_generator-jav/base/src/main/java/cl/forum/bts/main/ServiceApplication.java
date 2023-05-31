package cl.forum.bts.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import cl.forum.bts.config.Properties;

@SpringBootApplication
@EnableConfigurationProperties(Properties.class)
@ComponentScan(basePackages = { "cl.*" })
public class ####Country####ServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(####Country####ServiceApplication.class, args);
	}

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

		CorsConfiguration config = new CorsConfiguration();

		config.setAllowCredentials(true);
		config.addAllowedOrigin("http://prodconfluence01.forum.local:8090");
		config.addAllowedOrigin("*.openshiftapps.com");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		
		source.registerCorsConfiguration("/**", config);

		return new CorsFilter(source);

	}
}
