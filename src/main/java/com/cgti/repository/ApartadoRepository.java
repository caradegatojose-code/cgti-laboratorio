package com.cgti.repository; // pertenece al paquete 

import com.cgti.model.Apartado; // importamos la clase apartado 
import com.cgti.model.EstadoApartado; // importamos el enum directamente
import jakarta.persistence.*; // permite la conexion a base de datos 
import java.time.LocalDateTime;
import java.util.List;

public class ApartadoRepository {
    private EntityManagerFactory emf = // conectamos con la base de datos 
        Persistence.createEntityManagerFactory("cgtiPU"); // el nombre de la base de datos 

    public void guardar(Apartado apartado) { // para guardar en base de datos el apartado 
        EntityManager em = emf.createEntityManager(); // crea una conexion activa con base de datos para crear el guardado 
        em.getTransaction().begin(); // le avisa ala base de datos que abra cambios 
        em.merge(apartado); // si existe actualiza , si es nuevo crealo 
        em.getTransaction().commit(); // confirma cambios 
        em.close(); // cierra conexion 
    }

    
    public List<Apartado> listarTodos() { 
        EntityManager em = emf.createEntityManager();// crea una conexion activa con base de datos 
        List<Apartado> lista = em.createQuery( //le pide a base de datos que le de 
            "SELECT a FROM Apartado a", Apartado.class) // la lista de todos los apartados existentes 
            .getResultList(); // ejecuta y regresa toda la lista 
        em.close();  // cierra conexion 
        return lista; // regresa la lista que pide la clase 
    }

   
    public List<Apartado> listarPorUsuario(Long usuarioId) {
        EntityManager em = emf.createEntityManager(); // crea una conexion activa con base de datos
        List<Apartado> lista = em.createQuery(
            "SELECT a FROM Apartado a WHERE a.usuario.id = :id", Apartado.class) // vuelve a pedir los apartados pero esta ves filtrado op id 
            .setParameter("id", usuarioId) // los parametros de el filtrado 
            .getResultList();// ejecuta y regresa toda la lista 
        em.close();// cierra conexion
        return lista;// regresa la lista que pide la clase 
    }


    public boolean laboratorioOcupado(String labNombre, LocalDateTime inicio, LocalDateTime fin) {
        EntityManager em = emf.createEntityManager();  // crea una conexion activa con base de datos
        long count = em.createQuery(
            "SELECT COUNT(a) FROM Apartado a WHERE a.laboratorio = :labNombre " +   /** Cuenta cuántos apartados existen donde
            "AND a.estado = :estado " +                                              * el laboratorio sea ese, el estado sea APROBADO, 
            "AND a.fechaInicio < :fin AND a.fechaFin > :inicio", Long.class)         * y el horario se choque con el que estoy pidiendo */
            .setParameter("labNombre", com.cgti.model.Laboratorio.valueOf(labNombre)) // Ay que convertir el enum a un valor para que la base de datos lo detecte
            .setParameter("estado", EstadoApartado.APROBADO) //  usando el enum
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
