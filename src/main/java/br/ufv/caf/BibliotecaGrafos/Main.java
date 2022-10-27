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

        Grafo grafo = new Grafo();
        String enderecoArquivo;
        
        boolean flag = true;

        int opcao,indVerticeEscolhido;
        Scanner in = new Scanner(System.in);
        
        System.out.println("Digite o endereço do arquivo que contém o grafo\n");
        enderecoArquivo = in.nextLine();
        
 

            // TODO: 22/10/2022 - Manter só entrada do endereço pelo terminal
//            enderecoArquivo = in.next();

            //enderecoArquivo = "/home/joao/Documents/UFV/CCF 331 - GRAFOS/TP1/GraphsLib/BibliotecaGrafos/BibliotecaGrafos/ConversaoJson/ExConversao/extxt.txt";
            //enderecoArquivo = "/home/thiagocr/Documentos/grafo1";
//            enderecoArquivo = "/home/douglas/Desktop/arquivoQuestao11.txt";

            grafo.montarGrafo(enderecoArquivo);


        System.out.println("Qual informação sobre o grafo você deseja:");
        System.out.println("1 - Informações sobre um vertice em específico;");
        System.out.println("2 - Informações gerais sobre o grafo;");
        System.out.println("3 - Relizar busca em profundidade;");
        System.out.println("4 - Caminho minimo/Distância;");
        
        opcao = in.nextInt();
        
        while(flag){
            switch (opcao) {
                //informações do vertice
                case 1 -> {
                    System.out.println("Qual o vertice escolhido:");
                    indVerticeEscolhido = in.nextInt();
                    System.out.println("Informações do vertice:"+indVerticeEscolhido);
                    System.out.println("Grau: " + grafo.getGrau(indVerticeEscolhido));
                    System.out.println("Vizinhos: " + grafo.getVizinhos(indVerticeEscolhido));
                    System.out.println("Excetricidade:" + grafo.calculaExcentricidade(indVerticeEscolhido));
                    System.out.println("Centralidade: "+ grafo.centralidadeGrafo(indVerticeEscolhido));
                }
                //informações do grafo
                case 2 -> {
                    System.out.println("Informações do grafo:");
                    System.out.println("Grafo: ");
                    grafo.printGrafo();
                    System.out.println("");
                    System.out.println("Ordem: " + grafo.getOrdem());
                    System.out.println("Tamanho: " + grafo.getTamanho());
                    System.out.println("Sequencia de graus: " + grafo.getSequenciaGraus());
                    System.out.println("Diametro: " + grafo.calculaDiametro());
                    System.out.println("Raio: " + grafo.calculaRaio());
                    grafo.calculaCentro();
                    System.out.printf("Centro: ");
                    grafo.getCentro();
                    grafo.calculaCaminhoMin();
                    System.out.println("Matriz de caminhos minimos:");
                    grafo.getL();

                }
                //busca em profundidade
                case 3 -> {
                    System.out.println("Qual o vertice escolhido para a busca:");
                    indVerticeEscolhido = in.nextInt();
                    grafo.BuscaProfundidade(indVerticeEscolhido);
                    System.out.println("sequencia da busca em profundidade: " + grafo.OrdemBusca);
                    System.out.println("Arestas que não fazem parte da busca: " + grafo.ArestasRetorno);

                }
                case 4 -> {
                    grafo.calculaCaminhoMin();
                    System.out.println("Matriz de caminho minimos:\n");
                    grafo.getL();
                }
                
                case 5 -> {
                    flag = false;
                }
                default -> System.out.println("Opcao inválida, digite uma opção válida.");
            }
        }
        
        
        /*
        System.out.println("");
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
        
/*
        if(grafo.calculaCaminhoMin()) {
            grafo.getL();
            System.out.println();
            grafo.getR();
            System.out.println();
            System.out.println(grafo.calculaDiametro());

            System.out.println(grafo.calculaRaio());
            grafo.calculaCentro();
            grafo.getCentro();
            System.out.println(grafo.distanciaVertices(5,4));
        }
        else
            System.out.println("GRAFO COM CICLO!!!");
*/
    }


}