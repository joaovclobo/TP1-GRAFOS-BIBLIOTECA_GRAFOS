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

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        Boolean flag = false;
        Grafo grafo = new Grafo();
        
        int opcaoMontagem;
        Scanner in = new Scanner(System.in);
        
        System.out.println("Digite a opcao que deseja:\n 1- Montagem automatica\n 2- Montagem manual");
        
        opcaoMontagem = in.nextInt();
        
        if(opcaoMontagem == 1){
            flag  = grafo.montarGrafo("/home/thiagocr/Documentos/grafo1");
            //grafo.montarGrafo("/home/douglas/Desktop/arquivoQuestao11.txt");
        }
        else{
            
            int numeroVertices,vertice1, vertice2;
            float pesoAresta;
            String dados[], dadosAresta[];

            LeitorDeArquivo leituraArquivo = new LeitorDeArquivo();


            dados = leituraArquivo.leitura("/home/douglas/Desktop/arquivoQuestao11.txt");

            numeroVertices = leituraArquivo.getNumVertices();

            grafo.addVertices(numeroVertices);

            for(int i = 1; i < leituraArquivo.getQuantidadeArestas(); i++){

                dadosAresta = dados[i].split(" ");

                vertice1 = Integer.parseInt(dadosAresta[0]);

                vertice2 = Integer.parseInt(dadosAresta[1]);

                pesoAresta = Float.parseFloat(dadosAresta[2]);

                grafo.addAresta(vertice1, vertice2, pesoAresta);

            }

            grafo.calculaCaminhoMin();


            
        }


        grafo.printGrafo();

        System.out.println("\n");
        System.out.println("Ordem: " + grafo.getOrdem());
        System.out.println("Tamanho: " + grafo.getTamanho());
        System.out.println("Vizinhos: " + grafo.getVizinhos(5));
        System.out.println("Grau: " + grafo.getGrau(2));
        System.out.println("Sequencia de graus: " + grafo.getSequenciaGraus());

        if(flag) {
            //grafo.getL();
            grafo.getR();
        }

        else
            System.out.println("GRAFO COM CICLO!!!");

    }
}