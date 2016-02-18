package redeMap;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class FluxoMaxCustoMin {
	
	private Grafo g;
	private SortedMap<Integer,SortedMap<Integer,Fluxo>> gResidual;
	private Integer custo;
	private Integer[] dt;
	private Integer[] rot;
	
	BellmanFord bellman = new BellmanFord();
	
	
	public FluxoMaxCustoMin(Grafo g, Integer[] dt, Integer[] rot) {
		super();
		this.g = g;
		this.custo = 0;
		this.gResidual = g.getGrafo();
		this.setDt(dt);
		this.setRot(rot);
	}
	
	public Integer getCusto() {
		return custo;
	}
	public void setCusto(Integer custo) {
		this.custo = custo;
	}
	public Grafo getgResidual() {
		return g;
	}
	public void setgResidual(Grafo gResidual) {
		this.g = gResidual;
	}
	public Integer[] getDt() {
		return dt;
	}
	public void setDt(Integer[] dt) {
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
							SortedMap<Integer, Fluxo> aux = new TreeMap<>();
							aux.put(i, new Fluxo(j,i,gResidual.get(i).get(j).getFluxoMin(), gResidual.get(i).get(j).getFluxo(), gResidual.get(i).get(j).getCusto(), 0));
							novoResidual.put(j,aux);
						}
						
						/*if(gResidual.get(i).get(j).getFluxo() == gResidual.get(i).get(j).getFluxoMax() ){
							if(novoResidual.get(i) == null)
								novoResidual.put(i,new TreeMap<>());
							novoResidual.get(i).put(j, new Fluxo(i,j,gResidual.get(i).get(j).getFluxoMin(), gResidual.get(i).get(j).getFluxo(), gResidual.get(i).get(j).getCusto(), 0));
						}*/
					}
			}
		}
		gResidual = novoResidual;
	}
	
	public void aumentarFluxo(){
		int min = Integer.MAX_VALUE;
		
		int i = g.getNumVertices()-1;
		while(i > 0){
			if(min > g.getGrafo().get(rot[i]).get(i).getFluxoMax()){
				min = g.getGrafo().get(rot[i]).get(i).getFluxoMax();
			}
			i = rot[i];
		}
		
		i = g.getNumVertices()-1;
		while(i > 0){
			int fAnterior = g.getGrafo().get(rot[i]).get(i).getFluxo();
			g.getGrafo().get(rot[i]).get(i).setFluxo(g.getGrafo().get(rot[i]).get(i).getFluxo() + min);
			custo += (g.getGrafo().get(rot[i]).get(i).getFluxo() -fAnterior)* g.getGrafo().get(rot[i]).get(i).getCusto();
			i = rot[i];
		}
	}
	
	public void fluxoMaximoFordFulkerson(){
		while (bellman.menorCaminho(g.getNumVertices(), dt, rot, gResidual)) {
			
		
			
			aumentarFluxo();
			gerarRedeResidual();
			
			System.err.println("------------Novo Residual------------------");
			for (Map<Integer,Fluxo> para : gResidual.values()) {
				for (Fluxo fluxo : para.values()) {
					System.err.println(fluxo.toString());
				}
			}
		}
		System.err.println("Custo: "+custo);
	}
	
}
