package Model;

import java.util.ArrayList;


public class Jogador {
    private final String nome;  //nome do jogador
    private ArrayList<Personagem> personagens= new ArrayList(); //lista com seus personagens
    //private Boolean isMestre; //true caso seja mestre e false caso contrário (a mesa que deveria ter esse controle)

    public Jogador(String fNomeJogador){ //gerador
        nome = fNomeJogador;
    }

    public void criaPersonagem(String fsistema, String fNomePersonagem, boolean isMestre)  { //cria e adiciona um personagem ao jogador
        personagens.add(new Personagem(fsistema, fNomePersonagem, isMestre, nome));
    }

    public String toString(){  //transforma em string as características básicas do jogador
        String jogador;
        jogador = "Nome: "+ nome + "\n";
        //for (Personagem personagem : personagens){personagem.showStats();}
        jogador += "É mestre?: \n";
        /*if (isMestre)jogador += "Sim\n";
        else jogador += "Não\n";*/
        jogador += "Possui " + personagens.size() + " personagens:´\n";
        for (Personagem perso : personagens){
            jogador += perso.getNome() + "\n";
        }
        return jogador;
    }

    //gets
    //public Boolean isMestre(){return isMestre;}
    public ArrayList<Personagem> getPersonagens(){return personagens;}

}
