/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.el.PropertyNotFoundException;
import model.ProyectosModel;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.file.UploadedFile;

/**
 *
 * @author edgard
 */
public class ProyectosImpl extends Conexion implements ICRUD<ProyectosModel> {

    public void registrarProyectos(UploadedFile archivo2, ProyectosModel obj) throws SQLException,PropertyNotFoundException, Exception {
        String sql = "INSERT INTO PROYECTOS (nombre,descripcion,tipo,estado,revisado,ods,facultad,escuelaProfesional,semestre,fecha,link,formato,asesor_fk,estudiantes_fk) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try ( PreparedStatement ps = this.conectar().prepareStatement(sql)) {
            Timestamp fechaActual = new Timestamp(System.currentTimeMillis());
            InputStream inputStream = InputStream.nullInputStream();

            ps.setString(1, obj.getNombre());
            ps.setString(2, obj.getDescripcion());
            ps.setString(3, obj.getTipo());
            String estado = "P";
            String revisado = "S";
            ps.setString(4, estado);
            ps.setString(5, revisado);
            ps.setString(6, obj.getOds());
            ps.setString(7, obj.getFacultad());
            ps.setString(8, obj.getEscuelaProfesional());
            ps.setString(9, obj.getSemestre());
            ps.setTimestamp(10, fechaActual);
            ps.setString(11, obj.getLink());
            ps.setBinaryStream(12, archivo2.getInputStream());
            ps.setString(13, obj.getAsesor_fk());
            ps.setString(14, obj.getEstudiantes_fk());
            ps.execute();
            ps.close();
        } catch (Exception e) {
            if (e instanceof PropertyNotFoundException) {
               throw e;
            }
            Logger.getGlobal().log(Level.WARNING, "Error al Ingresar PROYECTOS Dao {0} ", e.getMessage());
           
        }
    }

    @Override
    public void modificar(ProyectosModel obj) throws Exception {
        String sql = "update PROYECTOS set nombre=?,descripcion=?,tipo=?,estado=?,revisado=?,ods=?,facultad=?,escuelaProfesional=?,semestre=?,fecha=?,link=?,asesor_fk=?,estudiantes_fk=? where id=?";
        try {
            Timestamp fechaActual = new Timestamp(System.currentTimeMillis());
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, obj.getNombre());
            ps.setString(2, obj.getDescripcion());
            ps.setString(3, obj.getTipo());
            ps.setString(4, obj.getEstado());
            ps.setString(5, obj.getRevisado());
            ps.setString(6, obj.getOds());
            ps.setString(7, obj.getFacultad());
            ps.setString(8, obj.getEscuelaProfesional());
            ps.setString(9, obj.getSemestre());
            ps.setTimestamp(10, fechaActual);
            ps.setString(11, obj.getLink());
            ps.setString(12, obj.getAsesor_fk());
            ps.setString(13, obj.getEstudiantes_fk());
            ps.setInt(14, obj.getId());
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
                doc.setOds(rs.getString("ods"));
                doc.setFacultad(rs.getString("facultad"));
                doc.setEscuelaProfesional(rs.getString("escuelaProfesional"));
                doc.setSemestre(rs.getString("semestre")); 
                doc.setLink(rs.getString("link"));
                doc.setAsesor_fk(rs.getString("asesor_fk"));
                doc.setEstudiantes_fk(rs.getString("estudiantes_fk"));
                doc.setTipoConcat(rs.getString("tipoConcat"));
                doc.setEstadoConcat(rs.getString("estadoConcat"));
                doc.setRevisadoConcat(rs.getString("revisadoConcat"));
                doc.setOdsConcat(rs.getString("odsConcat"));
                doc.setFacultadConcat(rs.getString("facultadConcat"));
                doc.setEscuelProfesionalConcat("escuelProfesionalConcat");
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

    @Override
    public void registrar(ProyectosModel obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void modificarArchivo(UploadedFile archivo, ProyectosModel obj) throws Exception {
        String sql = "update PROYECTOS set formato=? where id=?";
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

    public void modificarArchivo2(UploadedFile archivo2, ProyectosModel obj) throws Exception {
        String sql = "update PROYECTOS set resolucion=? where id=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setBinaryStream(1, archivo2.getInputStream());
            ps.setInt(2, obj.getId());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.WARNING, "Error al modificar archivo Dao {0} ", e.getMessage());
        } finally {
            this.Cerrar();
        }
    }

    public StreamedContent traerImagen(StreamedContent archivo, int id) throws SQLException, NullPointerException {

        String sql = "select formato, nombre from PROYECTOS WHERE id =?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet st = ps.executeQuery();
            while (st.next()) {
                InputStream stream = Objects.requireNonNull(st.getBinaryStream("formato"), "archivo no debe ser nulo");
                        
                String description = st.getString("nombre");
                archivo = DefaultStreamedContent.builder()
                        .name(description + ".pdf")
                        .contentType("application/pdf")
                        .stream(() -> stream)
                        .build();

                System.out.println("Estoy en while dao traer pdf, " + archivo);
            }
         } catch (Exception e) {
            if (e instanceof NullPointerException) {
               throw e;
            }
            System.out.println("Error en traer pdf: " + e.getMessage());
        }
        return archivo;
    }

    public StreamedContent traerImagen2(StreamedContent archivo2, int id) throws SQLException, NullPointerException {

        String sql = "select resolucion, nombre from PROYECTOS WHERE id =?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet st = ps.executeQuery();
            while (st.next()) {
                InputStream stream = Objects.requireNonNull(st.getBinaryStream("resolucion"), "archivo no debe ser nulo");
                
                String description = st.getString("nombre");
                archivo2 = DefaultStreamedContent.builder()
                        .name(description + ".pdf")
                        .contentType("application/pdf")
                        .stream(() -> stream)
                        .build();

                System.out.println("Estoy en while dao traer pdf, " + archivo2);
            }
        } catch (Exception e) {
            if (e instanceof NullPointerException) {
               throw e;
            }
            System.out.println("Error en traer pdf: " + e.getMessage());
        }
        return archivo2;
    }

    public List<ProyectosModel> listarFecha() throws Exception {
        List<ProyectosModel> lisFech = null;
        ProyectosModel fech;
        ResultSet rs;
        String sql = " SELECT distinct DATE_FORMAT(fecha,'%Y-%m-%d') AS fechaNew from V_PROYECTOS";
        try {
            lisFech = new ArrayList();
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                fech = new ProyectosModel();
                fech.setFechaNew(rs.getString("fechaNew"));
                lisFech.add(fech);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.SEVERE, "Error al listar fecha {0} ", e.getMessage());
        }
        return lisFech;
    }
}
