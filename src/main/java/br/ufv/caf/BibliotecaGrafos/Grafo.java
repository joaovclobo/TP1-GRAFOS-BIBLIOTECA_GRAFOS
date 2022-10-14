/*

Universidade Federal De Viçosa - Campus Florestal
Trabalho prático 1 - Disciplina CCF 331 - TEORIA E MODELO DE GRAFOS

Professor responsável:

  - Marcus Henrique Soares Mendes

Alunos responsáveis:

  - Thiago Rocha - 4225
  - Douglas Silva - 4253
  - Lucas Souza - 4235
  - João Lobo - 4693

*/

package br.ufv.caf.BibliotecaGrafos;

import java.util.ArrayList;
import java.util.Collections;

public class Grafo {
    private ArrayList<Aresta> arestas ;
    private ArrayList<Vertice> vertices;

    public Grafo() {
        arestas = new ArrayList<>();
        vertices = new ArrayList<>();
    }

    /*------------ Funções da biblioteca ------------*/

    public int getOrdem(){
        return vertices.size();
    }

    public int getTamanho(){
        return arestas.size();
    }

    public ArrayList<Integer> getVizinhos(int indVetice) {
        return vertices.get(indVetice - 1).getVizinhosVertice();
    }

    public int getGrau(int indVetice){
        return vertices.get(indVetice).getGrau();
    }

    public ArrayList<Integer> getSequenciaGraus(){
        ArrayList<Integer> sequenciaGraus = new ArrayList<>();

        for (Vertice v: vertices) {
            sequenciaGraus.add(v.getGrau());
        }

        sequenciaGraus.sort(Collections.reverseOrder());

        return sequenciaGraus;
    }

    /*------------ Funções para iniciar e mostrar o grafo ------------*/

    public void addAresta(int indVertice1, int indVertice2, double peso){

        Vertice vertice1 = vertices.get(indVertice1 - 1);
        Vertice vertice2 = vertices.get(indVertice2 - 1);

        vertice1.aumentaGrau();
        vertice2.aumentaGrau();

        vertice1.addVizinho(vertice2);
        vertice2.addVizinho(vertice1);

        this.arestas.add(new Aresta(indVertice1, indVertice2, peso));

    }

    private void addVertice(int indice){
        Vertice vertice = new Vertice(indice);
        this.vertices.add(vertice);
    }

    public void addVertices(int numeroVertices){
        for (int i = 0; i < numeroVertices; i++) {
            addVertice(i + 1);
        }
    }

    void printGrafo(){
        System.out.println("\n-------- Vertices --------\n");

        for (Vertice vertice: vertices) {
            System.out.println(vertice);;
        }
        System.out.println("\n-------- Arestas --------\n");

        for (Aresta aresta: arestas) {
            System.out.println(aresta);
        }
    }

}
