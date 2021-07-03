package space.androma.auction.trades.rest.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//import java.security.AuthProvider;

//потребуется переопределить некоторые встроенные протоколы безопасности Spring
// для использования нашей базы данных и алгоритма хеширования, поэтому нам
// потребуется специальный файл конфигурации
@Configuration
@Slf4j
@EnableWebSecurity(debug = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

/*
    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Autowired
    MongoUserDetailsService userDetailsService;
*/

    @Autowired
    private PasswordEncoder passwordEncoder;

//che za nach?   //pre Start 2 PRE 3
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
// PRE 0
    @Bean
    PasswordEncoder passwordEncoder()
    { //TODO окьлрииитть
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder;
    }
//Этап аутентификации   //pre Start 1
    //PRE 2
    @Override
    protected void configure(HttpSecurity http) throws Exception {
       /* http
                .csrf().disable() // Отключает CSRF Protection, поскольку она не нужна для API
                .authorizeRequests()
                .antMatchers("/resources/**", "/login**", "/signup").permitAll()
                .antMatchers("/user","/lot").authenticated()
                .and().formLogin().loginPage("/login")
                .defaultSuccessUrl("/lot").failureUrl("/login?error").permitAll()
                .and().logout().logoutSuccessUrl("/login").permitAll();*/

/*        http
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .httpBasic();*/

/*        http.authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/**").hasAnyRole("ADMIN", "USER")
                .and().formLogin()
                .and().logout().logoutSuccessUrl("/login").permitAll()
                .and().csrf().disable();*/
        http.authorizeRequests()
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/user/**").access("hasRole('ROLE_USER')")
                .and().formLogin();

log.info("configure(HttpSecurity http) passed: "+ http);
/*
                .and().formLogin().loginPage("/login")   //alter: and().httpBasic(): сообщает Spring, чтобы он ожидал базовую HTTP аутентификацию (обсуждалось выше).
                .permitAll().and().logout().invalidateHttpSession(true)
                .clearAuthentication(true).logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                //.defaultSuccessUrl("/lot").failureUrl("/login?error").permitAll()
                .and().logout().logoutSuccessUrl("/").permitAll().and().exceptionHandling().accessDeniedHandler(accessDeniedHandler);
        //.and().sessionManagement().disable(): сообщает Spring, что не следует хранить информацию о сеансе для пользователей, поскольку это не нужно для API
*/
    }
/*
    @Autowired
    private AuthProvider authProvider;*/

//Указание диспетчера аутентификации - config what we use for Auth   //pre Start 0
    //PRE 1
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //.inMemoryAuthentication()
               // .withUser("Roma").password("sssss").roles("USER");*/
 //       auth.authenticationProvider(authProvider);
        auth.inMemoryAuthentication()
                .passwordEncoder(passwordEncoder)
                .withUser("user").password(passwordEncoder.encode("123456")).roles("USER")
                .and()
                .withUser("admin").password(passwordEncoder.encode("123456")).roles("USER", "ADMIN");

        //builder.userDetailsService(userDetailsService);
        log.info("configure(AuthenticationManagerBuilder builder) passed: "+ auth);
                /*.jdbcAuthentication().dataSource(dataSource)
                .authoritiesByUsernameQuery("SELECT user.login as username, role.role as role FROM user "
                        + "INNER JOIN user_role ON user.id = user_role.user_id "
                        + "INNER JOIN role ON user_role.role_id = role.id " + "WHERE user.login = ?")
                .usersByUsernameQuery("select login, password, 1 as enabled from user where user.login = ?");*/

/*        List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority(“user”));

        return new AuUser(user.getUsername(), user.getPassword(), authorities);*/

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }

}

