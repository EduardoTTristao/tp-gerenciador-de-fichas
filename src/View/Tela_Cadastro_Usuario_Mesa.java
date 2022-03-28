package View;

import Controller.MesasController;
import Model.Entidade.Usuario;
import java.util.Scanner;

public class Tela_Cadastro_Usuario_Mesa {
    public void mostrar(MesasController controle, String mesa, Usuario mestre){
        Scanner scan = new Scanner(System.in);
        scan.useDelimiter("\n");
        
        System.out.println("Digite o nome do usuario:");
        String nome = scan.next();
        
        try{
            controle.addJogador(nome, mesa, mestre);
            }catch(Exception e){
                System.err.println("Erro: " + e.getMessage());}
    }
    
}
