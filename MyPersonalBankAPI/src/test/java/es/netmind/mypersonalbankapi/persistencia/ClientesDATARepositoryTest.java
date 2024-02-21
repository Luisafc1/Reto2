package es.netmind.mypersonalbankapi.persistencia;

import es.netmind.mypersonalbankapi.config.SpringConfig;
import es.netmind.mypersonalbankapi.modelos.clientes.Cliente;
import es.netmind.mypersonalbankapi.modelos.clientes.Empresa;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.notNullValue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringConfig.class})
@EnableAutoConfiguration
class ClientesDATARepositoryTest {

    @Autowired
    private ClienteDataRepo repo;

    @Test
    void testBeans() {
        assertThat(repo, notNullValue());
    }


    @Test
        //@Transactional
    void dadosClientesEmpresa_cuandoinsertarClientesEmpresaEnDB_entoncesIdValido() throws Exception {
        Cliente emp = new Empresa(null, "La Mar Sosa SL", "lms@s.com",
                "Calle SI 3", LocalDate.now(), true, false, "J12345678", new String[]{"Dev", "Marketing"});
        repo.save(emp);

        System.out.println(emp);

        assertThat(emp.getId(), greaterThan(0));
    }


    @Test
    void dadosClientes_cuandolistarClientesEnDB_entoncesIdValido() throws Exception {

        List<Cliente> listaCli = repo.findAll();
        System.out.println("Lista Clientespruebas");
        System.out.println(listaCli);
        assertThat(listaCli.size(), greaterThan(0));
    }
}