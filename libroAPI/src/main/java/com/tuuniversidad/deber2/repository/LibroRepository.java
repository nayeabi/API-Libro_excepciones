package com.tuuniversidad.deber2.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.tuuniversidad.deber2.models.Libro;
import org.springframework.stereotype.Repository;

@Repository
public class LibroRepository {

    private List<Libro> libros;

    public LibroRepository() {
        libros = new ArrayList<>();
        libros.add(new Libro(1L, "El Señor de los anillos", "J.R.R.Tolkien","George Allen & Unwin HarperCollins",1954));
        libros.add(new Libro(2L, "El nombre de la rosa", "Umberto Eco", "Bompiani", 1980));
        libros.add(new Libro(3L, "La hipotesis del amor", "Ali Hazelwood", "Contraluz",2022));
        libros.add(new Libro(4L, "El amor en los tiempos del cólera", "Gabriel García Márquez", "Editorial Oveja Negra", 1985));
    }

    public List<Libro> obtenerTodosLosLibros() {
        return libros;
    }

    public Optional<Libro> obtenerLibroPorId(Long id) {
        return libros.stream().filter(libro -> libro.getId().equals(id)).findFirst();
    }

    public void agregarLibro(Libro libro) {
        libros.add(libro);
    }
}
