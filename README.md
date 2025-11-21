# _tarea
Este Proyeccto se utiliza para realizar una agenda de tareas, puedes administrar las tareas con diferentes niveles de prioridad. Existe perfil de desarrolo y perfil de producción

# Tecnologías Utilizadas
- **Java 21**
- **Spring Boot 3.2.5**
- **Maven** (Gestor de dependencias)
- **Lombok** (Generación de código)
- **Spring Profiles** (Gestión de entornos)

# Como cambiar de perfil
1)Para utilizar la app como dev ingrese al archivo application.properties 
2) modifica para que el profile actvio sea igual a dev spring.profiles.active=dev
3) Para poder cambiarlo a perfil producción cambia spring.profiles.active=prod

# Pasos para utilizar el proyecto
Al seleccionar TareasApplication (main) ejecute el programa
Saldra el menu con 8 opciones para realizar el programa dentro de un while
"1. Agregar tarea" pide la descripción de la tarea que luego utiliza el metodo agregarTarea de tareaService
"2. Listar todas las tareas" para listar todad las tareas utilizando listarTodasLasTareas de tareaService
"3. Listar tareas pendientes" esta opcion solo muestra las tares pendientes con el metodo listarTareasPendientes de tareaService
"4. Listar tareas completadas" mostrar todas las tareas completas utilizando listarTareasCompletadas de tareaService
"5. Marcar tarea como completada" para pasar una tarea como completada y asi salga en tareas completadas, con el metodo marcarTareaComoCompletada  de tareaServicio
"6. Eliminar tarea" utiliza esta opción para eliminar una tarea (no es lo mismo que este completada) con el id de la tarea utilizando eliminarTareaPorId de tareaService
"7. Mostrar estadísticas" esta funcion solo puede verlo el perfil dev que mustre las estadísticas
"8. Salir" para salir del while

# Problemas surgidos
Al utilizar Maven como gestor de dependencias, IntelliJ IDEA no reconocía los métodos generados por Lombok, mostrando errores
Maven requiere configuración explícita para los annotation processors, a diferencia de Gradle que los detecta automáticamente. IntelliJ no obtenía los processors del classpath de Maven.

# Solución
1. Configurar Annotation Processing en IntelliJ
2. Habilitar "Obtain processors from project classpath"
3. Agregar Lombok manualmente al classpath del IDE cuando fue solicitado (en mi caso solo funciono agregarlo manualmente al classpath)

### Prerrequisitos
- Java 21 o superior
- Maven 3.6+ 
- IntelliJ IDEA (recomendado) o otro IDE

# Las capturas de Pantallas
Las capturas estan nombradas en la manera del cual hay que ver los

### Clonar y Ejecutar
```bash
# 1. Clonar el proyecto
git clone [url-del-repositorio]

# 2. Navegar al directorio
cd tareas

# 3. Compilar el proyecto
mvn clean compile

# 4. Ejecutar la aplicación
mvn spring-boot:run
