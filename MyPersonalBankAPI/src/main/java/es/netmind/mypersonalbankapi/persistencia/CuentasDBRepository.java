package es.netmind.mypersonalbankapi.persistencia;

import es.netmind.mypersonalbankapi.modelos.cuentas.Cuenta;
import lombok.Getter;
import lombok.Setter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class CuentasDBRepository implements ICuentasRepo {

    private String db_url1;


    public List<Cuenta> getListaCuentasByCliente(int idCliente) throws Exception {

        List<Cuenta> cuenta = new ArrayList<>();
        String sql = "SELECT * FROM cuenta cu, cliente c WHERE c.id = cu.cliente_id and c.id=? ";

        try (
                Connection conn = DriverManager.getConnection(db_url1);
                PreparedStatement stmt = conn.prepareStatement(sql);

        ) {

            stmt.setInt(1,idCliente);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                cuenta.add(
                        new Cuenta(
                                rs.getInt("id"),
                                rs.getDate("fecha_creacion").toLocalDate(),
                                rs.getDouble("saldo"),
                                rs.getDouble("interes"),
                                rs.getDouble("comision")
                        ) {
                            @Override
                            public boolean validar() {
                                return false;
                            }
                        });
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception(e);
        }

        return cuenta;
    }
    @Override
    public List<Cuenta> getAll() {
        return null;
    }

    @Override
    public Cuenta getAccountById(Integer id) throws Exception {
        return null;
    }

    @Override
    public Cuenta addAccount(Cuenta cuenta) throws Exception {
        return null;
    }

    @Override
    public boolean deleteAccount(Cuenta cuenta) throws Exception {
        return false;
    }

    @Override
    public Cuenta updateAccount(Cuenta cuenta) throws Exception {
        return null;
    }

    @Override
    public List<Cuenta> getAccountsByClient(Integer uid) throws Exception {
        return null;
    }

    @Override
    public Cuenta getAccountsByClientAndId(Integer uid, Integer aid) throws Exception {
        return null;
    }
}
