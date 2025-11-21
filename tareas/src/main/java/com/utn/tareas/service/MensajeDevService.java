package com.utn.tareas.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("dev")
@Service
public class MensajeDevService implements MensajeService {

    @Override
    public String mostrarBienvenida() {
        return "Bienvenido a la app Chores deasarollo";
    }

    @Override
    public String mostrarDespedida() {
        return "Chau que tengas un buen día";
    }
}
