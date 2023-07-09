USE RSU_ASISTENCIA;
SELECT * FROM ASISTENCIA;
select * from PROYECTO_DETALLE ;
select id,nombre from PROYECTOS where estado ='A' OR estado='E';
select * from PROYECTO_DETALLE where estudiantes_fk=2; 
 select * from ESTUDIANTES WHERE rol_fk=1;
INSERT INTO ASISTENCIA (dia,cantHoras,fecha,estado,estudiantes_fk,proyecto_detalle_fk) VALUES (?,?,?,?,?,?)
update ASISTENCIA set dia=?,cantHoras=?,fecha=?,estado=?,estudiantes_fk=?,proyecto_detalle_fk=? where id=?