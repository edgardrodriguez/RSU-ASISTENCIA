/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ProyectosModel;

/**
 *
 * @author edgard
 */
public class ProyectosImpl extends Conexion implements ICRUD<ProyectosModel> {

    @Override
    public void registrar(ProyectosModel obj) throws Exception {
        String sql = "INSERT INTO PROYECTOS (nombre,descripcion,tipo,estado,revisado,asesor_fk,estudiantes_fk) VALUES (?,?,?,?,?,?,?)";
        try ( PreparedStatement ps = this.conectar().prepareStatement(sql)) {
            ps.setString(1, obj.getNombre());
            ps.setString(2, obj.getDescripcion());
            ps.setString(3, obj.getTipo());
            ps.setString(4, obj.getEstado());
            ps.setString(5, obj.getRevisado());
            ps.setString(6, obj.getAsesor_fk());
            ps.setString(7, obj.getEstudiantes_fk());
            ps.execute();
            ps.close();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.WARNING, "Error al Ingresar PROYECTOS Dao {0} ", e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void modificar(ProyectosModel obj) throws Exception {
        String sql = "update PROYECTOS set nombre=?,descripcion=?,tipo=?,estado=?,revisado=?,asesor_fk=?,estudiantes_fk=? where id=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, obj.getNombre());
            ps.setString(2, obj.getDescripcion());
            ps.setString(3, obj.getTipo());
            ps.setString(4, obj.getEstado());
            ps.setString(5, obj.getRevisado());
            ps.setString(6, obj.getAsesor_fk());
            ps.setString(7, obj.getEstudiantes_fk());
            ps.setInt(8, obj.getId());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.WARNING, "Error al modificar PROYECTOS Dao {0} ", e.getMessage());
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public void eliminar(ProyectosModel obj) throws Exception {
        String sql = "delete from PROYECTOS where id=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, obj.getId());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.WARNING, "Error al eliminar PROYECTOS Dao {0} ", e.getMessage());
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public List<ProyectosModel> listarTodos() throws Exception {
        List<ProyectosModel> listado = null;
        ProyectosModel doc;
        ResultSet rs;
        String sql = "select * from  V_PROYECTOS";
        try {
            listado = new ArrayList();
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                doc = new ProyectosModel();
                doc.setFila(rs.getString("fila"));
                doc.setId(rs.getInt("id"));
                doc.setNombre(rs.getString("nombre"));
                doc.setDescripcion(rs.getString("descripcion"));
                doc.setTipo(rs.getString("tipo"));
                doc.setEstado(rs.getString("estado"));
                doc.setRevisado(rs.getString("revisado"));
                doc.setAsesor_fk(rs.getString("asesor_fk"));
                doc.setEstudiantes_fk(rs.getString("estudiantes_fk"));
                doc.setTipoConcat(rs.getString("tipoConcat"));
                doc.setEstadoConcat(rs.getString("estadoConcat"));
                doc.setRevisadoConcat(rs.getString("revisadoConcat"));
                doc.setConcatAse(rs.getString("concatAse"));
                doc.setConcatEst(rs.getString("concatEst"));
                listado.add(doc);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.WARNING, "Error al listar PROYECTOS Dao {0} ", e.getMessage());
        }
        return listado;
    }

    public List<ProyectosModel> ListarAsesor() throws SQLException {
        List<ProyectosModel> listadoA = null;
        ProyectosModel per;
        ResultSet rs;
        String sql = "select * from ASESOR";
        try {
            listadoA = new ArrayList();
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                per = new ProyectosModel();
                per.setIdAse(rs.getInt("id"));
                per.setNombreAse(rs.getString("nombre"));
                per.setApellidoAse(rs.getString("apellidos")); 
                listadoA.add(per);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.WARNING, "Error aL listar ASESOR Dao {0} ", e.getMessage());
        }
        return listadoA;
    }
    public List<ProyectosModel> ListarEstudiantes() throws SQLException {
        List<ProyectosModel> listadoA = null;
        ProyectosModel per;
        ResultSet rs;
        String sql = " select * from ESTUDIANTES WHERE rol_fk=1";
        try {
            listadoA = new ArrayList();
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                per = new ProyectosModel();
                per.setIdEst(rs.getInt("id"));
                per.setNombreEst(rs.getString("nombre"));
                per.setApellidoEst(rs.getString("apellidos"));
                listadoA.add(per);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.WARNING, "Error aL listar ESTUDIANTES Dao {0} ", e.getMessage());
        }
        return listadoA;
    }
}
