USE RSU_ASISTENCIA;
SELECT id,estado,fecha,proyectos_fk,estudiantes_fk FROM PROYECTO_DETALLE where estudiantes_fk="5";
select * from ROL;
select * from V_ASISTENCIA where DATE_FORMAT(fecha,'%Y-%m-%d')='2023-09-15';
select id, nombre, concat(tipo,"_",id,"_",semestre)as proyect from PROYECTOS;
-- $P{Parameter1};
select id,concat(tipo,"_",id,"_",semestre)as Proyect from PROYECTOS 
where id not in (select distinct proyectos_fk from PROYECTO_DETALLE where estudiantes_fk ="2");

select * from V_PROYECTOS;
select distinct estudiantes_fk, concatEst from V_PROYECTOS;
select distinct asesor_fk, concatAse from V_PROYECTOS;
drop table ROL  ;
show tables;
select * from V_PROYECTO_DETALLE where estudiantes_fk="2";
select id,email,password,estado,rol_fk from ADMIN where email="admin1@admin1.com" and password="1234";
                                    <f:selectItem itemLabel="EN PROCESO" itemValue="P"/>
                                    <f:selectItem itemLabel="APROBACION" itemValue="A"/>
                                    <f:selectItem itemLabel="EJECUCION" itemValue="E"/>
                                    <f:selectItem itemLabel="FINALIZACION" itemValue="F"/>
SELECT COUNT(CASE estado WHEN 'P' THEN 'P' END) AS EN_PROCESO,
 COUNT(CASE estado WHEN 'A' THEN 'A' END) AS APROBACION,
 COUNT(CASE estado WHEN 'E' THEN 'E' END) AS EJECUCION,
 COUNT(CASE estado WHEN 'F' THEN 'F' END) AS FINALIZACION FROM PROYECTOS;
select * from ESTUDIANTES; 
 select * from ESTUDIANTES WHERE rol_fk=1;
 select fecha from V_PROYECTOS;
 
 SELECT distinct DATE_FORMAT(fecha,'%Y-%m-%d') 
AS fechaNew from V_PROYECTO_DETALLE;


SELECT * FROM V_PROYECTOS where DATE_FORMAT(fecha,'%Y-%m-%d')='2023-08-18';

 SELECT distinct DATE_FORMAT(fecha,'%Y-%m-%d') 
AS fechaNew from V_PROYECTOS;

INSERT INTO ASISTENCIA (dia,cantHoras,fecha,estado,estudiantes_fk,proyecto_detalle_fk) VALUES (?,?,?,?,?,?)
update ASISTENCIA set dia=?,cantHoras=?,fecha=?,estado=?,estudiantes_fk=?,proyecto_detalle_fk=? where id=?