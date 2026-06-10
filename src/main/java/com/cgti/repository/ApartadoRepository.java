package com.cgti.repository; // pertenece al paquete 

import com.cgti.model.Apartado; // importamos la clase apartado 
import com.cgti.model.EstadoApartado; // importamos el enum directamente
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Clase que se comunica con la base de datos para todo lo relacionado con apartados.
 * Aquí se guardan, consultan y verifican disponibilidades.
 */
public class ApartadoRepository {

    // Conexión a la base de datos definida en persistence.xml
    private EntityManagerFactory emf =
        Persistence.createEntityManagerFactory("cgtiPU");

    /**
     * Guarda un apartado nuevo o actualiza uno existente.
     * Si ya tiene ID lo actualiza, si no lo crea.
     */
    public void guardar(Apartado apartado) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(apartado);
        em.getTransaction().commit();
        em.close();
    }

    /**
     * Regresa la lista completa de todos los apartados en la base de datos.
     */
    public List<Apartado> listarTodos() {
        EntityManager em = emf.createEntityManager();
        List<Apartado> lista = em.createQuery(
            "SELECT a FROM Apartado a", Apartado.class)
            .getResultList();
        em.close();
        return lista;
    }

    /**
     * Regresa solo los apartados que pertenecen a un usuario en específico.
     */
    public List<Apartado> listarPorUsuario(Long usuarioId) {
        EntityManager em = emf.createEntityManager();
        List<Apartado> lista = em.createQuery(
            "SELECT a FROM Apartado a WHERE a.usuario.id = :id", Apartado.class)
            .setParameter("id", usuarioId)
            .getResultList();
        em.close();
        return lista;
    }

    /**
     * Revisa si un laboratorio ya está apartado (y aprobado) en el horario indicado.
     * Devuelve true si hay choque de horario, false si está libre.
     */
    public boolean laboratorioOcupado(String labNombre, LocalDateTime inicio, LocalDateTime fin) {
        EntityManager em = emf.createEntityManager();
        long count = em.createQuery(
            "SELECT COUNT(a) FROM Apartado a WHERE a.laboratorio = :labNombre " +
            "AND a.estado = :estado " +
            "AND a.fechaInicio < :fin AND a.fechaFin > :inicio", Long.class)
            .setParameter("labNombre", com.cgti.model.Laboratorio.valueOf(labNombre))
            .setParameter("estado", EstadoApartado.APROBADO) // ✔ usando el enum
            .setParameter("inicio", inicio)
            .setParameter("fin", fin)
            .getSingleResult();
        em.close();
        return count > 0;
    }

    /**
     * Revisa si un equipo específico ya está apartado (y aprobado) en el horario indicado.
     * Devuelve true si hay choque de horario, false si está libre.
     */
    public boolean equipoOcupado(Long equipoId, LocalDateTime inicio, LocalDateTime fin) {
        EntityManager em = emf.createEntityManager();
        long count = em.createQuery(
            "SELECT COUNT(a) FROM Apartado a WHERE a.equipo.id = :equipoId " +
            "AND a.estado = :estado " +                      // ✔ corregido: ya no es String
            "AND a.fechaInicio < :fin AND a.fechaFin > :inicio", Long.class)
            .setParameter("equipoId", equipoId)
            .setParameter("estado", EstadoApartado.APROBADO) // ✔ usando el enum igual que laboratorioOcupado
            .setParameter("inicio", inicio)
            .setParameter("fin", fin)
            .getSingleResult();
        em.close();
        return count > 0;
    }
}
