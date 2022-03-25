package librerias.estructurasDeDatos.deDispersion;

import librerias.estructurasDeDatos.modelos.Map;
import librerias.estructurasDeDatos.modelos.ListaConPI; 
import librerias.estructurasDeDatos.lineales.LEGListaConPI;

/**
 * TablaHash: implmentation of a Linked Hash Table 
 * in which its buckets, or collision lists, are 
 * implemented through Lists with PI of EntradaHash<C, V>
 * 
 * @param <C>  the type of the keys
 * @param <V>  the type of the value associated with a key
 * 
 * @author (EDA-QA) 
 * @version (Curso 2021-2022)
 */

public class TablaHash<C, V> implements Map<C, V> {
    
    // A Hash Table HAS:
    
    // A JAVA CONSTANT representing...
    /** The (float) value of the standard (default) load factor of a  
     *  Hash Table, the same used in the class java.util.HashMap) */
    public static final double FACTOR_DE_CARGA = 0.75;
    
    // A JAVA CONSTANT representing...
    /** The (boolean) value indicating if a Hash Table performs 
     *  Rehashing when its load factor overcomes FC_ESTANDAR
     */
    public static final boolean REHASHING = true; // in lab activity 3.1 is "false";
    
    // AN array of Lists with PI of EntradaHash<C, V> elArray:
    // - elArray[h] represents a bucket or collision   
    //   list associated to Hash index h
    // - elArray[h] contains a reference to the List     
    //   with PI where all Entries whose key has Hash 
    //   index h are stored 
    protected ListaConPI<EntradaHash<C, V>>[] elArray;
    
    // AN integer talla representing the number of Entries  
    // stored in a Hash Table or, equivalently,
    // in its buckets
    protected int talla; 
    
    // AN integer indicating the number of Rehashing operations
    // executed to improve the average time needed to locate 
    // the keys of its talla Entries 
    private int numRH;
    
    
    // A method indiceHash representing the Dispersion function  
    // of the Hash Table
    //**WITHOUT THIS METHOD WE DO NOT HAVE A HASH TABLE, JUST AN ARRAY**
    // Returns the Hash index of the Key c of an Entry,  
    // i.e. the position of the bucket in which the Entry with    
    // Key c is located
    protected int indiceHash(C c) {
        int indiceHash = c.hashCode() % elArray.length;
        if (indiceHash < 0) { indiceHash += elArray.length; }
        return indiceHash;
    }
    
    /** Creates an empty Hash Table, with tallaMaximaEstimada   
     *  Entries and a load factor of 0.75 */
    @SuppressWarnings("unchecked") 
    public TablaHash(int tallaMaximaEstimada) {
        int n = (int) (tallaMaximaEstimada / FACTOR_DE_CARGA);
        int capacidad = siguientePrimo(n);
        elArray = new LEGListaConPI[capacidad];
        for (int i = 0; i < elArray.length; i++) {
            elArray[i] = new LEGListaConPI<EntradaHash<C, V>>();
        }
        talla = 0;
        numRH = 0;
    }
    // Returns a primer number GREATER or EQUAL to n, 
    // i.e. the next prime number to n
    public static final int siguientePrimo(int n) {
        if (n % 2 == 0) n++;
        for (; !esPrimo(n); n += 2); 
        return n;
    } 
    // Checks if n is a prime number
    protected static final boolean esPrimo(int n) {
        for (int i = 3; i * i <= n; i += 2) 
            if (n % i == 0) return false; // n IS NOT prime
        return true; // n IS prime
    }    
    
    /** Checks if a Hash Table is empty,  
     *  i.e. if it has 0 Entries */
    public boolean esVacio() { return talla == 0; }
    
    /** Returns the size, or number of Entries, 
      * of a Hash Table */
    public int talla() { return talla; } 
    
    /** Returns the number of Reshashing operations
      *  performed to create a Hash Table */
    public int numeroDeRH() { return numRH; } 

    /** Given a Key c of an Entry, tries to move the  
      * PI of the (List) bucket where such an Entry 
      * shoud be located poiting to the Entry. 
      * If the Entry does not exist, the PI will
      * point to the end of the bucket (null) */
    private ListaConPI<EntradaHash<C, V>> localizar(C c) {
        int pos = indiceHash(c);
        ListaConPI<EntradaHash<C, V>> cubeta = elArray[pos];
        for (cubeta.inicio(); 
             !cubeta.esFin() && !cubeta.recuperar().clave.equals(c); 
             cubeta.siguiente());
        return cubeta;
    }
        
    /** Returns the value of an Entry with Key c of a Hash 
     * Table, or null if such an Entry is not in the Table */
    public V recuperar(C c) {
        V valor = null;
        // Search in the bucket for the Entry with key c whose value must be retrieved 
        ListaConPI<EntradaHash<C, V>> cubeta = localizar(c);
        // Search resolution: IFF the Entry exists, retrieve its value
        if (!cubeta.esFin()) { valor = cubeta.recuperar().valor; }
        return valor;
    }
    
    /** Deletes the Entry with Key c of a Hash Table and 
     *  returns its associated value, or null if such an  
     *  Entry is not in the Table */
    public V eliminar(C c) {
        V valor = null;
        // Search in the bucket for the entry to delete
        ListaConPI<EntradaHash<C, V>> cubeta = localizar(c);
        // Search resolution: 
        // IFF the Entry is deleted, after retrieving its value
        if (!cubeta.esFin()) {
            valor = cubeta.recuperar().valor;
            cubeta.eliminar();
            talla--;
        }
        return valor;
    }
        
    /** Inserts the Entry (c, v) in a Hash Table and   
     *  returns the value previously associated with c
     *  or null if such an Entry is not in the Table */
    // Invokes the rehashing() method IFF
    // - The value of the REHASHING constant is true
    // AND
    // - AFTER inserting a new Entry in its corresponding  
    //   bucket and uptading the size of the Table 
    //   the (load factor) factorCarga() > FACTOR_DE_CARGA
    public V insertar(C c, V v) {
        V antiguoValor = null;
        // Search in the bucket for Entry with key c 
        ListaConPI<EntradaHash<C, V>> cubeta = localizar(c);
        // Search resolution:  
        // if Entry (c, v) exists, update its value; otherwise insert it
        if (cubeta.esFin()) { 
            // if it is not found, insert Entry (c, v)
            cubeta.insertar(new EntradaHash<C, V>(c, v));
            talla++;
                       
            if (factorCarga() > FACTOR_DE_CARGA && REHASHING) { 
                numRH++;
                rehashing(); 
            }            
        }
        else { 
            // if it is already inserted, update (the value of the) Entry and return the previous
            antiguoValor = cubeta.recuperar().valor;
            cubeta.recuperar().valor = v; 
        }
        return antiguoValor;
    }
    
    // Method that implements Rehashing. Please note the atribute numRH 
    // MUST NOT be restarted (for obvious reasons)
    // The attribute numColisiones MUST NOT be restarted either. This is
    // to take into account the cost of the rehashing operations in the
    // time that, on average, takes to localize one of the talla Entries
    // of the Hash Table.  
    //
    @SuppressWarnings("unchecked")
    protected final void rehashing() {
        /* TO COMPLETE */
        int newS=siguientePrimo(this.elArray.length*2);
        ListaConPI<EntradaHash<C, V>>[] old=this.elArray;
        this.elArray=new LEGListaConPI[newS];
        for (int i = 0; i < elArray.length; i++) {
            elArray[i] = new LEGListaConPI<EntradaHash<C, V>>();
        }
        this.talla=0;
        for(int i =0; i<old.length; i++){
            ListaConPI<EntradaHash<C, V>> cubeta=old[i];
           for(cubeta.inicio(); !cubeta.esFin(); cubeta.siguiente()){
                this.insertar(cubeta.recuperar().clave, cubeta.recuperar().valor);
            } 
        }
        
    }
    
    /** Returns a ListaConPI with the talla() keys  
     *  of a Hash Table */
    public ListaConPI<C> claves() {
        ListaConPI<C> l = new LEGListaConPI<C>();
        for (int i = 0; i < elArray.length; i++) 
            for (elArray[i].inicio(); !elArray[i].esFin(); elArray[i].siguiente()) 
                 l.insertar( elArray[i].recuperar().clave); 
        return l;
    }
    
    /** Returns the (real) load factor of a Hash Table,   
     *  which is equivalente to the average length of   
     *  its buckets in a Linked Table implementation*/
    public final double factorCarga() { 
        return (double) talla / elArray.length; 
    }
    
    /** Returns a String with the Entries of a Hash Table
     *  in a given format (see toString in EntradaHash)
     */
    // REMEMBER: use the StringBuilder class for efficiency
    public final String toString() {
        StringBuilder res = new StringBuilder();
        for (ListaConPI<EntradaHash<C, V>> cubeta : elArray) 
            for (cubeta.inicio(); !cubeta.esFin(); cubeta.siguiente()) 
                res.append(cubeta.recuperar() + "\n"); 
        return res.toString(); 
    }
    
    // Methods for the performance analysis of a  
    // Linked Hash Table (and NOT a Map!!)
    
    /** Returns the standard deviation of the lengths 
     *  of the buckets of a Linked Hash Table */
    public final double desviacionTipica() {
        double avg=0;
        double mean=factorCarga();
        for(int i=0; i<this.elArray.length; i++){
            double aux=this.elArray[i].talla()-mean;
            avg+=aux*aux;
        }
        avg=avg/this.elArray.length;
        return Math.sqrt(avg);
        
    }
    
    /** Returns the average cost of locating a
     *  key in a Linked Hash Table, 
     *  computed from the number of collisions
     *  caused by locating all of its talla keys 
     */
    public final double costeMLocalizar() {
        int collisions=0;
        for(int i=0; i<elArray.length; i++){
            int n= elArray[i].talla();
            collisions+=(n*(n-1))/2;
        }
        return (double) collisions/talla; // so that it compiles
        
    }

    /** Returns a String with the occupancy histogram 
     *  of a Linked Hash Table in text format. Therefore, 
     *  each of its lines must have two integer values  
     *  separated by a tabuation: a bucket length 
     *  (integer value in the interval [0, 9]) 
     *  and a number of buckets. 
     *  VERY IMPORTANT: the number of buckets in line i IS: 
     ** (a) If i in [0, 8], the number of buckets in the   
     **     Table with length i
     ** (b) If i = 9 (last line), the number of buclets with 
     **     length 9 or HIGHER in the Table
     */      
    public String histograma() {
        String res = "";
        int[] histo = new int[10];
        for (int i = 0; i < elArray.length; i++) {
            int longCubeta = elArray[i].talla();
            if (longCubeta < 9) { histo[longCubeta]++; }
            else { histo[9]++; }
        }
        for (int i = 0; i < histo.length; i++) {
            res += i + "\t" + histo[i] + "\n";
        }        
        return res;        
    }
}