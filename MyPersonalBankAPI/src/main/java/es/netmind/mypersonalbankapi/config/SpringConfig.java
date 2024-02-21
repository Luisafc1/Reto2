package es.netmind.mypersonalbankapi.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/*@Configuration
@Import({ReposConfig.class, ServicesConfig.class})
@ComponentScan(basePackages = {"es.netmind.mypersonalbankapi.persistencia", "es.netmind.mypersonalbankapi.controladores"})
@PropertySource("classpath:_application.properties_")
@EntityScan("es.netmind.mypersonalbankapi.modelos")
@EnableJpaRepositories(basePackages = {"es.netmind.mypersonalbankapi.persistencia"})*/
public class SpringConfig {

}
