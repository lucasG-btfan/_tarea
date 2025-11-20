package com.utn.tareas.service;

import com.utn.tareas.model.Tarea;
import com.utn.tareas.model.Prioridad;
import com.utn.tareas.repository.TareaRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TareaService {

    private final TareaRepository tareaRepository;

    @Value("${app.nombre}")
    private String appNombre;

    @Value("${app.max-tareas}")
    private int maxTareas;

    @Value("${app.mostrar-estadisticas}")
    private boolean mostrarEstadisticas;

    public TareaService(TareaRepository tareaRepository) {
        this.tareaRepository = tareaRepository;
    }

    public Tarea agregarTarea(String descripcion, Prioridad prioridad) {
        if (tareaRepository.obtenerTodas().size() >= maxTareas) {
            throw new RuntimeException("No se pueden agregar más tareas. Límite alcanzado: " + maxTareas);
        }
        Tarea nuevaTarea = new Tarea();
        nuevaTarea.setDescripcion(descripcion);
        nuevaTarea.setCompletada(false);
        nuevaTarea.setPrioridad(prioridad);
        return tareaRepository.guardar(nuevaTarea);
    }

    public List<Tarea> listarTodasLasTareas() {
        return tareaRepository.obtenerTodas();
    }

    public List<Tarea> listarTareasPendientes() {
        return tareaRepository.obtenerTodas().stream()
                .filter(t -> !t.isCompletada())
                .collect(Collectors.toList());
    }

    public List<Tarea> listarTareasCompletadas() {
        return tareaRepository.obtenerTodas().stream()
                .filter(Tarea::isCompletada)
                .collect(Collectors.toList());
    }

    public void marcarTareaComoCompletada(Long id) {
        tareaRepository.buscarPorId(id).ifPresent(tarea -> {
            tarea.setCompletada(true);
        });
    }

    public String obtenerEstadisticas() {
        if (!mostrarEstadisticas) {
            return "Las estadísticas están deshabilitadas.";
        }
        List<Tarea> todas = tareaRepository.obtenerTodas();
        long total = todas.size();
        long completadas = todas.stream().filter(Tarea::isCompletada).count();
        long pendientes = total - completadas;
        return String.format(
                "Total de tareas: %d, Completadas: %d, Pendientes: %d",
                total, completadas, pendientes
        );
    }

    public void imprimirPropiedadesDeConfiguracion() {
        System.out.println("Propiedades de configuración:");
        System.out.println("Nombre de la aplicación: " + appNombre);
        System.out.println("Máximo de tareas permitidas: " + maxTareas);
        System.out.println("Mostrar estadísticas: " + mostrarEstadisticas);
    }
    public boolean eliminarTareaPorId(Long id) {
        return tareaRepository.eliminarPorId(id);
    }

}
