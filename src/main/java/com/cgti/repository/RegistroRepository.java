package com.cgti.repository;

import com.cgti.model.Registro;
import jakarta.persistence.*;
import java.util.List;

public class RegistroRepository {

    private EntityManagerFactory emf =
        Persistence.createEntityManagerFactory("cgtiPU");

    public void guardar(Registro registro) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(registro);
        em.getTransaction().commit();
        em.close();
    }

    public List<Registro> listarTodos() {
        EntityManager em = emf.createEntityManager();
        List<Registro> lista = em.createQuery(
            "SELECT r FROM Registro r", Registro.class)
            .getResultList();
        em.close();
        return lista;
    }

    public List<Registro> listarPorUsuario(Long usuarioId) {
        EntityManager em = emf.createEntityManager();
        List<Registro> lista = em.createQuery(
            "SELECT r FROM Registro r WHERE r.usuario.id = :id",
            Registro.class)
            .setParameter("id", usuarioId)
            .getResultList();
        em.close();
        return lista;
    }
}
