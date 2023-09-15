/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import model.ProyectoDetalleModel;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.file.UploadedFile;
import org.primefaces.util.SerializableSupplier;

/**
 *
 * @author edgard
 */
public class ProyectoDetalleImpl extends Conexion implements ICRUD<ProyectoDetalleModel> {

    public void registrarProyectos(ProyectoDetalleModel obj) throws Exception {
        String sql = "INSERT INTO PROYECTO_DETALLE (estado,fecha,proyectos_fk,estudiantes_fk) VALUES (?,?,?,?)";
        try ( PreparedStatement ps = this.conectar().prepareStatement(sql)) {
            Timestamp fechaActual = new Timestamp(System.currentTimeMillis());
            String estado = "A";
            ps.setString(1, estado);
            ps.setTimestamp(2, fechaActual);
            ps.setInt(3, obj.getProyectos_fk());
            ps.setInt(4, obj.getEstudiantes_fk());
            ps.execute();
            ps.close();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.WARNING, "Error al Ingresar PROYECTO_DETALLE Dao {0} ", e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void modificar(ProyectoDetalleModel obj) throws Exception {
        String sql = "update PROYECTO_DETALLE set estado=?,fecha=? where id=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            Timestamp fechaActual = new Timestamp(System.currentTimeMillis());
            ps.setString(1, obj.getEstado());
            ps.setTimestamp(2, fechaActual);
            ps.setInt(3, obj.getId());
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
                doc.setFila(rs.getString("fila"));
                doc.setId(rs.getInt("id"));
                doc.setProyectos_fk(rs.getInt("proyectos_fk"));
                doc.setEstudiantes_fk(rs.getInt("estudiantes_fk"));
                doc.setEstado(rs.getString("estado"));
                doc.setConcatCodigo(rs.getString("concatCodigo"));
                doc.setNombrePro(rs.getString("proNom"));
                doc.setConcatEst(rs.getString("concatEst"));
                doc.setEstadoConcat(rs.getString("estadoConcat"));
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
        String sql = "select id,nombre from PROYECTOS where estado ='A'";
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

    public List<ProyectoDetalleModel> ListarPro(int useID) throws SQLException {
        List<ProyectoDetalleModel> listadoA = null;
        ProyectoDetalleModel per;
        ResultSet rs;
        String sql = "select id,concat(tipo,\"_\",id,\"_\",semestre)as proyect from PROYECTOS where id not in (select distinct proyectos_fk from PROYECTO_DETALLE where estudiantes_fk =?);";
        try {
            listadoA = new ArrayList();
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, useID);
            rs = ps.executeQuery();
            while (rs.next()) {
                per = new ProyectoDetalleModel();
                per.setIdPro(rs.getInt("id"));
                per.setCodigoProyecto(rs.getString("proyect"));
                listadoA.add(per);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.WARNING, "Error aL listar PROYECTOS Dao {0} ", e.getMessage());
        }
        return listadoA;
    }

    public List<ProyectoDetalleModel> listarFecha() throws Exception {
        List<ProyectoDetalleModel> lisFech = null;
        ProyectoDetalleModel fech;
        ResultSet rs;
        String sql = "SELECT distinct DATE_FORMAT(fecha,'%Y-%m-%d')AS fechaNew from V_PROYECTO_DETALLE;";
        try {
            lisFech = new ArrayList();
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                fech = new ProyectoDetalleModel();
                fech.setFechaNew(rs.getString("fechaNew"));
                lisFech.add(fech);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.SEVERE, "Error al listar fecha cuot {0} ", e.getMessage());
        }
        return lisFech;
    }

    @Override
    public void registrar(ProyectoDetalleModel obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
