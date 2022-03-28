package View;

import Controller.MesasController;
import Controller.UserController;
import Model.Entidade.Usuario;
import java.util.Scanner;

public class Tela_Inicial_Usuario {
    
    Scanner scan = new Scanner(System.in);
    int opcao;
    
    public void mostrar(UserController controleu, MesasController controlem, Usuario usuario){
        
        menu();
        opcao=scan.nextInt();
        
        while(opcao!=0){
            switch(opcao){
                case 1:
                    Tela_Inicial_Mesas inicialmesa = new Tela_Inicial_Mesas();
                    inicialmesa.mostrar(controlem, controleu, usuario);
                    break;
                case 2:
                    Tela_Inicial_Personagens inicialpersonagens = new Tela_Inicial_Personagens();
                    inicialpersonagens.mostrar(controleu, usuario);
                    break;
                default:
                    System.out.println("Opção Inválida");
            }
            menu();
            opcao=scan.nextInt();            
        }
        
    }
    
    public static void menu(){
        System.out.println("\nBem-Vindo");
        System.out.println("1) Acessar Mesas");
        System.out.println("2) Acessar Personagens");
        System.out.println("0) Voltar");
    }
}
