package Model.Entidade;

import Model.Entidade.Exceptions.SistemaNaoCadastradoException;
import java.util.ArrayList;

public class PersonagemDND extends Personagem{
    private String raca;
    private String classe;
    private int nivel;
    private final ArrayList<RecursosDeClasse> recursos;

    //para inicializar um personagem de um sistema armazenado
    public PersonagemDND (String nome, String donoDoPersonagem, int nivel) throws SistemaNaoCadastradoException{
        super(nome, donoDoPersonagem);
        ArrayList<Atributo> atributos = new ArrayList();

        //determina os campos de atributo a serem preenchidos dependendo do sistema        
        atributos.add(new AtributoDND("Força"));
        atributos.add(new AtributoDND("Destreza"));
        atributos.add(new AtributoDND("Constituição"));
        atributos.add(new AtributoDND("Inteligência"));
        atributos.add(new AtributoDND("Sabedoria"));
        atributos.add(new AtributoDND("Carisma"));

        super.setAtributos(atributos);
        
        recursos = new ArrayList<>();

        this.nivel = nivel;
    }
    
    public void addRecurso(String nome, String descricao){
        recursos.add(new RecursosDeClasse(nome, descricao));
    }
    
    public ArrayList<RecursosDeClasse> listRecur(){
        ArrayList<RecursosDeClasse> r = new ArrayList();
        for (RecursosDeClasse rec : recursos){
            r.add(rec);
        }
        return r;
    }

    public String getRaca() {
        return raca;
    }

    public String getClasse() {
        return classe;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
    
    @Override
    public boolean isDead(){
        return super.getVidaAtual() <= 0;
    }
}
