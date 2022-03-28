package View;

import Controller.MesasController;
import Controller.UserController;
import Model.Entidade.Usuario;
import java.util.ArrayList;
import java.util.Scanner;

public class Tela_Inicial_Mesas {
    

    Scanner scan = new Scanner(System.in);
    int opcao;
    
    public void mostrar(MesasController controlem, UserController controleu, Usuario usuario){
        
        menu();
        opcao=scan.nextInt();
        
        while(opcao!=0){
            switch(opcao){
                case 1:
                    ArrayList<String> mesas;
                    mesas = controlem.getMesas();
                    System.out.println("Mesas:\n");
                    for(int i = 0; i < mesas.size(); i++){
                        System.out.printf("[%d] - %s  \n", i, mesas.get(i));
                    }
                    break;
                    
                case 2:
                    System.out.println("Digite o nome da mesa: ");
                    String nomemesa = scan.next();
                    
                    Tela_Inicial_Mesa inicialmesa = new Tela_Inicial_Mesa();
                    inicialmesa.mostrar(controlem, controleu, usuario, nomemesa);
                    break;
                    
                case 3:
                    Tela_Cadastro_Mesa cadastromesa = new Tela_Cadastro_Mesa();
                    cadastromesa.mostrar(controlem, usuario);
            }
            menu();
            opcao=scan.nextInt();            
        }
        
    }
    
    public static void menu(){
        System.out.println("\nMenu de Mesas");
        System.out.println("1) Listar Mesas");
        System.out.println("2) Acessar Mesa");
        System.out.println("3) Cadastrar Mesa");
        System.out.println("0) Voltar");
    }
    
}
