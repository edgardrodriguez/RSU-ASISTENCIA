/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author edgard
 */
import lombok.Data;

@Data
public class AsistenciaModel {

    int id;
    String dia;
    int cantHoras;
    Timestamp fecha;
    String estado;
    int estudiantes_fk;
    int proyecto_fk;
    String nombrePro;
    
    String diaConcat;
    String fila;
    String concatEst;
    String descripcion;

    int idEst;
    String nombreEst;
    String apellidoEst;
    
    int idPro;
    String nomPro;
}
