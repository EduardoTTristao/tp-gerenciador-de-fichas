package Model.Entidade.Exceptions;

public class AtrInexistenteException extends Exception {
    private final String atrNome;

    public AtrInexistenteException(String atrNome) {
        super("Atributo inexistente: "+atrNome);
        this.atrNome = atrNome;
    }

    public String getAtrNome() {
        return atrNome;
    }
    
}