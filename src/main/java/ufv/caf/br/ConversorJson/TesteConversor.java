package ufv.caf.br.ConversorJson;

import java.io.IOException;

public class TesteConversor {
    public static void main(String[] args) throws IOException {

        Txt2Json t2j = new Txt2Json();
        t2j.converteArquivo("", "");

        Json2txt j2t = new Json2txt();
        j2t.converteArquivo("", "");
    }
}