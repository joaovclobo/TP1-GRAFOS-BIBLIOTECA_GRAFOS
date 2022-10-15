package ufv.caf.br.ConversorJson;

import java.io.*;
import java.util.ArrayList;

public class Json2txt {
    private String ordem = "0";
    private ArrayList<String> vertices = new ArrayList<>();

    public void converteArquivo(String arqEntrada, String arqSaida) throws IOException {

        arqEntrada = "/home/joao/Documents/UFV/CCF 331 - GRAFOS/TP1/GraphsLib/BibliotecaGrafos/BibliotecaGrafos/ConversaoJson/ExConversao/GrafoBaseExemplo.json";
        arqSaida = "/home/joao/Documents/UFV/CCF 331 - GRAFOS/TP1/GraphsLib/BibliotecaGrafos/BibliotecaGrafos/ConversaoJson/ExConversao/saida.txt";

        FileReader fr = new FileReader(arqEntrada);
        BufferedReader br = new BufferedReader(fr);

        String linha = br.readLine();

        String[] entradaDividida = linha.split("\\{");

        for (String linhaEntrada : entradaDividida) {

            if (linhaEntrada.contains("label") & linhaEntrada.contains("length")) {

                String[] linhaOrdemDividida = linhaEntrada.split(",");

                for (String strOrdem : linhaOrdemDividida) {
                    if (strOrdem.contains("length")) {
                        ordem = strOrdem.replace("\"length\":", "") + "\n";
                    }
                }
            }

            if (linhaEntrada.contains("from")) {

                String verticesPeso = "";

                String[] linhaVertice = linhaEntrada.split(",");

                verticesPeso += linhaVertice[0].split(":")[1] + " ";
                verticesPeso += linhaVertice[1].split(":")[1] + " ";
                verticesPeso += linhaVertice[2].split(":")[1].replace("\"", "");

                vertices.add(verticesPeso + "\n");
            }
        }

        br.close();
        fr.close();

        FileWriter fw = new FileWriter(arqSaida, false);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(this.ordem);

        for (String vertice : vertices) {
            bw.write(vertice);
        }

        bw.close();
        fw.close();
    }

}
