package Exceptions;

public class UsuarioNaoCadastradoException extends Exception {
    private final String nick;

    public UsuarioNaoCadastradoException(String nick) {
        super("Usuario não cadastrado: "+nick);
        this.nick = nick;
    }
    
    public String getNick(){return nick;}
    
}
