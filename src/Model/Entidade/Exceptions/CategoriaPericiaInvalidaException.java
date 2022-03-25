package Model.Entidade.Exceptions;

public class CategoriaPericiaInvalidaException extends Exception {

    public CategoriaPericiaInvalidaException(String categoria) {
        super("Categoria invalida: "+categoria);
    }
    
}
