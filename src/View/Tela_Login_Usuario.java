package View;

import Controller.UserController;
import Model.Entidade.Usuario;
import java.util.Scanner;

public class Tela_Login_Usuario {
    public Usuario mostrar(UserController controle){
        Usuario usuario = null;
        Scanner scan = new Scanner(System.in);
        scan.useDelimiter("\n");
        
        System.out.println("Nome: ");
        String nome = scan.next();
        
        System.out.println("Senha: ");
        String senha = scan.next();
        
        try{
            usuario =  controle.autentica(nome, senha);
            }catch(Exception e){
                System.err.println("Erro: " + e.getMessage());}
        
        return usuario;
    }
    
}
