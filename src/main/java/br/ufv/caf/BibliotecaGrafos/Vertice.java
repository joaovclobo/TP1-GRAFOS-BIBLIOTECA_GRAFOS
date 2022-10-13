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
import java.util.Comparator;

public class Vertice {

    private int indice;
    private ArrayList<Vertice> vizinhos;
    private int grau;

    protected Vertice(int indice) {
        this.vizinhos = null;
        this.grau = 0;
    }

    private int getIndice() {
        return indice;
    }

    protected int getGrau() {
        return grau;
    }

    protected void addVizinho(Vertice vizinho){
        this.vizinhos.add(vizinho);
    }

    protected void aumentaGrau(){
        this.grau++;
    }

    protected ArrayList<Integer> getVizinhosVertice() {
        ArrayList indVizinhos = new ArrayList();

        for (Vertice v : vizinhos) {
            indVizinhos.add(v.getIndice());
        };

        Collections.sort(indVizinhos);

        return indVizinhos;
    }
}
