#!/bin/sh
mvn clean package && docker build -t com.mycompany/ASISTENCIA .
docker rm -f ASISTENCIA || true && docker run -d -p 9080:9080 -p 9443:9443 --name ASISTENCIA com.mycompany/ASISTENCIA