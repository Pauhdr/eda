public class MidTerm1{
    public static void main(String[] args){
    
    }

    public static int deleteDuplicates(ListaConPI<T> list){
        T last=null;
        int count =0;
        for(list.inicio();!list.esFin();list.siguiente()){
            T aux= list.recuperar();
            if(aux==last){
                list.eliminar();
                count++;

            }
            last=aux;
        }
        return count;
    }

    public static ListaConPI<String> monitor(ListaConPI<String> c, int t){
        Map<String, ListaConPI<Integer>>=new TablaHash<String,ListaConPI<Integer>>(c.talla());
        for(c.incicio();!c.esFin();c.siguiente()) m.insertar(c.recuperar(),0);
        ListaConPI<String> keys = m.claves();
        for(keys.inicio();!keys.esFin();keys.siguiente()){
            if(m.recuperar(keys.recuperar()).talla()<t){
                keys.eliminar();
            }
        }
        return keys;
    }

    public static int countOccurrencies(T[] v, T e, int start, int end){
        if(end==start) return v[start].compareTo(e)==0 ? 1 : 0;
        int mid=end/2;
        if(v[mid].compareTo(e)<0) return countOccurrencies(v, e, mid+1, end);
        if(v[mid].compareTo(e)>0) return countOccurrencies(v, e, start, mid-1);
        return 1+countOccurrencies(v,e,start, mid-1) + countOccurrencies(v,e,mid+1,end);

    }
}
