//package ejemplos.tema1;

//import librerias.estructurasDeDatos.lineales.ArrayCola;
//import librerias.estructurasDeDatos.lineales.ArrayCola;

public class TestEDACola {
  public static void main(String[] args) {      
      Cola q = new ArrayCola();
      System.out.println("Creada una Cola con " + q.talla 
          + " Integer, q = " + q.toString());
      q.encolar(10); 
      q.encolar(20); 
      q.encolar(30);
      System.out.println("La Cola de Integer actual es q = " + q.toString());
      System.out.println("Usando otros metodos para mostrar sus Datos el resultado es ...");
      String datosQ = "";
      while (!q.esVacia()) {
          Integer primero = q.primero();
          if (primero.equals(q.desencolar())) datosQ += primero + " "; 
          else datosQ += "ERROR ";
      }
      System.out.println(" el mismo, " + datosQ 
          + ", PERO q se vacia, q = " + q.toString());
  }
}