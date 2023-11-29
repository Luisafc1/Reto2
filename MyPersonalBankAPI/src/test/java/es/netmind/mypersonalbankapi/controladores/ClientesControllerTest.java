package es.netmind.mypersonalbankapi.controladores;

import es.netmind.mypersonalbankapi.config.SpringConfig;
import es.netmind.mypersonalbankapi.persistencia.IClientesRepo;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Setter
@Getter
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringConfig.class})
class ClientesControllerTest {

    @Autowired
    IClientesController ClientesController;


    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    @Test
    void testBeans() {
        assertThat(ClientesController, notNullValue());
    }
    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));


    }

    @Test
    @Order(1)
    void dadosClientesenRepo_cuandosepidelista_entonceslistaOK() throws Exception {

        ClientesController.mostrarLista();
        System.out.println(outContent);
        System.out.println("\nLista de clientes:");
        System.out.println("───────────────────────────────────");
        assertThat(outContent.toString(), containsString("Lista de clientes:"));
        assertThat(outContent.toString(), containsString("───────────────────────────────────"));
        //assertThat(outContent.toString(), containsString("(1) Juan Juanez 1"));
        //assertThat(outContent.toString(), containsString("(2) Luisa Perez 2"));
        assertThat(outContent.toString(), containsString("(3) Servicios Informatico SL 3"));

    }

    @Test
    @Order(2)
    void dadosClientesenRepo_cuandosepidelista_entonceslistaNOK() throws Exception {

        ClientesController.eliminar(1);
        ClientesController.eliminar(2);
        ClientesController.eliminar(3);

        ClientesController.mostrarLista();
        System.out.println(outContent);
        System.out.println("\nLista de clientes:");
        System.out.println("───────────────────────────────────");
        assertThat(outContent.toString(), containsString("Lista de clientes:"));
        assertThat(outContent.toString(), containsString("───────────────────────────────────"));
        assertNotEquals(outContent.toString(), containsString("("));


    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }



}