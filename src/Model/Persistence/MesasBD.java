package Model.Persistence;

import Model.Entidade.Mesa;
import java.util.ArrayList;

public class MesasBD {
    private final ArrayList<Mesa> mesas;
    
    public MesasBD(){
        mesas = new ArrayList<>();
    }
    
    public void regMesa(Mesa mesa){mesas.add(mesa);}
    
    public Mesa retornaMesa(String nome){
        for (Mesa mesa : mesas)
            if (mesa.getNome().equals(nome)) return mesa;
        return null;
    }
    
    public ArrayList<String> getNomes(){
        ArrayList<String> mesasN = new ArrayList<>();
        for (Mesa mesa : this.mesas)
            mesasN.add(mesa.getNome());
        return mesasN;
    }
}
