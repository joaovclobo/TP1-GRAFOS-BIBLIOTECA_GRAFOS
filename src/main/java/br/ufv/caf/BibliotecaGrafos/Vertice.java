package br.ufv.caf.BibliotecaGrafos;

import java.util.ArrayList;

public class Vertice {

    private int indice;
    private ArrayList<Vertice> vizinhos;
    private int grau;

    protected Vertice(int indice) {
        this.vizinhos = null;
        this.grau = 0;
    }

    public int getIndice() {
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

    protected ArrayList<Integer> getVizinhos() {
        ArrayList indVizinhos = new ArrayList();

        for (Vertice v : vizinhos) {
            indVizinhos.add(v.getIndice());
        };
        return indVizinhos;
    }
}
