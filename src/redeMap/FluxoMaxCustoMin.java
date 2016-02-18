package redeMap;

import java.util.Map;
import java.util.SortedMap;

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
		for (int i = 0; i < g.getNumVertices(); i++) {
			for (int j = 0; j < g.getNumVertices(); j++) {
				if(gResidual.get(i)!=null)
					if(gResidual.get(i).get(j) != null){
						if(gResidual.get(i).get(j).getFluxo() > gResidual.get(i).get(j).getFluxoMin() ){
							if(gResidual.get(j) != null)
								if(gResidual.get(j).get(i) != null)
									gResidual.get(j).get(i).setFluxoMax(gResidual.get(i).get(j).getFluxo());
								else
								gResidual.get(j).put(i, new Fluxo(j,i,gResidual.get(i).get(j).getFluxoMin(), gResidual.get(i).get(j).getFluxo(), gResidual.get(i).get(j).getCusto(), 0)); 
						}
						if(gResidual.get(i).get(j).getFluxo() == gResidual.get(i).get(j).getFluxoMax()){
							gResidual.get(i).remove(j);
						}
					}
			}
		}
	}
	
	public void aumentarFluxo(){
		int min = Integer.MAX_VALUE;
		
		int i = (rot.length - 1);
		while(i > 0){
			if(min > gResidual.get(rot[i]).get(i).getFluxoMax()){
				min = gResidual.get(rot[i]).get(i).getFluxoMax();
			}
			i = rot[i];
		}
		
		i = (rot.length - 1);
		while(i > 0){
			int fAnterior = gResidual.get(rot[i]).get(i).getFluxo();
			gResidual.get(rot[i]).get(i).setFluxo(gResidual.get(rot[i]).get(i).getFluxo() + min);
			custo += (gResidual.get(rot[i]).get(i).getFluxo() -fAnterior)* gResidual.get(rot[i]).get(i).getCusto();
			i = rot[i];
		}
	}
	
	public void fluxoMaximoFordFulkerson(){
		while (bellman.menorCaminho(g.getNumVertices(), dt, rot, gResidual)) {
			aumentarFluxo();
			gerarRedeResidual();
		}
		System.err.println("Custo: "+custo);
	}
	
}
