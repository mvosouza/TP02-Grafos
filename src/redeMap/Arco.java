package redeMap;

public class Arco {
	private int origem;
	private int destino;
	private int fluxoMin;
	private long fluxoMax;
	private int custo;
	private long fluxo;
	
	
	public Arco(int origem, int destino, int fluxoMin, int fluxoMax, int custo, int fluxo) {
		super();
		this.origem = origem;
		this.destino = destino;
		this.fluxoMin = fluxoMin;
		this.fluxoMax = fluxoMax;
		this.custo = custo;
		this.fluxo = fluxo;
	}
	
	public Arco(int origem, int destino, int fluxoMin2, long fluxoMax2, int custo2, long fluxo2) {
		super();
		this.origem = origem;
		this.destino = destino;
		this.fluxoMin = fluxoMin2;
		this.fluxoMax = fluxoMax2;
		this.custo = custo2;
		this.fluxo = fluxo2;
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
	public long getFluxoMax() {
		return fluxoMax;
	}
	public void setFluxoMax(long fluxoMax) {
		this.fluxoMax = fluxoMax;
	}
	public int getCusto() {
		return custo;
	}
	public void setCusto(int custo) {
		this.custo = custo;
	}
	public long getFluxo() {
		return fluxo;
	}
	public void setFluxo(long fluxo) {
		this.fluxo = fluxo;
	}

	@Override
	public String toString() {
		return "Fluxo [origem=" + origem + ", destino=" + destino + ", fluxoMin=" + fluxoMin + ", fluxoMax=" + fluxoMax
				+ ", custo=" + custo + ", fluxo=" + fluxo + "]";
	}

}
