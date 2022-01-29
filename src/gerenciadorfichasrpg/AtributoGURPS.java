package gerenciadorfichasrpg;

public class AtributoGURPS {
    
    private String nome; //nome do atributo
    private int valor; //valor do atributo em questão
    
    //cria os campos de conteudo necessarios para o preenchimento daquele atributo
    public AtributoGURPS(String nome){
        this.nome = nome;
    }

    //preenche um campo especifico de conteudo
    public void setValor(int valor) {
        this.valor = valor;
    }
    
    
    //exibe os dados armazenados no atributo
    public void showAtr(){
        System.out.println("----");
        System.out.println(nome+": ["+valor+"]"+" custo: "+this.getCusto()+"\n");
        System.out.println("----");
    }
    
    public String getNome() { //retorna o nome do atributo
        return nome;
    }

    public int getValor() { //retorna o valor do atributo
        return valor;
    }
    
    public int getCusto(){ //retorna o custo do atriuto
        int custo = 0;
        switch (nome){
                case "ST":
                    custo = (valor - 10)*10;
                case "DX":
                    custo = (valor - 10)*20;
                case "HT":
                    custo = (valor - 10)*10;
                case "IQ":
                    custo = (valor - 10)*20;
                case "per":
                    custo = (valor - 10)*5;
                case "PV":
                    custo = (valor - 10)*2;
                case "vont":
                    custo = (valor - 10)*5;
                case "PF":
                    custo = (valor - 10)*3;
        }
        return custo;
    }
}
