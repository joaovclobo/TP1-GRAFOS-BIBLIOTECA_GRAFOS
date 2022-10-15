package ufv.caf.br.ConversorJson;

import java.io.*;

public class Txt2Json {
    private String textoPre = "{\"data\":{\"nodes\":{\"_subscribers\":{\"*\":[],\"add\":[{}],\"remove\":[{}],\"update\":[{}]},\"_options\":{},\"_data\":{";
    private String textoEntre = ",\"_idProp\":\"id\",\"_type\":{}},\"edges\":{\"_subscribers\":{\"*\":[],\"add\":[{}],\"remove\":[{}],\"update\":[{}]},\"_options\":{},\"_data\":{";
    private String textoPos = "\"_idProp\":\"id\",\"_type\":{}}},\"options\":{\"locale\":\"pt-br\",\"manipulation\":{\"enabled\":false},\"edges\":{\"font\":{\"color\":\"#ffffff\",\"strokeWidth\":0,\"size\":18}},\"nodes\":{\"color\":{\"border\":\"#698B69\",\"background\":\"#458B74\",\"highlight\":{\"border\":\"#698B69\",\"background\":\"#4f6e4f\"}},\"font\":{\"color\":\"white\"}},\"physics\":{\"enabled\":true,\"forceAtlas2Based\":{\"gravitationalConstant\":-50,\"centralGravity\":0.01,\"springConstant\":0.02,\"springLength\":100,\"damping\":0.4,\"avoidOverlap\":0},\"maxVelocity\":50,\"minVelocity\":0.1,\"solver\":\"forceAtlas2Based\",\"stabilization\":{\"enabled\":true,\"iterations\":1000,\"updateInterval\":100,\"onlyDynamicEdges\":false,\"fit\":true},\"timestep\":0.5,\"adaptiveTimestep\":true}},\"ponderado\":true,\"ordenado\":false}";
    private String textoVertices = "";
    private String textoArestas = "";

    private String textoVertice(int indVertice1){
        return "\""+ indVertice1 + "\":{\"id\":" + indVertice1 + ",\"label\":\"" + indVertice1 + "\"}";
    }

    private void textoVertices(int ordem){
        for (int i = 0; i < ordem; i++) {
            this.textoVertices += textoVertice(i+1);
            if (i != ordem - 1){
                this.textoVertices += ",";
            }
        }
        this.textoVertices += "},\"length\": " + ordem;
    }

    private void textoAresta(int indVertice1, int indVertice2, double peso, int idAresta){
        this.textoArestas += "\""+ idAresta + "\":{\"from\":" + indVertice1 + ",\"to\":" + indVertice2 + ",\"label\":\"" + peso +"\",\"id\":\"" + idAresta + "\",\"color\":{}}";
    }

    public void converteArquivo(String arqEntrada, String arqSaida) throws IOException {

        arqEntrada = "/home/joao/Documents/UFV/CCF 331 - GRAFOS/TP1/GraphsLib/BibliotecaGrafos/BibliotecaGrafos/ConversaoJson/ExConversao/extxt.txt";
        arqSaida = "/home/joao/Documents/UFV/CCF 331 - GRAFOS/TP1/GraphsLib/BibliotecaGrafos/BibliotecaGrafos/ConversaoJson/ExConversao/saida.json";

        FileReader fr = new FileReader(arqEntrada);
        BufferedReader br = new BufferedReader(fr);

        String linha = br.readLine();;
        int numLinha = 0;

        while(linha != null){

                if (numLinha == 0){
                    textoVertices(Integer.parseInt(linha));

                } else {
                    String[] linhaDividida = linha.split(" ");
                    textoAresta(Integer.parseInt(linhaDividida[0]), Integer.parseInt(linhaDividida[1]), Double.parseDouble(linhaDividida[2]), numLinha);
                }
                linha = br.readLine();

                if (linha == null) {

                    this.textoArestas += "},\"length\": " + numLinha + ",";

                } else if (numLinha != 0){
                    this.textoArestas += ",";

                }

                numLinha++;
            }

        br.close();
        fr.close();

        FileWriter fw = new FileWriter(arqSaida,false);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(textoPre + textoVertices + textoEntre + textoArestas + textoPos);
        bw.close();
        fw.close();
    }

}
