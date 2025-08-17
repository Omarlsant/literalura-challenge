package br.com.alura.literalura.model;

import jakarta.persistence.*;
@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String idioma;
    private Integer numeroDeDescargas;

    @ManyToOne
    private Autor autor;

    public Libro() {
    }

    public static Libro desdeDatosLibro(BookData datosLibro) {
        var libro = new Libro();
        libro.id = Long.valueOf(datosLibro.id());
        libro.numeroDeDescargas = datosLibro.downloads();
        libro.titulo = datosLibro.titulo();

        if (!datosLibro.idiomas().isEmpty()) {
            libro.idioma = datosLibro.idiomas().get(0);
        }

        if (!datosLibro.autores().isEmpty()) {
            libro.autor = Autor.fromPersonData(datosLibro.autores().get(0));
        }

        return libro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Integer getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(Integer numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "Título: " + titulo
                + "\nAutor(a): " + (autor == null ? "-" : autor.toString())
                + "\nIdioma: " + (idioma == null ? "-" : idioma)
                + "\nNúmero de descargas: " + numeroDeDescargas;
    }
}
                + "\nNúmero de downloads: " + downloads;
    }
}
