package Model.Entidade.Exceptions;

public class MesaInexistenteException extends Exception {
    private final String mesaInexistente;
    
    public MesaInexistenteException(String nomeMesa) {
        super("Mesa inexistente: "+nomeMesa);
        mesaInexistente = nomeMesa;
    }
    
    public String getMesaInexistente(){
        return mesaInexistente;
    }
    
}
