package redeMap;

import java.util.SortedMap;

public class BellmanFord {
	
	public boolean menorCaminho(int n, Integer[] dt, Integer[] rot, SortedMap<Integer,SortedMap<Integer,Fluxo>> grafo){
		boolean altera = false;
		int cont = 0;
		
		dt[0] = 0;
		rot[0] = 0;
		
		for (int i = 1; i < n; i++) {
			if (grafo.get(0).get(i) != null) {
				cont++;
				rot[i] = 0;
				dt[i] = grafo.get(0).get(i).getCusto();
			}
			else{
				rot[i] = -1;
				dt[i] = Integer.MAX_VALUE;
			}
		}
		if (cont == 0) {
			return false;
		}
		
		for (int k = 0; k < n-1; k++) {
			altera = false;
			for (int i = 2; i < n; i++) {
				for (int j = 1; j < n; j++) {
					if(grafo.get(j) != null)
						if(grafo.get(j).get(i) != null){
							if (dt[i] > dt[j]+grafo.get(j).get(i).getCusto()){
								dt[i] = dt[j]+grafo.get(j).get(i).getCusto();
								rot[i] = j;
								altera = true;
							}
					}
				}
			}
			if(altera == false){
				k = n;
			}
		}
		return true;
	}
}