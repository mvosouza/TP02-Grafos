package rede;

public class FluxoMaxCustoMin {
	
	private Grafo g;
	private Fluxo[][] gResidual;
	private Integer custo;
	private Integer[] dt;
	private Integer[] rot;
	
	BellmanFord bellman = new BellmanFord();
	
	public FluxoMaxCustoMin(Grafo g, Integer[] dt, Integer[] rot) {
		super();
		this.g = g;
		this.custo = 0;
		this.gResidual = g.getMatrizAdj();
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
				if(gResidual[i][j] != null){
					if(gResidual[i][j].getFluxo() < gResidual[i][j].getFluxoMax()){
						gResidual[i][j].setFluxoMax(gResidual[i][j].getFluxoMax()-gResidual[i][j].getFluxo());
					}
					if(gResidual[i][j].getFluxo() > 0 ){
						if(gResidual[j][i] != null)
							gResidual[j][i].setFluxoMax(gResidual[i][j].getFluxo());
						else
						gResidual[j][i] = new Fluxo(gResidual[i][j].getFluxoMin(), gResidual[i][j].getFluxo(), gResidual[i][j].getCusto(), 0);
					}
					if(gResidual[i][j].getFluxo() == gResidual[i][j].getFluxoMax()){
						gResidual[i][j].setFluxoMax(gResidual[i][j].getFluxo());
					}
				}
			}
		}
		for (int i = 0; i < g.getNumVertices(); i++) {
			for (int j = 0; j < g.getNumVertices(); j++) {
				if(gResidual[i][j] != null){
					if(gResidual[i][j].getFluxo() == gResidual[i][j].getFluxoMax())
						gResidual[i][j] = null;
				}
			}
		}
	}
	
	public void aumentarFluxo(){
		int min = Integer.MAX_VALUE;
		
		int i = (rot.length - 1);
		while(i > 0){
			if(min > gResidual[rot[i]][i].getFluxoMax()){
				min = gResidual[rot[i]][i].getFluxoMax();
			}
			i = rot[i];
		}
		
		i = (rot.length - 1);
		while(i > 0){
			int fAnterior = gResidual[rot[i]][i].getFluxo();
			gResidual[rot[i]][i].setFluxo(gResidual[rot[i]][i].getFluxo() + min);
			custo += (gResidual[rot[i]][i].getFluxo() -fAnterior)* gResidual[rot[i]][i].getCusto();
			i = rot[i];
		}
	}
	
	public void fluxoMaximoFordFulkerson(){
		while (bellman.menorCaminho(g.getNumVertices(), dt, rot, gResidual)) {
			
			aumentarFluxo();
			/*for (int i = 0; i < g.getNumVertices(); i++) {
				for (int j = 0; j < g.getNumVertices(); j++) {
					System.out.print(gResidual[i][j]+" ");
				}
				System.out.println();
			}*/
			gerarRedeResidual();
			/*for (int i = 0; i < g.getNumVertices(); i++) {
				for (int j = 0; j < g.getNumVertices(); j++) {
					System.out.print(gResidual[i][j]+" ");
				}
				System.out.println();
			}*/
		}
		/*for (int i = 0; i < g.getNumVertices(); i++) {
			for (int j = 0; j < g.getNumVertices(); j++) {
				System.out.print(gResidual[i][j]+" ");
			}
			System.out.println();
		}*/
		
		System.err.println("Custo: "+custo);
	}
	
}
