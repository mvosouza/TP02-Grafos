package rede;


public class BellmanFord {
	
	public boolean menorCaminho(int n, Integer[] dt, Integer[] rot, Fluxo[][] matrizAdj){
		boolean altera = false;
		
		int cont = 0;
		
		dt[0] = 0;
		rot[0] = Integer.MAX_VALUE;
		for (int i = 1; i < n; i++) {
			if (matrizAdj[0][i] != null) {
				cont++;
				rot[i] = 0;
				dt[i] = matrizAdj[0][i].getCusto();
			}
			else{
				rot[i] = 0;
				dt[i] = Integer.MAX_VALUE;
			}
		}
		if (cont == 0) {
			return false;
		}
		
		for (int k = 0; k < n-1; k++) {
			altera = false;
			for (int i = 1; i < n; i++) {
				for (int j = 1; j < n; j++) {
					if(matrizAdj[i][j]!=null){
						if (dt[j] > dt[i]+matrizAdj[i][j].getCusto()){
							dt[j] = dt[i]+matrizAdj[i][j].getCusto();
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
		return true;
	}
	
}