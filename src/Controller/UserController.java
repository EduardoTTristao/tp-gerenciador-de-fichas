package Controller;

import Model.Entidade.Exceptions.PersonagemInexistenteException;
import Model.Entidade.Exceptions.SenhasDiferentesException;
import Model.Entidade.Exceptions.SenhaFracaException;
import Model.Entidade.Exceptions.UsuarioNaoCadastradoException;
import Model.Entidade.Exceptions.SistemaNaoCadastradoException;
import Model.Entidade.Exceptions.UsuarioJaCadastradoException;
import Model.Entidade.Personagem;
import Model.Entidade.PersonagemDND;
import Model.Entidade.PersonagemGURPS;
import Model.Entidade.Usuario;
import Model.Persistence.UserBD;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class UserController {
    private final UserBD bd;
    
    public UserController(){
        bd = new UserBD();
    }
    
    // cadastra um usuario
    public void cadastrar(String login, String senha, String senhaRepetida) throws UsuarioJaCadastradoException, SenhaFracaException, SenhasDiferentesException{
        if (!senha.equals(senhaRepetida)) throw new SenhasDiferentesException();
        validarSenha(senha);
        if (bd.retornaJogador(login) != null) throw new UsuarioJaCadastradoException(bd.retornaJogador(login));
        bd.cadastrar(new Usuario(login, senha));
    }
    
    // autentica um usuario
    public Usuario autentica(String login, String senha){
        Usuario jgd = bd.retornaJogador(login);
        if (jgd != null) if (jgd.getSenha().equals(senha)) return jgd;
        return null;
    }
    
    // cria um personagem pro usuario
    public void criarPerso(String nick, String sistema, String nomeDoPersonagem) throws SistemaNaoCadastradoException, UsuarioNaoCadastradoException{
        Usuario user = bd.retornaJogador(nick);
        if (user == null) 
            throw new UsuarioNaoCadastradoException(nick);
        if ("DND".equals(sistema))
            user.criaPersonagem(new PersonagemDND(nomeDoPersonagem,user.getNome(),1));
        else{
            if ("GURPS".equals(sistema))
                user.criaPersonagem(new PersonagemGURPS(nomeDoPersonagem,user.getNome()));
            else
                throw new SistemaNaoCadastradoException(sistema);
        }
    }
    
    // retorna os ids dos seus personagens
    public ArrayList<String> getIdsPersos(Usuario user){
        return user.getIDSPersos();
    }
    
    //retorna um personagem especifico
    public Personagem getPerso(String nick, String persoId) throws UsuarioNaoCadastradoException, PersonagemInexistenteException{
        Usuario user = bd.retornaJogador(nick);
        if (user == null) 
            throw new UsuarioNaoCadastradoException(nick);
        return user.getPersonagem(persoId);
    }

    //valida a senha
    private void validarSenha(String senha) throws SenhaFracaException {
        if (senha.length() < 8)
            throw new SenhaFracaException("Senha muito curta");
        if (!Pattern.compile(".*[a-zA-Z].*").matcher(senha).matches())
            throw new SenhaFracaException("Senha precisa conter letras");
    }
}
