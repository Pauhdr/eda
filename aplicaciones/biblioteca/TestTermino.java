package ejemplos.biblioteca;

public class TestTermino{
	public static void main(String[] args){
		// String[] words = {"saco","asco", "noreste", "enteros", "cronista", "cortinas"};
		// for (int i=0; i < words.length; i++){
		// 	String w = words[i];
		// 	Termino t = new Termino(w, 1);
		// 	Termino j = new Termino(w, 31);
		// 	Termino m = new Termino(w, 4);
		// 	t.hashCode();
		// 	j.hashCode();
		// 	m.hashCode();
		// 	System.out.println(w +" "+ t.toString() +" " + m.toString() +" "+ j.toString() );
		// }
		try{
			BuscadorDeLaBibl b10=new BuscadorDeLaBibl();
			System.out.println(b10.hapax().toString());
		}catch(Exception e){}
	}

}
