package es.netmind.mypersonalbankapi.persistencia;

import es.netmind.mypersonalbankapi.modelos.prestamos.Prestamo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PrestamosDBRepository implements IPrestamosRepo{

    private String db_url1;


    public List<Prestamo> getListaPrestamoByCliente(int idCliente) throws Exception {

        List<Prestamo> prestamo = new ArrayList<>();
        String sql = "SELECT * FROM prestamo p, cliente c WHERE c.id = p.cliente_id and c.id=? ";

        try (
                Connection conn = DriverManager.getConnection(db_url1);
                PreparedStatement stmt = conn.prepareStatement(sql);

        ) {

            stmt.setInt(1,idCliente);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                prestamo.add(
                        new Prestamo(
                                rs.getInt("id"),
                                rs.getDate("fecha_concesion").toLocalDate(),
                                rs.getDouble("monto"),
                                rs.getDouble("saldo"),
                                rs.getInt("interes"),
                                rs.getInt("interes_mora"),
                                rs.getBoolean("moroso"),
                                rs.getBoolean("liquidado"),
                                rs.getInt("anios")
                        ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception(e);
        }

        return prestamo;
    }

     public Prestamo getPrestamoByCliente(int idCliente,int idPrestamo) throws Exception {

        Prestamo prestamo = null;

        String sql = "SELECT * FROM prestamo p, cliente c WHERE c.id = p.cliente_id and c.id=? and p.id=? ";

        try (
                Connection conn = DriverManager.getConnection(db_url1);
                PreparedStatement stmt = conn.prepareStatement(sql);

        ) {

            stmt.setInt(1,idCliente);
            stmt.setInt(2,idPrestamo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                prestamo =
                        new Prestamo(
                                rs.getInt("id"),
                                rs.getDate("fecha_concesion").toLocalDate(),
                                rs.getDouble("monto"),
                                rs.getDouble("saldo"),
                                rs.getInt("interes"),
                                rs.getInt("interes_mora"),
                                rs.getBoolean("moroso"),
                                rs.getBoolean("liquidado"),
                                rs.getInt("anios")
                        );
            }

            else {
                throw new Exception();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception(e);
        }

        return prestamo;
    }

    @Override
    public List<Prestamo> getAll() {
        return null;
    }

    @Override
    public Prestamo getLoanById(Integer id) throws Exception {
        return null;
    }

    @Override
    public Prestamo addLoan(Prestamo prestamo) throws Exception {
        return null;
    }

    @Override
    public boolean deleteLoan(Prestamo prestamo) throws Exception {
        return false;
    }

    @Override
    public Prestamo updateLoan(Prestamo prestamo) throws Exception {
        return null;
    }

    @Override
    public List<Prestamo> getLoansByClient(Integer uid) throws Exception {
        return null;
    }

    @Override
    public Prestamo getLoansByClientAndId(Integer uid, Integer aid) throws Exception {
        return null;
    }


}
