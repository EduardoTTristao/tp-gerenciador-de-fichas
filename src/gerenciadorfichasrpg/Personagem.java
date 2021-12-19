package gerenciadorfichasrpg;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author eduar
 */
public class Personagem {
        String nome; //nome do personagem
        boolean isNPC; //se o personagem e Non-player-caracter ou player-caracter
        String nomeDoJogador; //nome do jogador
        ArrayList<Atributo> atributos; //os atributos daquele personagem
    
        //para inicializar um personagem de um sistema armazenado
        public Personagem (String sistema, String fnome, Boolean fisNPC, String fnomeDoJogador) throws FileNotFoundException{
            
            Scanner scan = new Scanner(System.in);
            scan.useDelimiter("\n");
            
            //atributos base
            nome = fnome; //armazena o nome do personagem
            isNPC = fisNPC; //armazena se o personagem nao e de jogador
            if (!isNPC){nomeDoJogador = fnomeDoJogador;}  //determina o nome do jogador que possui aquele personagem
            
            //parte dos atributos do sistema
            File file = new File("atributos do sistema/" +sistema); //obtem o arquivo do sistema
            AtributosSistema base = new AtributosSistema(file); //pega os atributos do sistema
            atributos = base.getAtributos(); //atribui os atributos do sistema nos atributos do personagem


            for (Atributo atributo : atributos) { //determina cada atributo e seus campos
                boolean tem = true;
                if (atributo.isOpcional()) { //verifica se e opcional, se for, checa se aquele personagem tem o atributo
                    System.out.println("Seu personagem possui " + atributo.getNome().toLowerCase() + "?(s/n)");
                    if (scan.next().charAt(0) == 'n') tem = false;
                }
                if (tem) { //caso o personagem tenha o atributo, preenche os campos
                    System.out.println("---");
                    System.out.println(atributo.getNome());

                    ArrayList<Conteudo> conteudos = atributo.getConteudos();
                    for (Conteudo conteudo : conteudos) {
                        System.out.print("Digite o/a " + conteudo.getNome() + ": ");
                        atributo.setConteudo(conteudo.getNome(), scan.next()); //preenchendo os campos
                    }
                    System.out.println("---");
                }
            }
        }
        
        //exibe todas as caracteristicas armazenadas do personagem
        public void showStats(){
            System.out.println("Nome do personagem:" + nome); //exibe o nome do personagem
            if(!isNPC){System.out.println("Nome do jogador:" + nomeDoJogador);} //exibe o nome do jogador
            System.out.print("Tipo: ");
            if (isNPC)System.out.println("NPC"); //verifica se e npc ou pc e retorna
            else System.out.println("PC");
            
            //for (Atributo atr : atributos){ //exibe todos os atributos e seus respectivos campos preenchidos
                //atr.showAtr();
            //}
        }
}

