package com.cgti.service; // pertenece a el paquete 

import com.cgti.model.*; // importa todas las clases 
import com.cgti.repository.ApartadoRepository;
import java.time.LocalDateTime;
import java.util.List;

public class ApartadoService {

    private ApartadoRepository repo = new ApartadoRepository();

    // Cualquier usuario reserva una máquina específica
    public String apartarEquipo(Usuario usuario, Laboratorio laboratorio, Equipo equipo,
            LocalDateTime inicio, LocalDateTime fin, String proposito) {

        // Verifica que la máquina específica esté libre
        if (repo.equipoOcupado(equipo.getId(), inicio, fin)) {
            return "Esa máquina ya está reservada en ese horario";
        }

        // Verificación pasó → se aprueba automáticamente
        Apartado apartado = new Apartado();
        apartado.setUsuario(usuario);
        apartado.setLaboratorio(laboratorio);
        apartado.setEquipo(equipo);
        apartado.setFechaInicio(inicio);
        apartado.setFechaFin(fin);
        apartado.setProposito(proposito);
        apartado.setEstado(EstadoApartado.APROBADO);
        repo.guardar(apartado);
        return "Máquina apartada y aprobada exitosamente";
    }

    public List<Apartado> listarTodos() { return repo.listarTodos(); }

    public List<Apartado> listarPorUsuario(Long usuarioId) {
        return repo.listarPorUsuario(usuarioId);
    }
}
