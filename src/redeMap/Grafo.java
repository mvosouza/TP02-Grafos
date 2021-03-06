package redeMap;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.SortedMap;
import java.util.TreeMap;

public class Grafo {

	private String nome;
	
	private Integer numArcos = 0;
	private Integer numVertices = 0;
	private SortedMap<Integer,SortedMap<Integer,Arco>> grafo; 
	private LinkedList<Vertice> vertices;
	
	Grafo(String nome){
		this.nome = nome;
		this.grafo = new TreeMap<>();
		vertices = new LinkedList<>();
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
	public SortedMap<Integer, SortedMap<Integer, Arco>> getGrafo() {
		return grafo;
	}
	public void setGrafo(SortedMap<Integer, SortedMap<Integer, Arco>> grafo) {
		this.grafo = grafo;
	}
	
	public void criarGrafo(){
		
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
					if (grafo.get(Integer.parseInt(vet[1])) != null) {
						grafo.get(Integer.parseInt(vet[1])).put(Integer.parseInt(vet[2]), new Arco(Integer.parseInt(vet[1]),Integer.parseInt(vet[2]),Integer.parseInt(vet[3]), Integer.parseInt(vet[4]), Integer.parseInt(vet[5]),0));
					}
					else{
						SortedMap<Integer, Arco> aux = new TreeMap<Integer,Arco>();
						aux.put(Integer.parseInt(vet[2]), new Arco(Integer.parseInt(vet[1]),Integer.parseInt(vet[2]),Integer.parseInt(vet[3]), Integer.parseInt(vet[4]), Integer.parseInt(vet[5]),0));
						grafo.put(Integer.parseInt(vet[1]), aux);
					}
					break;
					
				case "n":
					vertices.add(new Vertice(Integer.parseInt(vet[1]), Integer.parseInt(vet[2])));
					break;
					
				case "p":
					numVertices = Integer.parseInt(vet[2])+2;
					numArcos = Integer.parseInt(vet[3]);
					
					grafo.put(0, new TreeMap<>());
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
		
		while (!vertices.isEmpty()) {
			Vertice v = vertices.removeFirst();
			if (v.getDemOuOferta() > 0) {
				grafo.get(0).put(v.getId(), new Arco(0,v.getId(),0,v.getDemOuOferta(),0, 0));
			}
			else{
				if(grafo.get(v.getId()) == null)
					grafo.put(v.getId(), new TreeMap<>());
				grafo.get(v.getId()).put(numVertices-1, new Arco(v.getId(),numVertices-1,0,Math.abs(v.getDemOuOferta()),0, 0));
			}
		}
		
		//imprimeTodosArcos();
		
	}
	
	public void imprimeTodosArcos(){
		for (SortedMap<Integer, Arco> para : grafo.values()) {
			for (Arco fluxo : para.values()) {
				System.out.println(fluxo.toString());
			}
		}
	}
	
	public void imprimeFluxos(){
		System.err.println("\nArcos");
		for (SortedMap<Integer, Arco> mapArcos : grafo.values()) {
			for (Arco f : mapArcos.values()) {
				if(f.getOrigem() != 0 && f.getDestino() != numVertices-1)
					if(f.getFluxo() > 0)
						System.err.println("("+f.getOrigem()+","+f.getDestino()+") - Fluxo: "+f.getFluxo()+" - Custo: "+f.getCusto());
			}
		}
	}
	
	public void imprimeCustoTotal(){
		BigDecimal custo = new BigDecimal(0);
		for (SortedMap<Integer, Arco> mapArcos : grafo.values()) {
			for (Arco f : mapArcos.values()) {
				if(f.getFluxo() > 0){
					BigDecimal aux = new BigDecimal(f.getFluxo()).multiply(new BigDecimal(f.getCusto()));
					custo = custo.add(aux);
				}
			}
		}
		System.err.println("Custo total Arestas: "+custo.toString());
	}
}
