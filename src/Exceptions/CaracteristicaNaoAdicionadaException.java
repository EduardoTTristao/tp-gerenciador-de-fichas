
package Exceptions;

public class CaracteristicaNaoAdicionadaException extends Exception{
    public CaracteristicaNaoAdicionadaException(String campo) {
        super("Caracteristica não adicionada às "+campo);
    }
}
