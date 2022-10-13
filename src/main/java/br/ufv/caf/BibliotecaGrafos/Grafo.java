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

    /*------------ Funções da biblioteca (Disponíveis para o usuário) ------------*/

    public int getOrdem(){
        return vertices.size();
    }

    public int getTamanho(){
        return arestas.size();
    }

    public ArrayList<Integer> getVizinhos(int indVetice) {
        return vertices.get(indVetice).getVizinhosVertice();
    }


    public int getGrau(int indVertice){
        return vertices.get(indVertice).getGrau();
    }

    public ArrayList<Integer> getSequenciaGraus(){
        ArrayList<Integer> sequenciaGraus = new ArrayList<>();

        for (Vertice v: vertices) {
            sequenciaGraus.add(v.getGrau());
        }

        sequenciaGraus.sort(Collections.reverseOrder());

        return sequenciaGraus;
    }

    /*------------ Funções da auxiliares (Não disponíveis para o usuário) ------------*/

    private void addAresta(int indVertice1, int indVertice2, float peso){

        Vertice vertice1 = vertices.get(indVertice1);
        Vertice vertice2 = vertices.get(indVertice1);

        vertice1.aumentaGrau();
        vertice2.aumentaGrau();

        vertice1.addVizinho(vertice2);
        vertice2.addVizinho(vertice1);

        this.arestas.add(new Aresta(indVertice1, indVertice2, peso));

    }

    private void addVertice(int indice){
        this.vertices.add(new Vertice(indice));
    }

    private void addVertices(int numeroVertices){
        for (int i = 0; i < numeroVertices; i++) {
            addVertice(i);
        }
    }
}
