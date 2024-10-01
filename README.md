## CRUD Ready

Esta es una pequeña práctica enfocada al proyecto *"Ready"*,
el cual es un gestor de tareas en linea y ha sido mi 
proyecto escogido para presentar en la tecnología Análisis
y Desarrollo de Software en el SENA.

La práctica se basa básicamente en un CRUD de tareas de 
usuario utilizando Servelts, JSP, haciendo una conexión
a la base de datos (MySQL) por medio de JDBC y buscando
mantener las mejores prácticas en programación y convenciones
del lenguaje, junto con una estructura MVC.

### ¿Cómo probar la app?

En este caso, la manera más fácil de probar la app sería
simplemente clonar este repositorio utilizando *git clone*,
ir a MySQL (preferiblemente) y crear un nuevo *Schema*
(puedes llamarle como quieras, yo le llamé *"crud_ready"*)
para luego ejecutar la siguiente sentencia que creará la
tabla *tareas*:


`` CREATE TABLE `tareas` (`` <br>
`` `id` bigint NOT NULL AUTO_INCREMENT,`` <br>
`` `titulo` varchar(100) NOT NULL,``<br>
`` `descripcion` text,``<br>
`` `fecha_creacion` datetime NOT NULL,``<br>
`` `fecha_terminal` datetime NOT NULL,``<br>
`` `estado` varchar(10) NOT NULL DEFAULT 'PENDIENTE',``<br>
`` PRIMARY KEY (`id`)``<br>
`` ) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;``

Luego de haber creado la tabla en tu DB, ahora simplemente
deberías ir a la clase de *Conexion.java* (src/main/java/com/juliobro/crudready/config/Conexion.java)
y configurar los campos de acuerdo a tu entorno personal. 
Puedes testear que tu conexión funcione correctamente en
src/test/java/com/juliobro/crudready/config/ConexionTest.

Y ya estaría, configuras tu servidor (yo utilicé un servidor
Tomcat en su versión 10.1.30) y no debería faltar nada más para 
que puedas probar la app sin inconvenientes.