package br.com.alura.literalura.repository;

import br.com.alura.literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RepositorioLibros extends JpaRepository<Libro, Long> {

    List<Libro> findByIdioma(String idioma);

    Integer countByIdioma(String idioma);

    List<Libro> findTop10ByOrderByNumeroDeDescargasDesc();

}
