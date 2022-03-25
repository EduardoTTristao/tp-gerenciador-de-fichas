package Model.Entidade;

public class AtributoDND extends Atributo{

    public AtributoDND(String nome) {
        super(nome);
    }
    
    //retorna como string os dados do atributo 
    @Override
    public String toString(){
        return super.getNome()+": ["+super.getValor()+"]"+" Mod: ["+this.getMod()+"]";
    }
    
    public int getMod(){ //retorna o modificador do atriuto
        return (int) Math.floor((super.getValor() - 10)/2);
    }
    
}
