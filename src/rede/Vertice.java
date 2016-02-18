package rede;

public class Vertice {
	private int id;
	private int demOuOferta;
	
	public Vertice(int id, int demOuOferta) {
		super();
		this.setId(id);
		this.setDemOuOferta(demOuOferta);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDemOuOferta() {
		return demOuOferta;
	}

	public void setDemOuOferta(int demOuOferta) {
		this.demOuOferta = demOuOferta;
	}
	
	
	
}
