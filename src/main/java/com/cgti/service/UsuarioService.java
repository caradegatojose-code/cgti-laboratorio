package com.cgti.service;

import com.cgti.model.Usuario;
import com.cgti.repository.UsuarioRepository;
import java.util.List;
import java.util.Optional;

public class UsuarioService { // verificaciones de existencias 

    private UsuarioRepository repo = new UsuarioRepository();

    public String registrar(Usuario usuario) {
        Optional<Usuario> existente = repo.buscarPorCorreo(usuario.getCorreo());// Verificar si el correo ya existe
        if (existente.isPresent()) {
            return "El correo ya está registrado";
        }
        repo.guardar(usuario);
        return "Usuario registrado correctamente";
    }
    public Optional<Usuario> buscarPorCorreo(String correo) {
        return repo.buscarPorCorreo(correo);
    }
    public List<Usuario> listarTodos() {// buscar y enlistar todos los usuarios
        return repo.listarTodos();
    }
    public String eliminar(Long id) {
        Optional<Usuario> u = repo.buscarPorId(id); // verificar que exista 
        if (u.isEmpty()) {
            return "Usuario no encontrado";
        }
        repo.eliminar(id);
        return "Usuario eliminado correctamente";
    }
}
