/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.AdminModel;

/**
 *
 * @author edgard
 */
public class AdminImpl extends Conexion implements ICRUD<AdminModel> {

    @Override
    public void registrar(AdminModel obj) throws Exception {
        String sql = "INSERT INTO ADMIN (email,password,estado,rol_fk) VALUES (?,?,?,?)";
        try ( PreparedStatement ps = this.conectar().prepareStatement(sql)) {
            ps.setString(1, obj.getEmail());
            ps.setString(2, obj.getPassword());
            String est="A";
            ps.setString(3, est);
            int rolb=4;
            ps.setInt(4, rolb);
            ps.execute();
            ps.close();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.WARNING, "Error al Ingresar ADMIN Dao {0} ", e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void modificar(AdminModel obj) throws Exception {
        String sql = "update ADMIN set email=?,password=?,estado=?,rol_fk=? where id=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, obj.getEmail());
            ps.setString(2, obj.getPassword());
            ps.setString(3, obj.getEstado());
            int rolb=4;
            ps.setInt(4, rolb);
            ps.setInt(5, obj.getId());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.WARNING, "Error al modificar ADMIN Dao {0} ", e.getMessage());
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public void eliminar(AdminModel obj) throws Exception {
        String sql = "delete from ADMIN where id=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, obj.getId());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.WARNING, "Error al eliminar ADMIN Dao {0} ", e.getMessage());
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public List<AdminModel> listarTodos() throws Exception {
        List<AdminModel> listado = null;
        AdminModel doc;
        ResultSet rs;
        String sql = "select * from V_ADMIN";
        try {
            listado = new ArrayList();
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                doc = new AdminModel();
                doc.setId(rs.getInt("id"));
                doc.setEmail(rs.getString("email"));
                doc.setPassword(rs.getString("password"));
                doc.setEstado(rs.getString("estado"));
                doc.setRol_fk(rs.getInt("rol_fk"));
                doc.setFila(rs.getString("fila"));
                listado.add(doc);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.WARNING, "Error al listar ADMIN Dao {0} ", e.getMessage());
        }
        return listado;
    }

}
