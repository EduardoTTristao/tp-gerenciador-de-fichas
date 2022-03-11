package Model.Entidade;

import Exceptions.AtrInexistenteException;
import Exceptions.AtributoNegativoException;
import java.util.ArrayList;
import java.util.Scanner;
import Exceptions.SistemaNaoCadastradoException;
import java.util.UUID;

public class Personagem {
        private String nome; //nome do personagem
        private final String id;
        private final String sistemaFicha; //sistema dessa ficha
        private boolean isNPC; //se o personagem e Non-player-caracter ou player-caracter
        private String donoDoPersonagem; //nome do jogador ou o nome do mestre que possui aquele personagem
        private final ArrayList<Atributo> atributos = new ArrayList(); //os atributos daquele personagem
        private String mesa;
    
        //para inicializar um personagem de um sistema armazenado
        public Personagem (String sistema, String nome, String donoDoPersonagem) throws SistemaNaoCadastradoException{
            
            Scanner scan = new Scanner(System.in);
            scan.useDelimiter("\n");
            
            //atributos base
            id = UUID.randomUUID().toString();
            this.nome = nome; //armazena o nome do personagem
            this.donoDoPersonagem = donoDoPersonagem;  //determina o nome do jogador que possui aquele personagem
            this.sistemaFicha = sistema; //determina o sistema da ficha
            this.mesa = "Sem mesa"; //inicializa sem pertencer a alguma mesa
            
            //determina os campos de atributo a serem preenchidos dependendo do sistema
            if ("D&D".equals(sistema)) {
                atributos.add(new AtributoDND("Força"));
                atributos.add(new AtributoDND("Destreza"));
                atributos.add(new AtributoDND("Constituição"));
                atributos.add(new AtributoDND("Inteligência"));
                atributos.add(new AtributoDND("Sabedoria"));
                atributos.add(new AtributoDND("Carisma"));
            }
            else{
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
                else {
                    throw new SistemaNaoCadastradoException(sistema);
                }
            }
        }
        
        public ArrayList<String> retornaNomeAtributos(){
            ArrayList<String> nomeAtributos = new ArrayList();
            for (Atributo atr : atributos){
                nomeAtributos.add(atr.getNome());
            }
            return nomeAtributos;
        }
        
        public void setAtr(String atrNome, int valor) throws AtrInexistenteException, AtributoNegativoException{
            for (Atributo atr : atributos){ //define o valor de um determinado atributo
                if (atr.getNome().equals(atrNome)){
                    atr.setValor(valor);
                    return;
                }
            }
            throw new AtrInexistenteException(atrNome);
        }
        
        //retorna como string todas as caracteristicas armazenadas do personagem
        @Override
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

    public String getSistemaFicha() {
        return sistemaFicha;
    }

    public boolean isIsNPC() {
        return isNPC;
    }

    public String getDonoDoPersonagem() {
        return donoDoPersonagem;
    }

    public String getMesa() {
        return mesa;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void setMesa(String mesa){this.mesa = mesa;}

    public void setIsNPC(boolean isNPC) {
        this.isNPC = isNPC;
    }

    public String getId() {
        return id;
    }
    
    
}

