package in.ineuron.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;



@Configuration
public class SwaggerDocsConfig {
	
	 @Bean
	  public OpenAPI springShopOpenAPI() {
	      return new OpenAPI()
	              .info(new Info().title("Tourist Info API")
	              .description("Gives Information about Tourist Travels")
	              .version("v1.0")
	              .contact(new Contact().name("Ritankar").email("ritankar0425@gmail.com"))
	              .license(new License().name("Apache 2.0").url("http://springdoc.org/dummy")))
	              .externalDocs(new ExternalDocumentation()
	              .description("Tourist Travel API")
	              .url("https://springshop.wiki.github.org/docs"));
	  }
	
}
