package es.netmind.mypersonalbankapi.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
//@Import({ReposConfig.class, ServicesConfig.class})
@ComponentScan(basePackages = {"es.netmind.mypersonalbankapi.persistencia", "es.netmind.mypersonalbankapi.controladores"})
@PropertySource("classpath:application.properties")
public class SpringConfig {

}
