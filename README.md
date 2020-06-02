# PCLSystem

## Propósito y alcance del proyecto
El propósito del desarrollo del software es la de poder facilitar a estudios jurídicos parametrizando el manejo de los procesos que se realizan dentro del rubro como la gestión de expedientes y cobros a clientes, calendarización y delegación de actividades, y reportes.
El proyecto tendrá como alcance la elaboración de una aplicación web para el manejo de los procesos del estudio judicial, de manera a que esto pueda facilitar la toma de decisiones y la automatización de las actividades dentro de la empresa.

## Módulos del proyecto
* Gestión de usuarios
* Gestión de clientes
* Gestión de expedientes
* Facturación
* Agenda
* Notificaciones
* Reportes

## Descripción técnica del backend
Se crea una API Rest para la gestión de información y procesos necesarios dentro de la empresa.

### Tecnologías utilizadas
* Wildfly
* Postgres
* Java EE
* Java Servlet
* Hibernate
* JasperReports


### Observaciones
Para envío de correos, crear un archivo *.env* en los proyectos de Notificacion con los parámetros de *CORREO* y *CONTRASENHA*. Este archivo debe estar ubicado en la misma carpeta donde se encuentran el resto de los archivos. Sin este archivo, la aplicación correrá con errores.
Ejemplo:
```
CORREO:micorreo@dominio.com
CONTRASENHA:micontrasenha
```