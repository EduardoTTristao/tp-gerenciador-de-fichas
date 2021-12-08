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
        public Personagem (String sistema) throws FileNotFoundException{
            
            Scanner scan = new Scanner(System.in);
            scan.useDelimiter("\n");
            
            //atributos base
            System.out.print("Nome do personagem: ");
            nome = scan.next(); //armazena o nome do personagem
            System.out.print("é NPC?(s/n) ");
            isNPC = "s".equals(scan.next()); //armazena se o personagem nao e de jogador
            if (!isNPC){
                System.out.print("Nome do jogador: ");
                nomeDoJogador = scan.next(); //determina o nome do jogador que possui aquele personagem
            }
            
            //parte dos atributos do sistema
            File file = new File("atributos do sistema/"+sistema); //obtem o arquivo do sistema
            AtributosSistema base = new AtributosSistema(file); //pega os atributos do sistema
            atributos = base.getAtributos(); //atribui os atributos do sistema nos atributos do personagem
            
            
            for (int i = 0; i < atributos.size();i++){ //determina cada atributo e seus campos
                boolean tem = true;
                if (atributos.get(i).isOpcional()){ //verifica se e opcional, se for, checa se aquele personagem tem o atributo
                    System.out.println("Seu personagem possui "+atributos.get(i).getNome().toLowerCase()+"?(s/n)");
                    if (scan.next().charAt(0) == 'n') tem = false;
                }
                if (tem){ //caso o personagem tenha o atributo, preenche os campos
                    System.out.println("---");
                    System.out.println(atributos.get(i).getNome());
                    
                    ArrayList<Conteudo> conteudos = atributos.get(i).getConteudos();
                    for (int j = 0; j < conteudos.size();j++){
                        System.out.print("Digite o/a "+conteudos.get(j).getNome()+": ");
                        atributos.get(i).setConteudo(conteudos.get(j).getNome(),scan.next()); //preenchendo os campos
                    }
                    System.out.println("---");
                }
            }
        }
        
        //exibe todas as caracteristicas armazenadas do personagem
        public void showStats(){
            System.out.println("Nome do personagem:" + nome); //exibe o nome do personagem
            System.out.println("Nome do jogador:" + nomeDoJogador); //exibe o nome do jogador
            System.out.print("Tipo: ");
            if (isNPC)System.out.println("NPC"); //verifica se e npc ou pc e retorna
            else System.out.println("PC");
            
            for (Atributo atr : atributos){ //exibe todos os atributos e seus respectivos campos preenchidos
                atr.showAtr();
            }
        }
}
