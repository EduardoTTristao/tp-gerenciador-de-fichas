package Model.Entidade;

import Model.Entidade.Exceptions.CaracteristicaJaAdicionadaException;
import Model.Entidade.Exceptions.CaracteristicaNaoAdicionadaException;
import Model.Entidade.Exceptions.CategoriaPericiaInvalidaException;
import Model.Entidade.Exceptions.CustoIncompativelException;
import Model.Entidade.Exceptions.ModPericiaInvalidaException;
import Model.Entidade.Exceptions.SistemaNaoCadastradoException;
import java.util.ArrayList;

public class PersonagemGURPS extends Personagem{
    private int fadiga;
    private int fadigaAtual;
    private final ArrayList<Caracteristica> desvantagens;
    private final ArrayList<Caracteristica> vantagens;
    private final ArrayList<Pericia> pericias;

    //para inicializar um personagem de um sistema armazenado
    public PersonagemGURPS (String nome, String donoDoPersonagem) throws SistemaNaoCadastradoException{
        super(nome, donoDoPersonagem);
        ArrayList<Atributo> atributos = new ArrayList();

        //determina os campos de atributo a serem preenchidos dependendo do sistema      
        atributos.add(new AtributoGURPS("ST"));
        atributos.add(new AtributoGURPS("DX"));
        atributos.add(new AtributoGURPS("IQ"));
        atributos.add(new AtributoGURPS("HT"));

        atributos.add(new AtributoGURPS("per"));
        atributos.add(new AtributoGURPS("PV"));
        atributos.add(new AtributoGURPS("vont"));
        atributos.add(new AtributoGURPS("PF"));  

        super.setAtributos(atributos);
        
        desvantagens = new ArrayList<>();
        vantagens = new ArrayList<>();
        pericias = new ArrayList<>();
    }

    @Override
    public boolean isDead(){
        return super.getVidaAtual() < -super.getVida();
    }

    public int getFadiga() {
        return fadiga;
    }

    public int getFadigaAtual() {
        return fadigaAtual;
    }

    public void cansa() {
        this.fadiga -= 1;
    }

    public ArrayList<Caracteristica> getDesvantagens() {
        return desvantagens;
    }

    public ArrayList<Caracteristica> getVantagens() {
        return vantagens;
    }
    
    public void addDesvantagem(String nome, String descricao, int custo) throws CaracteristicaJaAdicionadaException, CustoIncompativelException{
        Caracteristica ex = null;
        for (Caracteristica d : desvantagens){
            if (d.getNome().equals(nome)){
                ex = d;
                break;
            }
        }
        if (ex != null)
            throw new CaracteristicaJaAdicionadaException(nome);
        if (custo > 0)
            throw new CustoIncompativelException("Desvantagem");
        desvantagens.add(new Caracteristica(descricao, nome, custo));
    }
    
    public void retDesvantagem(String nome) throws CaracteristicaNaoAdicionadaException{
        Caracteristica ex = null;
        for (Caracteristica d : desvantagens){
            if (d.getNome().equals(nome)){
                ex = d;
                break;
            }
        }
        if (ex != null)
            desvantagens.remove(ex);
        else
            throw new CaracteristicaNaoAdicionadaException("Desvantagens");
    }
    
    public void addVantagem(String nome, String descricao, int custo) throws CaracteristicaJaAdicionadaException, CustoIncompativelException{
        Caracteristica ex = null;
        for (Caracteristica d : vantagens){
            if (d.getNome().equals(nome)){
                ex = d;
                break;
            }
        }
        if (ex != null)
            throw new CaracteristicaJaAdicionadaException(nome);
        if (custo < 0)
            throw new CustoIncompativelException("Vantagem");
        vantagens.add(new Caracteristica(descricao, nome, custo));
    }
    
    public void retVantagem(String nome) throws CaracteristicaNaoAdicionadaException{
        Caracteristica ex = null;
        for (Caracteristica d : vantagens){
            if (d.getNome().equals(nome)){
                ex = d;
                break;
            }
        }
        if (ex != null)
            vantagens.remove(ex);
        else
            throw new CaracteristicaNaoAdicionadaException("Vantagens");
    }

    public void setFadigaAtual(int fadigaAtual) {
        this.fadigaAtual = fadigaAtual;
    }
    
    public void addPericia(String nome, String categoria, int modificador) throws CaracteristicaJaAdicionadaException, CategoriaPericiaInvalidaException, ModPericiaInvalidaException {
        Pericia ex = null;
        for (Pericia d : pericias){
            if (d.getNome().equals(nome)){
                ex = d;
                break;
            }
        }
        if (ex != null)
            throw new CaracteristicaJaAdicionadaException(nome);
        pericias.add(new Pericia(nome, modificador, categoria));
    }
    
    public void retPericia(String nome) throws CaracteristicaNaoAdicionadaException{
        Pericia ex = null;
        for (Pericia d : pericias){
            if (d.getNome().equals(nome)){
                ex = d;
                break;
            }
        }
        if (ex != null)
            pericias.remove(ex);
        else
            throw new CaracteristicaNaoAdicionadaException("Pericias");
    }
    
    public int getCustoTotal() throws CategoriaPericiaInvalidaException{
        int custo = 0;
        
        //somando vantagens
        for (Caracteristica v : vantagens)
            custo += v.getCusto();
        //somand desvantagens
        for (Caracteristica d : desvantagens)
            custo += d.getCusto();
        //somando pericias
        for (Pericia p : pericias)
            custo += p.getCusto();
        //somando atributos
        for (Atributo a : super.getAtributos())
            custo += ((AtributoGURPS)a).getCusto();
        return custo;
    }
        
}
