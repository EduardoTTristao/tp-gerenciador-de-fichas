package gerenciadorfichasrpg;

import java.util.ArrayList;

public class Mesa {
    private ArrayList<Jogador> jogadores;  //lista com os jogadores
    private ArrayList<Personagem> personagens;  //lista com os personagens

    public Mesa(ArrayList<Jogador> jogadores, ArrayList<Personagem> personagens){  //gerador
        this.jogadores = jogadores;
        this.personagens = personagens;
    }

    public void imprimeMesa(Jogador fjogador){  //imprime a mesa com jogadores e personagens caso o solicitante seja mestre
        if(fjogador.getMestre()){
            for (Jogador jogador : jogadores){
                jogador.imprimeJogador();
                for(Personagem personagem: jogador.getPersonagens()){
                    personagem.showStats();}}}
        else {System.out.println("Jogador não é mestre da sessão");}

    }
}
