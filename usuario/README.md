# Demo Nisum Registro Usuarios

### Features
- Aplicación demo Nisum java 11 spring boot;
- Servicios rest;
- Los servicios requieren autenticación Bearer Token;
- Ide Netbeans 16

### Instalación
- Ejecutar UsuarioApplication.java ubicado en com.nisum.usuario;
- La base de datos H2 se generará automáticamente;
- La data del usuario inicial se encuentra en data.sql el cual se inserta en la base H2 al momento de levanta la aplicacion; para la demostración tenemos:
Usuario: "Mau Vigil"
Password: "mauricio.123"

### Token
##### Autenticación
- Para obtener un token ingresar a http://localhost:8080/authenticate
- Probar con el usuario/clave inicial. Request:
{
"username":"Mau Vigil",
"password":"mauricio.123"
}
- De ser correcto la aplicación devolverá un "token"

##### Registro
- Para obtener un token ingresar a http://localhost:8080/nisum/create
- Utilizar token en el Authorization(Bearer Token).
- Se contemplan todas las validaciones requeridas
- Registra usuarios con el request:
{
"name": "Juan Perez",
"email": "juan@dominio.cl",
"password": "abcde123456",
"phones": [
{
"number": "45678989",
"citycode": "2",
"contrycode": "46"
}
]
}
- De ser correcto la aplicación devolverá un "token"

##### Listado
- Para obtener un token ingresar a http://localhost:8080/nisum/users
- Utilizar token en el Authorization (Bearer Token).
- Se muestra la lista de usuarios creados hasta el momento

### Pruebas
- Ejecutar UsuarioApplicationTests.java ubicado en Test Packages com.nisum.usuario;

### Documentación
- Diagrama solución: diagrama.pdf, ubicado en la carpeta raiz del proyecto;

### Swagger
- Integrado con Swagger;
- http://localhost:8080/swagger-ui.html

### H2
- Consola habilitada;
- http://localhost:8080/h2-console/