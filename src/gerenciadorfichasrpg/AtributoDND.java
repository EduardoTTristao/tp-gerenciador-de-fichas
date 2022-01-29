package gerenciadorfichasrpg;

import java.util.ArrayList;

/**
 *
 * @author eduar
 */

public class AtributoDND {
    private String nome; //nome do atributo
    private int valor; //valor do atributo em questão
    
    //cria os campos de conteudo necessarios para o preenchimento daquele atributo
    public AtributoDND(String nome){
        this.nome = nome;
    }

    //preenche um campo especifico de conteudo
    public void setValor(int valor) {
        this.valor = valor;
    }
    
    
    //exibe os dados armazenados no atributo
    public void showAtr(){
        System.out.println("----");
        System.out.println(nome+": ["+valor+"]"+" Mod: ["+this.getMod()+"]"+"\n");
        System.out.println("----");
    }
    
    public String getNome() { //retorna o nome do atributo
        return nome;
    }

    public int getValor() { //retorna o valor do atributo
        return valor;
    }
    
    public int getMod(){ //retorna o modificador do atriuto
        return (int) Math.floor((valor - 10)/2);
    }
    
}
