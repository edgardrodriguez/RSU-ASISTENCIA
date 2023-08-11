USE RSU_ASISTENCIA;
SELECT * FROM V_PROYECTOS;
select * from PROYECTO_DETALLE ;
select id,nombre from PROYECTOS where estado ='A' OR estado='E';
                                    <f:selectItem itemLabel="EN PROCESO" itemValue="P"/>
                                    <f:selectItem itemLabel="APROBACION" itemValue="A"/>
                                    <f:selectItem itemLabel="EJECUCION" itemValue="E"/>
                                    <f:selectItem itemLabel="FINALIZACION" itemValue="F"/>
SELECT COUNT(CASE estado WHEN 'P' THEN 'P' END) AS EN_PROCESO,
 COUNT(CASE estado WHEN 'A' THEN 'A' END) AS APROBACION,
 COUNT(CASE estado WHEN 'E' THEN 'E' END) AS EJECUCION,
 COUNT(CASE estado WHEN 'F' THEN 'F' END) AS FINALIZACION FROM PROYECTOS;
select * from PROYECTO_DETALLE where estudiantes_fk=2; 
 select * from ESTUDIANTES WHERE rol_fk=1;
INSERT INTO ASISTENCIA (dia,cantHoras,fecha,estado,estudiantes_fk,proyecto_detalle_fk) VALUES (?,?,?,?,?,?)
update ASISTENCIA set dia=?,cantHoras=?,fecha=?,estado=?,estudiantes_fk=?,proyecto_detalle_fk=? where id=?