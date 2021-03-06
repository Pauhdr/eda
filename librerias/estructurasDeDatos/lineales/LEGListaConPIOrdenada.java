package librerias.estructurasDeDatos.lineales;

import librerias.estructurasDeDatos.modelos.ListaConPI;

/** Implementa la interfaz ListaConPI mediante una LEG ...
 *  (a) Con Nodo ficticio cabecera.
 *  (b) Una referencia al primer Nodo.
 *  (c) Una referencia al ultimo Nodo.
 *  (d) Para representar el Punto de Interes, una referencia al Nodo 
 *       anterior al que ocupa el punto de interes.
 *  (e) Un int talla que representa la talla de la LEG.
 *
 * @version Febrero 2019
 * @param <E> tipo de datos de la estructura
 */

public class LEGListaConPIOrdenada<E extends Comparable<E>> extends LEGListaConPI<E> {

    /** construye una Lista Con PI vacia **/
    public LEGListaConPIOrdenada() {
        super();
    }
    
    /** inserta e en una Lista antes del Elemento que ocupa su PI, 
     * que permanece inalterado.
     *
     * @param e Elemento a insertar.
     **/

    public void insertar(E e) { 
        for(this.inicio(); !this.esFin() && this.recuperar().compareTo(e)<0;this.siguiente()){}
        NodoLEG<E> nuevo = new NodoLEG<E>(e, ant.siguiente);
        ant.siguiente = nuevo;
        if (nuevo.siguiente == null) ult = nuevo;
        ant = ant.siguiente;
        talla++;
    } 

}
