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
import java.util.ArrayList;
import java.util.Collections;

public class Grafo {
    
    private ArrayList<Aresta> arestas ;
    private ArrayList<Vertice> vertices;
    
    private int vertice1, vertice2, numeroVertices;    
    private float pesoAresta;   
    private String dados[], dadosAresta[];



    /********************* Estruturas para o algoritmo de caminho mínimo: Dijkstra *********************/
    private double[][] L;
    private int[][] R;


    public Grafo() {
        arestas = new ArrayList<>();
        vertices = new ArrayList<>();
    }

    /********************* Funções pertinentes do algoritmo Floyd-Warshall *********************/
    /******************************************************************************************/

    /*
        Autor: ThiagoCaRo
        Funcionalidade: Inicializar a matriz L conforme o pseudo-algoritmo
     */
    private void inicializaMatrizL(){
        int ordem = getOrdem();
        this.L = new double[ordem][ordem];
        for(int i = 0; i < ordem; i++){
            for(int j = 0; j < ordem; j++){
                if(i == j)
                    L[i][j] = 0.0;
                else{

                    for(Aresta a : arestas){
                        if((a.indVertice1 == i+1 && a.indVertice2 == j+1) ||
                                (i+1 == a.indVertice2 && j+1 == a.indVertice1)){
                            L[i][j] = a.peso;
                            break;
                        }

                        else
                            L[i][j] = Double.POSITIVE_INFINITY;

                    }
                }
            }
        }
        getL();
    }

    /*
        Autor: ThiagoCaRo
        Funcionalidade: Inicializar a matriz R conforme o pseudo-algoritmo,
        a matriz L deve ser inicializada primeiro
     */
    private void inicializaMatrizR(){
        int ordem = getOrdem();
        this.R = new int[ordem][ordem];

        for(int i = 0; i < ordem; i++){
            for(int j = 0; j < ordem; j++){

                if(this.L[i][j] == Double.POSITIVE_INFINITY)
                    R[i][j] = 0;

                else
                    R[i][j] = i+1;
            }
        }
    }


    /*
        Autor: ThiagoCaRo
        Funcionalidade: Execução do algoritmo de fato alterando os valores de L e R
     */


    private boolean Floyd_Warshall(){
        int ordem = getOrdem();
        for(int i = 0; i < ordem; i++){
            for(int j = 0; j < ordem; j++){
                if(L[i][j]<0.0)
                    return false;
                for(int k = 0; k < ordem; k++){
                    if(L[i][j] > (L[i][k] + L[k][j])){
                        L[i][j] = L[i][k] + L[k][j];
                        R[i][j] = R[k][j];
                    }
                }
            }
        }
        return true;
    }

    /*
        Autor: ThiagoCaRo
        Funcionalidade: Retorna a matriz R
     */
    public void getR(){
        int ordem = getOrdem();
        for(int i = 0; i < ordem; i++){
            System.out.print((i+1)+" ");

        }

        //System.out.println("\n-------------------------------");
        for(int i = 0; i < ordem; i++) {
            System.out.println();
            for (int j = 0; j < ordem; j++) {
                System.out.printf("%d ",R[i][j]);
            }
        }
    }

    /*
        Autor: ThiagoCaRo
        Funcionalidade: Retorna a matriz L
     */
    public void getL(){
        int ordem = getOrdem();
        for(int i = 0; i < ordem; i++) {
            System.out.println();
            for (int j = 0; j < ordem; j++) {
                System.out.printf("%f ",L[i][j]);
            }
        }
    }

    /************************************************************************************/
    /******************************** FIM Floyd-Warshall *******************************/
    public void calculaCaminhoMin(){
        inicializaMatrizL();
        inicializaMatrizR();
        Floyd_Warshall();
    }
    /*------------ Funções da biblioteca ------------*/

    public int getOrdem(){
        return vertices.size();
    }

    public int getTamanho(){
        return arestas.size();
    }

    public ArrayList<Integer> getVizinhos(int indVetice) {
        return vertices.get(indVetice - 1).getVizinhosVertice();
    }

    public int getGrau(int indVetice){
        return vertices.get(indVetice).getGrau();
    }

    public ArrayList<Integer> getSequenciaGraus(){
        ArrayList<Integer> sequenciaGraus = new ArrayList<>();

        for (Vertice v: vertices) {
            sequenciaGraus.add(v.getGrau());
        }

        sequenciaGraus.sort(Collections.reverseOrder());

        return sequenciaGraus;
    }

    /*------------ Funções para iniciar e mostrar o grafo ------------*/
    
    public boolean montarGrafo(String enderecoArquivo) throws IOException{
        
        LeitorDeArquivo leituraArquivo = new LeitorDeArquivo();
        
        this.dados = leituraArquivo.leitura(enderecoArquivo);
        
        this.numeroVertices = leituraArquivo.getNumVertices();   

        this.addVertices(numeroVertices);
        for(int i = 1; i < leituraArquivo.getQuantidadeArestas(); i++){
            
            this.dadosAresta = this.dados[i].split(" ");
            
            this.vertice1 = Integer.parseInt(this.dadosAresta[0]);
            
            this.vertice2 = Integer.parseInt(this.dadosAresta[1]);
            
            this.pesoAresta = Float.parseFloat(this.dadosAresta[2]);
            
            this.addAresta(vertice1, vertice2, pesoAresta);
        }


        inicializaMatrizL();
        inicializaMatrizR();
        return Floyd_Warshall();

    }

    public void addAresta(int indVertice1, int indVertice2, double peso){

        Vertice vertice1 = vertices.get(indVertice1 - 1);
        Vertice vertice2 = vertices.get(indVertice2 - 1);

        vertice1.aumentaGrau();
        vertice2.aumentaGrau();

        vertice1.addVizinho(vertice2);
        vertice2.addVizinho(vertice1);

        this.arestas.add(new Aresta(indVertice1, indVertice2, peso));

    }

    private void addVertice(int indice){
        Vertice vertice = new Vertice(indice);
        this.vertices.add(vertice);
    }

    public void addVertices(int numeroVertices){
        for (int i = 0; i < numeroVertices; i++) {
            addVertice(i + 1);
        }
    }

    void printGrafo(){
        System.out.println("\n-------- Vertices --------\n");

        for (Vertice vertice: vertices) {
            System.out.println(vertice);
        }
        System.out.println("\n-------- Arestas --------\n");

        for (Aresta aresta: arestas) {
            System.out.println(aresta);
        }
    }

    //Função sujeita  alterações


}