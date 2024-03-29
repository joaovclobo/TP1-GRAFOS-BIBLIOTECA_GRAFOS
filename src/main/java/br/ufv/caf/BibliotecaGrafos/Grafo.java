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

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Grafo {

    private ArrayList<Aresta> arestas ;
    private ArrayList<Vertice> vertices;
    private ArrayList<LinkedList<Integer>> listaAdjacncia;

    private ArrayList<Vertice> centro;
    public ArrayList<Vertice> OrdemBusca;
    public ArrayList<Aresta> ArestasRetorno;
    
    private ArrayList<Vertice> coberturaMinima;
    
    private ArrayList<Aresta> emparelhamentoMax;

    /********************* Estruturas para o algoritmo de caminho mínimo: Dijkstra *********************/

    private double[][] L;
    private int[][] R;

    public Grafo() {
        arestas = new ArrayList<>();
        vertices = new ArrayList<>();
        listaAdjacncia = new ArrayList<>();
        centro = new ArrayList<>();
        OrdemBusca = new ArrayList<>();
        ArestasRetorno = new ArrayList<>();
        coberturaMinima = new ArrayList<>();
        emparelhamentoMax = new ArrayList<>();
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
                System.out.printf("%.2f ",L[i][j]);
            }
        }
    }

    /************************************************************************************/
    /******************************** FIM Floyd-Warshall *******************************/
    public double calculaExcentricidade(int vertice){
        int ordem = getOrdem();
        double excentricidade = 0.0;
        for(int i = 0; i < ordem; i++){
            if(this.L[vertice-1][i] > excentricidade)
                excentricidade = this.L[vertice-1][i];
        }
        return excentricidade;
    }

    public double calculaRaio(){
        int ordem = getOrdem();
        double excentricidadeaux = calculaExcentricidade(1);
        double excentricidademem;
        for(int i = 2; i <= ordem; i++){
            excentricidademem = calculaExcentricidade(i);
            if(excentricidademem < excentricidadeaux)
                excentricidadeaux = excentricidademem;

        }
        return excentricidadeaux;
    }

    public double calculaDiametro(){
        int ordem = getOrdem();
        double excentricidadeaux = calculaExcentricidade(1);
        double excentricidademem;
        for(int i = 2; i <= ordem; i++){
            excentricidademem = calculaExcentricidade(i);
            if(excentricidademem > excentricidadeaux)
                excentricidadeaux = excentricidademem;

        }
        return excentricidadeaux;
    }



    public void calculaCentro(){
        int ordem = getOrdem();
        double raio = calculaRaio();

        for(int i = 0; i < ordem; i++){
            //for(int j = 0; j < ordem; j++){
            if(calculaExcentricidade(i+1) == raio) {
                this.centro.add(vertices.get(i));
                break;
            }
            //}
        }
    }

    public void getCentro(){
        for(int i = 0; i < centro.size(); i++){
            System.out.println(centro.get(i).toString());
        }
    }


    public boolean calculaCaminhoMin(){
        inicializaMatrizL();
        inicializaMatrizR();
        return Floyd_Warshall();
    }

    public double distanciaVertices(int vertice1, int vertice2){
        return this.L[vertice1-1][vertice2-1];
    }

    /*------------ Funções da biblioteca ------------*/

    public int getOrdem(){
        return vertices.size();
    }

    public int getTamanho(){
        return arestas.size();
    }

    public LinkedList<Integer> getVizinhos(int indVetice) {
        if (!listaAdjacncia.isEmpty()) {
            return listaAdjacncia.get(indVetice - 1);
        } else return null;
    }

    public int getGrau(int indVetice){
        return vertices.get(indVetice - 1).getGrau();
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

    public void montarGrafo(String enderecoArquivo) throws IOException{

        int vertice1, vertice2, numeroVertices;
        double pesoAresta;
        String dados[], dadosAresta[];

        LeitorDeArquivo leituraArquivo = new LeitorDeArquivo();

        dados = leituraArquivo.leitura(enderecoArquivo);

        this.addVertices(leituraArquivo.getNumVertices());

        for(int i = 1; i < leituraArquivo.getQuantidadeArestas(); i++){

            dadosAresta = dados[i].split(" ");

            vertice1 = Integer.parseInt(dadosAresta[0]);

            vertice2 = Integer.parseInt(dadosAresta[1]);

            pesoAresta = Double.parseDouble(dadosAresta[2]);

            addAresta(vertice1, vertice2, pesoAresta);
        }

    }

    private void addVizinho(int indVertice, int indVizinho){

        listaAdjacncia.get(indVertice - 1).add(indVizinho);

        Collections.sort(listaAdjacncia.get(indVertice - 1));

    }

    private void removeVizinho(int indVertice, int indVizinho){

        listaAdjacncia.get(indVertice - 1).remove(new Integer(indVizinho));

        Collections.sort(listaAdjacncia.get(indVertice - 1));

    }

    public void addAresta(int indVertice1, int indVertice2, double peso){

        vertices.get(indVertice1 - 1).aumentaGrau();
        vertices.get(indVertice2 - 1).aumentaGrau();

        addVizinho(indVertice1, indVertice2);
        addVizinho(indVertice2, indVertice1);

        this.arestas.add(new Aresta(indVertice1, indVertice2, peso));

    }

    private void removeAresta(int indVertice1, int indVertice2, double peso, int indiceAresta) {

        vertices.get(indVertice1 - 1).diminuiGrau();
        vertices.get(indVertice2 - 1).diminuiGrau();

        removeVizinho(indVertice1, indVertice2);
        removeVizinho(indVertice2, indVertice1);

        this.arestas.remove(indiceAresta);
    }

    private void addVertice(int indice){

        Vertice vertice = new Vertice(indice);
        if (!vertices.contains(vertice)) {
            this.vertices.add(vertice);
        }
    }

    public void addVertices(int numeroVertices){

        for (int i = 0; i < numeroVertices; i++) {

            listaAdjacncia.add(new LinkedList<>());
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

    public void BuscaProfundidade(int v) {
        for (Vertice vertice : vertices) {
            if (vertice.getIndice() == v) {
                vertice.marcado = true;
                OrdemBusca.add(vertice);
            }
        }
        if (this.getVizinhos(v) != null){
            for (int w : this.getVizinhos(v)) {
                for (Vertice ver : vertices) {
                    if (ver.getIndice() == w && ver.marcado == false) {
                        for (Aresta a : arestas) {
                            if ((a.indVertice1 == v && a.indVertice2 == w) || (a.indVertice1 == w && a.indVertice2 == v)) {
                                a.explorada = true;
                            }
                        }
                        ver.marcado = true;
                        BuscaProfundidade(w);
                    } else if (ver.getIndice() == w && ver.marcado == true) {
                        for (Aresta are : arestas) {
                            if ((are.indVertice1 == v && are.indVertice2 == w) || (are.indVertice1 == w && are.indVertice2 == v)) {
                                if (are.explorada == false) {
                                    are.explorada = true;
                                    ArestasRetorno.add(are);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public float centralidadeGrafo(int indVertice){
        this.calculaCaminhoMin();
        float somaCaminhoMin = 0;
        float centralidade;
        int ordem;

        ordem = this.getOrdem();
        for (int j = 0; j < ordem; j++) {
            somaCaminhoMin += this.L[indVertice-1][j];
        }
        centralidade = (ordem-1)/somaCaminhoMin;
        return centralidade;
    }

    public void zeroingBuscaProfundidade(){
        this.OrdemBusca.clear();
        this.ArestasRetorno.clear();

        for(Vertice ver: this.vertices){
            ver.marcado = false;
        }

        for(Aresta are: this.arestas){
            are.explorada = false;
        }
    }

    public void zeroingCentro(){
        this.centro.clear();
    }

    //Se não há arestas de retorno o grafo não possui ciclo.
    //A função retorna true casa haja ciclo e false caso contrário
    public boolean verificaCiclo(int v){
        BuscaProfundidade(v);

        return !this.ArestasRetorno.isEmpty();
    }

    public void arvoreMinima() throws IOException {
        double pesoTotal = 0;
        Grafo arvoreMinima = new Grafo();
        arvoreMinima.addVertices(this.vertices.size());

        ArrayList<Aresta> arestasOrdenadas = (ArrayList<Aresta>) this.arestas.clone();
        Collections.sort(arestasOrdenadas);

        for (Aresta aresta : arestasOrdenadas) {
            arvoreMinima.addAresta(aresta.indVertice1, aresta.indVertice2, aresta.peso);

            if (aresta.equals(arestasOrdenadas.get(0))){
                pesoTotal += aresta.peso;

            } else {

                if (arvoreMinima.verificaCiclo(aresta.indVertice1)) {
                    arvoreMinima.removeAresta(aresta.indVertice1, aresta.indVertice2, aresta.peso, arvoreMinima.arestas.size() - 1);

                } else {
                    pesoTotal += aresta.peso;
                    if (arvoreMinima.arestas.size() == arvoreMinima.vertices.size()-1){
                        break;
                    }
                }
            }
            arvoreMinima.zeroingBuscaProfundidade();

        }
        System.out.println("\nPeso total da árvore mínima: " + pesoTotal);
        System.out.printf("Árvore mínima que foi escrita no arquivo : ");
        arvoreMinima.printGrafo();

        Scanner in = new Scanner(System.in);

        System.out.println("\nDigite o endereço e o nome do arquivo de saída:");
        String arqSaida = in.nextLine();
        FileWriter fw = new FileWriter(arqSaida, false);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(String.valueOf(arvoreMinima.getOrdem()) + "\n");

        for (Aresta arestas : arvoreMinima.arestas) {
            bw.write(arestas.indVertice1 + " " + arestas.indVertice2 + " " + arestas.peso + "\n");
        }

        bw.write("Peso total = " + pesoTotal);

        bw.close();
        fw.close();
    }
    
    
    public void coberturaMinima(){
        
        Vertice verticeUtilizado = null;
        
        ArrayList<Vertice> verticesOrdenados = (ArrayList<Vertice>) 
                this.vertices.clone();
        ArrayList<Aresta> arestasCobertura = (ArrayList<Aresta>) 
                this.arestas.clone();
        
        ArrayList<Aresta> vetorAuxiliar = new ArrayList<>();
        
        
        Collections.sort(verticesOrdenados);
        // /home/douglas/Desktop/grafo2
        
        while(!arestasCobertura.isEmpty()){
           
            if(!verticesOrdenados.isEmpty()){
                verticeUtilizado = verticesOrdenados.get(0);
                verticesOrdenados.remove(0);
                
            }
            
            this.coberturaMinima.add(verticeUtilizado);
            
            
            for(int i = 0; i < arestasCobertura.size(); i++){
                if(arestasCobertura.get(i).indVertice1 == verticeUtilizado.getIndice()){
                    if(!vetorAuxiliar.contains(arestasCobertura.get(i))){
                        vetorAuxiliar.add(arestasCobertura.get(i));
                    }
                }else if(arestasCobertura.get(i).indVertice2 == verticeUtilizado.getIndice()){
                    if(!vetorAuxiliar.contains(arestasCobertura.get(i))){
                        vetorAuxiliar.add(arestasCobertura.get(i));
                    }
                }
            }
            
            if(vetorAuxiliar.size() == getTamanho()){
                for(int i = 0; i < vetorAuxiliar.size(); i++){
                    if(!arestasCobertura.isEmpty()){
                        arestasCobertura.remove(vetorAuxiliar.get(i));  
                    }else{
                        break;
                    }
                }
            }
        }
        
        
        System.out.println("Cobertura minima:\n");
        for(int i = 0; i < this.coberturaMinima.size();i++){
            System.out.println(this.coberturaMinima.get(i)); 
        }
        System.out.println("");
        System.out.println("Numero Cobertura:"+this.coberturaMinima.size());
    }
 
    
    
    public boolean caminhoAumentante(){
        
        for(int i = 0;i < arestas.size();i++){
            if(arestas.get(i).emparelhada == true){
                vertices.get(arestas.get(i).indVertice1).saturado = false;
                vertices.get(arestas.get(i).indVertice2).saturado = false;
                arestas.get(i).emparelhada = false;
                
                if(vertices.get(arestas.get(i+1).indVertice1).getIndice() == arestas.get(i+1).indVertice1 || 
                    vertices.get(arestas.get(i+1).indVertice1).getIndice() == arestas.get(i+1).indVertice2 ){
                        vertices.get(arestas.get(i+1).indVertice1).saturado =  true;
                        
                }else if(vertices.get(arestas.get(i+1).indVertice2).getIndice() == arestas.get(i+1).indVertice2 || 
                    vertices.get(arestas.get(i+1).indVertice2).getIndice() == arestas.get(i+1).indVertice1 ){
                        vertices.get(arestas.get(i+1).indVertice2).saturado =  true;
                    
                }else{
                    
                    vertices.get(arestas.get(i).indVertice1).saturado = true;
                    vertices.get(arestas.get(i).indVertice2).saturado = true;
                    arestas.get(i).emparelhada = true;
                }
                
                if(vertices.get(arestas.get(i+1).indVertice1).saturado ==  true &&
                        vertices.get(arestas.get(i+1).indVertice2).saturado == true){
                    arestas.get(i+1).emparelhada = true;
                }
                
                for(Vertice v: vertices){
                    if(v.getIndice() == arestas.get(i).indVertice1 || 
                            v.getIndice() == arestas.get(i).indVertice2){
                        v.saturado =  true;
                    }
                }
            }
            
        }
        return false;
    }
    
    
    public void EmparelhamentoMax(){
        
        for(Aresta a: arestas){
            if(a.emparelhada == false){
                if(vertices.get(a.indVertice1).saturado == false ||
                        vertices.get(a.indVertice2).saturado == false){
                    emparelhamentoMax.add(a);
                    a.emparelhada = true;
                    for(Vertice v: vertices){
                        if(v.getIndice() == a.indVertice1 || v.getIndice() == a.indVertice2){
                            v.saturado =  true;
                        }
                    }
                }
            }
        }
        
        for (Aresta aresta: arestas) {
            if(aresta.emparelhada == true){
                System.out.println(aresta);
                
            }
        }

    }


}

