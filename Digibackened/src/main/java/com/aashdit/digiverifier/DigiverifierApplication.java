package com.aashdit.digiverifier;

import com.aashdit.digiverifier.config.superadmin.repository.VendorCheckStatusMasterRepository;
import com.aashdit.digiverifier.digilocker.dto.DigilockerTokenResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import java.util.ResourceBundle;

@SpringBootApplication
@ComponentScan(basePackages = "com.aashdit.digiverifier.*")
@EnableJpaRepositories(basePackages = {"com.aashdit.*"})
@EntityScan(basePackages = {"com.aashdit.*"})
@EnableScheduling
@EnableAutoConfiguration
@EnableAsync
public class DigiverifierApplication {

    ResourceBundle rs = ResourceBundle.getBundle("application");
    @Autowired
    VendorCheckStatusMasterRepository vendorCheckStatusMasterRepository;

    public static void main(String[] args) {
        SpringApplication.run(DigiverifierApplication.class, args);
    }

//	@Bean
//	public WebMvcConfigurer corsConfigurer() {
//		return new WebMvcConfigurer() {
//			@Override
//			public void addCorsMappings(CorsRegistry registry) {
//				registry.addMapping("/api/**").allowedOrigins(rs.getString("origin"));
//			}
//		};
//	}

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    public DigilockerTokenResponse getDigilockerTokenResponse() {
        return new DigilockerTokenResponse();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource
                = new ReloadableResourceBundleMessageSource();

        messageSource.setBasename("classpath:messages_en");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

}
