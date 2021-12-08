package gerenciadorfichasrpg;

import java.util.ArrayList;

/**
 *
 * @author eduar
 */

public class Atributo {
    private String nome; //nome do atributo
    private boolean opcional; //se aquele atributo é obrigatorio para todos os personagens
    private ArrayList<Conteudo> conteudos = new ArrayList(); //lista com os conteudos que aquele atributo possui
    
    //cria os campos de conteudo necessarios para o preenchimento daquele atributo
    public Atributo(String nome, boolean opcional, ArrayList<String> nomeConteudos){
        this.nome = nome;
        this.opcional = opcional;
        
        //colocando o nome de todos campos de conteudos
        for (int i = 0; i < nomeConteudos.size();i++){
            Conteudo conteudoAdd = new Conteudo(nomeConteudos.get(i));
            conteudos.add(conteudoAdd);
        }
    }

    //preenche um campo especifico de conteudo
    public void setConteudo(String nome, String conteudo) {
        for (int i = 0; i < conteudos.size();i++){
            if (conteudos.get(i).getNome().equals(nome)){ //procurando o campo desejado
            
                conteudos.get(i).setConteudo(conteudo); //preenchendo o campo
                
            }
        }
    }
    
    
    //exibe os dados armazenados no atributo
    public void showAtr(){
        System.out.println("----");
        System.out.println(nome+"\n");
        for (Conteudo c : conteudos){
            System.out.print(c.getNome()+": ");
            if (c.getConteudo() == null) System.out.println("-");
            else System.out.println(c.getConteudo());
        }
        System.out.println("----");
    }

    public String getNome() { //retorna o nome do atributo
        return nome;
    }

    public boolean isOpcional() { //retorna se o atributo é opcional
        return opcional;
    }

    public ArrayList<Conteudo> getConteudos() { //retorna uma lista com todos os conteudos
        return conteudos;
    }
    
    
}
