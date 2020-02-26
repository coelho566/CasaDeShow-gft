package br.com.gft.casadeshow;

import java.io.File;
import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import br.com.gft.casadeshow.service.EventService;

@SpringBootApplication
public class CasadeshowApplication {

	public static void main(String[] args) {
		new File(EventService.uploadDirectory).mkdir();
		SpringApplication.run(CasadeshowApplication.class, args);
	}

	@Bean
	public FixedLocaleResolver localeResolver() {
		return new FixedLocaleResolver(new Locale("pt", "BR"));
	}

}
