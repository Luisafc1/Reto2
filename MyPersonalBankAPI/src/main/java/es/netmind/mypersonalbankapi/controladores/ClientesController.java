package es.netmind.mypersonalbankapi.controladores;

import es.netmind.mypersonalbankapi.exceptions.ClienteException;
import es.netmind.mypersonalbankapi.modelos.clientes.Cliente;
import es.netmind.mypersonalbankapi.modelos.prestamos.Prestamo;
import es.netmind.mypersonalbankapi.persistencia.*;
import es.netmind.mypersonalbankapi.utils.ClientesUtils;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@Controller
public class ClientesController implements IClientesController {


    private IClientesRepo clientesRepo;
    //private  IClientesRepo clientesRepo = ClientesInMemoryRepo.getInstance();
    private ClienteDataRepo clientesRepoData;

    @Transactional
    public void mostrarLista() throws Exception {
        System.out.println("\nLista de clientes:");
        System.out.println("───────────────────────────────────");

        List<Cliente> clientes = clientesRepoData.findAll();
        for (Cliente cl : clientes) {

            try {
                cl.validar();
                System.out.println("(" + cl.getId() + ") " + cl.getNombre() + " " + cl.getId());
            } catch (ClienteException e) {
                System.out.println("El cliente solicitado tiene datos erroneos 😞! Ponte en contacto con el admin. \nCode: " + e.getCode());
            } catch (Exception e) {
                System.out.println("Oops ha habido un problema, inténtelo más tarde 😞!");
            }

        }
    }

    @Override
    public void eliminar(int i) {

    }

    public void mostrarDetalle(Integer uid) {
        System.out.println("\nDetalle de cliente: " + uid);
        System.out.println("───────────────────────────────────");

        try {
            Cliente cl = clientesRepo.getClientById(uid);
            System.out.println(cl);
        } catch (ClienteException e) {
            System.out.println("Cliente NO encontrado 😞! \nCode: " + e.getCode());
        } catch (Exception e) {
            System.out.println("Oops ha habido un problema, inténtelo más tarde 😞!");
        }

    }

    @Transactional
    public void add(String[] args) {
        System.out.println("\nAñadiendo cliente");
        System.out.println("───────────────────────────────────");
        try {
            Cliente cl = ClientesUtils.extractClientFromArgsForCreate(args);
            clientesRepoData.save(cl);
            System.out.println("Cliente añadido: " + cl + " 🙂");
            mostrarLista();
        } catch (ClienteException e) {
            System.out.println("Cliente NO válido 😞! \nCode: " + e.getCode());
        } catch (DateTimeException e) {
            System.out.println("⚠ LAS FECHAS DEBEN TENER EL FORMATO yyyy-mm-dd, por ejemplo 2023-12-01 ⚠");
        } catch (Exception e) {
            System.out.println("Oops ha habido un problema, inténtelo más tarde 😞!");
            e.printStackTrace();
        }

    }

    public void eliminar(Integer uid) {
        System.out.println("\nBorrando cliente: " + uid);
        System.out.println("───────────────────────────────────");

        try {
            Cliente cl = clientesRepo.getClientById(uid);
            boolean borrado = clientesRepo.deleteClient(cl);
            if (borrado) {
                System.out.println("Cliente borrado 🙂!!");
                mostrarLista();
            } else System.out.println("Cliente NO borrado 😞!! Consulte con su oficina.");
        } catch (ClienteException e) {
            System.out.println("Cliente NO encontrado 😞! \nCode: " + e.getCode());
        } catch (Exception e) {
            System.out.println("Oops ha habido un problema, inténtelo más tarde 😞!");
        }

    }

    public void actualizar(Integer uid, String[] args) {
        System.out.println("\nActualizando cliente: " + uid);
        System.out.println("───────────────────────────────────");

        try {
            Cliente cl = clientesRepo.getClientById(uid);
            System.out.println("cl.getClass():" + cl.getClass() + " " + cl);
            ClientesUtils.updateClientFromArgs(cl, args);
            clientesRepo.updateClient(cl);
            System.out.println("Cliente actualizado 🙂!!");
            System.out.println(cl);
            mostrarLista();
        } catch (ClienteException e) {
            System.out.println("Cliente NO encontrado 😞! \nCode: " + e.getCode());
        } catch (DateTimeException e) {
            System.out.println("⚠ LAS FECHAS DEBEN TENER EL FORMATO yyyy-mm-dd, por ejemplo 2023-12-01 ⚠");
        } catch (Exception e) {
            System.out.println("Oops ha habido un problema, inténtelo más tarde 😞!");
            e.printStackTrace();
        }

    }

    public void evaluarPrestamo(Integer uid, Double cantidad) {
        System.out.println("\nEvaluando préstamos de " + cantidad + " EUR para el  cliente: " + uid);
        System.out.println("───────────────────────────────────");

        try {
            Cliente cliente = clientesRepo.getClientById(uid);
            System.out.println("Saldo total del cliente: " + cliente.obtenerSaldoTotal());
            int numPrestamos = cliente.getPrestamos() != null ? cliente.getPrestamos().size() : 0;
            System.out.println("Número total de préstamos del cliente: " + numPrestamos);

            Prestamo prestamoSolictado = new Prestamo(null, LocalDate.now(), cantidad, cantidad, 10, 5, false, false, 5);

            boolean aceptable = cliente.evaluarSolicitudPrestamo(prestamoSolictado);
            if (aceptable) System.out.println("SÍ se puede conceder 🙂!!");
            else System.out.println("NO puede conceder 😞!! Saldo insuficiente.");

        } catch (ClienteException e) {
            System.out.println("Cliente NO encontrado 😞! \nCode: " + e.getCode());
        } catch (Exception e) {
            System.out.println("Oops ha habido un problema, inténtelo más tarde 😞!");
            e.printStackTrace();
        }


    }
}
