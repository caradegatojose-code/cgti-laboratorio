package com.cgti.repository;

import com.cgti.model.Apartado;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

public class ApartadoRepository {

    private EntityManagerFactory emf =
        Persistence.createEntityManagerFactory("cgtiPU");

    public void guardar(Apartado apartado) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(apartado);
        em.getTransaction().commit();
        em.close();
    }

    public List<Apartado> listarTodos() {
        EntityManager em = emf.createEntityManager();
        List<Apartado> lista = em.createQuery(
            "SELECT a FROM Apartado a", Apartado.class)
            .getResultList();
        em.close();
        return lista;
    }

    public List<Apartado> listarPorUsuario(Long usuarioId) {
        EntityManager em = emf.createEntityManager();
        List<Apartado> lista = em.createQuery(
            "SELECT a FROM Apartado a WHERE a.usuario.id = :id", Apartado.class)
            .setParameter("id", usuarioId)
            .getResultList();
        em.close();
        return lista;
    }

    // Verificar si el laboratorio está ocupado en ese horario
    public boolean laboratorioOcupado(Long labId, LocalDateTime inicio, LocalDateTime fin) {
        EntityManager em = emf.createEntityManager();
        long count = em.createQuery(
            "SELECT COUNT(a) FROM Apartado a WHERE a.laboratorio.id = :labId " +
            "AND a.estado = 'APROBADO' " +
            "AND a.fechaInicio < :fin AND a.fechaFin > :inicio", Long.class)
            .setParameter("labId", labId)
            .setParameter("inicio", inicio)
            .setParameter("fin", fin)
            .getSingleResult();
        em.close();
        return count > 0;
    }

    // Verificar si un equipo específico está ocupado
    public boolean equipoOcupado(Long equipoId, LocalDateTime inicio, LocalDateTime fin) {
        EntityManager em = emf.createEntityManager();
        long count = em.createQuery(
            "SELECT COUNT(a) FROM Apartado a WHERE a.equipo.id = :equipoId " +
            "AND a.estado = 'APROBADO' " +
            "AND a.fechaInicio < :fin AND a.fechaFin > :inicio", Long.class)
            .setParameter("equipoId", equipoId)
            .setParameter("inicio", inicio)
            .setParameter("fin", fin)
            .getSingleResult();
        em.close();
        return count > 0;
    }
}
