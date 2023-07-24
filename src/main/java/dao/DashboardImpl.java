/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author edgard
 */
public class DashboardImpl extends Conexion{

    public List<Number> dashboardPersonal() throws Exception {
        this.conectar();
        List<Number> lista = new ArrayList();
        try {
            String sql = "SELECT COUNT(CASE tipo WHEN 'P' THEN 'P' END) AS PROYECCION_SOCIAL,\n" +
                        " COUNT(CASE tipo WHEN 'V' THEN 'V' END) AS VOLUNTARIADO,\n" +
                        " COUNT(CASE tipo WHEN 'E' THEN 'E' END) AS EXTENSION_UNIVERSITARIA FROM PROYECTOS";
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Logger.getGlobal().log(Level.INFO, "Existen datos");
                lista.add(rs.getInt("PROYECCION_SOCIAL"));
                lista.add(rs.getInt("VOLUNTARIADO"));
                lista.add(rs.getInt("EXTENSION_UNIVERSITARIA"));
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.SEVERE, "Error en dashboardPersonaImpl {0}", e.getMessage());
        } finally {
            this.Cerrar();
        }
        return lista;
    }
}
