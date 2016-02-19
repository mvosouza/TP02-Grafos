package redeMap;

public class Main {
	
	public static void main(String[] args) {
		Grafo g = new Grafo(args[0]);
		g.criarGrafo();
		long[] dt = new long[g.getNumVertices()];
		Integer[] rot = new Integer[g.getNumVertices()];
		
		/*for (int i = 0; i < rot.length; i++) {
			System.err.println("Rot["+i+"] = "+rot[i]);
		}*/
		
		long tempoInicio = System.currentTimeMillis();
		
		FluxoMaxCustoMin fMcM = new FluxoMaxCustoMin(g, dt, rot);
		fMcM.fluxoMaximoFordFulkerson();
		
		System.err.println("Tempo Total: "+(System.currentTimeMillis()-tempoInicio)+" ms");
	}
	
}
