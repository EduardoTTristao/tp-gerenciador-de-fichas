package Model.Entidade;

import Model.Entidade.Exceptions.AtrInexistenteException;
import Model.Entidade.Exceptions.AtributoNegativoException;
import java.util.ArrayList;
import Model.Entidade.Exceptions.SistemaNaoCadastradoException;
import java.util.UUID;

public abstract class Personagem {
    private String nome; //nome do personagem
    private final String id;
    private boolean isNPC; //se o personagem e Non-player-caracter ou player-caracter
    private String donoDoPersonagem; //nome do jogador ou o nome do mestre que possui aquele personagem
    private ArrayList<Atributo> atributos = new ArrayList(); //os atributos daquele personagem
    private String mesa;
    private int vida;
    private int vidaAtual = 0;

    //para inicializar um personagem de um sistema armazenado
    public Personagem (String nome, String donoDoPersonagem) throws SistemaNaoCadastradoException{

        //atributos base
        id = UUID.randomUUID().toString(); //id unico
        this.nome = nome; //armazena o nome do personagem
        this.donoDoPersonagem = donoDoPersonagem;  //determina o nome do jogador que possui aquele personagem
        this.mesa = "Sem mesa"; //inicializa sem pertencer a alguma mesa
    }

    public void setAtributos(ArrayList<Atributo> atributos){
        this.atributos = atributos;
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

    public void setDono(String donoDoPersonagem) {
        this.donoDoPersonagem = donoDoPersonagem;
    }

    public int getVida() {
        return vida;
    }

    public int getVidaAtual() {
        return vidaAtual;
    }

    public void machuca(int dano) { //decrementa na vida um dano especificado
        this.vidaAtual -= dano;
    }

    public void cura(int cura) { //aumenta na vida uma cura especificada
        this.vidaAtual += cura;
    }

    public void setVida(int vida) {
        this.vidaAtual += vida-this.vida;
        this.vida = vida;
    }

    public ArrayList<Atributo> getAtributos() {
        return atributos;
    }
    
    public abstract boolean isDead();
}

