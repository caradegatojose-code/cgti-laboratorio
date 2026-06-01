package com.cgti.service;

import com.cgti.model.Laboratorio;
import com.cgti.model.Registro;
import com.cgti.model.Usuario;
import com.cgti.repository.RegistroRepository;
import java.time.LocalDateTime;
import java.util.List;

public class RegistroService {

    private RegistroRepository repo = new RegistroRepository();

    public String registrarEntrada(Usuario usuario, Laboratorio laboratorio, String proposito) {
        Registro registro = new Registro();
        registro.setUsuario(usuario);
        registro.setLaboratorio(laboratorio);
        registro.setHoraEntrada(LocalDateTime.now());
        registro.setProposito(proposito);
        repo.guardar(registro);
        return "Entrada registrada correctamente";
    }

    public String registrarSalida(Registro registro) {
        registro.setHoraSalida(LocalDateTime.now());
        repo.guardar(registro);
        return "Salida registrada correctamente";
    }

    public List<Registro> listarTodos() {
        return repo.listarTodos();
    }

    public List<Registro> listarPorUsuario(Long usuarioId) {
        return repo.listarPorUsuario(usuarioId);
    }
}
