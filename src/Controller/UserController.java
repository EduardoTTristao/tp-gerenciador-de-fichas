package Controller;

import Exceptions.PersonagemInexistenteException;
import Exceptions.SenhasDiferentesException;
import Exceptions.SenhaFracaException;
import Exceptions.UsuarioNaoCadastradoException;
import Exceptions.SistemaNaoCadastradoException;
import Exceptions.UsuarioJaCadastradoException;
import Model.Entidade.Personagem;
import Model.Entidade.Usuario;
import Model.Persistence.UserBD;
import java.util.ArrayList;

public class UserController {
    private UserBD bd;
    
    public void cadastrar(String login, String senha, String senhaRepetida) throws UsuarioJaCadastradoException, SenhaFracaException, SenhasDiferentesException{
        if (!senha.equals(senhaRepetida)) throw new SenhasDiferentesException();
        if (!validarSenha(senha)) throw new SenhaFracaException();
        if (bd.retornaJogador(login) != null) throw new UsuarioJaCadastradoException(bd.retornaJogador(login));
        bd.cadastrar(new Usuario(login, senha));
    }
    
    public Usuario autentica(String login, String senha){
        Usuario jgd = bd.retornaJogador(login);
        if (jgd != null) if (jgd.getSenha().equals(senha)) return jgd;
        return null;
    }
    
    public void criarPerso(String nick, String sistema, String nomeDoPersonagem) throws SistemaNaoCadastradoException, UsuarioNaoCadastradoException{
        Usuario user = bd.retornaJogador(nick);
        if (user == null) 
            throw new UsuarioNaoCadastradoException(nick);
        user.criaPersonagem(new Personagem(sistema,nomeDoPersonagem,user.getNome()));
    }
    
    public ArrayList<String> getIdsPersos(Usuario user){
        return user.getPersonagens().listId();
    }
    
    public Personagem getPerso(String nick, String persoId) throws UsuarioNaoCadastradoException, PersonagemInexistenteException{
        Usuario user = bd.retornaJogador(nick);
        if (user == null) 
            throw new UsuarioNaoCadastradoException(nick);
        return user.getPersonagens().getPersonagem(persoId);
    }

    private boolean validarSenha(String senha) {
        return senha.length() >= 8 && senha.contains("[a-zA-Z]+");
    }
}
