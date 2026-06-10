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


     
    public boolean equipoOcupado(Long equipoId, LocalDateTime inicio, LocalDateTime fin) { // regresa true o falso dependiendo los parametros 
        EntityManager em = emf.createEntityManager(); // crea la conexion de datos 
        long count = em.createQuery( 
            "SELECT COUNT(a) FROM Apartado a WHERE a.equipo.id = :equipoId " + // cuenta cuatos apardatos al equipo existen y que no chuqeun en horarios 
            "AND a.estado = :estado " +                      
            "AND a.fechaInicio < :fin AND a.fechaFin > :inicio", Long.class)
            .setParameter("equipoId", equipoId) // pide el id de maquina 
            .setParameter("estado", EstadoApartado.APROBADO) // solo los apartdados cuentan 
            .setParameter("inicio", inicio) // pide la hora solicitada 
            .setParameter("fin", fin) // pide la hora de fin 
            .getSingleResult(); //  trae el valor
        em.close(); // cierra conecio 
        return count > 0; // si hay mas de 1 count entonces ay choque no se puede aprobar 
    }
}
