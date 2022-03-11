package Model.Entidade;

import Exceptions.UsuarioJaCadastradoException;
import Model.Persistence.UserBD;
import java.util.ArrayList;

public class Mesa {
    private final String nome;
    private final UserBD jogadores;  //lista com os jogadores
    private final UserBD mestres;  //lista com os mestres
    private String sistema;

    public Mesa(Usuario mestre, String sistema, String nome){
        this.nome = nome;
        this.sistema = sistema;
        jogadores = new UserBD();
        mestres = new UserBD();
        mestres.cadastrar(mestre);
    }
    
    public boolean isCadastrado (String nomeUsuario){
        if (jogadores.retornaJogador(nomeUsuario) != null)
            return true;
        return mestres.retornaJogador(nomeUsuario) != null;
    }
    
    public void addJogador(Usuario user) throws UsuarioJaCadastradoException{
        if (isCadastrado(user.getNome()))
            throw new UsuarioJaCadastradoException(user);
        jogadores.cadastrar(user);
    }

    public void addMestre(Usuario user) throws UsuarioJaCadastradoException{
        if (isCadastrado(user.getNome()))
            throw new UsuarioJaCadastradoException(user);
        mestres.cadastrar(user);
    }
    
    public boolean isMestre (String nome){
        return mestres.retornaJogador(nome) != null;
    }

    public String getNome() {
        return nome;
    }

    public ArrayList<Usuario> getJogadores() {
        return jogadores.getCadastrados();
    }

    public ArrayList<Usuario> getMestres() {
        return mestres.getCadastrados();
    }

    public void setSistema(String sistema) {
        this.sistema = sistema;
    }

    public String getSistema() {
        return sistema;
    }
}
