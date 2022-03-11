package librerias.util;
/**  
 *  The class Ordenacion contains: 
 *  - The implementation of the in-situ D&C sorting algorithms 
 *    Quick Sort and Merge Sort.  
 *  - A method to check if two given generic-type arrays are equal 
 *   
 * @author (EDA)  
 * @version (Year 2017-2018) 
 */ 
 
public class Ordenacion { 
     
    // QUICK SORT ------------------------------------------------- 
    /**  
     *  Quicksort sorting algorithm(Hoare, 1963).  
     *  It uses the Weiss partition algorithm,  
     *  with a median of 3 to compute the pivot. 
     *   
     *  @param a Its elements implement the Comparable interface 
     */ 
    public static <T extends Comparable<T>> void quickSort(T[]  a) {
        quickSort(a, 0, a.length-1);
    } 
    
    // It shorts the array [izq, der] through quickSort, izq <= der 
    private static <T extends Comparable<T>> void quickSort(T[] a, int izq, int der) { 
        if (izq < der) { 
            T pivot = mediana3(a, izq, der); 
            int i = izq; 
            int j = der - 1; 
            for ( ; i < j;) { 
                while (pivot.compareTo(a[++i]) > 0) { ; } 
                while (pivot.compareTo(a[--j]) < 0) { ; } 
                intercambiar(a, i, j); 
            } 
            intercambiar(a, i, j);        // Undo the last exchange 
            intercambiar(a, i, der - 1);  // Restore the pivot 
            // SORTED PIVOT --> 
            quickSort(a, izq, i - 1);     // Recursively sort the lower elements 
            quickSort(a, i + 1, der);     // Recursively sort the higher elements 
        } 
    } 
 
    // Exchanges the elements ind1 and ind2 of array a 
    private static <T> void intercambiar(T[] a, int ind1, int ind2) {
    
    } 
  
    // Compute the median of 3 of the subArray a[izq, der]  
    // and return the pivot value 

    private static <T extends Comparable<T>> T mediana3(T[] a, int izq, int der) {     
        int c = (izq + der) / 2;    
        if (a[c].compareTo(a[izq]) < 0)   { intercambiar(a, izq, c); } 
        if (a[der].compareTo(a[izq]) < 0) { intercambiar(a, izq, der); } 
        if (a[der].compareTo(a[c]) < 0)   { intercambiar(a, c, der); } 
        // hide the pivot in the position der-1  
        intercambiar(a, c, der - 1); 
        return a[der - 1]; 
    } 
 
    // MERGE SORT --------------------------------------------    
    // VERSION 1 (as seen in theory lessons):    
    /** 
     * Sort the array v in ascending order. 
     *  
     * @param v  Its elements must implement the Comparable interface 
     */ 
    public static <T extends Comparable<T>> void mergeSort1(T[] v) { 
        mergeSort1(v, 0, v.length);
    } 
     
    /** 
     * IF i<=f: sorts the subarray v[i, f] in ascending order. 
     *  
     * @param v  Its elements must implement the Comparable interface 
     * @param i  Lower end of the interval to be ordered 
     * @param f  Upper end of the range to be ordered 
     */ 
    private static <T extends Comparable<T>> void mergeSort1(T[] v,  int i, int f) {
        if(i<f){
            int mid = (i+f)/2;
            mergeSort1(v, i, mid);
            mergeSort1(v, mid+1, f);
            merge1(v, i, f, mid+1);
        }
    }         
     
    /** 
     * Internally merges subarrays v[i, m] and v[m + 1, f],  
     * which are both sorted in ascending order.  
     *  
     * @param v  Its elements must implement the Comparable interface 
     * @param i  Extremo inferior del intervalo a mezclar 
     * @param f  Extremo superior del intervalo a mezclar 
    */ 
    //@SuppressWarnings("unchecked") 
    private static <T extends Comparable<T>> void merge1(T[] v,  int i, int f, int m) {  } 
     
    // MERGE SORT --------------------------------------------    
    // VERSION 2  
    /** 
     * Sort the array v in ascending order. 
     *  
     * @param v  Its elements must implement the Comparable interface 
        EDA � Year 2021/22  Pasqual Mart� 
     */ 
    public static <T extends Comparable<T>> void mergeSort2(T[] v) { 
        v = mergeSort2(v, 0, v.length-1);
    } 
     
     /** 
     * IF i<=f: sorts the subarray v[i, f] in ascending order. 
     *  
     * @param v  Its elements must implement the Comparable interface 
     * @param i  Lower end of the interval to be ordered 
     * @param f  Upper end of the range to be ordered 
     * @return T[], the sorted v[i, f] array  
     */ 
    @SuppressWarnings("unchecked")
    private static <T extends Comparable<T>> T[] mergeSort2(T[] v, int i, int f) { 
        T[] aux = (T[]) new Object[v.length];
        if(i==f){
            aux = (T[]) new Object[1];
            aux[0]=v[1];
            return aux;
        }else{
            int mid = (i+f)/2;
            merge2(mergeSort2(v, i, mid), mergeSort2(v, mid+1, f));
        }
        return aux;
    }         
     
    
    /** 
     * Returns the merge of v1 and v2 arrays, which are both already sorted in ascending order 
     *  
     * @param v1  Its elements implement the Comparable interface 
     * @param v2  Its elements implement the Comparable interface 
     * @return T[], the outcome array after merging v1 and v2 
     */ 
    @SuppressWarnings("unchecked") 
    private static <T extends Comparable<T>> T[] merge2(T[] v1, T[] v2) { 
        int a=0;
        int b = 0;
        int p=0;
        T[] aux = (T[]) new Object[v1.length+v2.length];
        while (a<v1.length && b<v2.length){
            if(v1[a].compareTo(v2[b]) <= 0){
                aux[p]=v1[a];
                a++;
            }else{
                aux[p]=v2[b];
                b++;
            }
            p++;
        }
        return aux;
    } 
     
    // Auxiliary method  -------------------------------------------- 
    /**  
     *  Checks if arrays a and b are equals comparing element by element. 
     *   
     *  @param a  Its elements implement the Comparable interface 
     *  @return boolean, the result of the comparison 
     */ 
    public static <T extends Comparable<T>> boolean sonIguales(T[] a, T[] b) {  
    
        return false;
    }
}
    
    
    