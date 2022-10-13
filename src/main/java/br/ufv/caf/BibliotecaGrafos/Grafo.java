package br.ufv.caf.BibliotecaGrafos;

import java.util.ArrayList;

public class Grafo {
    private ArrayList<Aresta> arestas ;
    private ArrayList<Vertice> vertices;

    public Grafo() {
        arestas = new ArrayList<>();
        vertices = new ArrayList<>();
    }

    public int getOrdem(){
        return vertices.size();
    }

    public int getTamanho(){
        return arestas.size();
    }

    private void addAresta(int indVertice1, int indVertice2, float peso){
        this.arestas.add(new Aresta(indVertice1, indVertice2, peso));
    }

    private void addVertice(){
        this.vertices.add(new Vertice());
    }

    private void addVertices(int numeroVertices){
        for (int i = 0; i < numeroVertices; i++) {
            addVertice();
        }
    }


    public ArrayList<Integer> getVizinhos(int indVetice) {

        return ;
    }

    public int getGrau(int indVertice){

    }

    public ArrayList<Integer> getSequenciaGraus(){

    }

}
