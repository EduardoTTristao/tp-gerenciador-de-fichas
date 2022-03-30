/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package mainTestPackage;

import Controller.MesasController;
import Controller.UserController;
import Model.Entidade.Exceptions.AcessoNegadoException;
import Model.Entidade.Exceptions.AtrInexistenteException;
import Model.Entidade.Exceptions.AtributoNegativoException;
import Model.Entidade.Exceptions.MesaCadastradaException;
import Model.Entidade.Exceptions.MesaInexistenteException;
import Model.Entidade.Exceptions.PersonagemInexistenteException;
import Model.Entidade.Exceptions.SenhaFracaException;
import Model.Entidade.Exceptions.SenhasDiferentesException;
import Model.Entidade.Exceptions.SistemaNaoCadastradoException;
import Model.Entidade.Exceptions.UsuarioJaCadastradoException;
import Model.Entidade.Exceptions.UsuarioNaoCadastradoException;
import Model.Entidade.Personagem;
import Model.Entidade.Usuario;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author eduar
 */
public class TestesMesas {
    UserController UC;
    MesasController MC;
    
    @Before
    public void setUp() {
        UC = new UserController();
        MC = new MesasController();
    }
    
    @Test
    public void testeGerirMesas() throws UsuarioNaoCadastradoException {
        try {
            UC.cadastrar("Mestrinho", "fireball", "fireball");
        } catch (UsuarioJaCadastradoException | SenhaFracaException | SenhasDiferentesException ex) {
            assert(false);
        }
        
        Usuario mst = UC.autentica("Mestrinho", "fireball");
        
        try {
            MC.cadMesa("MesaTop", mst, "DND");
        } catch (MesaCadastradaException | SistemaNaoCadastradoException ex) {
            assert(false);
        }
        
        try {
            MC.cadMesa("MesaTop", mst, "DND");
            assert(false);
        } catch (MesaCadastradaException ex) {
        } catch (SistemaNaoCadastradoException ex) {
            assert(false);
        }
        
        try {
            MC.cadMesa("MesaTop", mst, "Vampiro");
            assert(false);
        } catch (MesaCadastradaException ex) {
            assert(false);
        } catch (SistemaNaoCadastradoException ex) {
        }
        
        try {
            UC.cadastrar("Jogador1", "senhafoda", "senhafoda");
        } catch (UsuarioJaCadastradoException | SenhaFracaException | SenhasDiferentesException ex) {
            assert(false);
        }
        
        Usuario jogador1 = UC.autentica("Jogador1", "senhafoda");
        
        try {
            MC.addJogadorNA(jogador1, "MesaTop");
        } catch (UsuarioJaCadastradoException | MesaInexistenteException ex) {
            assert(false);
        }
        
        try {
            MC.addJogadorNA(jogador1, "MesaTopa");
            assert(false);
        } catch (UsuarioJaCadastradoException ex) {
            assert(false);
        } catch (MesaInexistenteException ex) {
        }
        
        try {
            MC.addJogador(jogador1.getNome(), "MesaTopa", mst);
            assert(false);
        } catch (MesaInexistenteException ex) {
            assert(true);
        } catch (AcessoNegadoException | UsuarioJaCadastradoException ex) {
            assert(false);
        }
        
        try {
            MC.addJogador(jogador1.getNome(), "MesaTop", jogador1);
            assert(false);
        } catch (MesaInexistenteException | UsuarioJaCadastradoException ex) {
            assert(false);
        } catch (AcessoNegadoException ex) {
            assert(true);
        }
        
        try {
            MC.addJogador(jogador1.getNome(), "MesaTop", mst);
        } catch (MesaInexistenteException | AcessoNegadoException | UsuarioJaCadastradoException ex) {
            assert(false);
        }
        
        try {
            MC.addJogador(jogador1.getNome(), "MesaTop", mst);
            assert(false);
        } catch (MesaInexistenteException | AcessoNegadoException ex) {
            assert(false);
        } catch (UsuarioJaCadastradoException ex) {
            assert(true);
        }
        
        try {
            UC.criarPerso("Jogador1", "DND", "Henk");
        } catch (SistemaNaoCadastradoException | UsuarioNaoCadastradoException ex) {
            assert(false);
        }
        
        try {
            MC.addCharacter(UC.getPerso("Jogador1", UC.getIdsPersos(jogador1).get(0)), jogador1, "MesaTop");
        } catch (UsuarioNaoCadastradoException | PersonagemInexistenteException | MesaInexistenteException | AcessoNegadoException ex) {
            assert(false);
        }
        ArrayList<Personagem> persos = null;
        try {
             persos = MC.getPCS("MesaTop", mst);
        } catch (AcessoNegadoException ex) {
            assert(false);
        }
        
        try {
            persos.get(0).setAtr("Força", 9);
        } catch (AtrInexistenteException | AtributoNegativoException ex) {
            assert(false);
        }
        
        try {
            assertEquals(MC.getPCS("MesaTop", mst).get(0).getAtributos().get(0).getValor(),9);
        } catch (AcessoNegadoException ex) {
            assert(false);
        }
        
        try {
            assertEquals(MC.getPCS("MesaTop", mst).get(0).toString(), UC.getPerso("Jogador1", UC.getIdsPersos(jogador1).get(0)).toString());
        } catch (AcessoNegadoException | UsuarioNaoCadastradoException | PersonagemInexistenteException ex) {
            assert(false);
        }
        
    }
    
}
