package com.cgti.service;

import com.cgti.model.Equipo;
import com.cgti.repository.EquipoRepository;
import java.util.List;

public class EquipoService {

    private EquipoRepository repo = new EquipoRepository();

    public void guardar(Equipo equipo) {
        repo.guardar(equipo);
    }

    public List<Equipo> listarPorLaboratorio(String laboratorio) {
        return repo.listarPorLaboratorio(laboratorio);
    }
}
