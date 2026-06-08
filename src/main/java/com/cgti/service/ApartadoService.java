package com.cgti.service;

import com.cgti.model.*;
import com.cgti.repository.ApartadoRepository;
import java.time.LocalDateTime;
import java.util.List;

public class ApartadoService {

    private ApartadoRepository repo = new ApartadoRepository();

    // Docente reserva todo el laboratorio
    public String apartarLaboratorio(Usuario usuario, Laboratorio laboratorio,
            LocalDateTime inicio, LocalDateTime fin, String proposito) {

        if (repo.laboratorioOcupado(laboratorio.getId(), inicio, fin)) {
            return "El laboratorio ya está ocupado en ese horario";
        }

        Apartado apartado = new Apartado();
        apartado.setUsuario(usuario);
        apartado.setLaboratorio(laboratorio);
        apartado.setFechaInicio(inicio);
        apartado.setFechaFin(fin);
        apartado.setProposito(proposito);
        apartado.setEstado(EstadoApartado.PENDIENTE);
        repo.guardar(apartado);
        return "Apartado solicitado, esperando aprobación";
    }

    // Alumno reserva una máquina específica
    public String apartarEquipo(Usuario usuario, Laboratorio laboratorio, Equipo equipo,
            LocalDateTime inicio, LocalDateTime fin, String proposito) {

        if (repo.laboratorioOcupado(laboratorio.getId(), inicio, fin)) {
            return "El laboratorio está ocupado por un docente en ese horario";
        }

        if (repo.equipoOcupado(equipo.getId(), inicio, fin)) {
            return "Esa máquina ya está reservada en ese horario";
        }

        Apartado apartado = new Apartado();
        apartado.setUsuario(usuario);
        apartado.setLaboratorio(laboratorio);
        apartado.setEquipo(equipo);
        apartado.setFechaInicio(inicio);
        apartado.setFechaFin(fin);
        apartado.setProposito(proposito);
        apartado.setEstado(EstadoApartado.PENDIENTE);
        repo.guardar(apartado);
        return "Máquina apartada, esperando aprobación";
    }

    public List<Apartado> listarTodos() { return repo.listarTodos(); }

    public List<Apartado> listarPorUsuario(Long usuarioId) {
        return repo.listarPorUsuario(usuarioId);
    }
}
