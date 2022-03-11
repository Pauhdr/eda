
public class ExercisesU2{

    public static void main(String args[]){
		//ex 1
        /*int[] list = new int[]{-3, -2, -1, 0, 1, 2, 3, 4, 5};
		int index=findZero(list, 0, list.length -1);
		System.out.println(index+"->"+list[index]);
        */

    }
	
	public static int findZero(int[] arr, int start, int fin){
		int k = (fin-start)/2;
		//what happens if 0 is in the array
		if((arr[k]<0 && arr[k+1]>0)||arr[k]==0){
			return k;
		}else{
			if(arr[k]<0 && arr[k+1]<0) return findZero(arr, k+1, fin);
			else return findZero(arr, start, k);
		}
		
	}

    public static <T extends Comparable<T>> void mergeDC(T[] v, int i, int m, int j){
        T[] res= (T[]) new Comparable[j-i+1];
        int i1=i;
        int i2=m;
        int k=0;
        while(i1<m && i2<=j){
            if(v[i1].compareTo(v[i2]) <0) res[k++] = v[i1++];
            else res[k++]=v[i2++];
        }
        for (int r=i1; r<m; r++) res[k++]=v[r];
        for (int r=i2; r<=j; r++) res[k++]=v[r];
        for (int r=0; r<res.length; r++) v[r+i]=res[r];
    }

    public static int predominant(int[] arr){
        
    }

    private static int maxSequence(int[] v, int start, int fin){
        if (v.length == 1) return v[0]; //<0 ? 0 : v[0];
        else{
            int l = maxSequence(v, start, v.length/2);
            int r = maxSequence(v, (v.length/2)+1, fin);
            if r+l > 0 return r+l;
            else if 
        }
    }
}
















