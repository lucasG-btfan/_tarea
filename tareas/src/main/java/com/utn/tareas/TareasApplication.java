package com.utn.tareas;

import com.utn.tareas.model.Prioridad;
import com.utn.tareas.model.Tarea;
import com.utn.tareas.service.MensajeService;
import com.utn.tareas.service.TareaService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class TareasApplication implements CommandLineRunner {

	private final TareaService tareaService;
	private final MensajeService mensajeService;

	public TareasApplication(TareaService tareaService, MensajeService mensajeService) {
		this.tareaService = tareaService;
		this.mensajeService = mensajeService;
	}

	public static void main(String[] args) {
		SpringApplication.run(TareasApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		boolean ejecutando = true;

		while (ejecutando) {
			System.out.println("--Agenda de Tareas--");
			System.out.println("1. Agregar tarea");
			System.out.println("2. Listar todas las tareas");
			System.out.println("3. Listar tareas pendientes");
			System.out.println("4. Listar tareas completadas");
			System.out.println("5. Marcar tarea como completada");
			System.out.println("6. Eliminar tarea");
			System.out.println("7. Mostrar estadísticas");
			System.out.println("8. Salir");
			System.out.print("Seleccione una opción: ");

			int opcion;
			try {
				opcion = scanner.nextInt();
				scanner.nextLine();
			} catch (Exception e) {
				System.out.println("Opción no válida. Intente nuevamente.");
				scanner.nextLine();
				continue;
			}

			switch (opcion) {
				case 1:
					System.out.print("Descripción: ");
					String descripcion = scanner.nextLine();
					Prioridad prioridad = null;
					boolean prioridadValida = false;
					while (!prioridadValida) {
						System.out.print("Prioridad (ALTA/MEDIA/BAJA): ");
						String prioridadStr = scanner.nextLine().toUpperCase();
						try {
							prioridad = Prioridad.valueOf(prioridadStr);
							prioridadValida = true;
						} catch (IllegalArgumentException e) {
							System.out.println("Prioridad no válida. Use ALTA, MEDIA o BAJA.");
						}
					}
					try {
						Tarea nueva = tareaService.agregarTarea(descripcion, prioridad);
						System.out.println("Tarea agregada: " + nueva);
					} catch (Exception e) {
						System.out.println("Error: " + e.getMessage());
					}
					break;
				case 2:
					System.out.println("--- Todas las tareas ---");
					tareaService.listarTodasLasTareas().forEach(System.out::println);
					break;
				case 3:
					System.out.println("--- Tareas pendientes ---");
					tareaService.listarTareasPendientes().forEach(System.out::println);
					break;
				case 4:
					System.out.println("--- Tareas completadas ---");
					tareaService.listarTareasCompletadas().forEach(System.out::println);
					break;
				case 5:
					System.out.print("ID de tarea a completar: ");
					Long idCompletar = scanner.nextLong();
					tareaService.marcarTareaComoCompletada(idCompletar);
					System.out.println("Tarea marcada como completada");
					scanner.nextLine();
					break;
				case 6:
					System.out.print("ID de tarea a eliminar: ");
					Long idEliminar = scanner.nextLong();
					boolean eliminada = tareaService.eliminarTareaPorId(idEliminar);
					if (eliminada) {
						System.out.println("Tarea eliminada correctamente.");
					} else {
						System.out.println("No se encontró una tarea con ese ID.");
					}
					scanner.nextLine();
					break;
				case 7:

					String estadisticas= tareaService.obtenerEstadisticas();
					System.out.println(estadisticas);
					break;

				case 8:
					ejecutando = false;
					break;
				default:
					System.out.println("Opción no válida. Intente nuevamente.");
			}
		}
		scanner.close();
		System.out.println(mensajeService.mostrarDespedida());
	}
}
