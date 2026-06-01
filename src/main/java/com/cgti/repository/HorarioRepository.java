package com.cgti.repository;

import com.cgti.model.DiaSemana;
import com.cgti.model.HorarioDocente;
import jakarta.persistence.*;
import java.time.LocalTime;
import java.util.List;

public class HorarioRepository {

    private EntityManagerFactory emf =
        Persistence.createEntityManagerFactory("cgtiPU");

    public void guardar(HorarioDocente horario) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(horario);
        em.getTransaction().commit();
        em.close();
    }

    public List<HorarioDocente> listarTodos() {
        EntityManager em = emf.createEntityManager();
        List<HorarioDocente> lista = em.createQuery(
            "SELECT h FROM HorarioDocente h", HorarioDocente.class)
            .getResultList();
        em.close();
        return lista;
    }

    // Verificar si hay horario de docente en ese día y hora
    public boolean hayHorarioDocente(Long labId, DiaSemana dia, LocalTime inicio, LocalTime fin) {
        EntityManager em = emf.createEntityManager();
        long count = em.createQuery(
            "SELECT COUNT(h) FROM HorarioDocente h WHERE h.laboratorio.id = :labId " +
            "AND h.diaSemana = :dia " +
            "AND h.horaInicio < :fin AND h.horaFin > :inicio", Long.class)
            .setParameter("labId", labId)
            .setParameter("dia", dia)
            .setParameter("inicio", inicio)
            .setParameter("fin", fin)
            .getSingleResult();
        em.close();
        return count > 0;
    }
}
