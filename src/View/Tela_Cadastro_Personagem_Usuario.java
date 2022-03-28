package View;

import Controller.UserController;
import Model.Entidade.Usuario;
import java.util.Scanner;

public class Tela_Cadastro_Personagem_Usuario {
    public void mostrar(UserController controle, Usuario user){
        
        Scanner scan = new Scanner(System.in);
        scan.useDelimiter("\n");
        
        System.out.println("Nome do personagem: ");
        String nome = scan.next();
        
        System.out.println("Nome do sistema: ");
        String sistema = scan.next();
        
        try{
            controle.criarPerso(user.getNome(), sistema, nome);
            }catch(Exception e){
                System.err.println("Erro: " + e.getMessage());}
        
        }
}
