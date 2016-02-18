package redeMap;

public class Fluxo {
	private int origem;
	private int destino;
	private int fluxoMin;
	private int fluxoMax;
	private int custo;
	private int fluxo;
	
	
	public Fluxo(int origem, int destino, int fluxoMin, int fluxoMax, int custo, int fluxo) {
		super();
		this.origem = origem;
		this.destino = destino;
		this.fluxoMin = fluxoMin;
		this.fluxoMax = fluxoMax;
		this.custo = custo;
		this.fluxo = fluxo;
	}
	
	public int getOrigem() {
		return origem;
	}
	public void setOrigem(int origem) {
		this.origem = origem;
	}
	public int getDestino() {
		return destino;
	}
	public void setDestino(int destino) {
		this.destino = destino;
	}
	public int getFluxoMin() {
		return fluxoMin;
	}
	public void setFluxoMin(int fluxoMin) {
		this.fluxoMin = fluxoMin;
	}
	public int getFluxoMax() {
		return fluxoMax;
	}
	public void setFluxoMax(int fluxoMax) {
		this.fluxoMax = fluxoMax;
	}
	public int getCusto() {
		return custo;
	}
	public void setCusto(int custo) {
		this.custo = custo;
	}
	public int getFluxo() {
		return fluxo;
	}
	public void setFluxo(int fluxo) {
		this.fluxo = fluxo;
	}

	@Override
	public String toString() {
		return "Fluxo [origem=" + origem + ", destino=" + destino + ", fluxoMin=" + fluxoMin + ", fluxoMax=" + fluxoMax
				+ ", custo=" + custo + ", fluxo=" + fluxo + "]";
	}

}
