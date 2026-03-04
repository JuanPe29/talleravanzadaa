package com.example.tallerpravanzada.Service;

import com.example.tallerpravanzada.Entyti.Videojuego;
import com.example.tallerpravanzada.Repoitory.DesarroladoraRepository;
import com.example.tallerpravanzada.Repoitory.VideojuegoRepository;
import jakarta.persistence.Entity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideojuegoService {

    private final VideojuegoRepository videojuegoRepo;
    private final DesarroladoraRepository desarrolladoraRepo;

    public VideojuegoService(VideojuegoRepository v, DesarroladoraRepository d) {
        this.videojuegoRepo = v;
        this.desarrolladoraRepo = d;
    }

    public List<Videojuego> listar() {
        List<Videojuego> lista = videojuegoRepo.findAll();
        lista.forEach(this::calcularIva);
        return lista;
    }

    public Videojuego obtenerPorId(Long id) {
        Videojuego v = videojuegoRepo.findById(Math.toIntExact(id))
                .orElseThrow(() -> new ResourceNotFoundException("Videojuego no encontrado"));
        calcularIva(v);
        return v;
    }

    public Videojuego crear(Videojuego v) {
        validar(v);
        desarrolladoraRepo.findById(v.getDesarrolladora().getId())
                .orElseThrow(() -> new IllegalArgumentException("Desarrolladora no existe"));
        return videojuegoRepo.save(v);
    }

    public Videojuego actualizar(Long id, Videojuego v) {
        obtenerPorId(id);
        v.setId(id);
        validar(v);
        return videojuegoRepo.save(v);
    }

    public void eliminar(Long id) {
        obtenerPorId(id);
        videojuegoRepo.deleteById(Math.toIntExact(id));
    }

    public Videojuego aplicarDescuento(Long id, Double porcentaje) {
        Videojuego v = obtenerPorId(id);
        double descuento = v.getPrecio() * (porcentaje / 100);
        v.setPrecio(v.getPrecio() - descuento);
        return videojuegoRepo.save(v);
    }

    private void validar(Videojuego v) {
        if (v.getPrecio() < 0)
            throw new IllegalArgumentException("El precio no puede ser negativo");
        if (v.getTitulo() == null || v.getTitulo().isBlank())
            throw new IllegalArgumentException("El título es obligatorio");
    }

    private void calcularIva(Videojuego v) {
        v.setPrecioConIva(v.getPrecio() * 1.19);
    }
}
