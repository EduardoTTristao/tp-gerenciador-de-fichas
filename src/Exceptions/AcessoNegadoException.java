package Exceptions;

public class AcessoNegadoException extends Exception {

    public AcessoNegadoException(String message) {
        super("Acesso negado!\n"+message);
    }
    
}
