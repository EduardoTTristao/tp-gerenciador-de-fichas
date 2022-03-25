package Model.Entidade.Exceptions;


public class CaracteristicaJaAdicionadaException extends Exception{
    public CaracteristicaJaAdicionadaException(String caracteristica) {
        super(caracteristica+" já adicionada ");
    }
}
