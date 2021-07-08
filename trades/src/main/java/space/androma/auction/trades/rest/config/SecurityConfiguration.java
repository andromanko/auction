package space.androma.auction.trades.rest.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import space.androma.auction.trades.rest.utils.CustomUserDetailsService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//import java.security.AuthProvider;

//потребуется переопределить некоторые встроенные протоколы безопасности Spring
// для использования нашей базы данных и алгоритма хеширования, поэтому нам
// потребуется специальный файл конфигурации
@Configuration
@Slf4j
@EnableWebSecurity(debug = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

/*    @Autowired
    AuthProvider authProvider;*/

    private static List<String> clients = Arrays.asList("google");//, "facebook");


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public CustomUserDetailsService customUserDetailsService;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
// PRE 0
    @Bean
    PasswordEncoder passwordEncoder()
    {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().and()
                .authorizeRequests()
                .antMatchers("/signup").permitAll()
                .antMatchers("/user/**", "/lot/**").authenticated()
                .and().formLogin()
                .and().oauth2Login()
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/")
                .redirectionEndpoint()
                .baseUri("/login/oauth2/code/**")
                .and()
                .and().csrf().disable();
        log.info("configure(HttpSecurity http) passed: " + http);
    }

//only for formLogin //google not here
    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService);
            
        log.info("configure(AuthenticationManagerBuilder builder) passed: "+ auth);
    }

/*    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }*/

   @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        List<ClientRegistration> registrations = new ArrayList<>();
        //registrations.add(githubClientRegistration());
        registrations.add(googleClientRegistration());
        return new InMemoryClientRegistrationRepository(registrations);
    }
//TODO - move to application.properties
    private ClientRegistration googleClientRegistration() {
        //return ClientRegistration.withRegistrationId("google")
                String clientId ="244096497223-acnhd0svdg2b936tfvhed93bdum33v6e.apps.googleusercontent.com";
                String clientSecret = "DfBQfwXAfdIR9LRzeTp9ESbJ";

        return CommonOAuth2Provider.GOOGLE.getBuilder("google")
                .clientId(clientId).clientSecret(clientSecret)
                .build();

        }

    @Bean
    public OAuth2AuthorizedClientService authorizedClientService() {

        return new InMemoryOAuth2AuthorizedClientService(
                clientRegistrationRepository());
    }
}



