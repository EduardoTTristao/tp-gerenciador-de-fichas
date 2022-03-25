package Model.Entidade.Exceptions;

import Model.Entidade.Usuario;

public class UsuarioJaCadastradoException extends Exception {
    private final Usuario user;

    public UsuarioJaCadastradoException(Usuario user) {
        super("Usuario já cadastrado: " + user.getNome());
        this.user = user;
    }
    
    public Usuario getUser(){return user;}
    
}
