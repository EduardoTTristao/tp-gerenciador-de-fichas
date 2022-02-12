package Model;

import Model.AtributoDND;
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
        private final ArrayList<Atributo> atributos = new ArrayList(); //os atributos daquele personagem
    
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
            if ("D&D".equals(sistema)) {
                atributos.add(new AtributoDND("Força"));
                atributos.add(new AtributoDND("Destreza"));
                atributos.add(new AtributoDND("Constituição"));
                atributos.add(new AtributoDND("Inteligência"));
                atributos.add(new AtributoDND("Sabedoria"));
                atributos.add(new AtributoDND("Carisma"));
            }
            if ("GURPS".equals(sistema)) {
                atributos.add(new AtributoGURPS("ST"));
                atributos.add(new AtributoGURPS("DX"));
                atributos.add(new AtributoGURPS("IQ"));
                atributos.add(new AtributoGURPS("HT"));
                
                atributos.add(new AtributoGURPS("per"));
                atributos.add(new AtributoGURPS("PV"));
                atributos.add(new AtributoGURPS("vont"));
                atributos.add(new AtributoGURPS("PF"));
            }
        }
        
        public ArrayList<String> retornaNomeAtributos(){
            ArrayList<String> nomeAtributos = new ArrayList();
            for (Atributo atr : atributos){
                nomeAtributos.add(atr.getNome());
            }
            return nomeAtributos;
        }
        
        public void setAtr(String atrNome, int valor){
            for (Atributo atr : atributos){ //define o valor de um determinado atributo
                if (atr.getNome().equals(atrNome)){
                    atr.setValor(valor);
                }
            }
        }
        
        //retorna como string todas as caracteristicas armazenadas do personagem
        public String toString(){
            String ficha;
            ficha = "Nome do personagem:" + nome + "\n"; // o nome do personagem
            if(!isNPC){ficha += "Nome do jogador:" + donoDoPersonagem + "\n";} // o nome do jogador
            ficha += ficha + "Tipo: ";
            if (isNPC)ficha += "NPC\n"; //verifica se e npc ou pc e depois preenche
            else ficha += "PC\n";
            
            for (Atributo atr : atributos){ // todos os atributos e seus respectivos campos preenchidos
                ficha += "\n" + atr.toString() + "\n";
            }
            return ficha;
        }

    public String getNome() {
        return nome;
    }
}

