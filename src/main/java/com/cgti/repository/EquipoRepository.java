package com.cgti.repository;

import com.cgti.model.Equipo;
import jakarta.persistence.*;
import java.util.List;

public class EquipoRepository {

    private EntityManagerFactory emf =
        Persistence.createEntityManagerFactory("cgtiPU");

    public List<Equipo> listarPorLaboratorio(Long laboratorioId) {
        EntityManager em = emf.createEntityManager();
        List<Equipo> lista = em.createQuery(
            "SELECT e FROM Equipo e WHERE e.laboratorio.id = :id", Equipo.class)
            .setParameter("id", laboratorioId)
            .getResultList();
        em.close();
        return lista;
    }

    public void guardar(Equipo equipo) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(equipo);
        em.getTransaction().commit();
        em.close();
    }
}
