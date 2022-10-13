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
    float peso;
    int indVertice1;
    int indVertice2;
    
    protected Aresta(int indVertice1, int indVertice2, float peso){
        this.peso = peso;
        this.indVertice1 = indVertice1;
        this.indVertice2 = indVertice2;
    }
}
