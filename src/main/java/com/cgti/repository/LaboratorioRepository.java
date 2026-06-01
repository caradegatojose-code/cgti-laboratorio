package com.cgti.repository;

import com.cgti.model.Laboratorio;
import jakarta.persistence.*;
import java.util.List;

public class LaboratorioRepository {

    private EntityManagerFactory emf =
        Persistence.createEntityManagerFactory("cgtiPU");

    public List<Laboratorio> listarTodos() {
        EntityManager em = emf.createEntityManager();
        List<Laboratorio> lista = em.createQuery(
            "SELECT l FROM Laboratorio l", Laboratorio.class)
            .getResultList();
        em.close();
        return lista;
    }

    public void guardar(Laboratorio laboratorio) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(laboratorio);
        em.getTransaction().commit();
        em.close();
    }
}
