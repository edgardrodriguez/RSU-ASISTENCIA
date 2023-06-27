@echo off
call mvn clean package
call docker build -t com.mycompany/ASISTENCIA .
call docker rm -f ASISTENCIA
call docker run -d -p 9080:9080 -p 9443:9443 --name ASISTENCIA com.mycompany/ASISTENCIA