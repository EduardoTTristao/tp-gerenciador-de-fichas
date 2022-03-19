package Model.Entidade;

import Exceptions.SistemaNaoCadastradoException;
import java.util.ArrayList;

public class PersonagemGURPS extends Personagem{
    private int fadiga;
    private int fadigaAtual;

    //para inicializar um personagem de um sistema armazenado
    public PersonagemGURPS (String nome, String donoDoPersonagem) throws SistemaNaoCadastradoException{
        super(nome, donoDoPersonagem);
        ArrayList<Atributo> atributos = new ArrayList();

        //determina os campos de atributo a serem preenchidos dependendo do sistema      
        atributos.add(new AtributoGURPS("ST"));
        atributos.add(new AtributoGURPS("DX"));
        atributos.add(new AtributoGURPS("IQ"));
        atributos.add(new AtributoGURPS("HT"));

        atributos.add(new AtributoGURPS("per"));
        atributos.add(new AtributoGURPS("PV"));
        atributos.add(new AtributoGURPS("vont"));
        atributos.add(new AtributoGURPS("PF"));  

        super.setAtributos(atributos);
    }

    @Override
    public boolean isDead(){
        return super.getVidaAtual() < -super.getVida();
    }

    public int getFadiga() {
        return fadiga;
    }

    public int getFadigaAtual() {
        return fadigaAtual;
    }

    public void cansa() {
        this.fadiga -= 1;
    }

    public void setFadigaAtual(int fadigaAtual) {
        this.fadigaAtual = fadigaAtual;
    }
        
        
}
