package com.utn.tareas.repository;

import com.utn.tareas.model.Tarea;
import com.utn.tareas.model.Prioridad;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Repository
@Getter
@Setter
public class TareaRepository {

    private List<Tarea> tareas = new ArrayList<>();
    private AtomicLong idGenerator = new AtomicLong(1L);

    public TareaRepository() {
        this.tareas.add(new Tarea(idGenerator.getAndIncrement(), "Barrer la vereda", false, Prioridad.MEDIA));
        this.tareas.add(new Tarea(idGenerator.getAndIncrement(), "Estudiar para la presentación del parcial", false, Prioridad.ALTA));
        this.tareas.add(new Tarea(idGenerator.getAndIncrement(), "Rayar pan viejo", true, Prioridad.BAJA));
    }

    public Tarea guardar(Tarea tarea) {
        tarea.setId(idGenerator.getAndIncrement());
        tareas.add(tarea);
        return tarea;
    }

    public List<Tarea> obtenerTodas() {
        return new ArrayList<>(tareas);
    }

    public Optional<Tarea> buscarPorId(long id) {
        return tareas.stream()
                .filter(t -> t.getId() == id)
                .findFirst();
    }

    public boolean eliminarPorId(long id) {
        return tareas.removeIf(t -> t.getId() == id);
    }
}
