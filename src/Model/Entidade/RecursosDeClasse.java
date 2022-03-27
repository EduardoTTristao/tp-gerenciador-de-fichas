package Model.Entidade;

public class RecursosDeClasse {
    private final String nome;
    private final String descricao;
    public RecursosDeClasse(String nome, String descricao){
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
    
    
}
