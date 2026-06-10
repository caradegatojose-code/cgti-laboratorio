package com.cgti.repository;

import com.cgti.model.Equipo;
import com.cgti.model.Laboratorio;
import jakarta.persistence.*;
import java.util.List;

public class EquipoRepository {

    private EntityManagerFactory emf =
        Persistence.createEntityManagerFactory("cgtiPU");

    public List<Equipo> listarPorLaboratorio(String laboratorio) {
        EntityManager em = emf.createEntityManager();
        List<Equipo> lista = em.createQuery(
            "SELECT e FROM Equipo e WHERE e.laboratorio = :lab", Equipo.class)
            .setParameter("lab", Laboratorio.valueOf(laboratorio))
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
