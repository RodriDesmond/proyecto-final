# proyecto-final
 Trabajo Final Etapa 3 Informatorio 2021 JAVA
 
¨El Informatorio planea lanzar en varias plataformas (Web, iOS y Android) una aplicación donde se publicaran varios proyectos startup (emprendimientos). Donde los emprendedores podrán publicar sus ideas y de esa forma conseguir votos e inclusive recaudar fondos.
Los emprendimientos podrán ser de diferentes rubros (apps, desarrollo de productos, etc).
Se podrán conseguir votos y aportes en cualquier momento. Pero también existirá la posibilidad de suscribirse a eventos en donde el ganador recibirá el premio del evento.¨

DETALLES:
API REST desarrollada en Spring framework
USUARIOS:
Alta /users (POST)
Baja /users/{id}/remove (DELETE) (Soft detele, siguen registrados en la DB)
Modificacion /users/{id} (PUT)
Autorizaciones y autenticaciones hechas con Spring Security y Basic Auth
GET:
Todos los usuarios /users
Usuarios por ciudad /users?city=
Usuarios creados despues de fecha determinada /users?createdDate=

EMPRENDIMIENTOS:
Alta (POST)
Baja (DELETE)
Modificacion (PUT)
GET:
Emprendimientos por tag
Todos los emprendimientos
Emprendimientos no publicados
Emprendimientos de un usuario especifico
POST
Nuevo emprendimiento
Subscribir emprendimiento a un evento

EVENTOS:
Alta (POST)
Baja (DELETE)
Modificacion (PUT)
GET
Ranking de emprendimientos de un evento especifico

VOTOS:
Alta (POST)
GET
Consultar todos los votos de un usuario especifico

