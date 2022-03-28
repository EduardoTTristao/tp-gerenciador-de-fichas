package View;

import Controller.UserController;
import java.util.Scanner;

public class Tela_Cadastro_Usuario {
    public void mostrar(UserController controle){
        Scanner scan = new Scanner(System.in);
        scan.useDelimiter("\n");
        
        System.out.println("Nome: ");
        String nome = scan.next();
        
        System.out.println("Senha: ");
        String senha = scan.next();
        
        System.out.println("Repita a senha: ");
        String senharpt = scan.next();
        
        try{
            controle.cadastrar(nome, senha, senharpt);
            System.out.println("Usuario Cadastrado!");
            }catch(Exception e){
                System.err.println("Erro: " + e.getMessage());}

    }
}
