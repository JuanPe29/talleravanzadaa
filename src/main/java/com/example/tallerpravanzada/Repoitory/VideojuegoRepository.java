package com.example.tallerpravanzada.Repoitory;

import com.example.tallerpravanzada.Entyti.Genero;
import com.example.tallerpravanzada.Entyti.Videojuego;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VideojuegoRepository extends JpaRepository<Videojuego, Integer> {


    List<Videojuego> findByGenero(Genero genero);


    List<Videojuego> findByTituloContainingIgnoreCase(String titulo);

    // JPQL
    @Query("SELECT v FROM Videojuego v WHERE v.precio BETWEEN :min AND :max")
    List<Videojuego> buscarPorRangoPrecio(Double min, Double max);

    // Nativa
    @Query(value = "SELECT * FROM videojuegos ORDER BY fecha_creacion DESC LIMIT 5",
            nativeQuery = true)
    List<Videojuego> ultimosCinco();
}
