package space.androma.auction.trades.rest.config;

///для страницы логина //pre=start-4

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
//@Configuration СЮДА НЕ ЗАГЛЯДЫВАЕТ!!! НАХ НЕ НУЖНЫЙ КЛАСС!!!
public class MvcConfig implements WebMvcConfigurer
{
    //PRE 4,5
  public void addViewControllers(ViewControllerRegistry registry)
  {
     registry.addViewController("/login").setViewName("login");
     registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
     log.info("MvcConfig addViewControllers worked");
  }
}
