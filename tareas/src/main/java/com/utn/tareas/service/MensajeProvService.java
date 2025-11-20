package com.utn.tareas.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("prod")
@Service
public class MensajeProvService implements MensajeService {

    @Override
    public String mostrarBienvenida() {
        return "Bienvenido para a la app Chores Prodccion";
    }

    @Override
    public String mostrarDespedida() {
        return "Chau que tengas un buen día";
    }
}
