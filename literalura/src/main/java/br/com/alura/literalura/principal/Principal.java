package br.com.alura.literalura.principal;


import br.com.alura.literalura.model.Libro;
import br.com.alura.literalura.repository.AutoresRepository;
import br.com.alura.literalura.repository.RepositorioLibros;
import br.com.alura.literalura.service.BookApiService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class Principal {

    private final RepositorioLibros repositorioLibros;
    private final AutoresRepository autorRepository;
    private final BookApiService bookApiService;
    private final Scanner scanner = new Scanner(System.in);
    private final List<String> posiblesIdiomas;

    private final String ENDPOINT = "https://gutendex.com/books";
    private String complementoTituloAutores = "?search=";
    private String complementoIdioma = "?languages=";

    public Principal(BookApiService bookApiService,
                    RepositorioLibros repositorioLibros,
                    AutoresRepository autorRepository
    ) {
        this.bookApiService = bookApiService;
        this.repositorioLibros = repositorioLibros;
        this.autorRepository = autorRepository;

    posiblesIdiomas = new ArrayList<>();
    posiblesIdiomas.add("pt");
    posiblesIdiomas.add("en");
    posiblesIdiomas.add("es");
    posiblesIdiomas.add("fr");
    }

    public void mostrarMenu(){
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
****************************************

1 - Buscar libro por título
2 - Listar libros registrados
3 - Listar autores
4 - Listar autores en determinado año
5 - Listar libros en determinado idioma
6 - Listar los 10 libros más descargados

0 - Salir

******************************************
""";
            System.out.println(menu);
            opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion) {
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 2:
                    listarLibrosRegistrados();
                    break;
                case 3:
                    listarAutores();
                    break;
                case 4:
                    listarAutoresEnAnoDeterminado();
                    break;
                case 5:
                    listarLibrosPorIdioma();
                    break;
                case 6:
                    listarLibrosMasDescargados();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("\n[!] ¡Opción inválida!");
            }
        }
    }


    private void buscarLibroPorTitulo() {
        System.out.println("\nBÚSQUEDA POR TÍTULO ********************************************");
        System.out.print("Ingrese el título del libro: ");
        var titulo = scanner.nextLine();
        System.out.println("\nBuscando...\n");
        var bookData = bookApiService.search(titulo).stream().findFirst();
        if (bookData.isEmpty()) {
            System.out.println(" ******************* Ningún libro encontrado\n");
        } else {
            var libro = Libro.desdeDatosLibro(bookData.get());
            autorRepository.save(libro.getAutor());
            repositorioLibros.save(libro);
            System.out.println(libro + "\n");
        }
    }

    private void listarLibrosRegistrados() {
        System.out.println("\nLISTADO DE LIBROS REGISTRADOS ********************************************");
        var libros = repositorioLibros.findAll();
        if (libros.isEmpty())
            System.out.println("*********************** Ningún libro encontrado\n");
        libros.forEach(l -> System.out.println(l + "\n"));
    }

    private void listarAutores() {
        System.out.println("\nLISTADO DE AUTORES ********************************************************");
        var autores = autorRepository.findAll();
        autores.forEach(System.out::println);
        if (autores.isEmpty())
            System.out.println("****************************** Ningún autor encontrado\n");
    }

    private void listarAutoresEnAnoDeterminado() {
        System.out.println("\nBUSCAR AUTORES VIVOS EN UN AÑO DETERMINADO ***************************************");
        System.out.print("Ingrese el año deseado: ");
        var anio = scanner.nextInt();
        scanner.nextLine();
        System.out.println("\nBuscando...\n");
        var autores = autorRepository
                .findByAnioNacimientoLessThanEqualAndAnioFallecimientoGreaterThanEqual(anio, anio);
        if (autores.isEmpty())
            System.out.println("********************* Ningún autor encontrado\n");
        autores.forEach(System.out::println);
        System.out.println(" ");
    }

    private void listarLibrosPorIdioma() {
        System.out.println("\nLISTAR LIBROS DE UN IDIOMA ESPECÍFICO ********************************************");
        boolean idiomaValido = false;
        String idioma = "";
        while (!idiomaValido) {
            System.out.println("******************** pt => Portugués ********************");
            System.out.println("******************** en => Inglés ***********************");
            System.out.println("******************** es => Español *********************");
            System.out.println("******************** fr => Francés **********************");
            System.out.print("********************** Elija el idioma: ******************");
            var inputUsuario = scanner.nextLine();
            idiomaValido = posiblesIdiomas.stream()
                    .anyMatch(l -> l.equalsIgnoreCase(inputUsuario));
            if (!idiomaValido) System.out.println("\n Idioma inválido\n");
            idioma = inputUsuario;
        }
        var libros = repositorioLibros.findByIdioma(idioma);
        var cantidad = repositorioLibros.countByIdioma(idioma);
        System.out.println("\nLISTANDO " + cantidad + " LIBRO" + (cantidad != 1 ? "S" : ""));
        libros.forEach(l -> System.out.println(l + "\n"));
        if (libros.isEmpty())
            System.out.println("******************************* Ningún libro encontrado\n");
    }

    private void listarLibrosMasDescargados() {
        System.out.println("\nCargando...");
        var masDescargados = repositorioLibros.findTop10ByOrderByNumeroDeDescargasDesc();
        System.out.println("\nTOP 10 LIBROS MÁS DESCARGADOS ************************************");
        masDescargados.forEach(l -> System.out.println(l + "\n"));
        if (masDescargados.isEmpty())
            System.out.println("***************************** Ningún libro encontrado\n");
    }
}
