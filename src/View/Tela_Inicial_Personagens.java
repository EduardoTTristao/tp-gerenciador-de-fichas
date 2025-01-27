package View;

import Controller.MesasController;
import Controller.UserController;
import Model.Entidade.Usuario;
import java.util.ArrayList;
import java.util.Scanner;

public class Tela_Inicial_Personagens {
    
    Scanner scan = new Scanner(System.in);
    int opcao;
    
    public void mostrar(UserController controleu, MesasController controlem, Usuario usuario){
    
        menu();
        opcao=scan.nextInt();     
        while(opcao!=0){
            switch(opcao){
                case 1:
                    ArrayList<String> personagens;
                    personagens = usuario.getIDSPersos();
                    System.out.println("Personagens:\n");
                    for(int i = 0; i < personagens.size(); i++){
                        System.out.printf("[%d] - %s\n", i, personagens.get(i));}
                    break;
                case 2:
                    Tela_Info_Personagem infoperso = new Tela_Info_Personagem();
                    infoperso.mostrar(controleu, usuario.getNome());
                    break;
                case 3:
                    Tela_Cadastro_Personagem_Usuario cadperso = new Tela_Cadastro_Personagem_Usuario();
                    cadperso.mostrar(controleu, usuario);
                    break;
                case 4:
                    Tela_Cadastro_Personagem_Mesa cadpersomesa = new Tela_Cadastro_Personagem_Mesa();
                    cadpersomesa.mostrar(controlem, controleu, usuario);
                    
                    break;
                default:
                    System.out.println("Opção Inválida");
                    
            }
            menu();
            opcao=scan.nextInt();            
        }
        
    }
    
    
    public static void menu(){
        System.out.println("\nMenu de Personagens");
        System.out.println("1) Listar meus Personagens");
        System.out.println("2) Acessar Personagem");
        System.out.println("3) Criar Personagem");
        System.out.println("4) Adicionar Personagem a uma mesa");        
        System.out.println("0) Voltar");
    }
}
