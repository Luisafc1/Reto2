package es.netmind.mypersonalbankapi.persistencia;

import es.netmind.mypersonalbankapi.config.SpringConfig;
import es.netmind.mypersonalbankapi.modelos.clientes.Cliente;
import es.netmind.mypersonalbankapi.modelos.clientes.Empresa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringConfig.class})
@EnableAutoConfiguration
class ClientesDBRepositoryTest {
    @Autowired
    private IClientesRepo repo;

    @Test
    void testBeans() {
        assertThat(repo, notNullValue());
    }


    @Test
    //@Transactional
    void dadosClientesEmpresa_cuandoinsertarClientesEmpresaEnDB_entoncesIdValido() throws Exception {
        Cliente emp = new Empresa(null, "La Mar Salada SL", "lms@s.com",
                "Calle SI 3", LocalDate.now(), true, false, "J12345678", new String[]{"Dev", "Marketing"});
        repo.addClient(emp);

        System.out.println(emp);

        assertThat(emp.getId(), greaterThan(0));
    }

    @Test
    void dadosClientes_cuandolistarClientesEnDB_entoncesIdValido() throws Exception {

        List<Cliente> listaCli = repo.getAll();
        System.out.println("Lista Clientespruebas");
        System.out.println(listaCli);
        assertThat(listaCli.size(), greaterThan(0));
    }

    @Test
    void dadosClientesEmpresa_cuandolistarClientesEmpresaEnDB_entoncesIdValido() throws Exception {

        List<Empresa> emp = repo.getListaClienteEmpresa();
        System.out.println(emp);
        assertThat(emp.size(), greaterThan(0));
    }

    @Test
    void dadosCliente_cuandolistarDetalleClienteEnDB_entoncesIdValido() throws Exception {

        Cliente c1 = repo.getDetalleCliente(3);
        System.out.println(c1);
        assertThat(c1.getId(), greaterThan(0));
    }

    @Test
    void dadounCliente_ActualizarClienteEnDB_entoncesIdValido() throws Exception {

        Cliente c1 = repo.getCliente(2);

        c1.setNombre("cor petit");
        c1.setEmail("Andres@a");
        c1.setDireccion("Calle de los milagros");

        repo.actualizarCliente(c1);

        assertThat(c1.getId(), greaterThan(0));

    }

}