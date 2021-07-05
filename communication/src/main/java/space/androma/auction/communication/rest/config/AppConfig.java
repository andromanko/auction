package space.androma.auction.communication.rest.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import space.androma.auction.communication.service.config.ServiceConfiguration;

import java.util.List;

@EnableScheduling
@EnableWebMvc
@Configuration
@ComponentScan("space.androma.auction.communication")
@PropertySource("classpath:application.properties")
@EnableMongoRepositories(basePackages = "space.androma.auction.communication.api.dao")
@Import(value = {ServiceConfiguration.class})
public class AppConfig implements WebMvcConfigurer {


    @Value("${spring.data.mongodb.uri}")
    private String mongoDBUri;

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new MappingJackson2HttpMessageConverter());
    }

    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create("mongodb://mongodb-srv:27017");
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), "au_msg");
    }

}
