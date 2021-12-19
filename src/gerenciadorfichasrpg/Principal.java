package gerenciadorfichasrpg;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Principal {
    public static void main(String[] args) throws FileNotFoundException {

        ArrayList<Jogador> listaJogadores1 = new ArrayList<Jogador>();
        ArrayList<Personagem> listaPersonagens1 = new ArrayList<Personagem>();
        ArrayList<Mesa> listaMesas = new ArrayList<Mesa>();

        Mesa mesa1 = new Mesa(listaJogadores1, listaPersonagens1);

        Jogador jogador1 = new Jogador("Fulano", true);
        Jogador jogador2 = new Jogador("Ciclano", false);
        jogador1.criaPersonagem("dnd", "Pedrinho");
        jogador1.criaPersonagem("dnd", "Huguinho");
        jogador2.criaPersonagem("dnd", "Zezinho");
        jogador2.criaPersonagem("dnd", "Huguinho");

        assert false;
        listaJogadores1.add(jogador1);
        listaJogadores1.add(jogador2);

        assert false;
        listaPersonagens1.addAll(jogador1.getPersonagens());
        listaPersonagens1.addAll(jogador2.getPersonagens());

        listaMesas.add(mesa1);
        System.out.println("\nIMPRIME MESA\n");
        mesa1.imprimeMesa(listaJogadores1.get(0));
        System.out.println("\nIMPRIME PERSONAGEM\n");
        for(Personagem personagem: listaPersonagens1){System.out.println(personagem.nome);}


    }

}
