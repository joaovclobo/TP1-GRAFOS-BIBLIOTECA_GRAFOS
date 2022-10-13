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
