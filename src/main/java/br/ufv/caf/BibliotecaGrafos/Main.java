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

        int opcao,indVerticeEscolhido, indVerticeOrigem;
        Scanner in = new Scanner(System.in);
        
        System.out.println("Digite o endereço do arquivo que contém o grafo");
        enderecoArquivo = in.nextLine();
        

            //enderecoArquivo = "/home/joao/Documents/UFV/CCF 331 - GRAFOS/TP1/GraphsLib/BibliotecaGrafos/BibliotecaGrafos/ConversaoJson/ExConversao/extxt.txt";
            //enderecoArquivo = "/home/thiagocr/Documentos/grafo1";
//            enderecoArquivo = "/home/douglas/Desktop/grafo1";

        grafo.montarGrafo(enderecoArquivo);

        System.out.println("");
        System.out.println("Qual informação sobre o grafo você deseja:");
        System.out.println("1 - Informações sobre um vertice em específico;");
        System.out.println("2 - Informações gerais sobre o grafo;");
        System.out.println("3 - Relizar busca em profundidade;");
        System.out.println("4 - Caminho minimo/Distância;");
        System.out.println("5- Sair.");
        
        opcao = in.nextInt();
        grafo.calculaCaminhoMin();
        
        while(flag){
            switch (opcao) {
                case 1:
                    System.out.println("Qual o vertice escolhido:");
                    indVerticeEscolhido = in.nextInt();
                    System.out.println("Informações do vertice:"+ indVerticeEscolhido);
                    System.out.println("Grau: " + grafo.getGrau(indVerticeEscolhido));
                    System.out.println("Vizinhos: " + grafo.getVizinhos(indVerticeEscolhido));
                    System.out.println("Excetricidade:" + grafo.calculaExcentricidade(indVerticeEscolhido));
                    System.out.println("Centralidade: "+ grafo.centralidadeGrafo(indVerticeEscolhido));
                    break;
                case 2:
                    System.out.println("Informações do grafo:");
                    System.out.println("Grafo: ");
                    grafo.printGrafo();
                    System.out.println("");
                    System.out.println("Ordem: " + grafo.getOrdem());
                    System.out.println("Tamanho: " + grafo.getTamanho());
                    System.out.println("Sequencia de graus: " + grafo.getSequenciaGraus());
                    System.out.println("Matriz de caminhos minimos:");
                    grafo.getL();
                    System.out.println("");
                    System.out.println("Diametro: " + grafo.calculaDiametro());
                    System.out.println("Raio: " + grafo.calculaRaio());
                    grafo.calculaCentro();
                    System.out.printf("Centro: ");
                    grafo.getCentro();
                    break;
                case 3:
                    System.out.println("Qual o vertice escolhido para a busca:");
                    indVerticeEscolhido = in.nextInt();
                    grafo.BuscaProfundidade(indVerticeEscolhido);
                    System.out.println("sequencia da busca em profundidade: " + grafo.OrdemBusca);
                    System.out.println("Arestas que não fazem parte da busca: " + grafo.ArestasRetorno);
                    grafo.zeroingBuscaProfundidade();
                    break;
                case 4:
                    System.out.println("Qual o vertice escolhido de origem:");
                    indVerticeOrigem = in.nextInt();
                    System.out.println("Qual o vertice escolhido para a distancia:");
                    indVerticeEscolhido = in.nextInt();
                    if(grafo.calculaCaminhoMin()){
                        System.out.println("Matriz distancias (L)\n");
                        grafo.getL();
                        System.out.println("\n\n");

                        System.out.println("Matriz caminhos (R)\n");
                        grafo.getR();
                        System.out.println("\n\n");
                        System.out.println("Distancia de "+indVerticeOrigem+" para "+indVerticeEscolhido+"\n");
                        System.out.println(grafo.distanciaVertices(indVerticeOrigem, indVerticeEscolhido));
                    }

                    else{
                        System.out.println("O grafo possui ciclo negativo e Floyd Warshall não pode ser aplicado");
                    }


                    break;
                case 5:
                    flag = false;
                    break;
                default:
                    System.out.println("Opcao inválida, digite uma opção válida.");
                    break;
            }
            System.out.println("");
            System.out.println("Qual informação sobre o grafo você deseja:");
            System.out.println("1 - Informações sobre um vertice em específico;");
            System.out.println("2 - Informações gerais sobre o grafo;");
            System.out.println("3 - Relizar busca em profundidade;");
            System.out.println("4 - Caminho minimo/Distância;");
            System.out.println("5- Sair.");

            opcao = in.nextInt();
            
            }
        }
    }