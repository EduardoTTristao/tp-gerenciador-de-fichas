package Model.Entidade.Exceptions;

public class PersonagemInexistenteException extends Exception {
    private final String id;

    public PersonagemInexistenteException(String id) {
        super("Id de personagem não cadastrado: "+id);
        this.id = id;
    }
    
    public String getId(){return id;}
    
}
