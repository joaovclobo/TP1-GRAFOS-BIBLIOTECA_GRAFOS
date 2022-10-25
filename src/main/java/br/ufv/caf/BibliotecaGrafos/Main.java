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
        String enderecoArquivo;

        int opcaoMontagem;
        Scanner in = new Scanner(System.in);
        
        System.out.println("Digite a opcao que deseja:\n 1- Montagem arquivo\n 2- Montagem dados codigo");
        
        opcaoMontagem = in.nextInt();
        
        if(opcaoMontagem == 1){

            // TODO: 22/10/2022 - Manter só entrada do endereço pelo terminal
//            enderecoArquivo = in.next();

            enderecoArquivo = "/home/joao/Documents/UFV/CCF 331 - GRAFOS/TP1/GraphsLib/BibliotecaGrafos/BibliotecaGrafos/ConversaoJson/ExConversao/extxt.txt";
//            enderecoArquivo = "/home/thiagocr/Documentos/grafo1";
//            enderecoArquivo = "/home/douglas/Desktop/arquivoQuestao11.txt";

            flag  = grafo.montarGrafo(enderecoArquivo);

        }
        else{

            grafo.addVertices(5);

            /*grafo.addAresta(1, 2, 1.2);
            grafo.addAresta(2, 5, 2.3);
            grafo.addAresta(3, 5, -8.4);
            grafo.addAresta(3, 4, 0.3);
            grafo.addAresta(4, 5, 4.6);
            grafo.addAresta(1, 5, 0.1);*/
            
            grafo.addAresta(1, 2, 1.1);
            grafo.addAresta(1,3,2.3);
            grafo.addAresta(2, 5, 3.9);
            grafo.addAresta(2, 3, 3);
            grafo.addAresta(3, 4, 4.7);
            grafo.addAresta(4, 5, 6);
            grafo.addAresta(2, 4, 2);
        
        }

        grafo.printGrafo();

        System.out.println("\n");
        System.out.println("Ordem: " + grafo.getOrdem());
        System.out.println("Tamanho: " + grafo.getTamanho());
        System.out.println("Vizinhos: " + grafo.getVizinhos(1));
        System.out.println("Vizinhos: " + grafo.getVizinhos(2));
        System.out.println("Vizinhos: " + grafo.getVizinhos(3));
        System.out.println("Vizinhos: " + grafo.getVizinhos(4));
        System.out.println("Vizinhos: " + grafo.getVizinhos(5));
        System.out.println("Grau: " + grafo.getGrau(1));
        System.out.println("Grau: " + grafo.getGrau(2));
        System.out.println("Grau: " + grafo.getGrau(3));
        System.out.println("Grau: " + grafo.getGrau(4));
        System.out.println("Grau: " + grafo.getGrau(5));
        System.out.println("Sequencia de graus: " + grafo.getSequenciaGraus());
        grafo.BuscaProfundidade(1);
        System.out.println("sequencia da busca em profundidade: " + grafo.OrdemBusca);
        System.out.println("Arestas que não fazem parte da busca: " + grafo.ArestasRetorno);
        System.out.printf("centralidade: %.4f", grafo.centralidadeGrafo(1));
        
        

        // TODO: 22/10/2022 - Isso pode ficar na função, colocar isso na main não fica legal

        if(flag) {
            //grafo.getL();
            grafo.getR();
        }
        else
            System.out.println("GRAFO COM CICLO!!!");
    }

}