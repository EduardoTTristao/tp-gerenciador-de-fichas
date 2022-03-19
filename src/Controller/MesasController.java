package Controller;

import Exceptions.AcessoNegadoException;
import Model.Entidade.Usuario;
import Model.Entidade.Mesa;
import Model.Persistence.MesasBD;
import Exceptions.MesaInexistenteException;
import Exceptions.SistemaNaoCadastradoException;
import Exceptions.MesaCadastradaException;
import Exceptions.PersonagemInexistenteException;
import Exceptions.UsuarioJaCadastradoException;
import Model.Entidade.Personagem;
import Model.Persistence.PersonagemBD;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MesasController {
    private MesasBD bd;
    
    public MesasController(){
        bd = new MesasBD();
    }
    
    public void cadMesa (String nomeMesa, Usuario mestreInicial, String sistema) throws MesaCadastradaException, SistemaNaoCadastradoException{
        if (!"DND".equals(sistema) && !"GURPS".equals(sistema)) 
            throw new SistemaNaoCadastradoException(sistema);
        if (bd.retornaMesa(nomeMesa) != null) 
            throw new MesaCadastradaException();
        bd.regMesa(new Mesa(mestreInicial, sistema, nomeMesa));
    }
    
    public boolean isMestre (String nomeMesa, String nomeMestre) throws MesaInexistenteException{
        Mesa mesa = bd.retornaMesa(nomeMesa);
        if (mesa == null)
            throw  new MesaInexistenteException(nomeMesa);
        return mesa.isMestre(nomeMestre);
    }
    
    public ArrayList<String> getMesas(){
        return bd.getNomes();
    }
    
    public void addJogador(String nome, String nomeMesa, Usuario Mestre) throws MesaInexistenteException, AcessoNegadoException, UsuarioJaCadastradoException{
        if (isMestre(nomeMesa,Mestre.getNome()))
            throw new AcessoNegadoException("Não pode cadastrar jogadores sem ser mestre");
        Mesa mesa = bd.retornaMesa(nomeMesa);
        
        mesa.addJogador(mesa.getJogNA(nome));
    }
    
    public void addMestre(String mestreADD, String nomeMesa, Usuario Mestre) throws MesaInexistenteException, AcessoNegadoException, UsuarioJaCadastradoException{
        if (isMestre(nomeMesa,Mestre.getNome()))
            throw new AcessoNegadoException("Não pode cadastrar mestres sem ser mestre");
        Mesa mesa = bd.retornaMesa(nomeMesa);
        
        mesa.addJogador(mesa.getMesNA(mestreADD));
    }
    
    public void addJogadorNA(Usuario jogador, String nomeMesa) throws UsuarioJaCadastradoException, MesaInexistenteException{
        Mesa mesa = bd.retornaMesa(nomeMesa);
        if (mesa == null)
            throw  new MesaInexistenteException(nomeMesa);
        mesa.addJogadorNA(jogador);
    }
    
    public void addMestreNA(Usuario mestre, String nomeMesa) throws UsuarioJaCadastradoException, MesaInexistenteException{
        Mesa mesa = bd.retornaMesa(nomeMesa);
        if (mesa == null)
            throw  new MesaInexistenteException(nomeMesa);
        mesa.addMestreNA(mestre);
    }
    
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
    
    public ArrayList<Personagem> getPCS(String mesaNome, Usuario mestre) throws AcessoNegadoException{
        Mesa mesa = bd.retornaMesa(mesaNome);
        if(!mesa.isMestre(mestre.getNome())){
            throw new AcessoNegadoException("Não possui acesso aos pcs da mesa caso não seja mestre");
        }
        
        ArrayList<Personagem> personagens = new ArrayList<>();
        
        for (Usuario jgd : mesa.getJogadores()){
            PersonagemBD bdP = jgd.getPersonagens();
            for (String pcid : bdP.listId()){
                try {
                    personagens.add(bdP.getPersonagem(pcid));
                } catch (PersonagemInexistenteException ex) {
                    Logger.getLogger(MesasController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        return personagens;
    }
    
    public ArrayList<Personagem> getNPCS(String mesaNome, Usuario mestre) throws AcessoNegadoException{
        Mesa mesa = bd.retornaMesa(mesaNome);
        if(!mesa.isMestre(mestre.getNome())){
            throw new AcessoNegadoException("Não possui acesso aos npcs da mesa caso não seja mestre");
        }
        
        ArrayList<Personagem> personagens = new ArrayList<>();
        
        for (Usuario mst : mesa.getMestres()){
            PersonagemBD bdP = mst.getPersonagens();
            for (String npcid : bdP.listId()){
                try {
                    personagens.add(bdP.getPersonagem(npcid));
                } catch (PersonagemInexistenteException ex) {
                    Logger.getLogger(MesasController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        return personagens;
    }
}