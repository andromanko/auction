package space.androma.auction.trades.rest.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import space.androma.auction.trades.service.config.ServiceConfiguration;

import java.util.List;


@Configuration
@ComponentScan("space.androma.auction.trades")
@PropertySource("classpath:application.properties")
@EnableMongoRepositories(basePackages = "space.androma.auction.trades.api.dao")
@Import(value = { ServiceConfiguration.class })
public class AppConfig implements WebMvcConfigurer {


	@Value("${app.mongodb.uri}")
	private String mongoDBUri;

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(new MappingJackson2HttpMessageConverter());
	}

	public @Bean
	MongoClient mongoClient() {
		return MongoClients.create("mongodb://" + mongoDBUri + ":27017");
	}

	@Bean
	public MongoTemplate mongoTemplate() {
		return new MongoTemplate(mongoClient(), "test");
	}

}
