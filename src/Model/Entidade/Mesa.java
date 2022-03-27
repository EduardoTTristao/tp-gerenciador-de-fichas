package Model.Entidade;

import Model.Entidade.Exceptions.AcessoNegadoException;
import Model.Entidade.Exceptions.UsuarioJaCadastradoException;
import Model.Persistence.UserBD;
import java.util.ArrayList;

public class Mesa {
    private final String nome;
    private final UserBD jogadores;  //lista com os jogadores
    private final UserBD mestres;  //lista com os mestres
    private final UserBD jogadorNA; //jogadores nao aprovados
    private final UserBD mestresNA; //mestres nao aprovados
    private String sistema;

    public Mesa(Usuario mestre, String sistema, String nome){
        this.nome = nome;
        this.sistema = sistema;
        jogadores = new UserBD();
        mestres = new UserBD();
        jogadorNA = new UserBD();
        mestresNA = new UserBD();
        mestres.cadastrar(mestre);
    }
    
    //retorna se um usuario em especifico esta na mesa
    public boolean isCadastrado (String nomeUsuario){
        if (jogadores.retornaJogador(nomeUsuario) != null) {
            return jogadores.retornaJogador(nomeUsuario).getNome() != null;
        }
        else if(mestres.retornaJogador(nomeUsuario) != null){
            return mestres.retornaJogador(nomeUsuario).getNome() != null;
        }
        else{
            return false;
        }
    }
    
    //retorna um usuario da lista de jogadores nao aprovados
    public Usuario getJogNA(String nome){
        return jogadorNA.retornaJogador(nome);
    }
    
    //retorna um usuario da lista de mestres nao aprovados
    public Usuario getMesNA(String nome){
        return mestresNA.retornaJogador(nome);
    }
    
    //adiciona usuario a lista de jogadores nao aprovados
    public void addJogadorNA(Usuario user) throws UsuarioJaCadastradoException{
        if (isCadastrado(user.getNome()))
            throw new UsuarioJaCadastradoException(user);
        jogadorNA.cadastrar(user);
    }
    
    //adiciona usuario a lista de mestres nao aprovados
    public void addMestreNA(Usuario user) throws UsuarioJaCadastradoException{
        if (isCadastrado(user.getNome()))
            throw new UsuarioJaCadastradoException(user);
        mestresNA.cadastrar(user);
    }
    
    //adiciona jogador a lista de jogadores
    public void addJogador(Usuario user) throws UsuarioJaCadastradoException, AcessoNegadoException{
        boolean jogadorPediu = false;
        for (Usuario jog : jogadorNA.getCadastrados()){
            if(jog.getNome().equals(user.getNome())){
                jogadorPediu = true;
                break;
            }
        }
        if(jogadorPediu == false)
            throw new AcessoNegadoException("Não pode cadastrar um jogador a uma mesa que não pediu");
        
        if (isCadastrado(user.getNome()))
            throw new UsuarioJaCadastradoException(user);
        jogadores.cadastrar(user);
    }

    public void addMestre(Usuario user) throws UsuarioJaCadastradoException, AcessoNegadoException{
        boolean mestrePediu = false;
        for (Usuario jog : mestresNA.getCadastrados()){
            if(jog.getNome().equals(user.getNome())){
                mestrePediu = true;
                break;
            }
        }
        if(mestrePediu == false)
            throw new AcessoNegadoException("Não pode cadastrar um mestre a uma mesa que não pediu");
        if (isCadastrado(user.getNome()))
            throw new UsuarioJaCadastradoException(user);
        mestres.cadastrar(user);
    }
    
    public boolean isMestre (String nome){
        if(mestres.retornaJogador(nome) != null){
            return mestres.retornaJogador(nome).getNome() != null;
        }
        else{
            return false;
        }
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
