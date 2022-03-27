package Controller;

import Model.Entidade.Exceptions.AcessoNegadoException;
import Model.Entidade.Usuario;
import Model.Entidade.Mesa;
import Model.Persistence.MesasBD;
import Model.Entidade.Exceptions.MesaInexistenteException;
import Model.Entidade.Exceptions.SistemaNaoCadastradoException;
import Model.Entidade.Exceptions.MesaCadastradaException;
import Model.Entidade.Exceptions.PersonagemInexistenteException;
import Model.Entidade.Exceptions.UsuarioJaCadastradoException;
import Model.Entidade.Personagem;
import Model.Persistence.PersonagemBD;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MesasController {
    private final MesasBD bd;
    
    public MesasController(){
        bd = new MesasBD();
    }
    
    //cadastra a mesa
    public void cadMesa (String nomeMesa, Usuario mestreInicial, String sistema) throws MesaCadastradaException, SistemaNaoCadastradoException{
        if (!"DND".equals(sistema) && !"GURPS".equals(sistema)) 
            throw new SistemaNaoCadastradoException(sistema);
        if (bd.retornaMesa(nomeMesa) != null) 
            throw new MesaCadastradaException();
        bd.regMesa(new Mesa(mestreInicial, sistema, nomeMesa));
    }
    
    //retorna se algum usuario em especifico e mestre em uma mesa
    public boolean isMestre (String nomeMesa, String nomeMestre) throws MesaInexistenteException{
        Mesa mesa = bd.retornaMesa(nomeMesa);
        if (mesa == null)
            throw  new MesaInexistenteException(nomeMesa);
        return mesa.isMestre(nomeMestre);
    }
    
    //retorna o nome de todas as mesas
    public ArrayList<String> getMesas(){
        return bd.getNomes();
    }
    
    //adiciona um jogador a lista de mestres caso o Usuario seja mestre
    public void addJogador(String nome, String nomeMesa, Usuario Mestre) throws MesaInexistenteException, AcessoNegadoException, UsuarioJaCadastradoException{
        if (!isMestre(nomeMesa,Mestre.getNome()))
            throw new AcessoNegadoException("Não pode cadastrar jogadores sem ser mestre");
        Mesa mesa = bd.retornaMesa(nomeMesa);
        
        mesa.addJogador(mesa.getJogNA(nome));
    }
    
    //adiciona um mestre a lista de mestres caso o Usuario seja mestre
    public void addMestre(String mestreADD, String nomeMesa, Usuario Mestre) throws MesaInexistenteException, AcessoNegadoException, UsuarioJaCadastradoException{
        if (isMestre(nomeMesa,Mestre.getNome()))
            throw new AcessoNegadoException("Não pode cadastrar mestres sem ser mestre");
        Mesa mesa = bd.retornaMesa(nomeMesa);
        
        mesa.addJogador(mesa.getMesNA(mestreADD));
    }
    
    //adiciona um jogador a lista de jogadores nao aprovados
    public void addJogadorNA(Usuario jogador, String nomeMesa) throws UsuarioJaCadastradoException, MesaInexistenteException{
        Mesa mesa = bd.retornaMesa(nomeMesa);
        if (mesa == null)
            throw  new MesaInexistenteException(nomeMesa);
        mesa.addJogadorNA(jogador);
    }
    
    //adiciona um mestre a lista de mestres nao aprovados
    public void addMestreNA(Usuario mestre, String nomeMesa) throws UsuarioJaCadastradoException, MesaInexistenteException{
        Mesa mesa = bd.retornaMesa(nomeMesa);
        if (mesa == null)
            throw  new MesaInexistenteException(nomeMesa);
        mesa.addMestreNA(mestre);
    }
    
    //adiciona um Personagem a mesa caso o usuario que esta tentando faca parte da mesa
    public void addCharacter(Personagem personagem, Usuario user, String nomeMesa) throws MesaInexistenteException, AcessoNegadoException{
        Mesa mesa = bd.retornaMesa(nomeMesa);
        if (mesa == null)
            throw  new MesaInexistenteException(nomeMesa);
        
        if(mesa.isMestre(user.getNome())){
            personagem.setMesa(nomeMesa);
            personagem.setIsNPC(true);
            return;
        }
        if(mesa.isCadastrado(user.getNome())){
            personagem.setMesa(nomeMesa);
            personagem.setIsNPC(false);
            return;
        }
        throw new AcessoNegadoException("Usuario não faz parte da mesa");
    }
    
    // passado o mestre que esta tentando ter acesso e o nome da mesa e retorna a lista de PCS daquela mesa
    // caso ele nao seja mestre, nao retorna
    public ArrayList<Personagem> getPCS(String mesaNome, Usuario mestre) throws AcessoNegadoException{
        Mesa mesa = bd.retornaMesa(mesaNome);
        if(!mesa.isMestre(mestre.getNome())){
            throw new AcessoNegadoException("Não possui acesso aos pcs da mesa caso não seja mestre");
        }
        
        ArrayList<Personagem> personagens = new ArrayList<>();
        
        for (Usuario jgd : mesa.getJogadores()){
            for (String pcid : jgd.getIDSPersos()){
                try {
                    personagens.add(jgd.getPersonagem(pcid));
                } catch (PersonagemInexistenteException ex) {
                    Logger.getLogger(MesasController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        return personagens;
    }
    
    // passado o mestre que esta tentando ter acesso e o nome da mesa e retorna a lista de NPCS daquela mesa
    // caso ele nao seja mestre, nao retorna
    public ArrayList<Personagem> getNPCS(String mesaNome, Usuario mestre) throws AcessoNegadoException{
        Mesa mesa = bd.retornaMesa(mesaNome);
        if(!mesa.isMestre(mestre.getNome())){
            throw new AcessoNegadoException("Não possui acesso aos npcs da mesa caso não seja mestre");
        }
        
        ArrayList<Personagem> personagens = new ArrayList<>();
        
        for (Usuario mst : mesa.getMestres()){
            for (String npcid : mst.getIDSPersos()){
                try {
                    personagens.add(mst.getPersonagem(npcid));
                } catch (PersonagemInexistenteException ex) {
                    Logger.getLogger(MesasController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        return personagens;
    }
}
