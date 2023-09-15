/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;
import lombok.Data;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.file.UploadedFile;

@Data
public class ProyectoDetalleModel {

    int id;
    String descripcion;
    int proyectos_fk;
    int estudiantes_fk;
    String estado;
    String estadoConcat;
    String codigoProyecto;
    String concatCodigo;
    private UploadedFile archivo;
    private StreamedContent archivoTraido;
    
    String fila;
    String concatEst;
    String proNom;
    
    int idEst;
    String nombreEst;
    String apellidoEst;
    int rol_fk;
    
    int idPro;
    String nombrePro;
    
    int idFecha;
    String fechaNew;
    
}
