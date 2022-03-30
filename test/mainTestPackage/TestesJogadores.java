
package mainTestPackage;

import Controller.UserController;
import Model.Entidade.Exceptions.AtrInexistenteException;
import Model.Entidade.Exceptions.AtributoNegativoException;
import Model.Entidade.Exceptions.PersonagemInexistenteException;
import Model.Entidade.Exceptions.SenhaFracaException;
import Model.Entidade.Exceptions.SenhasDiferentesException;
import Model.Entidade.Exceptions.SistemaNaoCadastradoException;
import Model.Entidade.Exceptions.UsuarioJaCadastradoException;
import Model.Entidade.Exceptions.UsuarioNaoCadastradoException;
import Model.Entidade.Personagem;
import Model.Entidade.Usuario;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestesJogadores {
    UserController UC;
    
    @Before
    public void setUp(){
        UC = new UserController();
    }
    
    @Test
    public void testeCadastro() {
        try {
            UC.cadastrar("Marcus", "top10pessoas", "top10pessoas");
        } catch (UsuarioJaCadastradoException | SenhaFracaException | SenhasDiferentesException ex) {
            assertTrue(false);
        }
        
        try {
            UC.cadastrar("Luis", "top10pessoas", "top11pessoas");
            assertTrue(false);
        } catch (UsuarioJaCadastradoException | SenhaFracaException | SenhasDiferentesException ex) {
        }
        
        try {
            UC.cadastrar("Marcus", "top10pessoas", "top10pessoas");
            assertTrue(false);
        } catch (UsuarioJaCadastradoException | SenhaFracaException | SenhasDiferentesException ex) {
        }
        
        try {
            UC.cadastrar("Edu", "top10", "top10");
            assertTrue(false);
        } catch (UsuarioJaCadastradoException | SenhaFracaException | SenhasDiferentesException ex) {
        }
    }
    
    @Test
    public void JogadorTest() throws UsuarioNaoCadastradoException{
        try {
            UC.cadastrar("Marcus", "top10pessoas", "top10pessoas");
        } catch (SenhaFracaException | SenhasDiferentesException | UsuarioJaCadastradoException ex) {
            assertTrue(false);
        }
        
        Usuario marcus = UC.autentica("Marcus", "top10pessoas");
        
        try {
            UC.criarPerso("Marcus", "DND", "Ladin");
        } catch (SistemaNaoCadastradoException | UsuarioNaoCadastradoException ex) {
            assertTrue(false);
        }
        
        try {
            UC.criarPerso("Marcus", "Vampiro", "Ladin");
            assertTrue(false);
        } catch (SistemaNaoCadastradoException ex) {
        } catch (UsuarioNaoCadastradoException ex){
            assertTrue(false);
        }
        
        try {
            UC.criarPerso("luizin", "Vampiro", "Ladin");
            assertTrue(false);
        } catch (SistemaNaoCadastradoException ex) {
            assertTrue(false);
        } catch (UsuarioNaoCadastradoException ex){
        }
        Personagem Ladin = null;
        try {
            Ladin = UC.getPerso("Marcus", UC.getIdsPersos(marcus).get(0));
        } catch (UsuarioNaoCadastradoException | PersonagemInexistenteException ex) {
            assertTrue(false);
        }
        
        assertEquals(Ladin.getDonoDoPersonagem(),"Marcus");
        assertEquals(Ladin.getNome(),"Ladin");
        Ladin.setVida(30);
        Ladin.machuca(10);
        Ladin.cura(9);
        assertEquals(Ladin.getVidaAtual(),29);
        Ladin.machuca(29);
        assert(Ladin.isDead());
        
        try {
            Ladin.setAtr("Força", 1);
            Ladin.setAtr("Destreza", 2);
            Ladin.setAtr("Constituição", 3);
            Ladin.setAtr("Inteligência", 4);
            Ladin.setAtr("Sabedoria", 5);
            Ladin.setAtr("Carisma", 6);
        } catch (AtrInexistenteException | AtributoNegativoException ex) {
            assert(false);
        }
        try {
            Ladin.setAtr("Caca", 9);
            assert(false);
        } catch (AtrInexistenteException ex) {
        } catch (AtributoNegativoException ex) {
            assert(false);
        }
        try {
            Ladin.setAtr("Carisma", -2);
            assert(false);
        } catch (AtrInexistenteException ex) {
            assert(false);
        } catch (AtributoNegativoException ex) {
        }
        
        for (int i = 0; i < 6; i++)
            assertEquals(Ladin.getAtributos().get(i).getValor(),i+1);
    }
    
}
