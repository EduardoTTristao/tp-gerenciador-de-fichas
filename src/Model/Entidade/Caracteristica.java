
package Model.Entidade;

public class Caracteristica {
    private final String descricao;
    private final String nome;
    private final int custo;
    
    public Caracteristica(String descricao, String nome, int custo){
        this.descricao = descricao;
        this.nome = nome;
        this.custo = custo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getNome() {
        return nome;
    }

    public int getCusto() {
        return custo;
    }
}
