package com.cgti.repository;

import com.cgti.model.Usuario;
import jakarta.persistence.*;
import java.util.List;
import java.util.Optional;

public class UsuarioRepository {

    private EntityManagerFactory emf = 
        Persistence.createEntityManagerFactory("cgtiPU");
    
    public void guardar(Usuario usuario) {
        EntityManager em = emf.createEntityManager(); // conecta con base de datos
        em.getTransaction().begin();  // los traslada 
        em.merge(usuario);  // guarda y si no existe crea
        em.getTransaction().commit(); // confirma el guardado
        em.close(); // cierra conexion 

    }
   
    public Optional<Usuario> buscarPorId(Long id) {  // busca po id al usiario si 
        EntityManager em = emf.createEntityManager();//existe devulve null  
        Usuario u = em.find(Usuario.class, id);
        em.close();
        return Optional.ofNullable(u);
    }
    public Optional<Usuario> buscarPorCorreo(String correo) {
        EntityManager em = emf.createEntityManager();
        try {
            Usuario u = em.createQuery( // consuta a las clases 
                "SELECT u FROM Usuario u WHERE u.correo = :correo", Usuario.class)
                .setParameter("correo", correo)
                .getSingleResult(); // devulve un resultado 
            return Optional.of(u);
        } catch (NoResultException e) { // se devuelve vacio
            return Optional.empty();
        } finally {
            em.close(); // cierre 
        }
    }
    public List<Usuario> listarTodos() {
        EntityManager em = emf.createEntityManager();
        List<Usuario> lista = em.createQuery(
            "SELECT u FROM Usuario u", Usuario.class)
            .getResultList();
        em.close();
        return lista; // lista de todos 
    }
    public void eliminar(Long id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Usuario u = em.find(Usuario.class, id);// evitar errores de duplicacion 
        if (u != null) em.remove(u);
        em.getTransaction().commit();
        em.close();
    }
}
