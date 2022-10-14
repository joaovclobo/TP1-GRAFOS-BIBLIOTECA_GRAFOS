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

public class Aresta {
    double peso;
    int indVertice1;
    int indVertice2;
    
    protected Aresta(int indVertice1, int indVertice2, double peso){
        this.peso = peso;
        this.indVertice1 = indVertice1;
        this.indVertice2 = indVertice2;
    }

    @Override
    public String toString() {
        return "Aresta: " + this.indVertice1 + "," + this.indVertice2 + " - Peso: " + this.peso;
    }
}
