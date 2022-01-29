package gerenciadorfichasrpg;

import java.io.FileNotFoundException;
import java.util.ArrayList;


public class Jogador {
    private final String nome;  //nome do jogador
    private ArrayList<Personagem> personagens= new ArrayList(); //lista com seus personagens
    private Boolean isMestre; //true caso seja mestre e false caso contrário

    public Jogador(String fNomeJogador, Boolean fisMestre){ //gerador
        isMestre = fisMestre;
        nome = fNomeJogador;
    }

    public void criaPersonagem(String fsistema, String fNomePersonagem)  { //cria e adiciona um eprsonagem ao jogador
        personagens.add(new Personagem(fsistema, fNomePersonagem, isMestre, nome));
    }

    public void imprimeJogador(){  //imprime as características básicas do jogador
        System.out.println("Nome: "+ nome);
        //for (Personagem personagem : personagens){personagem.showStats();}
        System.out.print("É mestre?: ");
        if (isMestre)System.out.println("Sim");
        else System.out.println("Não");}

    //gets
    public Boolean isMestre(){return isMestre;}
    public ArrayList<Personagem> getPersonagens(){return personagens;}

}
