USE RSU_ASISTENCIA;

INSERT INTO ROL (role) 
values	('A'),
		('E'),
        ('L'),
        ('S');

INSERT INTO CARRERAS (nombre,ciclo,turno)
VALUES 	('INGENIERIA DE SISTEMAS','X','M'),
		('INGENIERIA DE SISTEMAS','IX','M'),
        ('INGENIERIA DE SISTEMAS','VIII','M'),
        ('INGENIERIA DE SISTEMAS','VII','M'),
        ('INGENIERIA DE SISTEMAS','VI','M'),
        ('INGENIERIA DE SISTEMAS','V','M'),
        ('INGENIERIA DE SISTEMAS','IV','M'),
        ('INGENIERIA DE SISTEMAS','III','M'),
        ('INGENIERIA DE SISTEMAS','II','M'),
        ('INGENIERIA DE SISTEMAS','I','M'),
		('INGENIERIA DE SISTEMAS','X','N'),
		('INGENIERIA DE SISTEMAS','IX','N'),
        ('INGENIERIA DE SISTEMAS','VIII','N'),
        ('INGENIERIA DE SISTEMAS','VII','N'),
        ('INGENIERIA DE SISTEMAS','VI','N'),
        ('INGENIERIA DE SISTEMAS','V','N'),
        ('INGENIERIA DE SISTEMAS','IV','N'),
        ('INGENIERIA DE SISTEMAS','III','N'),
        ('INGENIERIA DE SISTEMAS','II','N'),
        ('INGENIERIA DE SISTEMAS','I','N'),
        
        ('AGRONOMIA','X','M'),
		('AGRONOMIA','IX','M'),
        ('AGRONOMIA','VIII','M'),
        ('AGRONOMIA','VII','M'),
        ('AGRONOMIA','VI','M'),
        ('AGRONOMIA','V','M'),
        ('AGRONOMIA','IV','M'),
        ('AGRONOMIA','III','M'),
        ('AGRONOMIA','II','M'),
        ('AGRONOMIA','I','M'),
		('AGRONOMIA','X','N'),
		('AGRONOMIA','IX','N'),
        ('AGRONOMIA','VIII','N'),
        ('AGRONOMIA','VII','N'),
        ('AGRONOMIA','VI','N'),
        ('AGRONOMIA','V','N'),
        ('AGRONOMIA','IV','N'),
        ('AGRONOMIA','III','N'),
        ('AGRONOMIA','II','N'),
        ('AGRONOMIA','I','N'),
		
        ('ADMINSTRACION','X','M'),
		('ADMINSTRACION','IX','M'),
        ('ADMINSTRACION','VIII','M'),
        ('ADMINSTRACION','VII','M'),
        ('ADMINSTRACION','VI','M'),
        ('ADMINSTRACION','V','M'),
        ('ADMINSTRACION','IV','M'),
        ('ADMINSTRACION','III','M'),
        ('ADMINSTRACION','II','M'),
        ('ADMINSTRACION','I','M'),
		('ADMINSTRACION','X','N'),
		('ADMINSTRACION','IX','N'),
        ('ADMINSTRACION','VIII','N'),
        ('ADMINSTRACION','VII','N'),
        ('ADMINSTRACION','VI','N'),
        ('ADMINSTRACION','V','N'),
        ('ADMINSTRACION','IV','N'),
        ('ADMINSTRACION','III','N'),
        ('ADMINSTRACION','II','N'),
        ('ADMINSTRACION','I','N'),
		
        ('CONTABILIDAD','X','M'),
		('CONTABILIDAD','IX','M'),
        ('CONTABILIDAD','VIII','M'),
        ('CONTABILIDAD','VII','M'),
        ('CONTABILIDAD','VI','M'),
        ('CONTABILIDAD','V','M'),
        ('CONTABILIDAD','IV','M'),
        ('CONTABILIDAD','III','M'),
        ('CONTABILIDAD','II','M'),
        ('CONTABILIDAD','I','M'),
		('CONTABILIDAD','X','N'),
		('CONTABILIDAD','IX','N'),
        ('CONTABILIDAD','VIII','N'),
        ('CONTABILIDAD','VII','N'),
        ('CONTABILIDAD','VI','N'),
        ('CONTABILIDAD','V','N'),
        ('CONTABILIDAD','IV','N'),
        ('CONTABILIDAD','III','N'),
        ('CONTABILIDAD','II','N'),
        ('CONTABILIDAD','I','N'),
		
        ('TURISMO','X','M'),
		('TURISMO','IX','M'),
        ('TURISMO','VIII','M'),
        ('TURISMO','VII','M'),
        ('TURISMO','VI','M'),
        ('TURISMO','V','M'),
        ('TURISMO','IV','M'),
        ('TURISMO','III','M'),
        ('TURISMO','II','M'),
        ('TURISMO','I','M'),
		('TURISMO','X','N'),
		('TURISMO','IX','N'),
        ('TURISMO','VIII','N'),
        ('TURISMO','VII','N'),
        ('TURISMO','VI','N'),
        ('TURISMO','V','N'),
        ('TURISMO','IV','N'),
        ('TURISMO','III','N'),
        ('TURISMO','II','N'),
        ('TURISMO','I','N');

INSERT INTO ADMIN (email,password,estado,rol_fk)
VALUES	('admin1@admin1.com','1234','A',1),
		('admin2@admin2.com','1234','A',1),
        ('admin3@admin3.com','1234','A',1);

INSERT INTO ASESOR (nombre,apellidos,password,email,DNI,celular,estado,rol_fk)
VALUES 	('Jose Eduardo','Sanchez Julian','1234','docente1@docente1.com','34535234','923612459','A',3),
		('Ricardo Luis','Marcos Jimenez','1234','docente2@docente2.com','34535234','923612459','A',3),
        ('Brian Marco','Quispe Lucas','1234','docente3@docente3.com','34535234','923612459','A',3),
        ('Martin Carlos','Damian Garcia','1234','docente4@docente4.com','34535234','923612459','A',3),
        ('Jean Simon','Felipe Santos','1234','docente5@docente5.com','34535234','923612459','A',3),
        ('Jose Guerrero','Cuadros Caceres','1234','docente6@docente6.com','34535234','923612459','A',3),
        ('Maximo Juan','Renato Perez','1234','docente7@docente7.com','34535234','923612459','A',3);
 

INSERT INTO ESTUDIANTES (nombre,apellidos,password,email,DNI,celular,estado,rol_fk,carreras_fk)
VALUES ('Jose Eduardo','Sanchez Julian','1234','docente1@docente1.com','34535234','923612459','A',3,1);
 

INSERT INTO ESTUDIANTES (nombre,apellidos,password,email,DNI,celular,estado,rol_fk,carreras_fk,estudiantes_fk)
VALUES 	('Jose Eduardo','Sanchez Julian','1234','docente1@docente1.com','34535234','923612459','A',3,1,1),
		('Ricardo Luis','Marcos Jimenez','1234','docente2@docente2.com','34535234','923612459','A',3,21,1),
        ('Brian Marco','Quispe Lucas','1234','docente3@docente3.com','34535234','923612459','A',3,41,1),
        ('Martin Carlos','Damian Garcia','1234','docente4@docente4.com','34535234','923612459','A',3,61,1),
        ('Jean Simon','Felipe Santos','1234','docente5@docente5.com','34535234','923612459','A',3,81,1);

select * from PROYECTOS;
INSERT INTO PROYECTOS (nombre,descripcion,tipo,estado,revisado,asesor_fk,estudiantes_fk)
VALUES	('NAVIDAD EN SAN LUIS','CHOCOLATADA NAVIDEÑA','P','A','C','1','1'),
		('PROYECTO SOCIAL','ENTREGA DE VIVERES','P','A','C','2','1'),
        ('ORIENTACION FAMILIAR','ATENCION A POBLACION VULNERABLE','P','A','D','3','1'),
        ('SHOW INFANTIL','ENTREGA DE REGALOS','P','A','D','4','1'),
        ('COMPARTIR IMPERIAL','ENTREGA DE PANETONES','P','A','C','5','1'),
        ('NAVIDAD EN SAN ASIA','CHOCOLATADA NAVIDEÑA','P','A','C','6','1');

INSERT INTO PROYECTO_DETALLE (descripcion,proyectos_fk,estudiantes_fk)
values ('APOYO','1','3'),
	   ('COMPRA DE COSAS','2','3'),
       ('ACTIVIDAD','2','3'),
       ('COCINAR','3','4'),
       ('REPARTIR','4','2'),
       ('LIMPIAR','3','5');
       

INSERT INTO ASISTENCIA (dia,cantHoras,fecha,estado,estudiantes_fk,proyecto_detalle_fk)
VALUES	('1',4,'2023-01-01 00:00:01','A',1,1),
		('1',3,'2023-02-08 00:00:01','A',2,1),
        ('1',2,'2023-04-05 00:00:01','A',2,1), 
        ('1',4,'2023-01-01 00:00:01','A',3,2),
        ('1',2,'2023-01-01 00:00:01','A',3,2),
        ('1',4,'2023-01-01 00:00:01','A',3,2),
        ('1',3,'2023-01-01 00:00:01','A',4,3),
        ('1',4,'2023-01-01 00:00:01','A',4,3),
        ('1',3,'2023-01-01 00:00:01','A',4,3);