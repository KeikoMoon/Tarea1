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
public class Main{
    public static void main(String[] args){

        // Menu
        System.out.println("===== TRIE =====");
        System.out.println("1. Buscar palabra");
        System.out.println("2. Insertar palabra");
        System.out.println("3. Eliminar palabra");
        System.out.println("4. Autocompletar");
        System.out.println("5. Completar palabra");
        System.out.println("6. Salir");
        System.out.println("");
        System.out.println("Seleccione una opción: ");
    }
}