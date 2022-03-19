package Model.Entidade;

import Exceptions.SistemaNaoCadastradoException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class PersonagemDND extends Personagem{

    //para inicializar um personagem de um sistema armazenado
    public PersonagemDND (String nome, String donoDoPersonagem) throws SistemaNaoCadastradoException{
        super(nome, donoDoPersonagem);
        ArrayList<Atributo> atributos = new ArrayList();

        //determina os campos de atributo a serem preenchidos dependendo do sistema        
        atributos.add(new AtributoDND("Força"));
        atributos.add(new AtributoDND("Destreza"));
        atributos.add(new AtributoDND("Constituição"));
        atributos.add(new AtributoDND("Inteligência"));
        atributos.add(new AtributoDND("Sabedoria"));
        atributos.add(new AtributoDND("Carisma"));

        super.setAtributos(atributos);

    }

    @Override
    public boolean isDead(){
        return super.getVidaAtual() <= 0;
    }
}
