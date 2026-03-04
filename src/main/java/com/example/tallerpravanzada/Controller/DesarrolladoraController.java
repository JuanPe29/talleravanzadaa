package com.example.tallerpravanzada.Controller;

import com.example.tallerpravanzada.Entyti.Desarrolladora;
import com.example.tallerpravanzada.Service.DesarroladoraService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/desarrolladoras")
public class DesarrolladoraController {

    private final DesarroladoraService service;

    public DesarrolladoraController(DesarroladoraService service) {
        this.service = service;
    }

    @GetMapping
    public List<Desarrolladora> listar() {
        return service.listar();
    }

    @PostMapping
    public Desarrolladora crear(@RequestBody Desarrolladora d) {
        return service.crear(d);
    }



}
