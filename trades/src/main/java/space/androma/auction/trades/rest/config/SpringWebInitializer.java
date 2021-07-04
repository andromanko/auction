package space.androma.auction.trades.rest.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//инициализируется при старте
public class SpringWebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class[] getServletConfigClasses() {
        return null;//new Class[]{MvcConfig.class};//вопрос по конфигу может возникнуть
    } //было AppConfig

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    } //ok

    @Override
    protected Class[] getRootConfigClasses() {
        return new Class[] { AppConfig.class };
    }
    //return new Class[]{};
   // хрень
}
