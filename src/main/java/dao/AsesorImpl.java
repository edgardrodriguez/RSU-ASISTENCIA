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
import model.AsesorModel;

/**
 *
 * @author edgard
 */
public class AsesorImpl extends Conexion implements ICRUD<AsesorModel> {

    @Override
    public void registrar(AsesorModel obj) throws Exception {
        String sql = " INSERT INTO ASESOR (nombre,apellidos,password,email,DNI,celular,estado,rol_fk) VALUES (?,?,?,?,?,?,?,?)";
        try (PreparedStatement ps = this.conectar().prepareStatement(sql)){
            ps.setString(1, obj.getNombre());
            ps.setString(2, obj.getApellidos());
            ps.setString(3, obj.getPassword());
            ps.setString(4, obj.getEmail());
            ps.setString(5, obj.getDNI());
            ps.setString(6, obj.getCelular());
             String est="A";
            int rolb=3;
            ps.setString(7, est);
            ps.setInt(8, rolb);
            ps.execute();
             ps.close();
        } catch (Exception e)  {
            Logger.getGlobal().log(Level.WARNING, "Error al Ingresar ASESOR Dao {0} ", e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void modificar(AsesorModel obj) throws Exception {
        String sql = "update ASESOR set nombre=?,apellidos=?,password=?,email=?,DNI=?,celular=?,estado=?,rol_fk=? where id=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, obj.getNombre());
            ps.setString(2, obj.getApellidos());
            ps.setString(3, obj.getPassword());
            ps.setString(4, obj.getEmail());
            ps.setString(5, obj.getDNI());
            ps.setString(6, obj.getCelular());
            ps.setString(7, obj.getEstado());
            int rolb=3;
            ps.setInt(8, rolb);
            ps.setInt(9, obj.getId());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.WARNING, "Error al modificar Docentes Dao {0} ", e.getMessage());
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public void eliminar(AsesorModel obj) throws Exception {
        String sql = "delete from ASESOR where id=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, obj.getId());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.WARNING, "Error al eliminar DOCENTES Dao {0} ", e.getMessage());
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public List<AsesorModel> listarTodos() throws Exception {
        List<AsesorModel> listado = null;
        AsesorModel doc;
        ResultSet rs;
        String sql = "select * from  V_ASESOR";
        try {
            listado = new ArrayList();
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                doc = new AsesorModel();
                doc.setFila(rs.getString("fila"));
                doc.setId(rs.getInt("id"));
                doc.setNombre(rs.getString("nombre"));
                doc.setApellidos(rs.getString("apellidos"));
                doc.setPassword(rs.getString("password"));
                doc.setEmail(rs.getString("email"));
                doc.setDNI(rs.getString("DNI"));
                doc.setCelular(rs.getString("celular"));
                doc.setEstado(rs.getString("estado"));
                doc.setRol_fk(rs.getInt("rol_fk"));
                doc.setRole(rs.getString("role"));
                listado.add(doc);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.WARNING, "Error al listar Docentes Dao {0} ", e.getMessage());
        }
        return listado;
    }
    
}
