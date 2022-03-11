package Exceptions;

public class SistemaNaoCadastradoException extends Exception{
    private final String sistemaPedido;
    
    public SistemaNaoCadastradoException(String sistema) {
        super("Sistema não cadastrado: " + sistema);
        sistemaPedido = sistema;
    }
    
    public String getSisPedido(){return sistemaPedido;}
    
}
