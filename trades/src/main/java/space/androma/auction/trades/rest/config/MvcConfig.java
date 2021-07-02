package space.androma.auction.trades.rest.config;

import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

///для страницы логина
//@Configuration
public class MvcConfig implements WebMvcConfigurer
{
  public void addViewControllers(ViewControllerRegistry registry)
  {
     registry.addViewController("/login").setViewName("login");
     registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
  }
}