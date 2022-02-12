package Model;

public class AtributoGURPS extends Atributo{

    public AtributoGURPS(String nome) {
        super(nome);
    }
    
    //retorna como string os dados do atributo
    @Override
    public String toString(){
        return super.getNome()+": ["+super.getValor()+"]"+" custo: "+this.getCusto();
    }
    
    public int getCusto(){ //retorna o custo do atriuto
        int custo = 0;
        switch (super.getNome()){
                case "ST":
                    custo = (super.getValor() - 10)*10;
                case "DX":
                    custo = (super.getValor() - 10)*20;
                case "HT":
                    custo = (super.getValor() - 10)*10;
                case "IQ":
                    custo = (super.getValor() - 10)*20;
                case "per":
                    custo = (super.getValor() - 10)*5;
                case "PV":
                    custo = (super.getValor() - 10)*2;
                case "vont":
                    custo = (super.getValor() - 10)*5;
                case "PF":
                    custo = (super.getValor() - 10)*3;
        }
        return custo;
    }
}
