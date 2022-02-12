
package Model;

public abstract class Atributo {
    private final String nome; //nome do atributo
    private int valor; //valor do atributo em questão
    
    //cria os campos de conteudo necessarios para o preenchimento daquele atributo
    public Atributo(String nome){
        this.nome = nome;
    }
    
    public String getNome() { //retorna o nome do atributo
        return nome;
    }

    public int getValor() { //retorna o valor do atributo
        return valor;
    }

    //preenche o valor do atributo
    public void setValor(int valor) {
        this.valor = valor;
    }
    
    public abstract String toString();
}
