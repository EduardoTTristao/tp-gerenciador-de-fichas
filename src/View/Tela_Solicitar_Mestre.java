package View;

import Controller.MesasController;
import Model.Entidade.Usuario;
import java.util.Scanner;

public class Tela_Solicitar_Mestre {
    
    public void mostrar(MesasController controlem, Usuario usuario){
        Scanner scan = new Scanner(System.in);
        
        System.out.println("Digite o nome da mesa:");
        String mesa = scan.next();
        
        try{
            controlem.addMestreNA(usuario, mesa);
            }catch(Exception e){
                System.err.println("Erro: " + e.getMessage());}
    }
    
}
