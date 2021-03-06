# **Proyecto Realstate**

***Realstate** es una aplicación que nos permite, no solo crear nuestras propias Viviendas, sino también Propietarios, Immobiliarias, Interesados e incluso Intereses que se pueden asignar a las viviendas e interesados que deseemos.*
</br>
> ## ✒️ **Autora ✒️**

* #### M.ª Inmaculada Domínguez Vargas
</br>


### 📋 Las entidades que forman nuestra aplicación 📋
* #### Vivienda :house:

* #### Inmobiliaria :department_store:
* #### Usuario :frowning_man:
    1. Administrador
    2. Gestor
    3. Propietario

* #### Interesa :cupid:


</br>



## :wrench: ¿Qué hacer para arrancar el proyecto? :wrench:
* ### **Descargar IntelliJ IDEA Community Edition 2021.2.2**
* ### **Descargar JAVA JDK 17**
* ### **Abrir IntelliJ**
* ### **Configurar la version JDK**
1. Clicar a File.
2. Luego a Proyect Structure.
3. Seleccionar en Proyect SDK la version 17 descargada previamente

* ### **Arrancar el proyecto**
1. Clicar al boton Maven.
2. Luego a Pluggins.
3. Luego a spring-boot
4. Para finalizar clicar a spring-boot-run

## 🛠️ ¿Qué puede hacer Realstate? 🛠️

* ### **Registro y login:**
1. Dar de alta un usuario propietario
2. Dar de alta a un usuario gestor de una inmobiliaria.
3. Dar de alta a un usuario administrador
4. Hacer login desde cualquier tipo de usuario

* ### **Las funcionalidades que tiene la entidad Vivienda:**

1. Añadir nueva vivienda. Se da de alta también al propietario a la vez.
2. Obtener todas las propiedades. Se deben obtener solamente los datos que se vayan a utilizar.
3. Obtener los datos de una vivienda. Se deben obtener también los datos del propietario.
4. Modificar una vivienda.
5. Eliminar una vivienda (debe eliminar todos los intereses sobre dicha vivienda, pero no los interesados).
6. Establece que una vivienda pueda ser gestionada por una inmobiliaria.
7. Elimina la gestión de una vivienda por parte de una inmobiliaria.
8. Añadir un nuevo interesado con la información de interés para la vivienda ID.
9. Añadir un nuevo interés para la vivienda ID, pero rescatando la información de interesado con ID2.
10. Eliminar el interés de un interesado por una vivienda.
11. Obtener las viviendas por las que más gente se ha interesado.

* ### **Las funcionalidades que tiene la entidad Inmobiliaria:**

1. Añadir una nueva inmobiliaria
2. Obtener todas inmobiliarias
3. Obtener los datos de una inmobiliaria (debe obtener un listado sencillo de las viviendas gestionadas por la inmobiliaria).
4. Eliminar una inmobiliaria (cuando se elimina, no se eliminan las viviendas gestionadas por esa inmobiliaria).
5. Obtener todos los gestores de una inmobiliaria
6. Añadir un gestor a una inmobiliaria
7. Borrar un gestor de una inmobiliaria
* ### **Las funcionalidades que tienen los usuarios interesados:**

1. Obtener los datos de todos los interesados
2. Obtener los datos de un interesado

* ### **Las funcionalidades que tiene la entidad Propietario:**

1. Obtener todos los propietarios
2. Obtener los datos de un propietario (deben incluir también algunos datos de las viviendas que tiene en propiedad).
3. Eliminar un propietario (se deben eliminar en cascada las viviendas de este propietario).

