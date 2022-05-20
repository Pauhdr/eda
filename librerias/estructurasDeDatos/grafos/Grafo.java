package librerias.estructurasDeDatos.grafos;

import librerias.estructurasDeDatos.modelos.*;
import librerias.estructurasDeDatos.lineales.*;

/** Clase abstracta Grafo
 *
 *  Base de la jerarquia Grafo, que define el comportamiento de un grafo.
 *  No es una interfaz porque incluye el codigo de aquellas operaciones de un grafo 
 *  que son independientes tanto de su tipo como de su implementacion.
 */

public abstract class Grafo { 
    // atributos "auxiliares"
    protected boolean esDirigido; // Indica si el grafo es dirigido o no
    protected int[] visitados;    // Nodos visitados para los recorridos
    protected int ordenVisita;    // Orden de visita de los nodos en los recorridos
    protected Cola<Integer> q;    // Cola para el recorrido BFS
    
    /** Crea un grafo vacio, Dirigido si esDirigido es true
     *  o No Dirigido en caso contrario.
     * 
     *  @param esDirigido Indica el tipo del grafo, Dirigido o No
     */
    public Grafo(boolean esDirigido) { this.esDirigido = esDirigido; }
    
    /** Comprueba si un grafo es o no Dirigido.
     *
     *  @return boolean true si el grafo es Dirgido y false si es No Dirigido
     */
    public boolean esDirigido() { return esDirigido; }
    
    /** Devuelve el numero de vertices de un grafo.
     *  @return int numero de vertices de un grafo
     */
    public abstract int numVertices();
    
    /** Devuelve el numero de aristas de un grafo.
     *  @return int numero de aristas de un grafo
     */
    public abstract int numAristas();
    
    /** Comprueba si la arista (i,j) esta en un grafo.
     *  @param i    Vertice origen
     *  @param j    Vertice destino
     *  @return boolean true si (i,j) esta y false en caso contrario
     */
    public abstract boolean existeArista(int i, int j);
    
    /** Devuelve el peso de la arista (i,j) de un grafo, 
     *  0 si dicha arista no esta en el grafo.
     *  @param i    Vertice origen
     *  @param j    Vertice destino
     *  @return double Peso de la arista (i,j), 0 si no existe.
     */
    public abstract double pesoArista(int i, int j);
    
    /** Si no est, inserta la arista (i, j) en un grafo no Ponderado.
     *  @param i    Vertice origen
     *  @param j    Vertice destino
     */
    public abstract void insertarArista(int i, int j);
    
    /** Si no est, inserta la arista (i, j) de peso p en un grafo Ponderado.
     *  @param i    Vertice origen
     *  @param j    Vertice destino
     *  @param p    Peso de la arista (i,j)
     */
    public abstract void insertarArista(int i, int j, double p);

    /** Devuelve una Lista Con PI que contiene los adyacentes al vertice i de un grafo.
     *  @param i Vertice del que se obtienen los adyacentes
     *  @return ListaConPI con los vertices adyacentes a i
     */
    public abstract ListaConPI<Adyacente> adyacentesDe(int i);
       
    /** Devuelve un String 
     *  con cada uno de los vertices de un grafo y sus adyacentes, 
     *  en orden de insercion 
     *  @return  String que representa a un grafo
     */        
    public String toString() {
        String res = "";  
        for (int i = 0 ; i < numVertices(); i++) {
            res += "Vertice: " + i;
            ListaConPI<Adyacente> l = adyacentesDe(i);
            if (l.esVacia()) { res += " sin Adyacentes "; }
            else { res += " con Adyacentes "; } 
            for (l.inicio(); !l.esFin(); l.siguiente()) {
                res +=  l.recuperar() + " ";  
            }
            res += "\n";  
        }
        return res;      
    }  
    
    public int[] toArrayBFS() {
        int[] res = new int[numVertices()];
        visitados = new int[numVertices()]; 
        ordenVisita = 0;  
        q = new ArrayCola<Integer>();
        for (int  i = 0; i < numVertices(); i++) {
            if (visitados[i] == 0) { toArrayBFS(i, res); }
        }
        return res;
    }
    
    // Recorrido BFS del vertice origen, que almacena en res
    // su resultado
    protected void toArrayBFS(int origen, int[] res) { 
        res[ordenVisita++] = origen;
        visitados[origen] = 1;
        q.encolar(origen);
        while (!q.esVacia()) {
            int u = q.desencolar().intValue(); 
            ListaConPI<Adyacente> l = adyacentesDe(u); 
            for (l.inicio(); !l.esFin(); l.siguiente()) {
                Adyacente a = l.recuperar(); 
                if (visitados[a.destino] == 0) {
                    res[ordenVisita++] = a.destino;
                    visitados[a.destino] = 1;
                    q.encolar(a.destino);
                }
            }  
        }
    }
    
    /** PRECONDICION: !this.esDirigido()
      * Devuelve un subconjunto de aristas que conectan todos los vertices
      * de un grafo No Diridigo y Conexo, o null si el grafo no es Conexo.
      *  
      * @return Arista[], array con las numV - 1 aristas que conectan  
      *                   los numV vertices del grafo, o null si el grafo 
      *                   no es Conexo
     */ 
    public Arista[] arbolRecubrimientoBFS() {
        Arista[] res = new Arista[numVertices()-1];
        visitados = new int[numVertices()]; 
        ordenVisita = 0;  
        q = new ArrayCola<Integer>();
        for (int  i = 0; i < numVertices(); i++) {
            if (visitados[i] == 0) { arbolRecubrimientoBFS(i, res); }
        }
        if (ordenVisita != (numVertices()-1)) return null;
        
        return res;  
    }
    
    protected void arbolRecubrimientoBFS(int origen, Arista[] res) { 
        visitados[origen] = 1;
        q.encolar(origen);
        while (!q.esVacia()) {
            int u = q.desencolar().intValue(); 
            ListaConPI<Adyacente> l = adyacentesDe(u); 
            for (l.inicio(); !l.esFin(); l.siguiente()) {
                Adyacente a = l.recuperar(); 
                if (visitados[a.destino] == 0) {
                    res[ordenVisita++] = new Arista(u, a.destino, a.peso);
                    visitados[a.destino] = 1;
                    q.encolar(a.destino);
                }
            }  
        }
    }
    
    /** PRECONDICION: !this.esDirigido()
      * Devuelve un subconjunto de aristas que, con coste minimo,  
      * conectan todos los vertices de un grafo No Dirigido y Conexo, 
      * o null si el grafo no es Conexo.
      * 
      * @return Arista[], array con las numV - 1 aristas que conectan 
      *                   los numV vertices con coste minimo, o null 
      *                   si el grafo no es Conexo
     */ 
    public Arista[] kruskal() {       
        /*COMPLETAR EN LA SEGUNDA SESION*/
        return null;
    }
}