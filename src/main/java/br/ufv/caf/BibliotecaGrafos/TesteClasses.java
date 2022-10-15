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

public class TesteClasses {
    public static void main(String[] args) {

        Grafo grafo = new Grafo();
        grafo.addVertices(5);

        grafo.addAresta(1, 2, 1.2);
        grafo.addAresta(2, 5, 2.3);
        grafo.addAresta(3, 5, -8.4);
        grafo.addAresta(3, 4, 0.3);
        grafo.addAresta(4, 5, 4.6);
        grafo.addAresta(1, 5, 0.1);

        grafo.printGrafo();

        System.out.println("\n");
        System.out.println("Ordem: " + grafo.getOrdem());
        System.out.println("Tamanho: " + grafo.getTamanho());
        System.out.println("Vizinhos: " + grafo.getVizinhos(5));
        System.out.println("Grau: " + grafo.getGrau(2));
        System.out.println("Sequencia de graus: " + grafo.getSequenciaGraus());
    }
}