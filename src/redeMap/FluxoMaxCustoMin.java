package redeMap;

import java.util.SortedMap;
import java.util.TreeMap;

public class FluxoMaxCustoMin {
	
	private Grafo g;
	private SortedMap<Integer,SortedMap<Integer,Arco>> gResidual;
	private long[] dt;
	private Integer[] rot;
	
	BellmanFord bellman = new BellmanFord();
	
	public FluxoMaxCustoMin(Grafo g, long[] dt, Integer[] rot) {
		super();
		this.g = g;
		this.gResidual = new TreeMap<>();
		
		for (SortedMap<Integer, Arco> mapArcos : g.getGrafo().values()) {
			for (Arco f : mapArcos.values()) {
				if(gResidual.get(f.getOrigem()) == null)
					gResidual.put(f.getOrigem(),new TreeMap<>());
				gResidual.get(f.getOrigem()).put(f.getDestino(), new Arco(f.getOrigem(),f.getDestino(),f.getFluxoMin(),f.getFluxoMax(), f.getCusto(), f.getFluxo()));
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
		SortedMap<Integer,SortedMap<Integer,Arco>> novoResidual = new TreeMap<>();
		
		for (SortedMap<Integer, Arco> mapArcos : gResidual.values()) {
			for (Arco f : mapArcos.values()) {
				if(f.getFluxo() < f.getFluxoMax()){
					if(novoResidual.get(f.getOrigem()) == null)
						novoResidual.put(f.getOrigem(),new TreeMap<>());
					novoResidual.get(f.getOrigem()).put(f.getDestino(), new Arco(f.getOrigem(),f.getDestino(),f.getFluxoMin(),f.getFluxoMax() - f.getFluxo(), f.getCusto(), 0));
				}
				if(f.getFluxo() > f.getFluxoMin()){
					if(novoResidual.get(f.getDestino()) == null)
						novoResidual.put(f.getDestino(),new TreeMap<>());
					novoResidual.get(f.getDestino()).put(f.getOrigem(), new Arco(f.getDestino(),f.getOrigem(),f.getFluxoMin(), f.getFluxo(), f.getCusto(), 0));
				}
			}
		}
		
		gResidual = novoResidual;
	}
	
	public long aumentarFluxoAux(int n, long min){
		int proximo = rot[n];
		if(proximo != -1){
			if(min > gResidual.get(proximo).get(n).getFluxoMax()){
				min = gResidual.get(proximo).get(n).getFluxoMax();
			}
			min = aumentarFluxoAux(proximo, min);
			
			gResidual.get(proximo).get(n).setFluxo(gResidual.get(proximo).get(n).getFluxo() + min);
			g.getGrafo().get(proximo).get(n).setFluxo(g.getGrafo().get(proximo).get(n).getFluxo()+gResidual.get(proximo).get(n).getFluxo());
			
			return min;
		}
		else
			return min;
	}
	
	public void aumentarFluxo2() {
		aumentarFluxoAux(g.getNumVertices()-1, Long.MAX_VALUE);
	}
	
	public void aumentarFluxo(){
		long min = Integer.MAX_VALUE;
		
		int i = g.getNumVertices()-1;
		while(i > 0){
			if(min > gResidual.get(rot[i]).get(i).getFluxoMax()){
				min = gResidual.get(rot[i]).get(i).getFluxoMax();
			}
			i = rot[i];
		}
		
		i = g.getNumVertices()-1;
		System.err.print("[-");
		while(i > 0){
			gResidual.get(rot[i]).get(i).setFluxo(gResidual.get(rot[i]).get(i).getFluxo() + min);
			g.getGrafo().get(rot[i]).get(i).setFluxo(g.getGrafo().get(rot[i]).get(i).getFluxo()+gResidual.get(rot[i]).get(i).getFluxo());
			i = rot[i];
			if( i != 0)
				System.err.print(":"+i);
		}
		System.err.println(":-] --- "+min+" Unidades de Fluxo aumentado nesse caminho.");
	}

	
	public void executar(){
		while (bellman.menorCaminho(g.getNumVertices(), dt, rot, gResidual)) {
			aumentarFluxo2();
			gerarRedeResidual();
			/*System.out.println("------------Novo Residual------------------");
			for (SortedMap<Integer, Arco> para : gResidual.values()) {
				for (Arco fluxo : para.values()) {
					System.out.println(fluxo.toString());
				}
			}*/
		}
		
		
	}
	
}
