package rede;

import java.io.BufferedReader;
import java.io.FileReader;

public class Grafo {

	private String nome;
	
	private int numArcos = 0;
	private int numVertices = 0;
	private Fluxo[][] matrizAdj = null; 
	private Vertice vertices[] = new Vertice[2];
	
	Grafo(String nome){
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getNumArcos() {
		return numArcos;
	}
	public void setNumArcos(int numArcos) {
		this.numArcos = numArcos;
	}
	public int getNumVertices() {
		return numVertices;
	}
	public void setNumVertices(int numVertices) {
		this.numVertices = numVertices;
	}
	public Fluxo[][] getMatrizAdj() {
		return matrizAdj;
	}
	public void setMatrizAdj(Fluxo[][] matrizAdj) {
		this.matrizAdj = matrizAdj;
	}
	public Vertice[] getVertices() {
		return vertices;
	}
	public void setVertices(Vertice[] vertices) {
		this.vertices = vertices;
	}

	public void criarGrafo(){
		
		int cont = 0;
		
		FileReader file;
		String vet[];
		try {
			file = new FileReader(nome);
			BufferedReader buffer = new BufferedReader(file);
			String linha = "";
			while((linha = buffer.readLine()) != null){
				vet = linha.split(" ");
				switch (vet[0]) {
				
				case "a":
					Fluxo aux = new Fluxo(Integer.parseInt(vet[3]), Integer.parseInt(vet[4]), Integer.parseInt(vet[5]),0);
					matrizAdj[Integer.parseInt(vet[1])-1][Integer.parseInt(vet[2])-1] = aux;
					break;
					
				case "n":
					vertices[cont] = new Vertice(Integer.parseInt(vet[1]), Integer.parseInt(vet[2]));
					cont++;
					break;
					
				case "p":
					numVertices = Integer.parseInt(vet[2]);
					numArcos = Integer.parseInt(vet[3]);
					matrizAdj = new Fluxo[numVertices][numVertices];
					for (int i = 0; i < numVertices; i++) {
						for (int j = 0; j < numVertices; j++) {
							matrizAdj[i][j]=null;
						}
					}
					break;
					
				case "c":
					continue;
				}
			}
			buffer.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*for (int i = 0; i < numVertices; i++) {
			for (int j = 0; j < numVertices; j++) {
				System.out.print(matrizAdj[i][j]+" ");
			}
			System.out.println();
		}*/
	}
}
