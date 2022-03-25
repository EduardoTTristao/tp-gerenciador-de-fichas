package Model.Entidade.Exceptions;

public class CustoIncompativelException extends Exception{
    public CustoIncompativelException(String campo) {
        super("Custo inválido para "+campo);
    }
}
