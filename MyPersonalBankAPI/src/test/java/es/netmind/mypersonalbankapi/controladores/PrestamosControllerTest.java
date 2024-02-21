package es.netmind.mypersonalbankapi.controladores;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class PrestamosControllerTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @Test
    void dadoClienteConPrestamos_cuandoPidoListaPrestamos_entoncesOK() {
        int clienteId = 1;
        PrestamosController.mostrarLista(clienteId);
        System.out.println(outContent);
        assertThat(outContent.toString(), containsString("Lista de prestamos del cliente"));
        assertThat(outContent.toString(), containsString("id=1"));
        assertThat(outContent.toString(), containsString("monto=1000.0"));
    }

    @Test
    void dadoClienteSinPrestamos_cuandoPidoListaPrestamos_entoncesOK() {
        int clienteId = 2;
        PrestamosController.mostrarLista(clienteId);
        System.out.println(outContent);
        assertThat(outContent.toString(), containsString("El cliente no tiene prestamos!"));
    }

    @Test
    void dadoClienteNoExiste_cuandoPidoListaPrestamos_entoncesNOK() {
        int clienteId = 8;
        PrestamosController.mostrarLista(clienteId);
        System.out.println(outContent);
        assertThat(outContent.toString(), containsString("Oops ha habido un problema"));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }
}