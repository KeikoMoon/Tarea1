import java.util.ArrayList;

public class Trie {

    private NodoTrie raiz;

    public Trie(){
        raiz = new NodoTrie();
    }

    public void insertar(String palabra){
        NodoTrie actual = raiz; // comenzamos desde la raiz

        for (int i = 0; i < palabra.length(); i++){ // recorremos la palabra letra por letra

            char c = palabra.charAt(i); // obtenemos la letra que está en la posición i

            int indice = c - 'A'; // obtenemos el índice 0-25

            if (actual.P[indice] == null){ // si el nodo no está creado, se crea
                actual.P[indice] = new NodoTrie();
            }

            // si estamos en la última letra,
            // marcamos que esta letra completa una palabra
            if (i == palabra.length() - 1){
                actual.B = actual.B | (1 << indice);
            }

            actual = actual.P[indice]; // nos movemos al siguiente nodo
        }
    }

    // Comprueba que la palabra realmente exista
    public boolean buscar(String palabra){

        // comenzamos desde la raiz
        NodoTrie actual = raiz;

        for (int i = 0; i < palabra.length(); i++){

            char c = palabra.charAt(i);
            int indice = c - 'A';

            // si no existe el camino, la palabra no existe
            if (actual.P[indice] == null){
                return false;
            }

            // si estamos en la última letra,
            // comprobamos si esa letra termina una palabra
            if (i == palabra.length() - 1){
                return (actual.B & (1 << indice)) != 0;
            }

            // avanzamos al siguiente nodo
            actual = actual.P[indice];
        }

        return false;
    }


    public void eliminar(String palabra) {

        NodoTrie actual = raiz;

        // avanzamos hasta el nodo anterior a la última letra
        for (int i = 0; i < palabra.length() - 1; i++) {

            char c = palabra.charAt(i);
            int indice = c - 'A';

            if (actual.P[indice] == null) {
                return;
            }

            actual = actual.P[indice];
        }

        // índice de la última letra
        int indice = palabra.charAt(palabra.length() - 1) - 'A';

        // si no existe el camino hacia la última letra, no hacemos nada
        if (actual.P[indice] == null) {
            return;
        }

        // apagamos el bit que indica que esta letra completa una palabra
        int mascara = 1 << indice;
        mascara = ~mascara;
        actual.B = actual.B & mascara;
    }

    // le damos un prefijo y queremos encontrar todas las palabras que comienzan con ese prefijo

    public ArrayList<String> autocompletar(String prefijo){

        // comenzamos desde la raiz
        NodoTrie actual = raiz;

        for (int i = 0; i < prefijo.length(); i++){

            char c = prefijo.charAt(i);
            int indice = c - 'A';

            if(actual.P[indice] == null){
                return new ArrayList<>();
            }

            actual = actual.P[indice];
        }

        ArrayList<String> sugerencias = new ArrayList<>();

        if (buscar(prefijo)){
            sugerencias.add(prefijo);
        }

        completar(actual, prefijo, sugerencias);

        return sugerencias;
    }

    // funcion auxiliar
    private void completar(NodoTrie actual, String palabra, ArrayList<String> sugerencias){
        for(int i = 0; i < 26; i++){
            if(actual.P[i] != null){
                char letra = (char) ('A' + i);

                String nuevaPalabra = palabra + letra;

                if((actual.B & (1 << i)) != 0){
                    sugerencias.add(nuevaPalabra);
                }
                completar(actual.P[i], nuevaPalabra, sugerencias);
            }
        }

    }
}
