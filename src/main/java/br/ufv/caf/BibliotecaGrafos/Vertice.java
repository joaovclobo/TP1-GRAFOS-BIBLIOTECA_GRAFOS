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

public class Vertice implements Comparable<Vertice>{

    private int indice;
    private int grau;
    boolean marcado;
    boolean saturado;

    protected Vertice(int indice) {
        this.indice = indice;
        this.grau = 0;
        this.marcado = false;
        this.saturado = false;
    }

    protected int getGrau() {
        return grau;
    }

    public int getIndice() {
        return indice;
    }

    protected void aumentaGrau(){
        this.grau++;
    }

    protected void diminuiGrau(){
        this.grau--;
    }

    @Override
    public String toString(){
        return "Vertice: " + this.indice + " - Grau: " + this.grau;
    }
    
    @Override
    public int compareTo(Vertice other) {
        if (grau > other.grau) return -1;
        if (grau < other.grau) return 1;
        if (indice >other.indice) return -1;
        if (indice < other.indice) return 1;
        return 0;
    }
}
