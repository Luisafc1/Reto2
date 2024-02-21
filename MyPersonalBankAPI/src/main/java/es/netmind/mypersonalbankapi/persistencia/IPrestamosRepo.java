package es.netmind.mypersonalbankapi.persistencia;

import es.netmind.mypersonalbankapi.modelos.prestamos.Prestamo;

import java.util.List;

public interface IPrestamosRepo {
    public List<Prestamo> getAll();

    public Prestamo getLoanById(Integer id) throws Exception;

    public Prestamo addLoan(Prestamo prestamo) throws Exception;

    public boolean deleteLoan(Prestamo prestamo) throws Exception;

    public Prestamo updateLoan(Prestamo prestamo) throws Exception;

    public List<Prestamo> getLoansByClient(Integer uid) throws Exception;

    public Prestamo getLoansByClientAndId(Integer uid, Integer aid) throws Exception;
    public List<Prestamo> getListaPrestamoByCliente(int idCliente) throws Exception;

    public Prestamo getPrestamoByCliente(int idCliente,int idPrestamo) throws Exception;
}
