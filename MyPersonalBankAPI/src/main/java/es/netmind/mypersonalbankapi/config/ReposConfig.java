package es.netmind.mypersonalbankapi.config;

import es.netmind.mypersonalbankapi.persistencia.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReposConfig {

    @Value("${db_url}")
    String db_url1;

    @Bean
    public IClientesRepo createIClientesRepo() {
        ClientesDBRepository repo = new ClientesDBRepository();
        repo.setDb_url1(db_url1);
        return repo;
    }

    @Bean
    public IPrestamosRepo createIPrestamosRepo() {
        PrestamosDBRepository repo = new PrestamosDBRepository();
        repo.setDb_url1(db_url1);
        return repo;
    }

    @Bean
    public ICuentasRepo createICuentasRepo() {
        CuentasDBRepository repo = new CuentasDBRepository();
        repo.setDb_url1(db_url1);
        return repo;
    }

}
