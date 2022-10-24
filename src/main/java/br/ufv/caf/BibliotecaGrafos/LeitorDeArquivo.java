package br.ufv.caf.BibliotecaGrafos;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LeitorDeArquivo {
    
    private int numVertices;
    private String conteudoEmString,dados[];
    
    public String[] leitura(String enderecoArquivo) throws IOException{
        
        Path caminho = Paths.get(enderecoArquivo);
        byte[] conteudoArquivo = Files.readAllBytes(caminho);
        this.conteudoEmString = new String(conteudoArquivo);
        
        //apos ler o arquivo o transforma em uma unica string para facilitar manipulacao
        this.dados = this.conteudoEmString.split("\n");
        
        return this.dados;
        
    }
    
    public int getNumVertices(){
        
        this.numVertices = Integer.parseInt(this.dados[0]);
        
        return this.numVertices;
    }
    
    public int getQuantidadeArestas(){
        
        int qntArestas = dados.length;
        
        return qntArestas;
        
    }

}
