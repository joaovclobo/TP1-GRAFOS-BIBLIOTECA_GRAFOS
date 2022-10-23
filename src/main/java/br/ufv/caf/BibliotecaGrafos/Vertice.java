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

public class Vertice {

    private int indice;
    private int grau;

    protected Vertice(int indice) {
        this.indice = indice;
        this.grau = 0;
    }

    protected int getGrau() {
        return grau;
    }

    protected void aumentaGrau(){
        this.grau++;
    }

    @Override
    public String toString(){
        return "Vertice: " + this.indice + " - Grau: " + this.grau;
    }
}
