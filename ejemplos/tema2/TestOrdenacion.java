package ejemplos.tema2;
import java.util.*;
import librerias.util.Ordenacion;
/**
* The class TestOrdenacion allows to test and time
* the generic-array sorting methods defined in
* the class librerias.util.Ordenacion.
*
* @author (EDA)
* @version (Year 2019-2020)
*/
public class TestOrdenacion {
    /**
    * Checks the correctness of method mergeSort2 from
    * the Ordenacion class, basing the analysis on the correctness
    * of the quicksort method.
    */
    public static boolean comprobar() {
    Integer[] a1 = crearAleatorioInteger(100000);
    Integer[] a2 = Arrays.copyOf(a1, a1.length);
    // To be completed by the student:
    // check that mergeSort2 sorts correcty,
    // using the sonIguales method of
    // class Ordenacion to compare its result
    // with the one retuned by quickSort
    // Quick Sort sorting of a1:
    //COMPLETE
    // Merge Sort (version 2) sorting of a2:
    //COMPLETAR
    // sonIguales (Are they equal) a1 (quickSort) and a2 (mergeSort2)?
    // COMPLETE return
    return false;
    }
    /**
    * Timing of Integer arrays sorted by methods mergeSort1,
    * mergeSort2 and quickSort from class Ordenacion.
    */
    public static void temporizar() {
        final int INI = 10000;
        final int FI = 100000;
        final int INC = INI;
        final int numRep = 200;
        double t1, t2, tacum1, tacum2, tacum3;
        Integer[] aux1, aux2, aux3;
        System.out.println("#----------------------------------------------");
        System.out.println("# Comparison between quickSort & mergeSort: ");
        System.out.println("# Times in millisec for Integers.");
        System.out.println("#----------------------------------------------");
        System.out.println("# Size mergeSort1 mergeSort2 quickSort");
        System.out.println("#----------------------------------------------");
        for (int k = INI; k <= FI; k = k + INC) {
            int talla = k;
            t1 = 0; t2 = 0;
            tacum1 = 0; tacum2 = 0; tacum3 = 0;
            for (int i = 1; i <= numRep; i++) {
                aux1 = crearAleatorioInteger(talla);
                aux2 = Arrays.copyOf(aux1, aux1.length);
                aux3 = Arrays.copyOf(aux1, aux1.length);
                t1 = System.nanoTime();
                Ordenacion.mergeSort1(aux1);
                t2 = System.nanoTime();
                tacum1 += t2 - t1;
                // For completion:
                // Timing of mergeSort2
                t1 = System.nanoTime();
                Ordenacion.quickSort(aux3);
                t2 = System.nanoTime();
                tacum3 += t2 -
                t1;
            }
            System.out.printf(Locale.US,
            "%1$8d %2$12.4f %3$12.4f %4$12.4f\n",
            talla,
            tacum1 / numRep / 1e6,
            tacum2 / numRep / 1e6,
            tacum3 / numRep / 1e6);
            }
    }
    /**
    * Retuns an array containing talla randomly generated Integer.
    *
    * @param talla Size of the returned array
    * @return Integer[]
    EDA – Year 2021/22 Pasqual Martí
    */
    public static Integer[] crearAleatorioInteger(int talla) { return new Integer[1]; }
    /**
    * Timing of String arrays sorted by methods mergeSort1,
    * mergeSort2 and quickSort from class Ordenacion.
    */
    public static void temporizarString() {
        final int INI = 10000;
        final int FI = 100000;
        final int INC = INI;
        final int numRep = 200;
        final int charIgual = 50;
        double t1, t2, tacum1, tacum2, tacum3;
        String[] aux1, aux2, aux3;
        System.out.println("#----------------------------------------------");
        System.out.println("# Comparison between quickSort & mergeSort: ");
        System.out.println("# Times in millisec for Strings - " + charIgual);
        System.out.println("#----------------------------------------------");
        System.out.println("# Size mergeSort1 mergeSort2 quickSort");
        System.out.println("#----------------------------------------------");
        for (int k = INI; k <= FI; k = k + INC) {
            int talla = k;
            t1 = 0; t2 = 0;
            tacum1 = 0; tacum2 = 0; tacum3 = 0;
            for (int i = 1; i <= numRep; i++) {
                aux1 = crearAleatorioString(talla, charIgual);
                aux2 = Arrays.copyOf(aux1, aux1.length);
                aux3 = Arrays.copyOf(aux1, aux1.length);
                t1 = System.nanoTime();
                Ordenacion.mergeSort1(aux1);
                t2 = System.nanoTime();
                tacum1 += t2 - t1;
                // For completion:
                // Timing of mergeSort2
                t1 = System.nanoTime();
                Ordenacion.quickSort(aux3);
                t2 = System.nanoTime();
                tacum3 += t2 -
                t1;
            }
            System.out.printf(Locale.US,"%1$8d %2$12.4f %3$12.4f %4$12.4f\n",talla,
            tacum1 / numRep / 1e6,
            tacum2 / numRep / 1e6,
            tacum3 / numRep / 1e6);
        }
    }
    /**
    * Returns an array containing talla random Strings with
    * the same first n characters.
    *
    * @param talla Size of the returned array
    * @param n Number of equal initial characters
    * @return String[]
    */
    public static String[] crearAleatorioString(int talla, int n) {
        /*MODIFY*/
        return null;
    }
}