package com.example.tallerpravanzada.Entyti;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "desarrolladoras")
public class Desarrolladora {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre;

    private String sitioWeb;

    @OneToMany(mappedBy = "desarrolladora")


    private List<Videojuego> videojuegos;

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getSitioWeb() {
        return sitioWeb;
    }

    public List<Videojuego> getVideojuegos() {
        return videojuegos;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setSitioWeb(String sitioWeb) {
        this.sitioWeb = sitioWeb;
    }

    public void setVideojuegos(List<Videojuego> videojuegos) {
        this.videojuegos = videojuegos;
    }
}
