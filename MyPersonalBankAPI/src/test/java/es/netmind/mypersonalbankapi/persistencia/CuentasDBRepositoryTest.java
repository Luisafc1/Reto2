package es.netmind.mypersonalbankapi.persistencia;

import es.netmind.mypersonalbankapi.config.SpringConfig;
import es.netmind.mypersonalbankapi.modelos.cuentas.Cuenta;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringConfig.class})
class CuentasDBRepositoryTest {

    @Autowired
    private ICuentasRepo repo;
    @Test
    void dadosClienteCuentas_cuandolistarCuentasbyClienteEnDB_entoncesIdValido() throws Exception {

        List<Cuenta> cu = repo.getListaCuentasByCliente(1);

        System.out.println(cu);

        assertThat(cu.size(), greaterThan(0));
    }

}