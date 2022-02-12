package Model;

import java.util.ArrayList;

public class Mesa {
    private final String nome;
    private ArrayList<Jogador> jogadores;  //lista com os jogadores
    private ArrayList<Jogador> mestres;  //lista com os jogadores
    private ArrayList<Personagem> personagens;  //lista com os personagens
    private String sistema;

    public Mesa(ArrayList<Jogador> mestres, String nome){  //gerador
        this.mestres = mestres;
        this.nome = nome;
    }

    public void addJogador(Jogador jogador){
        jogadores.add(jogador);
    }

    public void addMestre(Jogador mestre){
        mestres.add(mestre);
    }

    public void addPersonagem(Personagem personagem){
        personagens.add(personagem);
    }
    
    /*
    public void imprimeMesa(Jogador fjogador){  //imprime a mesa com jogadores e personagens caso o solicitante seja mestre
        if(fjogador.isMestre()){
            for (Jogador jogador : jogadores){
                jogador.imprimeJogador();
                for(Personagem personagem: jogador.getPersonagens()){
                    personagem.showStats();}}}
        else {System.out.println("Jogador não é mestre da sessão");}

    }
    */
}
