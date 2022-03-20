package Model.Entidade.Exceptions;

public class ModPericiaInvalidaException extends Exception {

    public ModPericiaInvalidaException(int mod) {
        super("Categoria invalida: "+mod);
    }
    
}