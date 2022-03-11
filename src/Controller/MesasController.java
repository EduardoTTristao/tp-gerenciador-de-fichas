package Controller;

import Model.Entidade.Usuario;
import Model.Entidade.Mesa;
import Model.Persistence.MesasBD;
import Exceptions.MesaInexistenteException;
import Exceptions.SistemaNaoCadastradoException;
import Exceptions.MesaCadastradaException;

public class MesasController {
    private MesasBD bd;
    public void cadMesa (String nomeMesa, Usuario mestreInicial, String sistema) throws MesaCadastradaException, SistemaNaoCadastradoException{
        if (!"D&D".equals(sistema) && !"GURPS".equals(sistema)) 
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
}
