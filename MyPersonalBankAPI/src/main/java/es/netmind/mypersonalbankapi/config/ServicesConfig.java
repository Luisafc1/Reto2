package es.netmind.mypersonalbankapi.config;

import es.netmind.mypersonalbankapi.controladores.ClientesController;
import es.netmind.mypersonalbankapi.controladores.IClientesController;
import es.netmind.mypersonalbankapi.persistencia.IClientesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServicesConfig {
    @Autowired
    IClientesRepo clientesRepo;

   @Bean
    public IClientesController getClientesController(){
        ClientesController c1 = new ClientesController();
        c1.setClientesRepo(clientesRepo);
        return c1;
    }
}
