/*
hacer una aplicación que use el trie y haga lo siguiente

1. Buscar una palabra
2. Insertar una nueva palabra
3. Eliminar una palabra existente
4. Ingresar un prefijo y obtener sugerencias de autocompletado
5. Seleccionar una de las sugerencias para completar la palabra ingresada
6. Salir de la aplicación


Las entradas deber´an estar formadas exclusivamente por letras may´usculas
entre A y Z. Ante una palabra vac´ıa o una entrada que contenga s´ımbolos no
v´alidos, el programa deber´a mostrar un mensaje informativo y solicitar una
nueva entrada

*/
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Trie trie = new Trie();

        Scanner scanner = new Scanner(System.in);

        try {
            // el archivo debe estar en el proyecto fuera de src
            BufferedReader lector = new BufferedReader(new FileReader("diccionario.txt"));

            String palabra;

            while ((palabra = lector.readLine()) != null) {

                if (!palabra.isEmpty()) {
                    trie.insertar(palabra);
                }
            }

            lector.close();

        } catch (IOException e) {
            System.out.println("No se pudo leer el diccionario."+ e.getMessage());
        }

        boolean salir = false;

        while (!salir) {

            // Menu
            System.out.println("===== TRIE =====");
            System.out.println("1. Buscar palabra");
            System.out.println("2. Insertar palabra");
            System.out.println("3. Eliminar palabra");
            System.out.println("4. Autocompletar palabra");
            System.out.println("5. Salir");
            System.out.println("");
            System.out.print("Seleccione una opción: ");

            String opcion = scanner.nextLine();

            switch (opcion) {

                // 1. Buscar palabra
                case "1":
                    System.out.println("Ingrese la palabra que desea buscar:");
                    String palabraBuscar = scanner.nextLine().toUpperCase();

                    if(trie.buscar(palabraBuscar)){
                        System.out.println("La palabra existe.");
                    } else {
                        System.out.println("La palabra no existe");
                    }

                    break;

                // 2. Insertar palabra
                case "2":
                    System.out.println("ingrese la palabra que desea insertar: ");
                    String palabraInsertar = scanner.nextLine().toUpperCase();

                    trie.insertar(palabraInsertar);

                    System.out.println("Palabra insertada");

                    break;
                // 3. Eliminar palabra
                case "3":
                    System.out.println("Ingrese la palabra que desea eliminar: ");
                    String palabraEliminar = scanner.nextLine().toUpperCase();

                    trie.eliminar(palabraEliminar);

                    System.out.println("Palabra eliminada. ");
                    break;

                // 4. Autocompletar palabra
                case "4":
                    System.out.println("Ingrese el prefijo: ");
                    String prefijo = scanner.nextLine().toUpperCase();

                    ArrayList<String> sugerencias = trie.autocompletar(prefijo);

                    for (int i = 0; i < sugerencias.size(); i++){
                        System.out.println((i + 1) + ". " + sugerencias.get(i));
                    }

                    System.out.println("Seleccione una sugerencia");
                    String entrada = scanner.nextLine().toUpperCase();

                    int seleccion = Integer.parseInt(entrada);

                    String palabraSeleccionada = sugerencias.get(seleccion - 1);

                    System.out.println("Palabra seleccionada: " + palabraSeleccionada);

                    break;

                // 5. Salir
                case "5":
                    salir = true;
                    break;

                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }

        scanner.close();
    }
}