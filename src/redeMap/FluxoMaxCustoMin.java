package redeMap;

import java.util.SortedMap;
import java.util.TreeMap;

public class FluxoMaxCustoMin {
	
	private Grafo g;
	private SortedMap<Integer,SortedMap<Integer,Fluxo>> gResidual;
	private double custo;
	private long[] dt;
	private Integer[] rot;
	
	BellmanFord bellman = new BellmanFord();
	
	public FluxoMaxCustoMin(Grafo g, long[] dt, Integer[] rot) {
		super();
		this.g = g;
		this.setCusto(0);
		this.gResidual = new TreeMap<>();
		
		for (int i = 0; i < g.getNumVertices(); i++) {
			for (int j = 0; j < g.getNumVertices(); j++) {
				if(g.getGrafo().get(i)!=null)
					if(g.getGrafo().get(i).get(j) != null){
						if(gResidual.get(i) == null)
							gResidual.put(i,new TreeMap<>());
						gResidual.get(i).put(j, new Fluxo(i,j,g.getGrafo().get(i).get(j).getFluxoMin(),g.getGrafo().get(i).get(j).getFluxoMax(), g.getGrafo().get(i).get(j).getCusto(), g.getGrafo().get(i).get(j).getFluxo()));
					}
			}
		}
				
		this.setDt(dt);
		this.setRot(rot);
	}
	
	public Grafo getgResidual() {
		return g;
	}
	public void setgResidual(Grafo gResidual) {
		this.g = gResidual;
	}
	public double getCusto() {
		return custo;
	}

	public void setCusto(double custo) {
		this.custo = custo;
	}

	public long[] getDt() {
		return dt;
	}
	public void setDt(long[] dt) {
		this.dt = dt;
	}
	public Integer[] getRot() {
		return rot;
	}
	public void setRot(Integer[] rot) {
		this.rot = rot;
	}
	
	public void gerarRedeResidual(){
		SortedMap<Integer,SortedMap<Integer,Fluxo>> novoResidual = new TreeMap<>();
		
		for (int i = 0; i < g.getNumVertices(); i++) {
			for (int j = 0; j < g.getNumVertices(); j++) {
				if(gResidual.get(i)!=null)
					if(gResidual.get(i).get(j) != null){
						//Arco Direto
						if(gResidual.get(i).get(j).getFluxo() < gResidual.get(i).get(j).getFluxoMax() ){
							if(novoResidual.get(i) == null)
								novoResidual.put(i,new TreeMap<>());
							novoResidual.get(i).put(j, new Fluxo(i,j,gResidual.get(i).get(j).getFluxoMin(),gResidual.get(i).get(j).getFluxoMax() - gResidual.get(i).get(j).getFluxo(), gResidual.get(i).get(j).getCusto(), 0));
						}
						//Arco Reverso
						if(gResidual.get(i).get(j).getFluxo() > gResidual.get(i).get(j).getFluxoMin() ){
							if(novoResidual.get(j) == null)
								novoResidual.put(j,new TreeMap<>()); 
							novoResidual.get(j).put(i, new Fluxo(j,i,gResidual.get(i).get(j).getFluxoMin(), gResidual.get(i).get(j).getFluxo(), gResidual.get(i).get(j).getCusto(), 0));
							
						}
					}
			}
		}
		gResidual = novoResidual;
	}
	
	public void aumentarFluxo(){
		int min = Integer.MAX_VALUE;
		
		int i = g.getNumVertices()-1;
		while(i > 0){
			if(min > gResidual.get(rot[i]).get(i).getFluxoMax()){
				min = gResidual.get(rot[i]).get(i).getFluxoMax();
			}
			i = rot[i];
		}
		
		i = g.getNumVertices()-1;
		while(i > 0){
			gResidual.get(rot[i]).get(i).setFluxo(gResidual.get(rot[i]).get(i).getFluxo() + min);
			g.getGrafo().get(rot[i]).get(i).setFluxo(g.getGrafo().get(rot[i]).get(i).getFluxo()+gResidual.get(rot[i]).get(i).getFluxo());
			setCusto(getCusto() + (double)((gResidual.get(rot[i]).get(i).getFluxo())* gResidual.get(rot[i]).get(i).getCusto()));
			i = rot[i];
		}
	}
	
	public void fluxoMaximoFordFulkerson(){
		while (bellman.menorCaminho(g.getNumVertices(), dt, rot, gResidual)) {
			
			aumentarFluxo();
			gerarRedeResidual();
			System.out.println("Custo Parcial = "+custo);
			/*System.out.println("------------Novo Residual------------------");
			for (SortedMap<Integer, Fluxo> para : gResidual.values()) {
				for (Fluxo fluxo : para.values()) {
					System.out.println(fluxo.toString());
				}
			}*/
		}
		System.err.println("Custo Total = "+custo);
		g.imprimeFluxos();
	}
	
}
