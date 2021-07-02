package space.androma.auction.trades.rest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import space.androma.auction.trades.api.service.IUserService;


//@EnableScheduling
@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Autowired
    IUserService userService;

    //Добавление кодировщика Bcrypt
    @Autowired
    private PasswordEncoder passwordEncoder;


/*
    @Autowired
    private AuthProvider authProvider;
    //было authProvider. TODO разобраться -)
*/

//che za nach?   //pre Start 2
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

/*    @Bean
    PasswordEncoder passwordEncoder()
    { //TODO окьлрииитть
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder;
    }*/



/*  Этот должен прррриннннимммммммммаать AuthProvider
    @Override
    protected void configure(AuthenticationManagerBuilder auth)
    {
        auth.authenticationProvider(authProvider);
    }
*/

//Этап аутентификации   //pre Start 1
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Отключает CSRF Protection, поскольку она не нужна для API
                .authorizeRequests()
                .antMatchers("/resources/**", "/", "/login**", "/signup").permitAll()
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/login")   //alter: and().httpBasic(): сообщает Spring, чтобы он ожидал базовую HTTP аутентификацию (обсуждалось выше).
                .defaultSuccessUrl("/lot").failureUrl("/login?error").permitAll()
                .and().logout().logoutSuccessUrl("/").permitAll();
        //.and().sessionManagement().disable(): сообщает Spring, что не следует хранить информацию о сеансе для пользователей, поскольку это не нужно для API
    }

//Указание диспетчера аутентификации - config what we use for Auth   //pre Start 0
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
        builder.inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");

                //.userService(usersService);

                /*.jdbcAuthentication().dataSource(dataSource)
                .authoritiesByUsernameQuery("SELECT user.login as username, role.role as role FROM user "
                        + "INNER JOIN user_role ON user.id = user_role.user_id "
                        + "INNER JOIN role ON user_role.role_id = role.id " + "WHERE user.login = ?")
                .usersByUsernameQuery("select login, password, 1 as enabled from user where user.login = ?");*/

/*        List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority(“user”));

        return new User(user.getUsername(), user.getPassword(), authorities);*/

    }

}

