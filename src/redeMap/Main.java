package redeMap;

public class Main {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Grafo g = new Grafo("instancia_3.txt");
		g.criarGrafo();
		long[] dt = new long[g.getNumVertices()];
		Integer[] rot = new Integer[g.getNumVertices()];
		
		/*for (int i = 0; i < rot.length; i++) {
			System.err.println("Rot["+i+"] = "+rot[i]);
		}*/
		
		FluxoMaxCustoMin fMcM = new FluxoMaxCustoMin(g, dt, rot);
		
		fMcM.fluxoMaximoFordFulkerson();
		
	}

}
