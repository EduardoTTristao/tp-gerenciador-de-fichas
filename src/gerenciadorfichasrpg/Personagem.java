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
        private String nome; //nome do personagem
        private String sistemaFicha; //sistema dessa ficha
        private boolean isNPC; //se o personagem e Non-player-caracter ou player-caracter
        private String donoDoPersonagem; //nome do jogador ou o nome do mestre que possui aquele personagem
        private ArrayList<AtributoDND> atributos = new ArrayList<AtributoDND>(); //os atributos daquele personagem
    
        //para inicializar um personagem de um sistema armazenado
        public Personagem (String sistema, String nome, Boolean isNPC, String donoDoPersonagem) {
            
            Scanner scan = new Scanner(System.in);
            scan.useDelimiter("\n");
            
            //atributos base
            this.nome = nome; //armazena o nome do personagem
            this.isNPC = isNPC; //armazena se o personagem nao e de jogador
            this.donoDoPersonagem = donoDoPersonagem;  //determina o nome do jogador que possui aquele personagem
            this.sistemaFicha = sistema; //determina o sistema da ficha
            
            //determina os campos de atributo a serem preenchidos dependendo do sistema
            if (sistema == "D&D") {
                atributos.add(new AtributoDND("Força"));
                atributos.add(new AtributoDND("Destreza"));
                atributos.add(new AtributoDND("Constituição"));
                atributos.add(new AtributoDND("Inteligência"));
                atributos.add(new AtributoDND("Sabedoria"));
                atributos.add(new AtributoDND("Carisma"));
            }
            if (sistema == "GURPS") {
                atributos.add(new AtributoDND("ST"));
                atributos.add(new AtributoDND("DX"));
                atributos.add(new AtributoDND("IQ"));
                atributos.add(new AtributoDND("HT"));
                
                atributos.add(new AtributoDND("per"));
                atributos.add(new AtributoDND("PV"));
                atributos.add(new AtributoDND("vont"));
                atributos.add(new AtributoDND("PF"));
            }
        }
        
        public ArrayList<String> retornaNomeAtributos(){
            ArrayList<String> nomeAtributos = new ArrayList<String>();
            for (AtributoDND atr : atributos){
                nomeAtributos.add(atr.getNome());
            }
            return nomeAtributos;
        }
        
        public void setAtr(String atrNome, int valor){
            for (AtributoDND atr : atributos){ //define o valor de um determinado atributo
                if (atr.getNome().equals(atrNome)){
                    atr.setValor(valor);
                }
            }
        }
        
        //exibe todas as caracteristicas armazenadas do personagem
        public void showStats(){
            System.out.println("Nome do personagem:" + nome); //exibe o nome do personagem
            if(!isNPC){System.out.println("Nome do jogador:" + donoDoPersonagem);} //exibe o nome do jogador
            System.out.print("Tipo: ");
            if (isNPC)System.out.println("NPC"); //verifica se e npc ou pc e retorna
            else System.out.println("PC");
            
            for (AtributoDND atr : atributos){ //exibe todos os atributos e seus respectivos campos preenchidos
                atr.showAtr();
            }
        }
}

