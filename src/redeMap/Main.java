package redeMap;

public class Main {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Grafo g = new Grafo("instancia_0.txt");
		g.criarGrafo();
		Integer[] dt = new Integer[g.getNumVertices()];
		Integer[] rot = new Integer[g.getNumVertices()];
		
		/*for (int i = 0; i < rot.length; i++) {
			System.err.println("Rot["+i+"] = "+rot[i]);
		}*/
		
		FluxoMaxCustoMin fMcM = new FluxoMaxCustoMin(g, dt, rot);
		
		fMcM.fluxoMaximoFordFulkerson();
		
	}

}
