package es.netmind.mypersonalbankapi.modelos.clientes;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    @Test
    void cuando_cliente_es_correcto() throws Exception {
        // given

        // when
        Cliente c1 = new Personal(1, "Juan Juanez", "jj@j.com", "Calle JJ 1", LocalDate.now(), true, false, "12345678J");

        // then
        boolean resultado = c1.validar();
        assertTrue(resultado);

    }

    @Test
    void cuando_cliente_noes_correcto() throws Exception {
        // given

        // when
        Cliente c1 = new Personal(1, "xx", "jjj.com", "Calle JJ 1", LocalDate.now(), true, false, "12345678J");

        // then
         boolean resultado = c1.validar();
         assertFalse(resultado);


    }
}