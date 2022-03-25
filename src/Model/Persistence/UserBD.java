package Model.Persistence;

import Model.Entidade.Usuario;
import java.util.ArrayList;

public class UserBD {
    private final ArrayList<Usuario> cadastrados;
    
    public UserBD(){
        cadastrados = new ArrayList<>();
    }
    
    public void cadastrar(Usuario jogador){ cadastrados.add(jogador);}
    
    public Usuario retornaJogador(String nome){
        for (Usuario jgd : cadastrados)
            if (jgd.getNome().equals(nome)) return jgd;
        return null;
    }

    public ArrayList<Usuario> getCadastrados() {
        return cadastrados;
    }
}
