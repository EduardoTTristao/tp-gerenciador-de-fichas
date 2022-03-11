package Model.Entidade;

import Exceptions.AcessoNegadoException;
import Exceptions.UsuarioJaCadastradoException;
import Model.Persistence.PersonagemBD;
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
    
    public boolean isCadastrado (String nomeUsuario){
        if (jogadores.retornaJogador(nomeUsuario) != null)
            return true;
        return mestres.retornaJogador(nomeUsuario) != null;
    }
    
    public Usuario getJogNA(String nome){
        return jogadorNA.retornaJogador(nome);
    }
    
    public Usuario getMesNA(String nome){
        return mestresNA.retornaJogador(nome);
    }
    
    public void addJogadorNA(Usuario user) throws UsuarioJaCadastradoException{
        if (isCadastrado(user.getNome()))
            throw new UsuarioJaCadastradoException(user);
        jogadorNA.cadastrar(user);
    }
    
    public void addMestreNA(Usuario user) throws UsuarioJaCadastradoException{
        if (isCadastrado(user.getNome()))
            throw new UsuarioJaCadastradoException(user);
        mestresNA.cadastrar(user);
    }
    
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
