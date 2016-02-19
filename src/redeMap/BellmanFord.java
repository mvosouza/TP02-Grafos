package redeMap;

import java.util.SortedMap;

public class BellmanFord {
	
	public boolean menorCaminho(int n, long[] dt, Integer[] rot, SortedMap<Integer,SortedMap<Integer,Arco>> grafo){
		boolean altera = false;
		int cont = 0;
		
		dt[0] = 0;
		rot[0] = -1;
		
		for (int i = 1; i < n; i++) {
			if (grafo.get(0) != null)
				if (grafo.get(0).get(i) != null) {
					cont++;
					rot[i] = 0;
					dt[i] = grafo.get(0).get(i).getCusto();
				}
				else{
					rot[i] = -1;
					dt[i] = Math.abs(Integer.MAX_VALUE);
				}
		}
		
		if (cont == 0) {
			return false;
		}
		
		for (int k = 0; k < n-1; k++) {
			altera = false;
			for (int i = 1; i < n; i++) {
				for (int j = 1; j < n; j++) {
					if(grafo.get(i) != null)
						if(grafo.get(i).get(j) != null){
							if (dt[j] > dt[i]+grafo.get(i).get(j).getCusto() ){
								dt[j] = dt[i]+grafo.get(i).get(j).getCusto();
								rot[j] = i;
								altera = true;
							}
					}
				}
			}
			if(altera == false){
				k = n;
			}
		}
		
		/*for (int i = 0; i < rot.length; i++) {
			System.out.println("rot["+i+"] = "+rot[i]);
		}*/
		return true;
	}
}