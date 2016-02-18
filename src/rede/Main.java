package rede;

public class Main {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Grafo g = new Grafo("Teste.txt");
		g.criarGrafo();
		Integer[] dt = new Integer[g.getNumVertices()];
		Integer[] rot = new Integer[g.getNumVertices()];
		
		FluxoMaxCustoMin fMcM = new FluxoMaxCustoMin(g, dt, rot);
		
		fMcM.fluxoMaximoFordFulkerson();
		
	}

}
