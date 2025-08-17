package br.com.alura.literalura.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private Integer anioNacimiento;
    private Integer anioFallecimiento;

    @OneToMany(mappedBy = "autor")
    private Set<Libro> libros;

    public Autor() {
    }

    public static Autor desdeDatosPersona(PersonData datosPersona) {
        Autor autor = new Autor();
        autor.nombre = datosPersona.nome();
        autor.anioFallecimiento = datosPersona.anoMorte();
        autor.anioNacimiento = datosPersona.anoNascimento();
        return autor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getAnioNacimiento() {
        return anioNacimiento;
    }

    public void setAnioNacimiento(Integer anioNacimiento) {
        this.anioNacimiento = anioNacimiento;
    }

    public Integer getAnioFallecimiento() {
        return anioFallecimiento;
    }

    public void setAnioFallecimiento(Integer anioFallecimiento) {
        this.anioFallecimiento = anioFallecimiento;
    }

    public Set<Libro> getLibros() {
        return libros;
    }

    public void setLibros(Set<Libro> libros) {
        this.libros = libros;
    }

    @Override
    public String toString() {
        var stringRepresentation = nombre;
        if (anioNacimiento != null && anioFallecimiento != null) {
            stringRepresentation += " (" + anioNacimiento + "-" + anioFallecimiento + ")";
        } else if (anioNacimiento != null) {
            stringRepresentation += " (" + anioNacimiento + "-)";
        } else if (anioFallecimiento != null) {
            stringRepresentation += " (-" + anioFallecimiento + ")";
        }
        return stringRepresentation;
    }

}
