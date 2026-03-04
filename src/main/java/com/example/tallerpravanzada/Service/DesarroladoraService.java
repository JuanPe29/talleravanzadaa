package com.example.tallerpravanzada.Service;

import com.example.tallerpravanzada.Entyti.Desarrolladora;
import com.example.tallerpravanzada.Repoitory.DesarroladoraRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DesarroladoraService {

    private final DesarroladoraRepository repository;

    public DesarroladoraService(DesarroladoraRepository repository) {
        this.repository = repository;
    }

    public List<Desarrolladora> listar() {
        return repository.findAll();
    }

    public Desarrolladora crear(Desarrolladora desarrolladora) {
        return repository.save(desarrolladora);
    }
}
