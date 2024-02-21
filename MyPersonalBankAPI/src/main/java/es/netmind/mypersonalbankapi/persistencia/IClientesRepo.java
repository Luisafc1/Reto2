package es.netmind.mypersonalbankapi.persistencia;

import es.netmind.mypersonalbankapi.modelos.clientes.Cliente;
import es.netmind.mypersonalbankapi.modelos.clientes.Empresa;

import java.util.List;

public interface IClientesRepo {
    public Empresa insertClientesEmpresa(Empresa nuevoClienteEmpresa) throws Exception;

    public List<Cliente> getAll() throws Exception;

    public Cliente getClientById(Integer id) throws Exception;

    public Cliente addClient(Cliente cliente) throws Exception;

    public boolean deleteClient(Cliente cliente) throws Exception;

    public Cliente updateClient(Cliente cliente) throws Exception;

    public List<Empresa> getListaClienteEmpresa() throws Exception;

    public Cliente getDetalleCliente(int idCliente) throws Exception;

    public Cliente actualizarCliente(Cliente c2) throws Exception;
    public Cliente getCliente(int idCliente) throws Exception;

}
