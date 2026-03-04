package com.example.tallerpravanzada.Controller;


import com.example.tallerpravanzada.Entyti.Videojuego;
import com.example.tallerpravanzada.Service.VideojuegoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/videojuegos")
public class VideojuegoController {

    private final VideojuegoService service;

    public VideojuegoController(VideojuegoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Videojuego> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Videojuego obtener(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @PostMapping
    public Videojuego crear(@RequestBody Videojuego v) {
        return service.crear(v);
    }

    @PutMapping("/{id}")
    public Videojuego actualizar(@PathVariable Long id, @RequestBody Videojuego v) {
        return service.actualizar(id, v);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }

    @GetMapping("/buscar")
    public List<Videojuego> buscar(@RequestParam String titulo) {
        return service.listar()
                .stream()
                .filter(v -> v.getTitulo().toLowerCase().contains(titulo.toLowerCase()))
                .toList();
    }

    @GetMapping("/rango-precio")
    public List<Videojuego> rango(@RequestParam Double min, @RequestParam Double max) {
        return service.listar()
                .stream()
                .filter(v -> v.getPrecio() >= min && v.getPrecio() <= max)
                .toList();
    }

    @PatchMapping("/{id}/descuento")
    public Videojuego descuento(@PathVariable Long id, @RequestParam Double porcentaje) {
        return service.aplicarDescuento(id, porcentaje);
    }
}
