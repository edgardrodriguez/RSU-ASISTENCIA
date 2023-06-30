/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ProyectoDetalleModel;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

/**
 *
 * @author edgard
 */
public class ProyectoDetalleImpl extends Conexion implements ICRUD<ProyectoDetalleModel> {

    public void registrar(UploadedFile archivo, ProyectoDetalleModel obj) throws Exception {
        String sql = "INSERT INTO PROYECTO_DETALLE (descripcion,proyectos_fk,estudiantes_fk,evidencia) VALUES (?,?,?,?)";
        try ( PreparedStatement ps = this.conectar().prepareStatement(sql)) {
            ps.setString(1, obj.getDescripcion());
            ps.setInt(2, obj.getProyectos_fk());
            ps.setInt(3, obj.getEstudiantes_fk());
            ps.setBinaryStream(4, archivo.getInputStream());
            ps.execute();
            ps.close();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.WARNING, "Error al Ingresar PROYECTO_DETALLE Dao {0} ", e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void modificar(ProyectoDetalleModel obj) throws Exception {
        String sql = "update PROYECTO_DETALLE set descripcion=?,proyectos_fk=?,estudiantes_fk=? where id=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, obj.getDescripcion());
            ps.setInt(2, obj.getProyectos_fk());
            ps.setInt(3, obj.getEstudiantes_fk());
            ps.setInt(4, obj.getId());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.WARNING, "Error al modificar PROYECTO_DETALLE Dao {0} ", e.getMessage());
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public void eliminar(ProyectoDetalleModel obj) throws Exception {
        String sql = "delete from PROYECTO_DETALLE where id=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, obj.getId());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.WARNING, "Error al eliminar PROYECTO_DETALLE Dao {0} ", e.getMessage());
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public List<ProyectoDetalleModel> listarTodos() throws Exception {
        List<ProyectoDetalleModel> listado = null;
        ProyectoDetalleModel doc;
        ResultSet rs;
        String sql = "select * from V_PROYECTO_DETALLE";
        try {
            listado = new ArrayList();
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {

                doc = new ProyectoDetalleModel();
                doc.setId(rs.getInt("id"));
                doc.setDescripcion(rs.getString("descripcion"));
                doc.setProyectos_fk(rs.getInt("proyectos_fk"));
                doc.setEstudiantes_fk(rs.getInt("estudiantes_fk"));
                doc.setFila(rs.getString("fila"));
                doc.setConcatEst(rs.getString("concatEst"));
                doc.setProNom(rs.getString("proNom"));
                listado.add(doc);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.WARNING, "Error al listar PROYECTO_DETALLE Dao {0} ", e.getMessage());
        }
        return listado;
    }

    public List<ProyectoDetalleModel> ListarEstudiantes() throws SQLException {
        List<ProyectoDetalleModel> listadoA = null;
        ProyectoDetalleModel per;
        ResultSet rs;
        String sql = " select * from ESTUDIANTES WHERE rol_fk=2";
        try {
            listadoA = new ArrayList();
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                per = new ProyectoDetalleModel();
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

    public List<ProyectoDetalleModel> ListarProyectos() throws SQLException {
        List<ProyectoDetalleModel> listadoA = null;
        ProyectoDetalleModel per;
        ResultSet rs;
        String sql = "select id,nombre from PROYECTOS where estado ='A' OR estado='E'";
        try {
            listadoA = new ArrayList();
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                per = new ProyectoDetalleModel();
                per.setIdPro(rs.getInt("id"));
                per.setNombrePro(rs.getString("nombre"));
                listadoA.add(per);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.WARNING, "Error aL listar PROYECTOS Dao {0} ", e.getMessage());
        }
        return listadoA;
    }

    @Override
    public void registrar(ProyectoDetalleModel obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


    public void modificarArchivo(UploadedFile archivo,ProyectoDetalleModel obj) throws Exception {
        String sql = "update PROYECTO_DETALLE set evidencia=? where id=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setBinaryStream(1, archivo.getInputStream());
            ps.setInt(2, obj.getId());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.WARNING, "Error al modificar archivo Dao {0} ", e.getMessage());
        } finally {
            this.Cerrar();
        }
    }

}
