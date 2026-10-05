public class NodoTrie {
    NodoTrie[] P; // Cada nodo tendrá un arreglo que puede guardar 26 referencias a otros nodos
    int B; // Saber si una letra en concreto termine una palabra, bitmap

    // Constructor
    public NodoTrie(){
        P = new NodoTrie[26]; // 26 letras
        B = 0; // ninguna de las 26 letras termina una palabra
    }
}
