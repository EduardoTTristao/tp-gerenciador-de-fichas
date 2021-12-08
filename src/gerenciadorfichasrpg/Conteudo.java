package gerenciadorfichasrpg;

/**
 *
 * @author eduar
 */
public class Conteudo {
    private String nome;
    private String conteudo;

    public Conteudo(String nome) { //inicializa um campo de conteudo vazio
        this.nome = nome;
    }

    public String getNome() { //retorna o que o conteudo armazena
        return nome;
    }

    public String getConteudo() { //retorna o que foi preenchido dentro desse conteudo
        return conteudo;
    }

    public void setConteudo(String conteudo) { //preenche o campo do conteudo
        this.conteudo = conteudo;
    }
    
    
}
