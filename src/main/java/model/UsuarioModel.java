/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import lombok.Data;
/**
 *
 * @author edgard
 */

@Data
public class UsuarioModel {
    int id;
    String email;
    String password;
    String estado;
    int rol_fk;
}
