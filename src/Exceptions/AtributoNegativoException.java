package Exceptions;

public class AtributoNegativoException extends Exception {
    private final int valor;
    
    public AtributoNegativoException(int valor) {
        super("Atributo de valor negativo: "+valor);
        this.valor = valor;
    }
    
    public int getValor(){return valor;}
    
}
