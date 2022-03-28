package View;

import Controller.UserController;
import Controller.MesasController;
import Model.Entidade.Usuario;
import java.util.Scanner;


public class Tela_Inicial {
    
    public static void main(String[] args) {
        MesasController mesascontroller = new MesasController();
        UserController usercontroller = new UserController();
        Usuario usuario;
        Scanner scan = new Scanner(System.in);
        int opcao;
        
        menu();
        opcao=scan.nextInt();
       
        
        while(opcao!=0){
            switch(opcao){
                //login do usuario
                case 1 -> {
                    Tela_Login_Usuario loginusuario = new Tela_Login_Usuario();
                    usuario = loginusuario.mostrar(usercontroller);
                    
                    //acesso do usuario
                    if(usuario != null){
                        Tela_Inicial_Usuario inicialusuario = new Tela_Inicial_Usuario();
                        inicialusuario.mostrar(usercontroller, mesascontroller, usuario);
                    }
                }
                
                //cadastro do usuario
                case 2 -> {
                    Tela_Cadastro_Usuario cadastrousuario = new Tela_Cadastro_Usuario();
                    cadastrousuario.mostrar(usercontroller);
                }
                
                //default
                default -> System.err.println("Opção inválida!");
            }
            menu();
            opcao=scan.nextInt();
            
        }
        
    }
    
    public static void menu(){
        System.out.println("\nSistema Gerenciador de Fichas de RPG");
        System.out.println("1) Logar");
        System.out.println("2) Cadastrar");
        System.out.println("0) Sair");
    }
    
}

