package es.netmind.mypersonalbankapi.persistencia;

import es.netmind.mypersonalbankapi.config.SpringConfig;
import es.netmind.mypersonalbankapi.modelos.clientes.Empresa;
import es.netmind.mypersonalbankapi.modelos.prestamos.Prestamo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringConfig.class})
public class PrestamosDBRepositoryTest {
    @Autowired
    private IPrestamosRepo repo;

    @Test
    void testBeans() {
        assertThat(repo, notNullValue());
    }
    @Test
    void dadosClientePrestamos_cuandolistarPrestamobyClienteEnDB_entoncesIdValido() throws Exception {

        List<Prestamo> p1 = repo.getListaPrestamoByCliente(1);

        System.out.println(p1);

        assertThat(p1.size(), greaterThan(0));
    }

    @Test
    void dadosClientePrestamos_cuandoDetallePrestamobyClienteEnDB_entoncesIdValido() throws Exception {

        Prestamo p1 = repo.getPrestamoByCliente(3,2);
        Prestamo p2 = repo.getPrestamoByCliente(1,1);

        System.out.println(p1);
        System.out.println(p2);

        assertThat(p1.getId(), greaterThan(0));
        assertThat(p2.getId(), greaterThan(0));

    }

    @Test
    void dadosClientePrestamos_cuandoDetallePrestamobyClienteNoExisteEnDB_entoncesIdNoValido() throws Exception {

        //Prestamo p1 = repo.getPrestamoByCliente(4,1);

        //System.out.println(p1);

        assertThrows(Exception.class, () -> {
            Prestamo p1 = repo.getPrestamoByCliente(4, 1);
        });
    }

}
