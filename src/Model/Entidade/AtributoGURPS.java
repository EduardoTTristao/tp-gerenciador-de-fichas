package Model.Entidade;

import Exceptions.AtrInexistenteException;

public class AtributoGURPS extends Atributo{

    public AtributoGURPS(String nome) {
        super(nome);
    }
    
    //retorna como string os dados do atributo
    @Override
    public String toString() {
        return super.getNome()+": ["+super.getValor()+"]"+" custo: "+this.getCusto();
    }
    
    public int getCusto() { //retorna o custo do atriuto
        switch (super.getNome()){
                case "ST" -> {
                    return (super.getValor() - 10)*10;
            }
                case "DX" -> {
                    return (super.getValor() - 10)*20;
            }
                case "HT" -> {
                    return (super.getValor() - 10)*10;
            }
                case "IQ" -> {
                    return (super.getValor() - 10)*20;
            }
                case "per" -> {
                    return (super.getValor() - 10)*5;
            }
                case "PV" -> {
                    return (super.getValor() - 10)*2;
            }
                case "vont" -> {
                    return (super.getValor() - 10)*5;
            }
                case "PF" -> {
                    return (super.getValor() - 10)*3;
            }
        }
        return 0;
    }
}
