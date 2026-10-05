import java.util.ArrayList;

public class Trie {

    private NodoTrie raiz;

    public Trie(){
        raiz = new NodoTrie();
    }

    public void insertar(String palabra){
        NodoTrie actual = raiz; // comenzamos desde la raiz

        for (int i = 0; i < palabra.length(); i++){ // recorremos la palabra letra por letra
            char c  = palabra.charAt(i); // obtenemso la letra que está en la posición i

            int indice = c - 'A'; // se guarda la posicion desde ascii a nuestra version

            if (actual.P[indice] == null){ // si el nodo no esta creado, se crea
                actual.P[indice] = new NodoTrie();
            }

            actual = actual.P[indice]; // nos paramos en este nodo
        }
        int indice = palabra.charAt(palabra.length() - 1) - 'A';

        actual.B = actual.B | (1 << indice);
    }


    // Comprueba que la palabra realmente exista
    public boolean buscar(String palabra){

        // podemos reutilizar parte de la funcion anterior
        NodoTrie actual = raiz;

        for (int i = 0; i < palabra.length(); i++){
            char c = palabra.charAt(i);
            int indice = c - 'A';

            if (actual.P[indice] == null){
                return false;
            }

            actual = actual.P[indice];

            // si estamos en la última letra
            // comprobamos si esa letra termina una palabra
            if (i == palabra.length() - 1){
                return(actual.B & (1 << indice)) != 0;
            }
            actual = actual.P[indice];
        }
        return false;
    }


    public void eliminar(String palabra) {
        // podemos reutilizar parte de la funcion anterior
        NodoTrie actual = raiz;

        for (int i = 0; i < palabra.length(); i++) {
            char c = palabra.charAt(i);
            int indice = c - 'A';

            if (actual.P[indice] == null) {
                return;
            }

            actual = actual.P[indice];
        }

        // encontramos el indice de la ultima letra
        int indice = palabra.charAt(palabra.length() - 1) - 'A';

        // eliminamos
        int mascara = 1 << indice; // seleccionamos el bit
        mascara = ~mascara;        // invertimos la máscara
        actual.B = actual.B & mascara; // apagamos ese bit
    }

    // le damos un prefijo y queremos encontrar todas las palabras que comienzan con ese prefijo

    public ArrayList<String> autocompletar(String prefijo){
        // volvemos a usar la logica de buscar
        NodoTrie actual = raiz;

        for (int i = 0; i < prefijo.length(); i++){
            char c = prefijo.charAt(i);
            int indice = c - 'A';

            if(actual.P[indice] == null){
                return new ArrayList<>();
            }
            actual = actual.P[indice];

            actual = actual.P[indice];
        }

        ArrayList<String> sugerencias = new ArrayList<>();

        if (buscar(prefijo)){
            sugerencias.add(prefijo);
        }

        completar(actual, prefijo, sugerencias);

        return sugerencias;

    }

    private void completar(NodoTrie actual, String palabra, ArrayList<String> sugerencias){

        for (int i = 0; i < 26; i++){

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
