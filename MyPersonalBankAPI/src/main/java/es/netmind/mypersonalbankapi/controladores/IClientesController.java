package es.netmind.mypersonalbankapi.controladores;

import es.netmind.mypersonalbankapi.modelos.clientes.Cliente;

public interface IClientesController {


    void mostrarLista() throws Exception;

    void eliminar(int i);
    void add(Cliente cliente);
}
