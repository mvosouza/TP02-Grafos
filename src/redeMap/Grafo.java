package redeMap;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.SortedMap;
import java.util.TreeMap;

public class Grafo {

	private String nome;
	
	private Integer numArcos = 0;
	private Integer numVertices = 0;
	private SortedMap<Integer,SortedMap<Integer,Fluxo>> grafo; 
	private PriorityQueue<Vertice> verticesDemanda;
	
	Grafo(String nome){
		this.nome = nome;
		this.grafo = new TreeMap<>();
		verticesDemanda = new PriorityQueue();
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
	public SortedMap<Integer, SortedMap<Integer, Fluxo>> getGrafo() {
		return grafo;
	}
	public void setGrafo(SortedMap<Integer, SortedMap<Integer, Fluxo>> grafo) {
		this.grafo = grafo;
	}
	public PriorityQueue<Vertice> getVerticesDemanda() {
		return verticesDemanda;
	}
	public void setVerticesDemanda(PriorityQueue<Vertice> verticesDemanda) {
		this.verticesDemanda = verticesDemanda;
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
						grafo.get(Integer.parseInt(vet[1])).put(Integer.parseInt(vet[2]), new Fluxo(Integer.parseInt(vet[1]),Integer.parseInt(vet[2]),Integer.parseInt(vet[3]), Integer.parseInt(vet[4]), Integer.parseInt(vet[5]),0));
					}
					else{
						SortedMap<Integer, Fluxo> aux = new TreeMap<Integer,Fluxo>();
						aux.put(Integer.parseInt(vet[2]), new Fluxo(Integer.parseInt(vet[1]),Integer.parseInt(vet[2]),Integer.parseInt(vet[3]), Integer.parseInt(vet[4]), Integer.parseInt(vet[5]),0));
						grafo.put(Integer.parseInt(vet[1]), aux);
					}
					break;
					
				case "n":
					if (Integer.parseInt(vet[2]) > 0) {
						grafo.get(0).put(Integer.parseInt(vet[1]), new Fluxo(0,Integer.parseInt(vet[1]),0,Integer.parseInt(vet[2]),0, 0));
					}
					else{
						verticesDemanda.add(new Vertice(Integer.parseInt(vet[1]), Integer.parseInt(vet[2])));
					}
					break;
					
				case "p":
					numVertices = Integer.parseInt(vet[2])+1;
					numArcos = Integer.parseInt(vet[3]);
					
					grafo.put(0, new TreeMap<>());
					grafo.put(numVertices, new TreeMap<>());
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
		
		/*while (!verticesDemanda.isEmpty()) {
			Vertice v = verticesDemanda.remove();
			System.err.println(v.getId());
			grafo.get(v.getId()).put(numVertices, new Fluxo(v.getId(),numVertices,0,Math.abs(v.getDemOuOferta()),0, 0));
			System.err.println("Entrei");
		}*/
		
		for (Map<Integer,Fluxo> para : grafo.values()) {
			System.err.println("-----");
			for (Fluxo fluxo : para.values()) {
				System.out.println(fluxo.toString());
			}
		}
	}
}
