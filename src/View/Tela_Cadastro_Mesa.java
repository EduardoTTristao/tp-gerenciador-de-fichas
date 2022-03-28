package View;

import Controller.MesasController;
import Model.Entidade.Usuario;
import java.util.Scanner;


public class Tela_Cadastro_Mesa {
    public void mostrar(MesasController controle, Usuario mestre){
        Scanner scan = new Scanner(System.in);
        scan.useDelimiter("\n");
        
        System.out.println("Nome da mesa: ");
        String nome = scan.next();
        
        System.out.println("Nome do sistema: ");
        String sistema = scan.next();
        
        try{
            controle.cadMesa(nome, mestre, sistema);
            }catch(Exception e){
                System.err.println("Erro: " + e.getMessage());}
    }
}
