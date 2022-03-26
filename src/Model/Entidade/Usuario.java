package Model.Entidade;

import Model.Entidade.Exceptions.PersonagemInexistenteException;
import Model.Persistence.PersonagemBD;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Usuario {
    private final String nome;  //nome do jogador
    private final String senha; //senha do jogador
    private final PersonagemBD personagens; //banco de dados com todos os personagens

    public Usuario(String nome, String senha){
        this.nome = nome;
        this.senha = senha;
        personagens = new PersonagemBD();
    }
    
    public void criaPersonagem(Personagem personagem)  { //cria e adiciona um personagem ao jogador
        personagens.add(personagem);
    }

    @Override
    public String toString(){  //transforma em string as características básicas do jogador
        String jogador;
        jogador = "Nome: "+ nome + "\n";
        //for (Personagem personagem : personagens){personagem.showStats();}
        jogador += "É mestre?: \n";
        /*if (isMestre)jogador += "Sim\n";
        else jogador += "Não\n";*/
        jogador += "Personagens:´\n";
        for (String persoID : personagens.listId()){
            try {
                jogador += personagens.getPersonagem(persoID).getNome() + "\n";
            } catch (PersonagemInexistenteException ex) {
                Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return jogador;
    }

    //gets
    public String getNome(){return nome;}
    public String getSenha(){return senha;}
    
    public PersonagemBD getPersonagens(){return personagens;}

}
