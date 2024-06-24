package com.tuuniversidad.deber2.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.tuuniversidad.deber2.exception.LibroException;
import com.tuuniversidad.deber2.models.Libro;
import com.tuuniversidad.deber2.service.LibroService;

@RestController
@RequestMapping("/libros")
public class LibroController {

    private final LibroService libroService;

    @Autowired
    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @GetMapping
    public ResponseEntity<List<Libro>> obtenerTodosLosLibros() {
        List<Libro> libros = libroService.obtenerTodosLosLibros();
        return ResponseEntity.ok(libros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Libro> obtenerLibroPorId(@PathVariable Long id) {
        Optional<Libro> libro = libroService.obtenerLibroPorId(id);
        if (libro.isPresent()) {
            return ResponseEntity.ok(libro.get());
        } else {
            throw new LibroException("NOT FOUND: EL LIBRO " + id + " NO SE HA ENCONTRADO");
        }
}
    
    @PostMapping
    public ResponseEntity<String> crearLibro(@RequestBody Libro libro) {
        libroService.agregarLibro(libro);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Libro creado exitosamente.");
    }

    @ExceptionHandler(LibroException.class)
    public ResponseEntity<String> manejarLibroException(LibroException ex, WebRequest request) {
        // Imprimir el mensaje de la excepción en la consola
        System.err.println("Manejo de excepciones: " + ex.getMessage());
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }
}
En nuestro directorio tenemos una subcarpeta "main" que dentro de ella contiene otra carpeta llamada controller en el que contendra una clase llamada LibroController
[![p1.jpg](https://i.postimg.cc/v8SfPLtx/p1.jpg)](https://postimg.cc/14w8XFTy)
En la otra carpeta "models" tenemos a la clase libro
[![p2.jpg](https://i.postimg.cc/Vsrs9jGN/p2.jpg)](https://postimg.cc/6TKNtGNs)
En la carpeta repository tenemos una clase "LibroRepository" que contiene los datos de los libros
![image](https://github.com/nayeabi/API-Libro_excepciones/assets/147847823/fc4abff4-f356-4f50-a19f-0b81a611bcc3)
PRUEBAS DE EJECUCIÓN
[![prueba.jpg](https://i.postimg.cc/wB4R7Jn0/prueba.jpg)](https://postimg.cc/XZFYPZjC)
[![evidencia.jpg](https://i.postimg.cc/JhS7fQfV/evidencia.jpg)](https://postimg.cc/nsYt7qV0)
